package net.cathienova.havenalchemy.cables.blocks;

import net.cathienova.havenalchemy.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FacadeBlockEntity extends CableBlockEntity {

    public static final String FACADE_TAG = "facade";

    @Nullable private BlockState facadeBlock = null;

    public FacadeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.facade_block_entity.get(), pos, state);
    }

    // The default onDataPacket() will call load() to load the data from the packet.
    // In addition to that we send a block update to the client
    // and also request a model data update (for the cable baked model)
    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {
        super.onDataPacket(net, packet);

        if (level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            requestModelDataUpdate();
        }
    }

    // getUpdatePacket() is called on the server when a block is placed or updated.
    // It should return a packet containing all information needed to render this block on the client.
    // In our case this is the block mimic information. On the client side onDataPacket() is called
    // with this packet.
    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag nbtTag = new CompoundTag();
        saveFacade(nbtTag);
        return ClientboundBlockEntityDataPacket.create(this, (BlockEntity entity) -> {return nbtTag;});
    }

    // getUpdateTag() is called on the server on initial load of the chunk. It will cause
    // the packet to be sent to the client and handleUpdateTag() will be called on the client.
    // The default implementation of handleUpdateTag() will call load() to load the data from the packet.
    // In our case this is sufficient
    @Nonnull
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag updateTag = super.getUpdateTag();
        saveFacade(updateTag);
        return updateTag;
    }

    public @Nullable BlockState getFacadeBlock() {
        return facadeBlock;
    }

    // This is used to build the model data for the cable baked model.
    @Nonnull
    @Override
    public ModelData getModelData() {
        return ModelData.builder()
                .with(CableBlock.FACADEID, facadeBlock)
                .build();
    }


    public void setFacadeBlock(BlockState facadeBlock) {
        this.facadeBlock = facadeBlock;
        setChanged();
        getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS + Block.UPDATE_NEIGHBORS);
    }

    @Override
    public void load(CompoundTag tagCompound) {
        super.load(tagCompound);
        loadFacade(tagCompound);
    }

    private void loadFacade(CompoundTag tagCompound) {
        if (tagCompound.contains(FACADE_TAG)) {
            facadeBlock = NbtUtils.readBlockState(BuiltInRegistries.BLOCK.asLookup(), tagCompound.getCompound(FACADE_TAG));
        } else {
            facadeBlock = null;
        }
    }

    @Override
    public void saveAdditional(@Nonnull CompoundTag tagCompound) {
        super.saveAdditional(tagCompound);
        saveFacade(tagCompound);
    }

    private void saveFacade(@NotNull CompoundTag tagCompound) {
        if (facadeBlock != null) {
            CompoundTag tag = NbtUtils.writeBlockState(facadeBlock);
            tagCompound.put(FACADE_TAG, tag);
        }
    }
}
