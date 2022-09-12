package de.nicode3141.nicodesutils.block;

import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.item.ModItemGroup;
import de.nicode3141.nicodesutils.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
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

    //creates the Block "Block of Bitcoin"
    public static final RegistryObject<Block> BLOCK_OF_BITCOIN = registerBlock("block_of_bitcoin",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f)));





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
