package net.cathienova.havenalchemy.handler;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class InputSlotHandler implements IItemHandler {
    private final ItemStackHandler internalHandler;

    public InputSlotHandler(ItemStackHandler internalHandler) {
        this.internalHandler = internalHandler;
    }

    @Override
    public int getSlots() {
        // Expose all slots except the first one (slot 0).
        return internalHandler.getSlots() - 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        // Adjust slot index to account for the hidden slot 0.
        return internalHandler.getStackInSlot(slot + 1);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        // Adjust slot index to prevent insertion into slot 0.
        return internalHandler.insertItem(slot + 1, stack, simulate);
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        // Allow extraction from any slot except slot 0.
        return internalHandler.extractItem(slot + 1, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        // Adjust slot index to account for the hidden slot 0.
        return internalHandler.getSlotLimit(slot + 1);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        // Allow any item in any slot except slot 0.
        return internalHandler.isItemValid(slot + 1, stack);
    }
}
