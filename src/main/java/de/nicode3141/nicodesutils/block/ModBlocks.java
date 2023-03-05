package de.nicode3141.nicodesutils.block;

import de.nicode3141.nicodesutils.block.custom.BrpTeleporter;
import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.custom.NicodeBlock;
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

    //block reg
    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, NicodesUtils.MOD_ID);

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //COMMON BLOCKS

        //RANDOM BLOCKS

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

                //creates the Block "BRP Grass Block"
                public static final RegistryObject<Block> BRP_GRASS_BLOCK = registerBlock("brp_grass_block",
                        () -> new Block(AbstractBlock.Properties.create(Material.ORGANIC)
                                .harvestLevel(1).harvestTool(ToolType.SHOVEL).setRequiresTool().hardnessAndResistance(1f).sound(SoundType.PLANT)));

                //creates the Block "BRP Dirt"
                public static final RegistryObject<Block> BRP_DIRT = registerBlock("brp_dirt",
                        () -> new Block(AbstractBlock.Properties.create(Material.ORGANIC)
                                .harvestLevel(1).harvestTool(ToolType.SHOVEL).setRequiresTool().hardnessAndResistance(1f).sound(SoundType.PLANT)));

                //creates the Block "Compressed Bitcoin"
                public static final RegistryObject<Block> NICODIUM_ORE = registerBlock("nicodium_ore",
                        () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                                .harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f)));


                //creates the Block "Compressed Bitcoin"
                public static final RegistryObject<Block> NICODE_BLOCK = registerBlock("nicode_block",
                        () -> new NicodeBlock(AbstractBlock.Properties.create(Material.ROCK)
                                .harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f).notSolid()));


        //RESOURCES
        public static final RegistryObject<Block> NICKEL_ORE = registerBlock("nickel_ore",
                () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                        .harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f).notSolid()));

        public static final RegistryObject<Block> LANATHANUM_ORE = registerBlock("lanathanum_ore",
                () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                        .harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f).notSolid()));

        public static final RegistryObject<Block> COBALT_ORE = registerBlock("cobalt_ore",
                () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                        .harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f).notSolid()));

        public static final RegistryObject<Block> NICKEL_BLOCK = registerBlock("nickel_block",
                () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                        .harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f).notSolid()));

        public static final RegistryObject<Block> LANATHANUM_BLOCK = registerBlock("lanathanum_block",
                () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                        .harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f).notSolid()));

        public static final RegistryObject<Block> COBALT_BLOCK = registerBlock("cobalt_block",
                () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                        .harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f).notSolid()));


    //TILE ENTITY BLOCKS
        //creates TileEntity "Item Shredder"
        public static final RegistryObject<Block> ITEM_SHREDDER = registerBlock("item_shredder",
                () -> new ItemShredderBlock(AbstractBlock.Properties.create(Material.SAND)
                        .harvestLevel(1).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(1f)));

        public static final RegistryObject<Block> ELECTROLYSIS_CHAMBER = registerBlock("electrolysis_chamber",
                () -> new ItemShredderBlock(AbstractBlock.Properties.create(Material.SAND)
                        .harvestLevel(1).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(1f)));

    //CUSTOM BLOCKS
        public static final RegistryObject<Block> BRP_TELEPORTER = registerBlock("brp_teleporter",
                () -> new BrpTeleporter(AbstractBlock.Properties.create(Material.ROCK)
                        .harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f).notSolid()));






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
