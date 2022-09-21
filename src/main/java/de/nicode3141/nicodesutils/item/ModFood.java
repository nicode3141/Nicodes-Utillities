package de.nicode3141.nicodesutils.item;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ModFood {

    public static final Food Neuralink = new Food.Builder()
            .hunger(0).saturation(0)
            .effect(new EffectInstance(Effects.NAUSEA,100,2), 1.0F)
            .setAlwaysEdible().build();


}
