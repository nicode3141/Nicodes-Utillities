package de.nicode3141.nicodesutils.item;

import net.minecraft.world.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup NICODESUTILS_GROUP = new ItemGroup("nicodesutilstab") {
        @Override
        public ItemStack createIcon() {
            //ARTIFACT OF CODE is used here as the Icon
            return new ItemStack(ModItems.ARTIFACT_OF_CODE.get());
        }
    };
}
