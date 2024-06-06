package net.cathienova.havenalchemy.item;

import net.cathienova.havenalchemy.util.ModTags;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
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
        if (blockItems.size() == 0) {
            return InteractionResult.FAIL;
        }

        for (int i = 0; i < 9; i++) {
            ItemStack itemStack = player.getInventory().getItem(i);
            if (itemStack.getItem() instanceof net.minecraft.world.item.BlockItem) {
                if (itemStack.is(ItemTags.SAPLINGS) || itemStack.is(ItemTags.ANVIL) || itemStack.is(ItemTags.BEDS) ||
                        itemStack.is(ItemTags.DOORS) || itemStack.is(ItemTags.RAILS) || itemStack.is(ItemTags.SAPLINGS) ||
                        itemStack.is(ItemTags.TRAPDOORS) || itemStack.is(ItemTags.WOODEN_BUTTONS) || itemStack.is(ItemTags.WOODEN_DOORS) ||
                        itemStack.is(ItemTags.WOODEN_PRESSURE_PLATES) || itemStack.is(ItemTags.WOODEN_TRAPDOORS) || itemStack.is(ModTags.Items.seeds) ||
                        itemStack.is(ModTags.Items.chests)
                )
                {
                    return InteractionResult.FAIL;
                }

                blockItems.add(itemStack);
            }
        }

        if (blockItems.isEmpty()) {
            return InteractionResult.FAIL;
        }

        // Select a random block item from the hotbar
        ItemStack randomBlockItem = blockItems.get(new Random().nextInt(blockItems.size()));
        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());

        // Create a bounding box at the target position to check for entities
        AABB targetBlockArea = new AABB(pos, pos.offset(1, 1, 1));
        // Ensure no entities are present in the target block space before placing
        if (!context.getLevel().getEntities(null, targetBlockArea).isEmpty() || !context.getLevel().getBlockState(pos).isAir()) {
            return InteractionResult.FAIL; // Fail if there are entities
        }

        // Try to place the block
        BlockState blockState = ((net.minecraft.world.item.BlockItem) randomBlockItem.getItem()).getBlock().defaultBlockState();
        context.getLevel().setBlock(pos, blockState, 3);
        context.getLevel().gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(player, blockState));
        context.getLevel().playSound(player, pos, blockState.getSoundType().getPlaceSound(), net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);

        // Decrease the stack size by one
        if (!player.isCreative())
        {
            randomBlockItem.shrink(1);
            context.getItemInHand().hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(context.getHand()));
        }

        return InteractionResult.sidedSuccess(context.getLevel().isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
    {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        pTooltipComponents.add(Component.translatable("item.havenalchemy.trowel.tooltip").withStyle(net.minecraft.ChatFormatting.GOLD));
    }
}
