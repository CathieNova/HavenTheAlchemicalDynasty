package net.cathienova.havenalchemy.worldgen.tree;

import net.cathienova.havenalchemy.worldgen.ModConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class CharmelTreeGrower extends AbstractTreeGrower
{

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers)
    {
        return ModConfiguredFeatures.charmel_key;
    }
}
