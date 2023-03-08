package de.nicode3141.nicodesutils.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class NicodesUtilsCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;


    static {
        BUILDER.push("Configs for Nicodes Utillities");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
