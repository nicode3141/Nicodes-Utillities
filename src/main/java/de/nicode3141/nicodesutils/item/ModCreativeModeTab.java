package de.nicode3141.nicodesutils.item;

import de.nicode3141.nicodesutils.NicodesUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModCreativeModeTab {
    public static final CreativeModeTab NICODESUTILS_TAB = new CreativeModeTab("nicodesutilstab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.HYDROGEN_BUCKET.get());
        }
    };

}
