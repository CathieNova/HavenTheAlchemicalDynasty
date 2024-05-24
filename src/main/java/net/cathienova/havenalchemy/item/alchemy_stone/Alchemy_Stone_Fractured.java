package net.cathienova.havenalchemy.item.alchemy_stone;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Alchemy_Stone_Fractured extends Item
{
    public Alchemy_Stone_Fractured(Properties properties) {
        super(properties);
        properties.stacksTo(16);
        properties.rarity(Rarity.UNCOMMON);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag isAdvanced)
    {
        super.appendHoverText(stack, level, tooltip, isAdvanced);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context)
    {
        Level level = context.getLevel();
        Block block = level.getBlockState(context.getClickedPos()).getBlock();
        Player player = context.getPlayer();

        assert player != null;
        if (block == Blocks.DIRT && player.isCrouching())
        {
            if (!level.isClientSide())
            {
                level.setBlockAndUpdate(context.getClickedPos(), Blocks.GRASS_BLOCK.defaultBlockState());
                context.getItemInHand().shrink(1);
            }
            else
            {
                level.playSound(player, context.getClickedPos(), SoundEvents.CHAIN_PLACE, SoundSource.PLAYERS, 0.5f, 1f);
            }

            return super.useOn(context);
        }
        return InteractionResult.PASS;
    }
}
