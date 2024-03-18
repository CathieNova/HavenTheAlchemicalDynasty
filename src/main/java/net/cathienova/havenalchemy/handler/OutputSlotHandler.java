package net.cathienova.havenalchemy.handler;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;

public class OutputSlotHandler implements IItemHandler {
    private final ItemStackHandler internalHandler;
    private final int outputSlot;

    public OutputSlotHandler(ItemStackHandler internalHandler, int outputSlot) {
        this.internalHandler = internalHandler;
        this.outputSlot = outputSlot;
    }

    @Override
    public int getSlots() {
        return 1; // Expose only one slot.
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot == 0) {
            return internalHandler.getStackInSlot(outputSlot);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return stack; // Prevent inserting into the output slot through this handler.
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (slot == 0) {
            return internalHandler.extractItem(outputSlot, amount, simulate);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int slot) {
        if (slot == 0) {
            return internalHandler.getSlotLimit(outputSlot);
        }
        return 0;
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return false; // Prevent inserting into the output slot through this handler.
    }
}