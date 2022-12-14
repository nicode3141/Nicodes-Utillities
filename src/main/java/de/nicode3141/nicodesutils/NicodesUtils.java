package de.nicode3141.nicodesutils;

import de.nicode3141.nicodesutils.block.ModBlocks;
import de.nicode3141.nicodesutils.container.ModContainers;
import de.nicode3141.nicodesutils.entity.ModEntityTypes;
import de.nicode3141.nicodesutils.entity.custom.ModVillager;
import de.nicode3141.nicodesutils.entity.render.RGBSheepRenderer;
import de.nicode3141.nicodesutils.item.ModItems;
import de.nicode3141.nicodesutils.paintings.ModPaintings;
import de.nicode3141.nicodesutils.screen.ItemShredderScreen;
import de.nicode3141.nicodesutils.tileentity.ModTileEntities;
import de.nicode3141.nicodesutils.util.ModSoundEvents;
import de.nicode3141.nicodesutils.world.structure.ModStructures;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NicodesUtils.MOD_ID)
public class NicodesUtils
{
    public static final String MOD_ID = "nicodesutils";


    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public NicodesUtils() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModTileEntities.register(eventBus);
        ModContainers.register(eventBus);

        ModStructures.register(eventBus);
        ModSoundEvents.register(eventBus);

        ModPaintings.register(eventBus);
        ModEntityTypes.register(eventBus);

        ModVillager.VILLAGER_PROFESSIONS.register(eventBus);
        ModVillager.POINT_OF_INTEREST_TYPES.register(eventBus);


        eventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ModStructures.setupStructures();


        });

        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        event.enqueueWork(() -> {
            ScreenManager.registerFactory(ModContainers.ITEM_SHREDDER_CONTAINER.get(), ItemShredderScreen::new);
        });
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BRP_SHEEP.get(), RGBSheepRenderer::new);

    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
