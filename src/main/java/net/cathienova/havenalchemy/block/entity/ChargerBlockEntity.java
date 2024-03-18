package net.cathienova.havenalchemy.block.entity;

import net.cathienova.havenalchemy.util.AdaptedEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ChargerBlockEntity extends BlockEntity {

    public static final String ENERGY_TAG = "Energy";

    public static final int MAXTRANSFER = 64;
    public static final int CAPACITY = 64000;

    private final EnergyStorage energy = createEnergyStorage();
    private final LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> new AdaptedEnergyStorage(energy) {
        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            setChanged();
            return super.extractEnergy(maxExtract, simulate);
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            setChanged();
            return super.receiveEnergy(maxReceive, simulate);
        }

        @Override
        public boolean canExtract() {
            return true;
        }

        @Override
        public boolean canReceive() {
            return true;
        }
    });

    public ChargerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.charger_block_entity.get(), pos, state);
    }

    public void tickServer() {
        if (level != null && !level.isClientSide && energy.getEnergyStored() > 0) {
            for (Direction direction : Direction.values()) {
                BlockPos adjacentPos = worldPosition.relative(direction);
                BlockEntity adjacentEntity = level.getBlockEntity(adjacentPos);

                if (adjacentEntity != null) {
                    adjacentEntity.getCapability(ForgeCapabilities.ENERGY, direction.getOpposite()).ifPresent(energyStorage -> {
                        if (energyStorage.canReceive()) {
                            int energyExtracted = energy.extractEnergy(MAXTRANSFER, true); // Simulate
                            if (energyExtracted > 0) {
                                int energyAccepted = energyStorage.receiveEnergy(energyExtracted, false);
                                energy.extractEnergy(energyAccepted, false); // Actually extract
                                setChanged();
                            }
                        }
                    });
                }
            }

            // Update block state logic remains unchanged
            boolean powered = energy.getEnergyStored() > 0;
            if (powered != getBlockState().getValue(BlockStateProperties.POWERED)) {
                level.setBlockAndUpdate(worldPosition, getBlockState().setValue(BlockStateProperties.POWERED, powered));
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put(ENERGY_TAG, energy.serializeNBT());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains(ENERGY_TAG)) {
            energy.deserializeNBT(tag.get(ENERGY_TAG));
        }
    }

    @Nonnull
    private EnergyStorage createEnergyStorage() {
        return new EnergyStorage(CAPACITY, MAXTRANSFER, MAXTRANSFER);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            return energyHandler.cast();
        } else {
            return super.getCapability(cap, side);
        }
    }
}
