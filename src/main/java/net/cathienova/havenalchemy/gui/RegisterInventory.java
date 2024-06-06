package net.cathienova.havenalchemy.gui;

import net.cathienova.havenalchemy.screen.AlchemicalTransmutationMenu;
import net.cathienova.havenalchemy.util.EMCSystem;
import net.cathienova.havenalchemy.util.ItemUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class RegisterInventory extends ItemStackHandler {
    private final Player player;

    public RegisterInventory(Player player, int size) {
        super(size);
        this.player = player;
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        if (!stack.isEmpty()) {
            addOrUpdateLearnedItem(player, stack);

            if (slot == 50) {
                EMCSystem.IncreaseEmcToPlayer(player, stack);
                stack = ItemStack.EMPTY;
            }
        }
        super.setStackInSlot(slot, stack);

        // Debug logging
        System.out.println("Stack set in slot " + slot + ": " + stack);
    }

    // In RegisterInventory.java

    public void addOrUpdateLearnedItem(Player player, ItemStack stack) {
        CompoundTag playerNbt = player.getPersistentData();
        CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
        CompoundTag items = havenAlchemyTag.getCompound("registered_items");

        String itemId = ItemUtil.toID(stack.getItem()).toString();
        if (!items.contains(itemId)) {
            items.putBoolean(itemId, true);
        }

        havenAlchemyTag.put("registered_items", items);
        playerNbt.put("havenalchemy", havenAlchemyTag);

        if (player.containerMenu instanceof AlchemicalTransmutationMenu menu) {
            menu.updateExtractInventory();
            menu.syncLearnedItemsToClient(); // Sync learned items to client
        }

        // Debug logging
        System.out.println("Learned items updated: " + items);
    }

}
