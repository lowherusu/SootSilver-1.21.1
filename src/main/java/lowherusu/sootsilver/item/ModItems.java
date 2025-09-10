package lowherusu.sootsilver.item;

import lowherusu.sootsilver.SootSilver;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items.Items ITEMS = DeferredRegister.createItems(SootSilver.MOD_ID);

    public static final DeferredItem<Item> SOOT_SILVER_INGOT = ITEMS.register("soot_silver_ingot", () -> new Item(new Item.Properties()));




    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
