package net.cathienova.havenalchemy.worldgen.tree.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.cathienova.havenalchemy.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class CharmelTrunkPlacer extends TrunkPlacer {
    public static final Codec<CharmelTrunkPlacer> CODEC = RecordCodecBuilder.create(pineTrunkPlacerInstance ->
            trunkPlacerParts(pineTrunkPlacerInstance).apply(pineTrunkPlacerInstance, CharmelTrunkPlacer::new));

    public CharmelTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.charmel_trunk_placer.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter,
                                                            RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
        int heightVariationA = Math.max(heightRandA, 1);
        int heightVariationB = Math.max(heightRandB - heightRandA + 1, 1);

        int height = pFreeTreeHeight + pRandom.nextInt(heightVariationA) + pRandom.nextInt(heightVariationB);


        for(int i = 0; i < height; i++) {
            placeLog(pLevel, pBlockSetter, pRandom, pPos.above(i), pConfig);

            if (i > 3 && i % 2 == 0 && pRandom.nextBoolean()) {
                generateBranch(pLevel, pBlockSetter, pRandom, pPos.above(i), pConfig);
            }
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pPos.above(height), 0, false));
    }

    private void generateBranch(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter,
                                RandomSource pRandom, BlockPos pPos, TreeConfiguration pConfig) {
        Direction[] directions = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
        Direction branchDirection = directions[pRandom.nextInt(directions.length)];

        BlockPos branchPos = pPos.relative(branchDirection);
        placeLog(pLevel, pBlockSetter, pRandom, branchPos, pConfig);
    }
}