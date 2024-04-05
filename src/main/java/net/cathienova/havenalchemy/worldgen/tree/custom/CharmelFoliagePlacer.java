package net.cathienova.havenalchemy.worldgen.tree.custom;

import net.cathienova.havenalchemy.worldgen.tree.ModFoliagePlacers;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class CharmelFoliagePlacer extends FoliagePlacer
{
    public static final Codec<CharmelFoliagePlacer> CODEC = RecordCodecBuilder.create(charmelFoliagePlacerInstance
            -> foliagePlacerParts(charmelFoliagePlacerInstance).and(Codec.intRange(0, 16).fieldOf("height")
            .forGetter(fp -> fp.height)).apply(charmelFoliagePlacerInstance, CharmelFoliagePlacer::new));
    private final int height;

    public CharmelFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {
        super(pRadius, pOffset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacers.charmel_foliage_placer.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, FoliageSetter pBlockSetter, RandomSource pRandom, TreeConfiguration pConfig,
                                 int pMaxFreeTreeHeight, FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {
        for (int y = 0; y <= pFoliageHeight; ++y) {
            // First layer directly above trunk, extends 3 out
            if (y == 0) {
                placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(y), 3, 0, pAttachment.doubleTrunk());
            }
            // Second layer, extends 1 out
            else if (y == 1) {
                placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(y), 2, 0, pAttachment.doubleTrunk());
            }
            // Third layer, similar to second but 1 block slimmer
            else if (y == 2) {
                placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(y), 1, 0, pAttachment.doubleTrunk());
            }
            // Top layer, just one block on top
            else if (y == 3) {
                placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(y), 0, 0, pAttachment.doubleTrunk());
            }
        }
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return false;
    }

    @Override
    public int foliageHeight(RandomSource pRandom, int pHeight, TreeConfiguration pConfig) {
        return height;
    }
}