package lowherusu.sootsilver.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class WindboundFeather extends Item {
    public WindboundFeather() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(player.isInWater())return InteractionResultHolder.pass(itemstack);

        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.WIND_CHARGE_BURST,
                SoundSource.PLAYERS,
                0.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        player.getCooldowns().addCooldown(this, 60);

        if (!level.isClientSide) {
            // 視線ベクトル → 水平成分だけ取り出して正規化
            Vec3 look = player.getLookAngle();
            Vec3 horiz = new Vec3(look.x, 0.0, look.z);
            if (horiz.lengthSqr() > 1.0E-6) {
                horiz = horiz.normalize();
            } else {
                // 真上/真下を向いているなどで水平成分がない場合は、向きベクトルを諦める
                horiz = player.getForward(); // もしくは new Vec3(0,0,1)
            }

            double speed = 1.1;        // ダッシュの強さ（調整点）
            double lift  = 0.10;       // 少しだけ上向きに
            Vec3 impulse = horiz.scale(speed).add(0.0, lift, 0.0);



            // 速度加算＋上限
            Vec3 next = player.getDeltaMovement().add(impulse);
            double maxH = 1.6;
            double h = Math.hypot(next.x, next.z);
            if (h > maxH) {
                double s = maxH / h;
                next = new Vec3(next.x * s, next.y, next.z * s);
            }


            // 既存速度に加算（自然）
            player.setDeltaMovement(player.getDeltaMovement().add(impulse));
            // もしくは瞬時に置き換えたいなら: player.setDeltaMovement(impulse);

            // サーバー側の速度更新を確実にクライアントへ
            player.hurtMarked = true;

            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 0));
            player.resetFallDistance();

            // 追従エフェクトを 10tick ほど
            player.getPersistentData().putInt("sootsilver_dashTrail", 10);
        }

        if (!level.isClientSide) {
            ServerLevel srv = (ServerLevel) level;

            // 視線の水平成分（後方方向を得るために使う）
            Vec3 look = player.getLookAngle();
            Vec3 horiz = new Vec3(look.x, 0, look.z);
            if (horiz.lengthSqr() > 1.0E-6) horiz = horiz.normalize();
            else horiz = player.getForward();

            // プレイヤーの少し後ろ・少し上（背中あたり）
            Vec3 base = player.position()
                    .add(0, player.getBbHeight() * 0.6, 0)
                    .subtract(horiz.scale(0.35)); // 0.35m 後方

            // ふわっと拡散する雲（CLOUD）を10個
            srv.sendParticles(ParticleTypes.CLOUD,
                    base.x, base.y, base.z,
                    10,              // count
                    0.15, 0.08, 0.15,// spread（xyz 偏差）
                    0.01             // 速度スカラー
            );

            // スピード線っぽいパーティクルを少し
            srv.sendParticles(ParticleTypes.SWEEP_ATTACK,
                    base.x, base.y - 0.1, base.z,
                    4, 0.05, 0.03, 0.05, 0.0
            );
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        //itemstack.consume(1, player);
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}
