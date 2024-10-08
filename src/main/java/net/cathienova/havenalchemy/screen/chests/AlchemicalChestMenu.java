package net.cathienova.havenalchemy.screen.chests;

import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.block.chests.AlchemicalChestBlockEntity;
import net.cathienova.havenalchemy.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class AlchemicalChestMenu extends AbstractContainerMenu
{
    public final AlchemicalChestBlockEntity blockEntity;
    private final Level level;
    private ContainerData data;
    private static final int SLOTS = 91;
    private static final int ROWS = 7;
    private static final int COLS = 13;
    private static final int START_X = -28;
    private static final int START_Y = -17;
    private static final int START_PLAYER_INV_X = 8;
    private static final int START_PLAYER_INV_Y = 120;
    private static final int START_PLAYER_HOTBAR_X = 8;
    private static final int START_PLAYER_HOTBAR_Y = 178;

    public AlchemicalChestMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(SLOTS));
    }

    public AlchemicalChestMenu(int pContainerId, Inventory playerInventory, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.ALCHEMICAL_CHEST_MENU.get(), pContainerId);
        checkContainerSize(playerInventory, 36);
        blockEntity = ((AlchemicalChestBlockEntity) entity);

        this.level = playerInventory.player.level();
        this.data = data;

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            int index = 0;
            for (int y = 0; y < ROWS; ++y) {
                for (int x = 0; x < COLS; ++x) {
                    if (index <= SLOTS) { // Ensure we do not exceed slot 91 (92 slots total)
                        this.addSlot(new SlotItemHandler(iItemHandler, index, START_X + x * 18, START_Y + y * 18));
                        index++;
                    }
                }
            }
        });

        addDataSlots(data);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack originalStack = slot.getItem();

            // If the item is in one of the player's inventory slots (including hotbar)
            if (index < 36) {
                // Try to move it to one of the custom slots.
                if (!this.moveItemStackTo(originalStack, 36, 127, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 36 && index < 127) { // If the item is in one of the custom slots
                // Try to move it to the player's inventory.
                if (!this.moveItemStackTo(originalStack, 0, 36, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.alchemical_chest.get());
    }

    private void addPlayerInventory(Inventory playerInventory)
    {
        for (int i = 0; i < 3; ++i)
        {
            for (int l = 0; l < 9; ++l)
            {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, START_PLAYER_INV_X + l * 18, START_PLAYER_INV_Y + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, START_PLAYER_HOTBAR_X + i * 18, START_PLAYER_HOTBAR_Y));
        }
    }

    public AlchemicalChestBlockEntity getBlockEntity() {
        return this.blockEntity;
    }
}
