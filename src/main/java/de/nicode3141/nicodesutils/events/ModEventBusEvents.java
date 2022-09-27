package de.nicode3141.nicodesutils.events;

import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.entity.ModEntityTypes;
import de.nicode3141.nicodesutils.entity.custom.ModVillager;
import de.nicode3141.nicodesutils.entity.custom.RGBSheepEntity;
import de.nicode3141.nicodesutils.item.custom.ModSpawnEggItem;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = NicodesUtils.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.BRP_SHEEP.get(), RGBSheepEntity.setCustomAttributes().create());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItem.initSpawnEggs();
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event){
        ModVillager.registerPOI();
    }
}
