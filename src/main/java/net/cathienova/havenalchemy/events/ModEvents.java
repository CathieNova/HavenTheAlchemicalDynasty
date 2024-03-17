package net.cathienova.havenalchemy.events;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.loot.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = HavenAlchemy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegisterEvent event) {
        event.register(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, helper -> {
            helper.register(new ResourceLocation(HavenAlchemy.MOD_ID,"ancient_city_loot_modifier"),
                    AncientCityLootModifier.CODEC);
            helper.register(new ResourceLocation(HavenAlchemy.MOD_ID,"sculk_block_loot_modifier"),
                    SculkBlockLootModifier.CODEC);
            helper.register(new ResourceLocation(HavenAlchemy.MOD_ID,"warden_loot_modifier"),
                    WardenLootModifier.CODEC);
        });
    }
}
