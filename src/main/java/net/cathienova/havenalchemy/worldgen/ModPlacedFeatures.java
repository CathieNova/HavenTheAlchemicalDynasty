package net.cathienova.havenalchemy.worldgen;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> neosphore_ore_placed_key = registerKey("neosphore_ore_placed");
    public static final ResourceKey<PlacedFeature> basphalt_stone_placed_key = registerKey("basphalt_stone_placed");
    public static final ResourceKey<PlacedFeature> charmel_placed_key = registerKey("charmel_placed_key");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        //pCount = veins amount per chunk
        register(context, basphalt_stone_placed_key, configuredFeatures.getOrThrow(ModConfiguredFeatures.overworld_basphalt_stone_key),
                ModOrePlacement.commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-5))));

        register(context, neosphore_ore_placed_key, configuredFeatures.getOrThrow(ModConfiguredFeatures.overworld_neosphore_ore_key),
                ModOrePlacement.commonOrePlacement(50,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-5))));

        register(context, charmel_placed_key, configuredFeatures.getOrThrow(ModConfiguredFeatures.charmel_key),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.075f, 2), ModBlocks.charmel_sapling.get()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(HavenAlchemy.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
