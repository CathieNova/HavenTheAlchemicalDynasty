package net.cathienova.havenalchemy.worldgen;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> add_havenite_ore = registerKey("add_havenite_ore");
    public static final ResourceKey<BiomeModifier> add_basphalt_stone = registerKey("add_basphalt_stone");
    public static final ResourceKey<BiomeModifier> add_neosphore_ore = registerKey("add_neosphore_ore");

    public static final ResourceKey<BiomeModifier> add_charmel_tree = registerKey("add_charmel_tree");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(add_havenite_ore, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.havenite_ore_placed_key)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(add_basphalt_stone, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.HAS_ANCIENT_CITY),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.basphalt_stone_placed_key)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(add_neosphore_ore, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.HAS_ANCIENT_CITY),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.neosphore_ore_placed_key)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(add_charmel_tree, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_PLAINS),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.charmel_placed_key)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(HavenAlchemy.MOD_ID, name));
    }
}
