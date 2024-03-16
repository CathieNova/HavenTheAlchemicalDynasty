package net.cathienova.havenalchemy.item.bark;


import net.cathienova.havenalchemy.item.fuel.FuelItem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class BarkItem extends FuelItem
{
    protected Block Log;
    protected Block StrippedLog;

    protected Block WoodenLog;
    protected Block WoodenStrippedLog;

    public BarkItem(Properties pProperties)
    {
        super(pProperties, 40);
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        if (!context.getLevel().isClientSide && context.getHand() == InteractionHand.MAIN_HAND) {
            final BlockPos blockPos = new BlockPos(context.getClickedPos());
            final Level world = context.getLevel();
            final Player player = context.getPlayer();

            if (world.getBlockState(blockPos).getBlock() == this.Log || world.getBlockState(blockPos).getBlock() == this.WoodenLog) {
                assert player != null;
                player.swing(InteractionHand.MAIN_HAND, true);
                world.playSound(null, blockPos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1f, 1f);

                if (!player.isCreative()) {
                    context.getItemInHand().shrink(1);
                }
            }

            if (world.getBlockState(blockPos).getBlock() == this.Log) {
                world.setBlock(blockPos, this.StrippedLog.defaultBlockState(), 3);
            }
            if (world.getBlockState(blockPos).getBlock() == this.WoodenLog) {
                world.setBlock(blockPos, this.WoodenStrippedLog.defaultBlockState(), 3);

            }
        }
        return InteractionResult.SUCCESS;
    }
}