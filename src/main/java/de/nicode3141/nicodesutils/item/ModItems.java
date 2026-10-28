package de.nicode3141.nicodesutils.item;

import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.fluid.ModFluids;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, NicodesUtils.MOD_ID);

    //------------------------------------------------------------------------------------------------------------------

    public static final RegistryObject<Item> HYDROGEN_BUCKET = ITEMS.register("hydrogen_bucket",
            () -> new BucketItem(ModFluids.SOURCE_HYDROGEN, new Item.Properties()
                    .tab(ModCreativeModeTab.NICODESUTILS_TAB).craftRemainder(Items.BUCKET)
                    .stacksTo(1)));



    public static final RegistryObject<Item> CHEMISTRY_SUIT = ITEMS.register("chemistry_suit",
            () -> new ArmorItem(ModArmorMaterials.CHEMISTRY, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.NICODESUTILS_TAB)));



    //------------------------------------------------------------------------------------------------------------------
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
