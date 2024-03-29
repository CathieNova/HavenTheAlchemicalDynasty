package net.cathienova.havenalchemy.block.chests;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.block.entity.ModBlockEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

public enum ChestTypes
{
    NETHERITE(135, 15, new ResourceLocation(HavenAlchemy.MOD_ID, "block/netherite_chest")),
    EMERALD(117, 13, new ResourceLocation(HavenAlchemy.MOD_ID, "block/emerald_chest")),
    ALCHEMICAL(91, 13, new ResourceLocation(HavenAlchemy.MOD_ID, "block/alchemical_chest")),
    OBSIDIAN(84, 12, new ResourceLocation(HavenAlchemy.MOD_ID, "block/obsidian_chest")),
    DIAMOND(84, 12, new ResourceLocation(HavenAlchemy.MOD_ID, "block/diamond_chest")),
    GOLD(63, 9, new ResourceLocation(HavenAlchemy.MOD_ID, "block/gold_chest")),
    IRON(54, 9, new ResourceLocation(HavenAlchemy.MOD_ID, "block/iron_chest")),
    COPPER(36, 9, new ResourceLocation(HavenAlchemy.MOD_ID, "block/copper_chest")),
    STONE(18, 9, new ResourceLocation(HavenAlchemy.MOD_ID, "block/stone_chest")),
    DIRT(1, 1, new ResourceLocation(HavenAlchemy.MOD_ID, "block/dirt_chest"));

    public final int size;
    public final int rowLength;
    public final ResourceLocation texture;

    ChestTypes(int size, int rowLength, ResourceLocation texture)
    {
        this.size = size;
        this.rowLength = rowLength;
        this.texture = texture;
    }

    public static Block get(ChestTypes type)
    {
        return switch (type)
                {
                    case DIRT -> ModBlocks.dirt_chest.get();
                    case STONE -> ModBlocks.stone_chest.get();
                    case COPPER -> ModBlocks.copper_chest.get();
                    case IRON -> ModBlocks.iron_crop.get();
                    case GOLD -> ModBlocks.gold_crop.get();
                    case OBSIDIAN -> ModBlocks.obsidian_chest.get();
                    case DIAMOND -> ModBlocks.diamond_crop.get();
                    case EMERALD -> ModBlocks.emerald_chest.get();
                    case ALCHEMICAL -> ModBlocks.alchemical_chest.get();
                    case NETHERITE -> ModBlocks.netherite_chest.get();
                    default -> Blocks.CHEST;
                };
    }

    public BlockEntityType<? extends ChestBlockEntity> getBlockEntityType()
    {
        return switch (this)
                {
                    case DIRT -> ModBlockEntities.dirt_chest.get();
                    case STONE -> ModBlockEntities.stone_chest.get();
                    case COPPER -> ModBlockEntities.copper_chest.get();
                    case IRON -> ModBlockEntities.iron_chest.get();
                    case GOLD -> ModBlockEntities.gold_chest.get();
                    case OBSIDIAN -> ModBlockEntities.obsidian_chest.get();
                    case DIAMOND -> ModBlockEntities.diamond_chest.get();
                    case EMERALD -> ModBlockEntities.emerald_chest.get();
                    case ALCHEMICAL -> ModBlockEntities.alchemical_chest.get();
                    case NETHERITE -> ModBlockEntities.netherite_chest.get();
                    default -> BlockEntityType.CHEST;
                };
    }

    public String getTranslationKey()
    {
        return switch (this)
                {
                    case DIRT -> "block.havenalchemy.dirt_chest";
                    case STONE -> "block.havenalchemy.stone_chest";
                    case COPPER -> "block.havenalchemy.copper_chest";
                    case IRON -> "block.havenalchemy.iron_chest";
                    case GOLD -> "block.havenalchemy.gold_chest";
                    case OBSIDIAN -> "block.havenalchemy.obsidian_chest";
                    case DIAMOND -> "block.havenalchemy.diamond_chest";
                    case EMERALD -> "block.havenalchemy.emerald_chest";
                    case ALCHEMICAL -> "block.havenalchemy.alchemical_chest";
                    case NETHERITE -> "block.havenalchemy.netherite_chest";
                    default -> "block.minecraft.chest";
                };
    }
}