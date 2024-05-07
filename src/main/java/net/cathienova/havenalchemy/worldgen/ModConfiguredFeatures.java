package net.cathienova.havenalchemy.worldgen;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.util.ModTags;
import net.cathienova.havenalchemy.worldgen.tree.custom.CharmelFoliagePlacer;
import net.cathienova.havenalchemy.worldgen.tree.custom.CharmelTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.Objects;

public class ModConfiguredFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> overworld_havenite_ore_key = registerKey("havenite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> overworld_basphalt_stone_key = registerKey("basphalt_stone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> overworld_neosphore_ore_key = registerKey("neosphore_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> charmel_key = registerKey("charmel");
    public static final ResourceKey<ConfiguredFeature<?, ?>> acid_puddle_key = registerKey("acid_puddle");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest andesiteReplaceable = new TagMatchTest(ModTags.Blocks.andesite);
        RuleTest dioriteReplaceable = new TagMatchTest(ModTags.Blocks.diorite);
        RuleTest graniteReplaceable = new TagMatchTest(ModTags.Blocks.granite);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest basphaltStoneReplaceable = new TagMatchTest(ModTags.Blocks.basphalt_stone);

        List<OreConfiguration.TargetBlockState> overworldhaveniteOre = List.of(
                OreConfiguration.target(stoneReplaceable, ModBlocks.havenite_ore.get().defaultBlockState()),
                OreConfiguration.target(andesiteReplaceable, ModBlocks.andesite_havenite_ore.get().defaultBlockState()),
                OreConfiguration.target(dioriteReplaceable, ModBlocks.diorite_havenite_ore.get().defaultBlockState()),
                OreConfiguration.target(graniteReplaceable, ModBlocks.granite_havenite_ore.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.deepslate_havenite_ore.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldbasphalt_stone = List.of(OreConfiguration.target(deepslateReplaceables,
                ModBlocks.basphalt_stone.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldneosphoreOres = List.of(OreConfiguration.target(basphaltStoneReplaceable,
                ModBlocks.neosphore_ore.get().defaultBlockState()));

        //pSize = vein size
        register(context, overworld_havenite_ore_key, Feature.ORE, new OreConfiguration(overworldhaveniteOre, 10));
        register(context, overworld_basphalt_stone_key, Feature.ORE, new OreConfiguration(overworldbasphalt_stone, 45));
        register(context, overworld_neosphore_ore_key, Feature.ORE, new OreConfiguration(overworldneosphoreOres, 8));

        register(context, charmel_key, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.charmel_log.get()),
                new CharmelTrunkPlacer(3, 1, 3),
                BlockStateProvider.simple(ModBlocks.charmel_leaves.get()),
                new CharmelFoliagePlacer(ConstantInt.of(0), ConstantInt.of(1), 4),
                new TwoLayersFeatureSize(1, 0, 2))
                .ignoreVines()
                .build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(HavenAlchemy.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration)
    {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}