package lowherusu.sootsilver.event;

import lowherusu.sootsilver.SootSilver;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = SootSilver.MOD_ID)
public class DashTrailHandler {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        Level level = player.level();

        // サーバー側だけで処理
        if (!level.isClientSide) {
            int ticks = player.getPersistentData().getInt("sootsilver_dashTrail");
            if (ticks > 0) {
                // パーティクルを後方に生成
                Vec3 look = player.getLookAngle();
                Vec3 horiz = new Vec3(look.x, 0, look.z).normalize();
                Vec3 base = player.position()
                        .add(0, player.getBbHeight() * 0.6, 0)
                        .subtract(horiz.scale(0.35));

                ((ServerLevel)level).sendParticles(ParticleTypes.CLOUD,
                        base.x, base.y, base.z,
                        2, 0.1, 0.05, 0.1, 0.01
                );

                player.getPersistentData().putInt("sootsilver_dashTrail", ticks - 1);
            }
        }
    }
    /*
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;


        Player player = event.getEntity();
        Level level = player.level();
        if (level.isClientSide) return; // 全員に見せたいのでサーバー側で

        int ticks = player.getPersistentData().getInt("sootsilver_dashTrail");
        if (ticks <= 0) return;

        // 視線の水平成分
        Vec3 look = player.getLookAngle();
        Vec3 horiz = new Vec3(look.x, 0, look.z);
        if (horiz.lengthSqr() > 1.0E-6) horiz = horiz.normalize(); else horiz = player.getForward();

        // 背中〜やや後方にベース位置
        Vec3 base = player.position()
                .add(0, player.getBbHeight() * 0.6, 0)
                .subtract(horiz.scale(0.35));

        ServerLevel srv = (ServerLevel) level;

        // ティックごとに控えめに尾を出す
        srv.sendParticles(ParticleTypes.CLOUD,
                base.x, base.y, base.z,
                4,   // 量は少なめ、連続で出るので十分
                0.10, 0.06, 0.10,
                0.01
        );
        srv.sendParticles(ParticleTypes.SWEEP_ATTACK,
                base.x, base.y - 0.05, base.z,
                2, 0.03, 0.02, 0.03, 0.0
        );

        // 残り tick を減らす
        player.getPersistentData().putInt("sootsilver_dashTrail", ticks - 1);
    }*/
}

