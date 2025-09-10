package lowherusu.sootsilver.recipe;

import lowherusu.sootsilver.SootSilver;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(
            Registries.RECIPE_SERIALIZER, SootSilver.MOD_ID);

    //public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<>>
}
