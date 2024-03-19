package net.cathienova.havenalchemy.item.alchemy_stone;

import net.cathienova.havenalchemy.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Alchemy_Stone extends Item
{
    public Alchemy_Stone(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag isAdvanced)
    {
        super.appendHoverText(stack, level, tooltip, isAdvanced);

        if (Screen.hasShiftDown())
        {
            tooltip.add(Component.translatable("tooltip.havenalchemy.alchemystone.1").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("tooltip.havenalchemy.alchemystone.2"));
            tooltip.add(Component.translatable("tooltip.havenalchemy.alchemystone.3").withStyle(ChatFormatting.AQUA));
        }
        else
        {
            tooltip.add(Component.translatable("tooltip.havenalchemy.alchemystone.0").withStyle(ChatFormatting.GOLD));
        }
    }

    /*@Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (!level.isClientSide) {
            player.openMenu(new SimpleMenuProvider((id, inventory, playerEntity) ->
                    new CraftingMenu(id, inventory, ContainerLevelAccess.create(level, player.blockPosition())),
                    Component.literal("Crafting")));
            return InteractionResultHolder.consume(player.getItemInHand(hand));
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }*/

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return new ItemStack(ModItems.alchemy_stone.get());
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, LivingEntity interactionTarget, @NotNull InteractionHand usedHand)
    {
        if (interactionTarget.getType() == EntityType.SHEEP)
        {
            Sheep sheep = (Sheep) interactionTarget;
            DyeColor color = switch (sheep.getColor().toString().toUpperCase())
                    {
                        case "BLACK" -> DyeColor.BLUE;
                        case "BLUE" -> DyeColor.BROWN;
                        case "BROWN" -> DyeColor.CYAN;
                        case "CYAN" -> DyeColor.GRAY;
                        case "GRAY" -> DyeColor.GREEN;
                        case "GREEN" -> DyeColor.LIGHT_BLUE;
                        case "LIGHT_BLUE" -> DyeColor.LIGHT_GRAY;
                        case "LIGHT_GRAY" -> DyeColor.LIME;
                        case "LIME" -> DyeColor.MAGENTA;
                        case "MAGENTA" -> DyeColor.ORANGE;
                        case "ORANGE" -> DyeColor.PINK;
                        case "PINK" -> DyeColor.PURPLE;
                        case "PURPLE" -> DyeColor.RED;
                        case "RED" -> DyeColor.WHITE;
                        case "WHITE" -> DyeColor.YELLOW;
                        case "YELLOW" -> DyeColor.BLACK;
                        default -> DyeColor.WHITE;
                    };

            sheep.setColor(color);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context)
    {
        Level level = context.getLevel();
        Block block = level.getBlockState(context.getClickedPos()).getBlock();
        Player player = context.getPlayer();

        assert player != null;
        if (TransmuteGround(block, player.isCrouching()) != block)
        {
            if (!level.isClientSide())
            {
                level.setBlockAndUpdate(context.getClickedPos(), TransmuteGround(block, player.isCrouching()).defaultBlockState());
            }
            else
            {
                context.getPlayer().playSound(SoundEvents.CHAIN_PLACE, 0.5f, 1f);
            }

            return super.useOn(context);
        }
        return InteractionResult.PASS;
    }

    private Block TransmuteGround(Block block, boolean sneak)
    {
        if (block == Blocks.STONE && !sneak)
            return Blocks.COBBLESTONE;

        if (block == Blocks.COBBLESTONE && !sneak)
            return Blocks.STONE;

        if (block == Blocks.STONE && sneak)
            return Blocks.GRASS_BLOCK;

        if (block == Blocks.COBBLESTONE && sneak)
            return Blocks.GRASS_BLOCK;

        if (block == Blocks.GRASS_BLOCK && sneak)
            return Blocks.COBBLESTONE;

        if (block == Blocks.GRASS_BLOCK && !sneak)
            return Blocks.SAND;

        if (block == Blocks.SAND && !sneak)
            return Blocks.GRASS_BLOCK;

        if (block == Blocks.SAND && sneak)
            return Blocks.COBBLESTONE;

        if (block == Blocks.GRAVEL && !sneak)
            return Blocks.SANDSTONE;

        if (block == Blocks.SANDSTONE && !sneak)
            return Blocks.GRAVEL;

        if (block == Blocks.DIRT && sneak)
            return Blocks.COBBLESTONE;

        if (block == Blocks.DIRT && !sneak)
            return Blocks.GRASS_BLOCK;

        if (block == Blocks.WATER && !sneak)
            return Blocks.ICE;

        if (block == Blocks.LAVA && !sneak)
            return Blocks.OBSIDIAN;

        if (block == Blocks.NETHERRACK && !sneak)
            return Blocks.COBBLESTONE;

        if (block == Blocks.MELON && !sneak)
            return Blocks.PUMPKIN;

        if (block == Blocks.PUMPKIN && !sneak)
            return Blocks.MELON;

        return block;
    }
}
