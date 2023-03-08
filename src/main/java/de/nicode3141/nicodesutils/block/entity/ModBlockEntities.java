package de.nicode3141.nicodesutils.block.entity;

import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, NicodesUtils.MOD_ID);

    public static final RegistryObject<BlockEntityType<ElectrolysisChamberBlockEntity>> ELECTROLYSIS_CHAMBER =
            BLOCK_ENTITIES.register("electrolysis_chamber", () ->
                    BlockEntityType.Builder.of(ElectrolysisChamberBlockEntity::new,
                            ModBlocks.ELECTROLYSIS_CHAMBER.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
