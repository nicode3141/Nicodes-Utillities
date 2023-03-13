package de.nicode3141.nicodesutils.entity;

import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.block.custom.CustomTNTBlock;
import de.nicode3141.nicodesutils.entity.custom.EntityCustomTNT;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.block.TntBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static  final DeferredRegister<EntityType<?>> ENTITTY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, NicodesUtils.MOD_ID);

    public static final RegistryObject<EntityType<EntityCustomTNT>> CUSTOM_TNT =
            ENTITTY_TYPES.register("custom_tnt",
                    () -> EntityType.Builder.of(EntityCustomTNT::new, MobCategory.MISC)
                            .sized(0.98f,0.98f)
                            .fireImmune()
                            .build(new ResourceLocation(NicodesUtils.MOD_ID, "custom_tnt").toString()));

    public static void register(IEventBus eventBus) {
        ENTITTY_TYPES.register(eventBus);
    }
}
