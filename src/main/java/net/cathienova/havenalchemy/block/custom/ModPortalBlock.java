package net.cathienova.havenalchemy.block.custom;

import net.cathienova.havenalchemy.worldgen.dimension.ModDimensions;
import net.cathienova.havenalchemy.worldgen.portal.ModTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModPortalBlock extends Block
{
    public static final VoxelShape shape = Block.box(0, 0, 0, 16, 16, 16);

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext)
    {
        return shape;
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState pState)
    {
        return RenderShape.MODEL;
    }

    public ModPortalBlock(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit)
    {
        ItemStack itemStack = player.getItemInHand(hand);

        if (itemStack.isEmpty() && !level.isClientSide) {
            handleNecroticPortal(player, pos);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    private void handleNecroticPortal(Entity player, BlockPos pPos)
    {
        if (player.level() instanceof ServerLevel serverlevel)
        {
            MinecraftServer minecraftserver = serverlevel.getServer();
            ResourceKey<Level> resourcekey = player.level().dimension() == ModDimensions.havenalchemy_level_key ?
                    Level.OVERWORLD : ModDimensions.havenalchemy_level_key;

            ServerLevel portalDimension = minecraftserver.getLevel(resourcekey);
            if (portalDimension != null && !player.isPassenger())
            {
                pPos = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ());
                if (resourcekey == ModDimensions.havenalchemy_level_key)
                {
                    player.changeDimension(portalDimension, new ModTeleporter(pPos, true));
                }
                else
                {
                    player.changeDimension(portalDimension, new ModTeleporter(pPos, false));
                }
            }
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable BlockGetter pLevel, @NotNull List<Component> pTooltip, @NotNull TooltipFlag pFlag)
    {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        pTooltip.add(Component.literal("WORK IN PROGRESS"));
    }
}