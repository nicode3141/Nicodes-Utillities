package de.nicode3141.nicodesutils.item;

import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.entity.ModEntityTypes;
import de.nicode3141.nicodesutils.item.custom.ModSpawnEggItem;
import de.nicode3141.nicodesutils.item.custom.VarXItem;
import de.nicode3141.nicodesutils.util.ModSoundEvents;
import net.minecraft.item.*;
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
            () -> new MilkBucketItem(new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP).rarity(Rarity.EPIC).containerItem(BUCKET).isImmuneToFire().maxStackSize(128)));

    //Registers the Item "brp sword"
    public static final RegistryObject<Item> BRP_SWORD = ITEMS.register("brp_sword",
            () -> new SwordItem(ModItemTier.BRPSWORD, 99,0.0001f ,new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP).rarity(Rarity.EPIC)));

    //Registers the Item "rickroll music disc"
    public static final RegistryObject<Item> RICKROLL_MUSIC_DISC = ITEMS.register("rickroll_music_disc",
            () -> new MusicDiscItem(1,() -> ModSoundEvents.RICKROLL.get(),
                    new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP).maxStackSize(1)));

    //Registers the Item "COLA"
    public static final RegistryObject<Item> COLA = ITEMS.register("cola",
            () -> new HoneyBottleItem(new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP)));

    //Registers the Item "brp orb"
    public static final RegistryObject<Item> BRP_ORB = ITEMS.register("brp_orb",
            () -> new Item(new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP)));

    //Registers the Item "robux"
    public static final RegistryObject<Item> ROBUX = ITEMS.register("robux",
            () -> new Item(new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP)));

    //Registers the Item "dodgecoin"
    public static final RegistryObject<Item> DODGECOIN = ITEMS.register("dodgecoin",
            () -> new Item(new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP)));

    //Registers the Item "bitcoin"
    public static final RegistryObject<Item> BITCOIN = ITEMS.register("bitcoin",
            () -> new Item(new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP)));

    //Registers the Item "brp horse armor"
    public static final RegistryObject<Item> BRP_HORSE_ARMOR = ITEMS.register("brp_horse_armor",
            () -> new HorseArmorItem(17,"brp",
                    new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP)));

    //Registers the Item "neuralink 2.0"
    public static final RegistryObject<Item> NEURALINK = ITEMS.register("neuralink",
            () -> new Item(new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP).food(ModFood.Neuralink)));

    //Registers the Item "neuralink 2.0"
    public static final RegistryObject<Item> RGBSHEEP_SPAWN_EGG = ITEMS.register("rbgsheep_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.BRP_SHEEP, 0x464F56, 0x1066336,
                    new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP)));

    //Registers the Item "neuralink 2.0"
    public static final RegistryObject<Item> BRPBOW = ITEMS.register("brp_bow",
            () -> new BowItem(new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP).maxStackSize(1)));

    //Registers the Item "ĵɫʫɸɶɻʂʧʨʭʔʘ" (VarX)
    public static final RegistryObject<Item> VARX = ITEMS.register("varx",
            () -> new VarXItem(new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP)));

    //Registers the Item "ĵɫʫɸɶɻʂʧʨʭʔʘ" (VarX)
    public static final RegistryObject<Item> NICKEL_INGOT = ITEMS.register("nickel_ingot",
            () -> new Item(new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP)));

    //Registers the Item "ĵɫʫɸɶɻʂʧʨʭʔʘ" (VarX)
    public static final RegistryObject<Item> LANATHANUM_INGOT = ITEMS.register("lanathanum_ingot",
            () -> new Item(new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP)));

    //Registers the Item "ĵɫʫɸɶɻʂʧʨʭʔʘ" (VarX)
    public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot",
            () -> new Item(new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP)));


    //TODO @Headhott_TV:
    //;NEURALINK 2.0;POTION OF BRP;RING OF BRP;BRPP DINGS(ĵɫʫɸɶɻʂʧʨʭʔʘ);
    //MOB:NOICH

    //registry Event for Mod Items
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
