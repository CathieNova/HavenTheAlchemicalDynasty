package net.cathienova.havenalchemy.block.horticulture;

import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

public class IceCrop extends BaseCrop
{
    public IceCrop(Properties properties)
    {
        super(properties,
                Blocks.FARMLAND,
                ModBlocks.essentia_farmland.get(),
                ModBlocks.mysterium_farmland.get(),
                ModBlocks.vitalium_farmland.get(),
                ModBlocks.celestium_farmland.get(),
                ModBlocks.eternium_farmland.get()
        );
    }

    public static int MAX_AGE = 7;
    public static IntegerProperty AGE = BlockStateProperties.AGE_7;

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        pBuilder.add(AGE);
    }

    @Override
    public int getMaxAge()
    {
        return MAX_AGE;
    }

    @Override
    public @NotNull IntegerProperty getAgeProperty()
    {
        return AGE;
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId()
    {
        return ModItems.ice_seeds.get();
    }
}
