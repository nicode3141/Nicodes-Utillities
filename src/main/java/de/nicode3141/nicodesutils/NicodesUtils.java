package de.nicode3141.nicodesutils;

import com.mojang.logging.LogUtils;
import de.nicode3141.nicodesutils.block.ModBlocks;
import de.nicode3141.nicodesutils.block.entity.ModBlockEntities;
import de.nicode3141.nicodesutils.config.NicodesUtilsClientConfigs;
import de.nicode3141.nicodesutils.config.NicodesUtilsCommonConfigs;
import de.nicode3141.nicodesutils.item.ModCreativeModeTab;
import de.nicode3141.nicodesutils.item.ModItems;
import de.nicode3141.nicodesutils.networking.ModMessages;
import de.nicode3141.nicodesutils.recipe.ModRecipes;
import de.nicode3141.nicodesutils.screen.ElectrolysisChamberScreen;
import de.nicode3141.nicodesutils.screen.ModMenuTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NicodesUtils.MOD_ID)
public class NicodesUtils
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "nicodesutils";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public NicodesUtils()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, NicodesUtilsClientConfigs.SPEC,"nicodesutils-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NicodesUtilsCommonConfigs.SPEC,"nicodesutils-common.toml");
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // modEventBus.addListener(this::addCreative);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {

        });

        ModMessages.register();
    }



    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModMenuTypes.ELECTROLYSIS_CHAMBER_MENU.get(), ElectrolysisChamberScreen::new);
        }
    }
}
