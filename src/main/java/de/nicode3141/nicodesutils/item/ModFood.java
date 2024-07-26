package de.nicode3141.nicodesutils.item;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ModFood {

    public static final Food Neuralink = new Food.Builder()
            .hunger(0).saturation(0)
            .effect(new EffectInstance(Effects.NAUSEA,100,2), 1.0F)
            .setAlwaysEdible().build();

    public static final Food Cola = new Food.Builder()
            .effect(new EffectInstance(Effects.SPEED, 10, 1), 0.5f)
            .effect(new EffectInstance(Effects.NIGHT_VISION, 20, 1), 0.7f)
            .setAlwaysEdible().build();

}
