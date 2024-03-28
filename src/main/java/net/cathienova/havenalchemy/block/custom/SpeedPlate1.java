package net.cathienova.havenalchemy.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpeedPlate1 extends Block
{
    private static double speed = 0.15f;
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 1, 16);

    public SpeedPlate1(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
    {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState)
    {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection().getOpposite();
        return this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, facing);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag)
    {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        pTooltip.add(Component.translatable("block.havenalchemy.speed_plate.tooltip").withStyle(net.minecraft.ChatFormatting.GOLD));
    }

    @Override
    public void entityInside(BlockState state, net.minecraft.world.level.Level level, BlockPos pos, Entity entity) {
        super.entityInside(state, level, pos, entity);

        if (entity.isShiftKeyDown()) return; // Do not move the entity if it is sneaking

        Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        double dx = getOppositeDirection(facing).getStepX() * speed;
        double dz = getOppositeDirection(facing).getStepZ() * speed;

        if (facing == Direction.NORTH || facing == Direction.SOUTH) {
            entity.setDeltaMovement(entity.getDeltaMovement().x, entity.getDeltaMovement().y, dz);
        }
        else if (facing == Direction.EAST || facing == Direction.WEST) {
            entity.setDeltaMovement(dx, entity.getDeltaMovement().y, entity.getDeltaMovement().z);
        }
    }

    private Direction getOppositeDirection(Direction dir) {
        switch (dir) {
            case NORTH:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.NORTH;
            case EAST:
                return Direction.WEST;
            case WEST:
                return Direction.EAST;
            default:
                return dir;
        }
    }
}
