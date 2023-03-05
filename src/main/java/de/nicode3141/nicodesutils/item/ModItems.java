package de.nicode3141.nicodesutils.item;

import de.nicode3141.nicodesutils.nicodesUtils;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    //Adds this Mod's Items to the DeferredRegister
    // Fügt die Items dieser Mod zum DeferredRegister hinzu
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, nicodesUtils.MODID);


    //Registers the Item "artifact of code"
    public static final RegistryObject<Item> ARTIFACT_OF_CODE = ITEMS.register("artifact_of_code",
            () -> new Item(new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP)));


}
