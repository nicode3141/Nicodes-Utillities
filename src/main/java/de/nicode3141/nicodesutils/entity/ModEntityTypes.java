package de.nicode3141.nicodesutils.entity;

import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.entity.custom.ExtremeCreeperEntity;
import de.nicode3141.nicodesutils.entity.custom.ExtremeTNTEntity;
import de.nicode3141.nicodesutils.entity.custom.RGBSheepEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENITITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, NicodesUtils.MOD_ID);

    public static final RegistryObject<EntityType<RGBSheepEntity>> BRP_SHEEP =
            ENITITY_TYPES.register("brp_sheep", () -> EntityType.Builder.create(RGBSheepEntity::new,
                    EntityClassification.CREATURE).size(0.9F, 1.3F)
                    .build(new ResourceLocation(NicodesUtils.MOD_ID,"brp_sheep").toString()));

    public static final RegistryObject<EntityType<ExtremeTNTEntity>> EXTREME_TNT =
            ENITITY_TYPES.register("extreme_tnt", () -> EntityType.Builder.create(ExtremeTNTEntity::new,
                    EntityClassification.MISC).immuneToFire().size(1.0f, 1.0f)
                    .build(new ResourceLocation(NicodesUtils.MOD_ID, "extreme_tnt").toString()));

    public static final RegistryObject<EntityType<ExtremeCreeperEntity>> EXTREME_CREEPER =
            ENITITY_TYPES.register("extreme_creeper", () -> EntityType.Builder.create(ExtremeCreeperEntity::new,
                            EntityClassification.MONSTER).immuneToFire().size(1.0f, 1.0f)
                    .build(new ResourceLocation(NicodesUtils.MOD_ID, "extreme_creeper").toString()));

    public static void register(IEventBus eventBus) {
        ENITITY_TYPES.register(eventBus);
    }
}
