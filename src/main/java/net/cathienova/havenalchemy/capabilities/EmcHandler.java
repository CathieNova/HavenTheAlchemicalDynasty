package net.cathienova.havenalchemy.capabilities;

import net.minecraft.nbt.CompoundTag;

public class EmcHandler implements IEmcHandler {

    private long stored_emc = 0L;

    @Override
    public void storeEMC(long emc) {
        stored_emc += emc;
    }
    @Override
    public void takeEMC(long emc) {
        stored_emc -= emc;
    }

    @Override
    public long getEMC() {
        return stored_emc;
    }

    public void saveNBTData(CompoundTag tag) {
        tag.putLong("emc", stored_emc);
    }

    public void loadNBTData(CompoundTag tag) {
        stored_emc = tag.getLong("emc");
    }
}
