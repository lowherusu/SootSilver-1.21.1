package lowherusu.sootsilver.item;

import lowherusu.sootsilver.SootSilver;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SootSilver.MOD_ID);

    public static final Supplier<CreativeModeTab> TEST_ITEMS_TAB = CREATIVE_MODE_TAB.register("sootsilver_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SOOT_SILVER_INGOT.get()))
                    .title(Component.translatable("creativetab.sootsilver.sootsilver_items"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.SOOT_SILVER_INGOT);



                    }).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
