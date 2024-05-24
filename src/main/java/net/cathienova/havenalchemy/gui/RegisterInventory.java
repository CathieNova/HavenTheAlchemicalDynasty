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
            CompoundTag playerNbt = player.getPersistentData();
            CompoundTag items = new CompoundTag();

            if (playerNbt.contains("havenalchemy")) {
                CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
                if (havenAlchemyTag.contains("registered_items")) {
                    items = havenAlchemyTag.getCompound("registered_items");
                }
            }

            String itemId = ItemUtil.toID(stack.getItem()).toString();
            if (!items.contains(itemId)) {
                items.putBoolean(itemId, true);
                if (player.containerMenu instanceof AlchemicalTransmutationMenu menu) {
                    menu.updateExtractInventory();
                }
            }

            CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
            havenAlchemyTag.put("registered_items", items);
            playerNbt.put("havenalchemy", havenAlchemyTag);

            if (slot == 50) {
                EMCSystem.IncreaseEmcToPlayer(player, stack);
                stack = ItemStack.EMPTY;
            }
        }
        super.setStackInSlot(slot, stack);
    }
}
