package lowherusu.sootsilver.datagen;

import lowherusu.sootsilver.SootSilver;
import lowherusu.sootsilver.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper){
        super(output, SootSilver.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.SOOT_SILVER_INGOT.get());

        getBuilder("night_berry")
                .parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", modLoc("item/night_berry"))
                .texture("layer1", modLoc("item/night_berry_glow"));

        getBuilder(ModItems.WINDBOUND_FEATHER.getRegisteredName())
                .parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", modLoc("item/windbound_feather"))
                .texture("layer1", modLoc("item/windbound_feather_effect"));
    }
}
