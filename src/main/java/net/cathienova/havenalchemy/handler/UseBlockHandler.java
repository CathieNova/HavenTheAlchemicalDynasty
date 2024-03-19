package net.cathienova.havenalchemy.handler;

import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class UseBlockHandler {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos blockPos = event.getPos();
        InteractionHand hand = event.getHand();

        if (!player.isSpectator() && hand == InteractionHand.MAIN_HAND && player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof AxeItem) {
            Block block = level.getBlockState(blockPos).getBlock();
            if (Blocks.ACACIA_LOG.equals(block) || Blocks.ACACIA_WOOD.equals(block))
            {
                spawnItem(level, player, blockPos, new ItemStack(ModItems.acacia_bark.get()));
            }
            else if (Blocks.BIRCH_LOG.equals(block) || Blocks.BIRCH_WOOD.equals(block))
            {
                spawnItem(level, player, blockPos, new ItemStack(ModItems.birch_bark.get()));
            }
            else if (ModBlocks.charmel_log.get().equals(block) || ModBlocks.charmel_wood.get().equals(block))
            {
                spawnItem(level, player, blockPos, new ItemStack(ModItems.charmel_bark.get()));
            }
            else if (Blocks.CHERRY_LOG.equals(block) || Blocks.CHERRY_WOOD.equals(block))
            {
                spawnItem(level, player, blockPos, new ItemStack(ModItems.cherry_bark.get()));
            }
            else if (Blocks.CRIMSON_STEM.equals(block) || Blocks.CRIMSON_HYPHAE.equals(block))
            {
                spawnItem(level, player, blockPos, new ItemStack(ModItems.crimson_bark.get()));
            }
            else if (Blocks.DARK_OAK_LOG.equals(block) || Blocks.DARK_OAK_WOOD.equals(block))
            {
                spawnItem(level, player, blockPos, new ItemStack(ModItems.dark_oak_bark.get()));
            }
            else if (Blocks.JUNGLE_LOG.equals(block) || Blocks.JUNGLE_WOOD.equals(block))
            {
                spawnItem(level, player, blockPos, new ItemStack(ModItems.jungle_bark.get()));
            }
            else if (Blocks.MANGROVE_LOG.equals(block) || Blocks.MANGROVE_WOOD.equals(block))
            {
                spawnItem(level, player, blockPos, new ItemStack(ModItems.mangrove_bark.get()));
            }
            else if (Blocks.OAK_LOG.equals(block) || Blocks.OAK_WOOD.equals(block))
            {
                spawnItem(level, player, blockPos, new ItemStack(ModItems.oak_bark.get()));
            }
            else if (Blocks.SPRUCE_LOG.equals(block) || Blocks.SPRUCE_WOOD.equals(block))
            {
                spawnItem(level, player, blockPos, new ItemStack(ModItems.spruce_bark.get()));
            }
            else if (Blocks.WARPED_STEM.equals(block) || Blocks.WARPED_HYPHAE.equals(block))
            {
                spawnItem(level, player, blockPos, new ItemStack(ModItems.warped_bark.get()));
            }
        }
    }

    private static void spawnItem(Level level, Player player, BlockPos blockPos, ItemStack item) {
        if (!level.isClientSide()) {
            level.addFreshEntity(new ItemEntity(level, blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, item));
        }
    }
}
