package net.cathienova.havenalchemy.item.spirits;

import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class SpiritItem extends Item
{
    Block farmLand = null;

    public SpiritItem(Properties properties, Block farmLand)
    {
        super(properties);
        this.farmLand = farmLand;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context)
    {
        Level level = context.getLevel();
        Block block = level.getBlockState(context.getClickedPos()).getBlock();
        Player player = context.getPlayer();

        assert player != null;
        if (block == Blocks.FARMLAND && context.getItemInHand().getItem() == ModItems.essentia_spirit.get())
        {
            if (!level.isClientSide())
            {
                level.setBlockAndUpdate(context.getClickedPos(), ModBlocks.essentia_farmland.get().defaultBlockState());
                if (!player.isCreative())
                    context.getItemInHand().shrink(1);
            }
            else
            {
                level.playSound(player, context.getClickedPos(), SoundEvents.ROOTED_DIRT_PLACE, SoundSource.BLOCKS, 0.5f, 1f);
            }

            return super.useOn(context);
        }
        else if (block == ModBlocks.essentia_farmland.get() && context.getItemInHand().getItem() == ModItems.celestium_spirit.get())
        {
            if (!level.isClientSide())
            {
                level.setBlockAndUpdate(context.getClickedPos(), ModBlocks.celestium_farmland.get().defaultBlockState());
                if (!player.isCreative())
                    context.getItemInHand().shrink(1);
            }
            else
            {
                level.playSound(player, context.getClickedPos(), SoundEvents.ROOTED_DIRT_PLACE, SoundSource.BLOCKS, 0.5f, 1f);
            }

            return super.useOn(context);
        }
        else if (block == ModBlocks.celestium_farmland.get() && context.getItemInHand().getItem() == ModItems.eternium_spirit.get())
        {
            if (!level.isClientSide())
            {
                level.setBlockAndUpdate(context.getClickedPos(), ModBlocks.eternium_farmland.get().defaultBlockState());
                if (!player.isCreative())
                    context.getItemInHand().shrink(1);
            }
            else
            {
                level.playSound(player, context.getClickedPos(), SoundEvents.ROOTED_DIRT_PLACE, SoundSource.BLOCKS, 0.5f, 1f);
            }

            return super.useOn(context);
        }
        else if (block == ModBlocks.eternium_farmland.get() && context.getItemInHand().getItem() == ModItems.mysterium_spirit.get())
        {
            if (!level.isClientSide())
            {
                level.setBlockAndUpdate(context.getClickedPos(), ModBlocks.mysterium_farmland.get().defaultBlockState());
                if (!player.isCreative())
                    context.getItemInHand().shrink(1);
            }
            else
            {
                level.playSound(player, context.getClickedPos(), SoundEvents.ROOTED_DIRT_PLACE, SoundSource.BLOCKS, 0.5f, 1f);
            }

            return super.useOn(context);
        }
        return InteractionResult.PASS;
    }
}
