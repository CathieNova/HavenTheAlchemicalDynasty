package net.cathienova.havenalchemy.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModShear extends ShearsItem
{
    public ModShear(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player)
    {
        if (player.level().isClientSide) return false;

        if (itemstack.isDamageableItem() && player.level().getBlockState(pos).getBlock() instanceof net.minecraft.world.level.block.LeavesBlock)
        {
            if (itemstack.getDamageValue() < itemstack.getMaxDamage() - 1)
            {
                int newDamage = itemstack.getDamageValue() + 1;
                if (newDamage >= itemstack.getMaxDamage())
                {
                    itemstack.shrink(1);
                }
                else
                {
                    itemstack.setDamageValue(newDamage);
                }
            }
            else
            {
                itemstack.shrink(1);
            }
            return true;
        }
        return true;
    }
}
