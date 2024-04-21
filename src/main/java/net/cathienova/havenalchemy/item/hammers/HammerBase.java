package net.cathienova.havenalchemy.item.hammers;

import net.cathienova.havenalchemy.config.HavenConfig;
import net.cathienova.havenalchemy.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class HammerBase extends PickaxeItem
{

    public HammerBase(Tier material, int attackDamage, float attackSpeed, Properties properties)
    {
        super(material, attackDamage, attackSpeed, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag)
    {
        super.appendHoverText(stack, world, tooltip, flag);
        tooltip.add(Component.literal(ChatFormatting.GRAY + "Mines in a 3x3 area."));
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
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player)
    {
        if (player.level().isClientSide) return false;

        BlockState blockState = player.level().getBlockState(pos);
        if (!isBreakableByTool(blockState, player, this.getTier()))
        {
            return super.onBlockStartBreak(itemstack, pos, player);
        }

        BlockHitResult hitResult = (BlockHitResult) player.pick(10.0D, 1.0F, false);
        if (hitResult.getType() != HitResult.Type.BLOCK)
        {
            return false;
        }

        Direction face = hitResult.getDirection();
        breakAdjacentBlocks(player, pos, face);

        return true;
    }

    private void breakAdjacentBlocks(Player player, BlockPos pos, Direction face) {
        int breakAmount = 0;
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        // Determine the axis of expansion based on the face direction
        int startX = face.getAxis() == Direction.Axis.X ? 0 : -1;
        int endX = face.getAxis() == Direction.Axis.X ? 0 : 1;
        int startY = face.getAxis() == Direction.Axis.Y ? 0 : -1;
        int endY = face.getAxis() == Direction.Axis.Y ? 0 : 1;
        int startZ = face.getAxis() == Direction.Axis.Z ? 0 : -1;
        int endZ = face.getAxis() == Direction.Axis.Z ? 0 : 1;

        for (int dx = startX; dx <= endX; dx++) {
            for (int dy = startY; dy <= endY; dy++) {
                for (int dz = startZ; dz <= endZ; dz++) {
                    mutablePos.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
                    BlockState adjacentBlock = player.level().getBlockState(mutablePos);
                    if (isBreakableByTool(adjacentBlock, player, this.getTier()))
                    {
                        destroyBlock(player.level(), mutablePos, player);
                        breakAmount++;
                    }
                }
            }
        }

        int finalBreakAmount = HavenConfig.easyHammerDurability ? 1 : breakAmount;
        consumeDurability(player, finalBreakAmount);
    }

    private boolean isBreakableByTool(BlockState state, Player player, Tier toolTier) {
        if (isBlockAppropriateForPickaxe(state)) {
            // Retrieve the required harvest level for this block.
            int requiredHarvestLevel = getRequiredHarvestLevel(state);
            // Compare the tool's harvest level to the block's required level.
            if (toolTier.getLevel() >= requiredHarvestLevel) {
                return true;
            }
        }
        return false;
    }

    private boolean isBlockAppropriateForPickaxe(BlockState state) {
        // Check if the block is in a custom tag or a predefined category needing a pickaxe.
        return state.is(BlockTags.MINEABLE_WITH_PICKAXE);
    }

    private int getRequiredHarvestLevel(BlockState state)
    {
        if (state.is(BlockTags.NEEDS_STONE_TOOL))
        {
            return 1; // Stone tier
        }
        else if (state.is(BlockTags.NEEDS_IRON_TOOL))
        {
            return 2; // Iron tier
        }
        else if (state.is(BlockTags.NEEDS_DIAMOND_TOOL))
        {
            return 3; // Diamond tier
        }
        else if (state.is(ModTags.Blocks.needs_netherite_tool))
        {
            return 4; // Netherite tier
        }
        else if (state.is(ModTags.Blocks.needs_dark_matter_tool))
        {
            return 5; // Dark Matter tier
        }
        else if (state.is(ModTags.Blocks.needs_red_matter_tool))
        {
            return 6; // Red Matter tier
        }
        else if (state.is(ModTags.Blocks.needs_neosphore_tool))
        {
            return 7; // Neosphore tier
        }
        return 0; // Wood tier if no tag is present
    }

    private void destroyBlock(Level level, BlockPos pos, Player player)
    {
        BlockState blockState = level.getBlockState(pos);
        if (blockState.getBlock().canHarvestBlock(blockState, level, pos, player))
        {
            if (player.isCreative())
                level.destroyBlock(pos, false, player);
            else
            {
                level.destroyBlock(pos, true, player);
                player.awardStat(Stats.ITEM_USED.get(player.getMainHandItem().getItem()));
                player.awardStat(Stats.BLOCK_MINED.get(blockState.getBlock()));
            }
        }
    }

    private void consumeDurability(Player player, int durabilityCost)
    {
        if (!player.isCreative() && durabilityCost > 0)
        {
            player.getMainHandItem().hurtAndBreak(durabilityCost, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
        }
    }
}