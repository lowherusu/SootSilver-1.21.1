package lowherusu.sootsilver.item;

import lowherusu.sootsilver.SootSilver;
import lowherusu.sootsilver.item.custom.WindboundFeather;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SootSilver.MOD_ID);

    public static final DeferredItem<Item> SOOT_SILVER_INGOT = ITEMS.register("soot_silver_ingot", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> NIGHT_BERRY = ITEMS.register("night_berry",
            () -> new Item(new Item.Properties().food(ModFoodProperties.NIGHT_BERRY).rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> WINDBOUND_FEATHER = ITEMS.register("windbound_feather", () -> new WindboundFeather());


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
