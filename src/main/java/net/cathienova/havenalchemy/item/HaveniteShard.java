package net.cathienova.havenalchemy.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HaveniteShard extends Item
{
    public HaveniteShard(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        if (itemStack.isDamageableItem() && itemStack.getDamageValue() < itemStack.getMaxDamage() - 1) {
            ItemStack damaged = itemStack.copy();
            int newDamage = damaged.getDamageValue() + 1;
            if (newDamage >= damaged.getMaxDamage()) {
                return ItemStack.EMPTY;
            } else {
                damaged.setDamageValue(newDamage);
                return damaged;
            }
        } else {
            return ItemStack.EMPTY;
        }
    }
}
