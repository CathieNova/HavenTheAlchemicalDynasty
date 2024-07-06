package net.cathienova.havenalchemy.util;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

public class RestrictedOutputSlotHandler implements IItemHandler {
    private final IItemHandler baseHandler;
    private final int restrictedSlot;

    public RestrictedOutputSlotHandler(IItemHandler baseHandler, int restrictedSlot) {
        this.baseHandler = baseHandler;
        this.restrictedSlot = restrictedSlot;
    }

    @Override
    public int getSlots() {
        return baseHandler.getSlots();
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        return baseHandler.getStackInSlot(slot);
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        return baseHandler.insertItem(slot, stack, simulate);
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        ItemStack restrictedStack = baseHandler.getStackInSlot(restrictedSlot);
        ItemStack currentStack = baseHandler.getStackInSlot(slot);

        // If the restricted slot is empty, allow items to be extracted from other slots
        if (restrictedStack.isEmpty()) {
            return baseHandler.extractItem(slot, amount, simulate);
        }

        // Prevent extraction from the restricted slot itself
        if (slot == restrictedSlot) {
            return ItemStack.EMPTY;
        }

        // Allow extraction if the item in the current slot matches the item in the restricted slot
        if (!ItemStack.isSameItemSameTags(restrictedStack, currentStack)) {
            return ItemStack.EMPTY;
        }

        return baseHandler.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return baseHandler.getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return baseHandler.isItemValid(slot, stack);
    }
}
