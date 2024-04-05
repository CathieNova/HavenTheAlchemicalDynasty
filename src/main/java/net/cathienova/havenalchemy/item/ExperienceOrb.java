package net.cathienova.havenalchemy.item;

import net.cathienova.havenalchemy.util.PlayerExpUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class ExperienceOrb extends Item
{
    private final float expValue = 0.15f;
    public ExperienceOrb(Properties properties)
    {
        super(properties);
    }

    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(@Nonnull Level level, @Nonnull Player player, @Nonnull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        int stackCount = stack.getCount();
        if (player.isCrouching() && player.getItemInHand(hand).getCount() > 1) {
            for (int i = 0; i < stackCount; i++) {
                player.giveExperiencePoints(PlayerExpUtil.getPercentExperienceForNextLevel(player.experienceLevel, expValue));
                stack.shrink(1);
            }
        }
        else
        {
            player.giveExperiencePoints(PlayerExpUtil.getPercentExperienceForNextLevel(player.experienceLevel, expValue));
            stack.shrink(1);
        }

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag)
    {
        tooltip.add(Component.translatable("item.havenalchemy.experience_orb.tooltip"));
    }
}
