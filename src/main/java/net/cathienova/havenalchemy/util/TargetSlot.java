package net.cathienova.havenalchemy.util;

import net.cathienova.havenalchemy.screen.AlchemicalCondenserMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class TargetSlot extends Slot
{

    private AlchemicalCondenserMenu screenHandler;

    public TargetSlot(Inventory inventory, int index, int x, int y, AlchemicalCondenserMenu screenHandler) {
        super(inventory, index, x, y);
        this.screenHandler = screenHandler;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return EMCSystem.GetEmc(stack) != 0 || stack.isEmpty();
    }

    @Override
    public void set(ItemStack stack)
    {
        ItemStack newStack = stack.copy();
        newStack.setCount(1);
        super.set(newStack);
    }

    @Override
    public ItemStack remove(int pAmount)
    {
        set(ItemStack.EMPTY);
        return ItemStack.EMPTY;
    }

    public AlchemicalCondenserMenu getScreenHandler() {
        return screenHandler;
    }
}
