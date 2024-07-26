package de.nicode3141.nicodesutils.util;

import de.nicode3141.nicodesutils.NicodesUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, NicodesUtils.MOD_ID);

    //creates a SoundEvent with rickroll.ogg
    public static final RegistryObject<SoundEvent> RICKROLL =
            registerSoundEvent("rickroll");

    public static final RegistryObject<SoundEvent> WORKER_NOICH_VILLAGER =
            registerSoundEvent("worker_noich_villager");

    public static final RegistryObject<SoundEvent> MEDIUM_EXPLOSION =
            registerSoundEvent("medium-explosion");

    public static final RegistryObject<SoundEvent> EXTREME_EXPLOSION =
            registerSoundEvent("extreme-explosion");

    public static final RegistryObject<SoundEvent> DONT_WORRY =
            registerSoundEvent("dont-worry");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name){
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(NicodesUtils.MOD_ID,name)));
    }

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
