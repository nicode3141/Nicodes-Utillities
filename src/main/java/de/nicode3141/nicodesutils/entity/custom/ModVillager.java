package de.nicode3141.nicodesutils.entity.custom;

import com.google.common.collect.ImmutableSet;
import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.block.ModBlocks;
import de.nicode3141.nicodesutils.util.ModSoundEvents;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;

public class ModVillager {
    public static final DeferredRegister<PointOfInterestType> POINT_OF_INTEREST_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, NicodesUtils.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.PROFESSIONS, NicodesUtils.MOD_ID);

    public static final RegistryObject<PointOfInterestType> NOICH_POI = POINT_OF_INTEREST_TYPES.register("noich_villager",
            () -> new PointOfInterestType("noich_villager", PointOfInterestType.getAllStates(ModBlocks.COMPRESSED_BITCOIN.get()), 1, 1));

    public static final RegistryObject<VillagerProfession> NOICH_PROFESSION = VILLAGER_PROFESSIONS.register("noich_villager",
            () -> new VillagerProfession("noich_villager", NOICH_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_LEATHERWORKER));

    public static void registerPOI() {
        try {
            ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "registerBlockStates", PointOfInterestType.class).invoke(null, NOICH_POI.get());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}