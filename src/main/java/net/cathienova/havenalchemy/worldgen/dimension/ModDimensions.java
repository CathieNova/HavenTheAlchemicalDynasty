package net.cathienova.havenalchemy.worldgen.dimension;

import com.mojang.datafixers.util.Pair;
import net.cathienova.havenalchemy.HavenAlchemy;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;
import java.util.OptionalLong;

public class ModDimensions {
    public static final ResourceKey<LevelStem> havenalchemy_key = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(HavenAlchemy.MOD_ID, "havenalchemydim"));
    public static final ResourceKey<Level> havenalchemy_level_key = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(HavenAlchemy.MOD_ID, "havenalchemydim"));
    public static final ResourceKey<DimensionType> havenalchemy_dim_type = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(HavenAlchemy.MOD_ID, "havenalchemydim_type"));


    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(havenalchemy_dim_type, new DimensionType(
                OptionalLong.of(12000), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                false, // natural
                1.0, // coordinateScale
                false, // bedWorks
                false, // respawnAnchorWorks
                0, // minY
                256, // height
                128, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(Biomes.THE_VOID)),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED));

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(Pair.of(
                                        Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(Biomes.THE_VOID))))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.CAVES));

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.havenalchemy_dim_type), noiseBasedChunkGenerator);

        context.register(havenalchemy_key, stem);
    }
}