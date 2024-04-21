package net.cathienova.havenalchemy.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig
{
    public final ForgeConfigSpec.BooleanValue enableAcidRain;
    public final ForgeConfigSpec.IntValue miniCoalBurnTime;
    public final ForgeConfigSpec.IntValue miniCharcoalBurnTime;
    public final ForgeConfigSpec.BooleanValue easyHammerDurability;

    public CommonConfig(ForgeConfigSpec.Builder builder) {
        enableAcidRain = builder.comment("If enabled, players will take damage when in acid rain.").define("enableAcidRain", false);
        miniCoalBurnTime = builder.comment("The burn time of mini coal. (200 = 1 item)").defineInRange("miniCoalBurnTime", 200, 1, 100000);
        miniCharcoalBurnTime = builder.comment("The burn time of mini charcoal. (200 = 1 item)").defineInRange("miniCharcoalBurnTime", 200, 1, 100000);
        easyHammerDurability = builder.comment("If enabled, the hammers only takes 1 durability instead of the amount it breaks.").define("easyHammerDurability", true);
    }
}