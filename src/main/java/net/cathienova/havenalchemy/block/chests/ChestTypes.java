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
    ALCHEMICAL(91, 13, new ResourceLocation(HavenAlchemy.MOD_ID, "block/alchemical_chest"));

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
                    case ALCHEMICAL -> ModBlocks.alchemical_chest.get();
                    default -> Blocks.CHEST;
                };
    }

    public BlockEntityType<? extends ChestBlockEntity> getBlockEntityType()
    {
        return switch (this)
                {
                    case ALCHEMICAL -> ModBlockEntities.alchemical_chest.get();
                    default -> BlockEntityType.CHEST;
                };
    }

    public String getTranslationKey()
    {
        return switch (this)
                {
                    case ALCHEMICAL -> "block.havenalchemy.alchemical_chest";
                    default -> "block.minecraft.chest";
                };
    }
}