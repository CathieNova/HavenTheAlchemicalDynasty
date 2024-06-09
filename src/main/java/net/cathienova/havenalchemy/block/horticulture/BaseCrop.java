package net.cathienova.havenalchemy.block.horticulture;

import net.cathienova.havenalchemy.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class BaseCrop extends CropBlock
{
    public final Block[] validFarmland;

    public BaseCrop(Properties properties, Block... validFarmland) {
        super(properties);
        this.validFarmland = validFarmland;
    }

    public static int MAX_AGE = 7;
    public static IntegerProperty AGE = BlockStateProperties.AGE_7;;

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        pBuilder.add(AGE);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockState soil = worldIn.getBlockState(pos.below());
        for (Block farmland : validFarmland) {
            if (soil.is(farmland)) {
                return true;
            }
        }
        return false;
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
}
