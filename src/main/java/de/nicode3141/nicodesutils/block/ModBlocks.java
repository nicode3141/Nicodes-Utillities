package de.nicode3141.nicodesutils.block;

import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.block.custom.CustomTNTBlock;
import de.nicode3141.nicodesutils.block.custom.ElectrolysisChamberBlock;
import de.nicode3141.nicodesutils.fluid.ModFluids;
import de.nicode3141.nicodesutils.item.ModCreativeModeTab;
import de.nicode3141.nicodesutils.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, NicodesUtils.MOD_ID);



    public static final RegistryObject<Block> ELECTROLYSIS_CHAMBER = registerBlock("electrolysis_chamber",
            ()-> new ElectrolysisChamberBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.NICODESUTILS_TAB);


    public static final RegistryObject<LiquidBlock> HYDROGEN_BLOCK = BLOCKS.register("hydrogen_block",
            () -> new LiquidBlock(ModFluids.SOURCE_HYDROGEN, BlockBehaviour.Properties.copy(Blocks.WATER)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name,toReturn,tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name,RegistryObject<T> block,
                                                                            CreativeModeTab tab){
        return ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties().tab(ModCreativeModeTab.NICODESUTILS_TAB)));
    }

    public static final RegistryObject<Block> CUSTOM_TNT = registerBlock("custom_tnt",
            ()-> new CustomTNTBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.NICODESUTILS_TAB);




    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
