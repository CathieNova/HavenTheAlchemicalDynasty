package net.cathienova.havenalchemy.cables.blocks;

import net.cathienova.havenalchemy.block.entity.ModBlockEntities;
import net.cathienova.havenalchemy.util.AdaptedEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;

public class CableBlockEntity extends BlockEntity {

    public static final String ENERGY_TAG = "Energy";

    public static final int MAXTRANSFER = 128;
    public static final int CAPACITY = 1000;
    private Set<BlockPos> nonCableOutputs = new HashSet<>();
    private LinkedList<BlockPos> cableOutputs = new LinkedList<>();
    private final EnergyStorage energy = createEnergyStorage();
    private final LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> new AdaptedEnergyStorage(energy) {
        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            if (maxExtract > MAXTRANSFER) maxExtract = MAXTRANSFER;
            setChanged();
            return super.extractEnergy(maxExtract, simulate);
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            if (maxReceive > MAXTRANSFER) maxReceive = MAXTRANSFER;
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

    // Cached outputs
    private Set<BlockPos> outputs = null;

    protected CableBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public CableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.cable_block_entity.get(), pos, state);
    }

    public void tickServer() {
        if (energy.getEnergyStored() > 0) {
            checkOutputs();
            distributeEnergy();
        }
    }

    private void checkOutputs() {
        nonCableOutputs.clear();
        cableOutputs.clear();
        traverse(worldPosition, cable -> {
            for (Direction direction : Direction.values()) {
                BlockPos pos = cable.getBlockPos().relative(direction);
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity != null) {
                    boolean canReceive = blockEntity.getCapability(ForgeCapabilities.ENERGY, direction.getOpposite()).map(handler -> handler.canReceive()).orElse(false);
                    if (canReceive) {
                        if (blockEntity instanceof CableBlockEntity) {
                            cableOutputs.add(pos);
                        } else {
                            nonCableOutputs.add(pos);
                        }
                    }
                }
            }
        });
    }

    private void distributeEnergy() {
        // First, try to equally distribute energy to non-cable outputs.
        boolean energyRemaining = distributeEqually(nonCableOutputs);

        // If there's still energy left, proceed to distribute to cables, starting from the furthest.
        if (energyRemaining) {
            distributeToCables();
        }
    }

    private boolean distributeEqually(Set<BlockPos> outputs) {
        if (outputs.isEmpty() || energy.getEnergyStored() == 0) {
            return false;
        }

        // Calculate the amount of energy to attempt to distribute to each output evenly.
        int totalEnergyAvailable = energy.getEnergyStored();
        int energyPerOutput = totalEnergyAvailable / outputs.size();

        if (energyPerOutput == 0) {
            return false; // Not enough energy to distribute evenly.
        }

        boolean energyDistributed = false;
        for (BlockPos pos : outputs) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity != null) {
                LazyOptional<IEnergyStorage> capability = entity.getCapability(ForgeCapabilities.ENERGY);
                if (capability.isPresent()) {
                    IEnergyStorage handler = capability.orElseThrow(AssertionError::new);
                    if (handler.canReceive()) {
                        // Distribute only up to the calculated per-output amount, or the max transfer rate, whichever is smaller.
                        int energyToDistribute = Math.min(energyPerOutput, MAXTRANSFER);
                        int received = handler.receiveEnergy(energyToDistribute, false);
                        if (received > 0) {
                            energy.extractEnergy(received, false);
                            energyDistributed = true;
                        }
                    }
                }
            }
        }

        // Return true if there's still energy left after attempting to distribute.
        return energy.getEnergyStored() > 0 && energyDistributed;
    }

    private boolean distributeTo(Collection<BlockPos> outputs) {
        boolean energyDistributed = false;

        for (BlockPos pos : outputs) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity != null) {
                LazyOptional<IEnergyStorage> capability = entity.getCapability(ForgeCapabilities.ENERGY);
                if (capability.isPresent()) {
                    IEnergyStorage handler = capability.orElseThrow(AssertionError::new);
                    if (handler.canReceive()) {
                        int energyToDistribute = Math.min(energy.getEnergyStored(), MAXTRANSFER);
                        int received = handler.receiveEnergy(energyToDistribute, false);
                        if (received > 0) {
                            energy.extractEnergy(received, false);
                            energyDistributed = true;
                            if (energy.getEnergyStored() == 0) {
                                break; // Stop if there's no energy left to distribute
                            }
                        }
                    }
                }
            }
        }

        // Return true if there's still energy left after attempting to distribute to this set of outputs
        return energy.getEnergyStored() > 0;
    }

    private void distributeToCables() {
        // Iterate through cables in reverse order to attempt filling from the furthest first
        List<BlockPos> reversedCableOutputs = new ArrayList<>(cableOutputs);
        Collections.reverse(reversedCableOutputs);

        distributeTo(reversedCableOutputs);
    }

    public void markDirty() {
        traverse(worldPosition, cable -> cable.outputs = null);
    }

    // This is a generic function that will traverse all cables connected to this cable
    // and call the given consumer for each cable.
    private void traverse(BlockPos pos, Consumer<CableBlockEntity> consumer) {
        Set<BlockPos> traversed = new HashSet<>();
        traversed.add(pos);
        consumer.accept(this);
        traverse(pos, traversed, consumer);
    }

    private void traverse(BlockPos pos, Set<BlockPos> traversed, Consumer<CableBlockEntity> consumer) {
        for (Direction direction : Direction.values()) {
            BlockPos p = pos.relative(direction);
            if (!traversed.contains(p)) {
                traversed.add(p);
                if (level.getBlockEntity(p) instanceof CableBlockEntity cable) {
                    consumer.accept(cable);
                    cable.traverse(p, traversed, consumer);
                }
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
