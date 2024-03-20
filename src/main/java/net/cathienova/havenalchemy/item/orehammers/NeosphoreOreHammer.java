package net.cathienova.havenalchemy.item.orehammers;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NeosphoreOreHammer extends Item
{
    public NeosphoreOreHammer(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return new ItemStack(itemStack.getItem());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag isAdvanced)
    {
        super.appendHoverText(stack, level, tooltip, isAdvanced);

        tooltip.add(Component.translatable("tooltip.havenalchemy.ore_hammer").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("tooltip.havenalchemy.ore_hammer.durability.inf").withStyle(ChatFormatting.GOLD));
    }
}
