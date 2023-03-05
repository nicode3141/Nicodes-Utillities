package de.nicode3141.nicodesutils.tileentity;

import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.block.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, NicodesUtils.MOD_ID);

    public static RegistryObject<TileEntityType<ItemShredderTile>> ITEM_SHREDDER_TILE =
            TILE_ENTITIES.register("item_shredder_tile", () -> TileEntityType.Builder.create(
                    ItemShredderTile:: new , ModBlocks.ITEM_SHREDDER.get()).build(null));

    public static RegistryObject<TileEntityType<NicodeBlockTile>> NICODE_BLOCK_TILE =
            TILE_ENTITIES.register("nicode_block_tile", () -> TileEntityType.Builder.create(
                    NicodeBlockTile:: new , ModBlocks.NICODE_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }

}
