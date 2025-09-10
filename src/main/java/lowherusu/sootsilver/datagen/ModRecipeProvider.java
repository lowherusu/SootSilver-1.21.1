package lowherusu.sootsilver.datagen;

import lowherusu.sootsilver.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SOOT_SILVER_INGOT.get())
                .requires(Items.COAL)
                .requires(Items.IRON_INGOT)
                .unlockedBy("has_coal", has(Items.COAL))
                .save(recipeOutput);

        RecipeProvider.nineBlockStorageRecipesRecipesWithCustomUnpacking(recipeOutput, RecipeCategory.MISC, ModItems.SOOT_SILVER_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, Items.DIRT,
                "soot_silver_ingot_from_dirt_block", "soot_silver_ingot");
    }
}
