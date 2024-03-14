package net.cathienova.havenalchemy.worldgen.portal;

import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.block.custom.ModPortalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class ModTeleporter implements ITeleporter
{
    public static BlockPos thisPos = BlockPos.ZERO;
    public static boolean insideDimension = true;

    public ModTeleporter(BlockPos pos, boolean insideDim)
    {
        thisPos = pos;
        insideDimension = insideDim;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destinationWorld,
                              float yaw, Function<Boolean, Entity> repositionEntity)
    {
        entity = repositionEntity.apply(false);

        BlockPos destinationPos = new BlockPos(thisPos.getX(), thisPos.getY(), thisPos.getZ());

        int tries = 0;
        while ((destinationWorld.getBlockState(destinationPos).getBlock() != Blocks.AIR) &&
                !destinationWorld.getBlockState(destinationPos).canBeReplaced(Fluids.WATER) &&
                (destinationWorld.getBlockState(destinationPos.above()).getBlock() != Blocks.AIR) &&
                !destinationWorld.getBlockState(destinationPos.above()).canBeReplaced(Fluids.WATER) && (tries < 25))
        {
            destinationPos = destinationPos.above(100);
            tries++;
        }

        entity.setPos(destinationPos.getX(), destinationPos.getY(), destinationPos.getZ());

        if (insideDimension)
        {
            BlockPos bedrockStartPos = destinationPos.below();
            int platformSize = 5;

            for (int xOffset = -3; xOffset < platformSize; xOffset++) {
                for (int zOffset = -3; zOffset < platformSize; zOffset++) {
                    BlockPos currentPos = bedrockStartPos.offset(xOffset, 0, zOffset);
                    BlockState blockState = ModBlocks.basphalt_stone_bricks.get().defaultBlockState();
                    destinationWorld.setBlock(currentPos, blockState, 3);
                }
            }

            boolean doSetBlock = true;
            for (BlockPos checkPos : BlockPos.betweenClosed(destinationPos.below(100).west(100),
                    destinationPos.above(100).east(100)))
            {
                if (destinationWorld.getBlockState(checkPos).getBlock() instanceof ModPortalBlock)
                {
                    doSetBlock = false;
                    break;
                }
            }

            if (doSetBlock)
            {
                destinationWorld.setBlock(destinationPos, ModBlocks.catacombs_portal.get().defaultBlockState(), 3);
            }
        }

        return entity;
    }
}