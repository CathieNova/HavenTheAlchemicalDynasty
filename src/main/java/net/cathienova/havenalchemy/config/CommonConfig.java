package net.cathienova.havenalchemy.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig
{
    public final ForgeConfigSpec.BooleanValue enableAcidRain;
    public final ForgeConfigSpec.IntValue miniCoalBurnTime;
    public final ForgeConfigSpec.IntValue miniCharcoalBurnTime;
    public final ForgeConfigSpec.BooleanValue easyHammerDurability;
    public final ForgeConfigSpec.IntValue essenceShardDropChance;
    public final ForgeConfigSpec.IntValue mendingNecklaceRepairInterval;

    public CommonConfig(ForgeConfigSpec.Builder builder) {
        enableAcidRain = builder.comment("If enabled, players will take damage when in acid rain.").define("enableAcidRain", false);
        miniCoalBurnTime = builder.comment("The burn time of mini coal. (200 = 1 item)").defineInRange("miniCoalBurnTime", 200, 1, 100000);
        miniCharcoalBurnTime = builder.comment("The burn time of mini charcoal. (200 = 1 item)").defineInRange("miniCharcoalBurnTime", 200, 1, 100000);
        easyHammerDurability = builder.comment("If enabled, the hammers only takes 1 durability instead of the amount it breaks.").define("easyHammerDurability", true);
        essenceShardDropChance = builder.comment("The chance of a mob dropping an essence shard. (0-100%)").defineInRange("essenceShardDropChance", 25, 0, 100);
        mendingNecklaceRepairInterval = builder.comment("The interval in ticks between each repair of the mending necklace.").defineInRange("mendingNecklaceRepairInterval", 100, 1, 100000);
    }
}