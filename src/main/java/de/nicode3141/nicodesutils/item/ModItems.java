package de.nicode3141.nicodesutils.item;

import de.nicode3141.nicodesutils.NicodesUtils;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, NicodesUtils.MOD_ID);

    //------------------------------------------------------------------------------------------------------------------

    public static final RegistryObject<Item> HYDROGEN_BUCKET = ITEMS.register("hydrogen_bucket",
            () -> new Item(new Item.Properties()));






    //------------------------------------------------------------------------------------------------------------------
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
