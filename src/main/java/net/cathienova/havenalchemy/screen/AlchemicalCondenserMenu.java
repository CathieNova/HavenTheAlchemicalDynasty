package net.cathienova.havenalchemy.screen;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.block.entity.AlchemicalCondenserBlockEntity;
import net.cathienova.havenalchemy.util.EMCSystem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class AlchemicalCondenserMenu extends AbstractContainerMenu
{
    public final AlchemicalCondenserBlockEntity blockEntity;
    private final Level level;
    private ContainerData data;
    public long storedEMC = 0;
    public long maxEMC = 10000000;
    private static final int SLOTS = 92;
    public long getTargetEMC() {
        return EMCSystem.GetEmc(getBlockEntity().getTargetStack());
    }

    public AlchemicalCondenserMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(SLOTS));
    }

    public AlchemicalCondenserMenu(int pContainerId, Inventory playerInventory, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.ALCHEMICAL_CONDENSER_MENU.get(), pContainerId);
        checkContainerSize(playerInventory, 36);
        blockEntity = ((AlchemicalCondenserBlockEntity) entity);

        this.level = playerInventory.player.level();
        this.data = data;

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            int index = 0; // Start from index 1 for storage slots
            for (int y = 0; y < 7; ++y) { // 7 rows
                for (int x = 0; x < 13; ++x) { // 13 columns per row
                    if (index < 91) { // Ensure we do not exceed slot 90 (91 slots total)
                        this.addSlot(new SlotItemHandler(iItemHandler, index, -28 + x * 18, -8 + y * 18));
                        index++;
                    }
                }
            }
            addTargetSlot(iItemHandler, 91, -28, -28);
        });

        addDataSlots(data);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack originalStack = slot.getItem();

            // If the item is in one of the player's inventory slots (including hotbar)
            if (index < 35) {
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
                pPlayer, ModBlocks.alchemical_condenser.get());
    }

    private void addPlayerInventory(Inventory playerInventory)
    {
        for (int i = 0; i < 3; ++i)
        {
            for (int l = 0; l < 9; ++l)
            {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 120 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 178));
        }
    }

    protected void addTargetSlot(IItemHandler itemHandler, int index, int x, int y) {
        this.addSlot(new SlotItemHandler(itemHandler, index, x, y));
    }

    protected void addStorageSlots(IItemHandler itemHandler, int firstIndex, int firstX, int firstY, int rows, int cols) {
        for (int y = 0; y < rows; ++y) {
            for (int x = 0; x < cols; ++x) {
                int index = firstIndex + y * cols + x;
                this.addSlot(new SlotItemHandler(itemHandler, index, firstX + x * 18, firstY + y * 18));
            }
        }
    }

    public AlchemicalCondenserBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    public void clicked(int slotIndex, int button, ClickType actionType, Player player)
    {
        if (slotIndex == 91) { // Target Slot
            ItemStack oldStack = getCarried();
            super.clicked(slotIndex, button, actionType, player);
            if (!oldStack.isEmpty()) {
                setCarried(oldStack);
            }
            return;
        }
        super.clicked(slotIndex, button, actionType, player);
    }

    public long getStoredEMC() {
        this.storedEMC = data.get(0);
        return storedEMC;
    }
}
