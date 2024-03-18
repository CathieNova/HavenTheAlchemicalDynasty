package net.cathienova.havenalchemy.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrowelItem extends Item {

    public TrowelItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        ServerPlayer player = (ServerPlayer) context.getPlayer();
        if (player == null) {
            return InteractionResult.FAIL;
        }

        ArrayList<ItemStack> blockItems = new ArrayList<>();
        for (int i = 0; i < 9; i++) { // Hotbar slots are from 0 to 8
            ItemStack itemStack = player.getInventory().getItem(i);
            if (itemStack.getItem() instanceof net.minecraft.world.item.BlockItem) {
                blockItems.add(itemStack);
            }
        }

        if (blockItems.isEmpty()) {
            return InteractionResult.FAIL;
        }

        // Select a random block item from the hotbar
        ItemStack randomBlockItem = blockItems.get(new Random().nextInt(blockItems.size()));
        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());

        // Try to place the block
        BlockState blockState = ((net.minecraft.world.item.BlockItem) randomBlockItem.getItem()).getBlock().defaultBlockState();
        context.getLevel().setBlock(pos, blockState, 3);
        context.getLevel().gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(player, blockState));

        // Decrease the stack size by one
        randomBlockItem.shrink(1);
        context.getLevel().playSound(player, pos, blockState.getSoundType().getPlaceSound(), net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);
        context.getItemInHand().hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(context.getHand()));

        return InteractionResult.sidedSuccess(context.getLevel().isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
    {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        pTooltipComponents.add(Component.translatable("item.havenalchemy.trowel.tooltip"));
    }
}
