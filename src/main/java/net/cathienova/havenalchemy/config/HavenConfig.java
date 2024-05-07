package net.cathienova.havenalchemy.config;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.minecraftforge.fml.config.ModConfig;

public class HavenConfig
{
    public static boolean enableAcidRain;
    public static int miniCoalBurnTime;
    public static int miniCharcoalBurnTime;
    public static boolean easyHammerDurability;
    public static int essenceShardDropChance;


    public static void bake(ModConfig config) {
        enableAcidRain = HavenAlchemy.c_config.enableAcidRain.get();
        miniCoalBurnTime = HavenAlchemy.c_config.miniCoalBurnTime.get();
        miniCharcoalBurnTime = HavenAlchemy.c_config.miniCharcoalBurnTime.get();
        easyHammerDurability = HavenAlchemy.c_config.easyHammerDurability.get();
        essenceShardDropChance = HavenAlchemy.c_config.essenceShardDropChance.get();
    }
}
