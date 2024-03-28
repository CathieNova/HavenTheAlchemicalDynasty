package net.cathienova.havenalchemy.gui;

import net.cathienova.havenalchemy.util.EMCSystem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class RegisterInventory extends Inventory
{
    public Player player;

    public RegisterInventory(Player player) {
        super(player);
        this.player = player;

    }

    @Override
    public void setItem(int slot, ItemStack stack)
    {
        if (!stack.isEmpty()) {

            CompoundTag playerNbt = new CompoundTag();
            player.deserializeNBT(playerNbt);
            CompoundTag items = new CompoundTag();

            if (playerNbt.contains("havenalchemy")) {
                CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
                if (havenAlchemyTag.contains("registered_items")) {
                    items = havenAlchemyTag.getCompound("registered_items");
                }
            }

            /*if (!items.contains(ItemUtil.toID(stack.getItem()).toString())) {
                items.putBoolean(ItemUtil.toID(stack.getItem()).toString(), true);
                if (player. instanceof AlchemicalTableScreen screenHandler) {
                    screenHandler.extractInventory.placeExtractSlots();
                }
            }*/

            if (playerNbt.contains("havenalchemy")) {
                CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
                havenAlchemyTag.put("registered_items", items);
            } else {
                CompoundTag havenAlchemyTag = new CompoundTag();
                havenAlchemyTag.put("registered_items", items);
                playerNbt.put("havenalchemy", havenAlchemyTag);
            }
            player.serializeNBT();

            if (slot == 50) {
                EMCSystem.writeEmcToPlayer(player, stack);
                stack = ItemStack.EMPTY;
            }
        }
        super.setItem(slot, stack);
    }
}

