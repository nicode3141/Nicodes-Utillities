package de.nicode3141.nicodesutils.block;

import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.custom.ItemShredderBlock;
import de.nicode3141.nicodesutils.item.ModItemGroup;
import de.nicode3141.nicodesutils.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, NicodesUtils.MOD_ID);

    //creates the Block "Compressed Bitcoin"
    public static final RegistryObject<Block> COMPRESSED_BITCOIN = registerBlock("compressed_bitcoin",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f)));

    //creates the Block "Enhanced Dirt"
    public static final RegistryObject<Block> ENHANCED_DIRT = registerBlock("enhanced_dirt",
            () -> new Block(AbstractBlock.Properties.create(Material.SAND)
                    .harvestLevel(1).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(1f)));

    //creates Block "Dirt Stairs"
    public static final RegistryObject<Block> DIRT_STAIRS = registerBlock("dirt_stairs",
            () -> new StairsBlock(() -> ENHANCED_DIRT.get().getDefaultState(),
                    AbstractBlock.Properties.create(Material.SAND).harvestLevel(1).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(1f)));

    //creates TileEntity "Item Shredder"
    public static final RegistryObject<Block> ITEM_SHREDDER = registerBlock("item_shredder",
            () -> new ItemShredderBlock(AbstractBlock.Properties.create(Material.SAND)
                    .harvestLevel(1).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(1f)));

    //creates the Block "BRP Grass Block"
    public static final RegistryObject<Block> BRP_GRASS_BLOCK = registerBlock("brp_grass_block",
            () -> new Block(AbstractBlock.Properties.create(Material.ORGANIC)
                    .harvestLevel(1).harvestTool(ToolType.SHOVEL).setRequiresTool().hardnessAndResistance(1f).sound(SoundType.PLANT)));

    //creates the Block "BRP Dirt"
    public static final RegistryObject<Block> BRP_DIRT = registerBlock("brp_dirt",
            () -> new Block(AbstractBlock.Properties.create(Material.ORGANIC)
                    .harvestLevel(1).harvestTool(ToolType.SHOVEL).setRequiresTool().hardnessAndResistance(1f).sound(SoundType.PLANT)));


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
