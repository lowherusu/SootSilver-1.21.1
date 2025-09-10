package lowherusu.sootsilver.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties NIGHT_BERRY = new FoodProperties.Builder().nutrition(8)
            .saturationModifier(0.6f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 200), 0.2f)
            .alwaysEdible()
            .fast()
            .build();


}
