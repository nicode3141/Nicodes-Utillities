package de.nicode3141.nicodesutils.item;

import de.nicode3141.nicodesutils.NicodesUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NicodesUtils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    public static CreativeModeTab NICODESUTIL_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event){
        NICODESUTIL_TAB = event.registerCreativeModeTab(new ResourceLocation(NicodesUtils.MOD_ID,"nicodesutil_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.HYDROGEN_BUCKET.get())).title(Component.literal("Nicodes Utillities")).build());
    }

}
