package net.cathienova.havenalchemy.util;

import net.cathienova.havenalchemy.block.entity.AlchemicalCondenserBlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class CondenserStorageSlot extends Slot
{
    public CondenserStorageSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack pStack)
    {
        if (container instanceof AlchemicalCondenserBlockEntity tile) {
            return tile.getTargetStack().isEmpty() || tile.getTargetStack().getItem() != pStack.getItem();
        }
        return true;
    }
}
