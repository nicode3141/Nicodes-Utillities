package de.nicode3141.nicodesutils.item;

import de.nicode3141.nicodesutils.NicodesUtils;
import net.minecraft.item.*;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.item.Items.BUCKET;

public class ModItems {

    //Adds this Mod's Items to the DeferredRegister
    // Fügt die Items dieser Mod zum DeferredRegister hinzu
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, NicodesUtils.MOD_ID);


    //Registers the Item "artifact of code"
    public static final RegistryObject<Item> ARTIFACT_OF_CODE = ITEMS.register("artifact_of_code",
            () -> new Item(new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP)));

    //Registers the Item "book of coding"
    public static final RegistryObject<Item> BOOK_OF_CODING = ITEMS.register("book_of_coding",
            () -> new Item(new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP)));

    //Registers the Item "advanced milk bucket"
    public static final RegistryObject<Item> ADVANCED_MILK_BUCKET = ITEMS.register("advanced_milk_bucket",
            () -> new Item(new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP).rarity(Rarity.EPIC).containerItem(BUCKET).isImmuneToFire().maxStackSize(128)));

    //Registers the Item "brp sword"
    public static final RegistryObject<Item> BRP_SWORD = ITEMS.register("brp_sword",
            () -> new SwordItem(ModItemTier.BRPSWORD, 10,0.5f ,new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP).rarity(Rarity.EPIC)));




    //TODO:
    //PEPSI,COCE

    //registry Event for Mod Items
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
