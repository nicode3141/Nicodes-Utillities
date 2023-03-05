package de.nicode3141.nicodesutils.world.dimension;

import de.nicode3141.nicodesutils.NicodesUtils;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ModDimensions {
    public static RegistryKey<World> BRPDim = RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
        new ResourceLocation(NicodesUtils.MOD_ID,"brpdim"));
}
