package net.cathienova.havenalchemy.capabilities;

import net.cathienova.havenalchemy.util.EMCSystem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class EmcHandler implements IEmcHandler {

    private long stored_emc = 0L;
    private List<ResourceLocation> item_knowledge = new ArrayList<>();

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

    @Override
    public void deconstructItem(ItemStack itemStack) {
        learnItem(itemStack.getItem());
        storeEMC(EMCSystem.GetEmc(itemStack));
    }

    @Override
    public ItemStack constructItem(Item item, int qty) {
        long stackEMC = EMCSystem.GetEmc(item) * qty;
        if(getEMC() < stackEMC) {
            qty = (int) (getEMC() / EMCSystem.GetEmc(item));
            stackEMC = EMCSystem.GetEmc(item) * qty;
        }
        if(qty <= 0) {
            return ItemStack.EMPTY;
        }
        takeEMC(stackEMC);
        return new ItemStack(item, qty);
    }

    @Override
    public boolean learnItem(Item item) {
        if(hasKnowledge(item)) return false;
        item_knowledge.add(ForgeRegistries.ITEMS.getKey(item));
        return true;
    }

    @Override
    public boolean forgetItem(Item item) {
        if(!hasKnowledge(item)) return false;
        item_knowledge.remove(ForgeRegistries.ITEMS.getKey(item));
        return true;
    }

    @Override
    public boolean hasKnowledge(Item item) {
        return item_knowledge.contains(ForgeRegistries.ITEMS.getKey(item));
    }

    public void saveNBTData(CompoundTag tag) {
        tag.putLong("emc", stored_emc);
        tag.putInt("knowledge", item_knowledge.size());

        int index = 0;
        for(ResourceLocation loc : item_knowledge) {
            tag.putString("knowledge-" + index, loc.toString());
            index++;
        }
    }

    public void loadNBTData(CompoundTag tag) {
        stored_emc = tag.getLong("emc");
        List<ResourceLocation> temp = new ArrayList<>();

        int size = tag.getInt("knowledge");
        for(int i = 0; i < size; i++) {
            String rs = tag.getString("knowledge-" + i);
            temp.add(ResourceLocation.tryParse(rs));
        }
        item_knowledge = temp;
    }
}
