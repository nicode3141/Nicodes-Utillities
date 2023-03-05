package de.nicode3141.nicodesutils.block;

import de.nicode3141.nicodesutils.item.ModItemGroup;
import de.nicode3141.nicodesutils.item.ModItems;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    //block reg
    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, NicodesUtils.MOD_ID);

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //COMMON BLOCKS

    //RANDOM BLOCKS


    //TILE ENTITY BLOCKS


    public static final RegistryObject<Block> ELECTROLYSIS_CHAMBER = registerBlock("electrolysis_chamber",
            () -> new ElectrolysisChamberBlock(AbstractBlock.Properties.create(Material.SAND)
                    .harvestLevel(1).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(2f)));


    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    //registers the Item for the Block automaticly
    private static <T extends Block> void registerBlockItem(String name,RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().group(ModItemGroup.NICODESUTILS_GROUP)));
    }




    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
