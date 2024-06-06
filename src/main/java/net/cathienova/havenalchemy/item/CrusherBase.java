package net.cathienova.havenalchemy.item;

import net.cathienova.havenalchemy.block.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class CrusherBase extends PickaxeItem
{
    public CrusherBase(Tier tier, int attackDamageModifier, float attackSpeedModifier, Properties properties)
    {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag)
    {
        super.appendHoverText(stack, world, tooltip, flag);
        tooltip.add(Component.literal(ChatFormatting.GRAY + "crushes various blocks."));
        if (stack.getItem() == ModItems.neosphore_crusher.get())
        {
            tooltip.add(Component.translatable("tooltip.havenalchemy.ore_hammer.durability.inf").withStyle(ChatFormatting.GOLD));
        }
    }

    @Override
    public boolean isEnchantable(ItemStack pStack)
    {
        return true;
    }

    @Override
    public int getEnchantmentValue()
    {
        return 25;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
        if (player.level().isClientSide()) return false;

        BlockState blockState = player.level().getBlockState(pos);
        if (blockState.getBlock() == Blocks.STONE || blockState.getBlock() == Blocks.COBBLESTONE) {
            Block.popResource(player.level(), pos, new ItemStack(Blocks.GRAVEL));
            player.level().destroyBlock(pos, false);
            player.awardStat(Stats.BLOCK_MINED.get(blockState.getBlock()));
            itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(p.getUsedItemHand()));
            return true;
        }
        else if (blockState.getBlock() == Blocks.GRAVEL) {
            Block.popResource(player.level(), pos, new ItemStack(Blocks.SAND));
            player.level().destroyBlock(pos, false);
            player.awardStat(Stats.BLOCK_MINED.get(blockState.getBlock()));
            itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(p.getUsedItemHand()));
            return true;
        }
        else if (blockState.getBlock() == Blocks.SAND) {
            Block.popResource(player.level(), pos, new ItemStack(ModBlocks.dust.get()));
            player.level().destroyBlock(pos, false);
            player.awardStat(Stats.BLOCK_MINED.get(blockState.getBlock()));
            itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(p.getUsedItemHand()));
            return true;
        }
        else if (blockState.getBlock() == Blocks.END_STONE)
        {
            Block.popResource(player.level(), pos, new ItemStack(ModBlocks.crushed_end_stone.get()));
            player.level().destroyBlock(pos, false);
            player.awardStat(Stats.BLOCK_MINED.get(blockState.getBlock()));
            itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(p.getUsedItemHand()));
            return true;
        }
        else if (blockState.getBlock() == Blocks.NETHERRACK)
        {
            Block.popResource(player.level(), pos, new ItemStack(ModBlocks.crushed_netherrack.get()));
            player.level().destroyBlock(pos, false);
            player.awardStat(Stats.BLOCK_MINED.get(blockState.getBlock()));
            itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(p.getUsedItemHand()));
            return true;
        }

        return true;
    }
}
