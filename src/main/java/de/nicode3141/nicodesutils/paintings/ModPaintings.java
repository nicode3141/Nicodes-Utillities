package de.nicode3141.nicodesutils.paintings;

import de.nicode3141.nicodesutils.NicodesUtils;
import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

public class ModPaintings {
    public static final DeferredRegister<PaintingType> PAINTING_TYPES =
            DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, NicodesUtils.MOD_ID);

    public static final RegistryObject<PaintingType> SUNSET =
            PAINTING_TYPES.register("sunset", () -> new PaintingType(16,16));

    public static void register(IEventBus eventbus) {
        PAINTING_TYPES.register(eventbus);
    }

}
