package net.cathienova.havenalchemy.util;

import net.cathienova.havenalchemy.screen.AlchemicalTransmutationMenu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class RemoveSlot extends Slot
{
    private final Player player;
    private final AlchemicalTransmutationMenu menu;

    public RemoveSlot(Container container, int index, int x, int y, Player player, AlchemicalTransmutationMenu menu) {
        super(container, index, x, y);
        this.player = player;
        this.menu = menu;
    }

    @Override
    public void set(ItemStack stack) {
        CompoundTag playerTag = player.getPersistentData();
        CompoundTag items = player.getPersistentData();

        if (playerTag.contains("havenalchemy")) {
            CompoundTag havenAlchemyTag = playerTag.getCompound("havenalchemy");
            if (havenAlchemyTag.contains("registered_items")) {
                items = havenAlchemyTag.getCompound("registered_items");
            }
        }

        if (items.contains(stack.getItem().getName(stack).toString())) {
            items.remove(stack.getItem().getName(stack).toString());
            if (player.containerMenu instanceof AlchemicalTransmutationMenu menu) {
                menu.extractInventory.removeItem(0, stack.getCount());
            }
        }

        if (playerTag.contains("havenalchemy")) {
            CompoundTag havenAlchemyTag = playerTag.getCompound("havenalchemy");
            havenAlchemyTag.put("registered_items", items);
        } else {
            CompoundTag havenAlchemyTag = new CompoundTag();
            havenAlchemyTag.put("registered_items", items);
            playerTag.put("havenalchemy", havenAlchemyTag);
        }

        player.addItem(stack.copy());

        if (player.containerMenu instanceof AlchemicalTransmutationMenu menu) {
            menu.extractInventory.removeItem(0, stack.getCount());
        }
        super.set(ItemStack.EMPTY);
    }
}
