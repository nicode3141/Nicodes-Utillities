package de.nicode3141.nicodesutils.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class NicodesUtilsClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Configs for Nicodes Utillities");

        // HERE DEFINE YOUR CONFIGS

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
