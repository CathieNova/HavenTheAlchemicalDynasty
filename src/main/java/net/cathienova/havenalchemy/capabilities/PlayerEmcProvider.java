package net.cathienova.havenalchemy.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerEmcProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final Capability<EmcHandler> EMC_HANDLER = CapabilityManager.get(new CapabilityToken<>() {});

    private EmcHandler emcHandler = null;
    private final LazyOptional<EmcHandler> opt = LazyOptional.of(this::createEMCHandler);

    @NonNull
    private EmcHandler createEMCHandler() {
        if(emcHandler == null) {
            emcHandler = new EmcHandler();
        }
        return emcHandler;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        return getCapability(capability);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == EMC_HANDLER) {
            return opt.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createEMCHandler().saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        createEMCHandler().loadNBTData(tag);
    }
}
