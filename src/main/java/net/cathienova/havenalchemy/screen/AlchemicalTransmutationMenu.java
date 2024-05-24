package net.cathienova.havenalchemy.screen;

import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.block.entity.AlchemicalTransmutationBlockEntity;
import net.cathienova.havenalchemy.gui.RegisterInventory;
import net.cathienova.havenalchemy.util.EMCSystem;
import net.cathienova.havenalchemy.util.ExtractInventory;
import net.cathienova.havenalchemy.util.ItemUtil;
import net.cathienova.havenalchemy.util.RemoveSlot;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AlchemicalTransmutationMenu extends AbstractContainerMenu {
    private final AlchemicalTransmutationBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;
    private final SimpleContainer otherInventory = new SimpleContainer(52);
    private final Player player;
    private IItemHandler handler;
    private final RegisterInventory registerInventory;
    public ExtractInventory extractInventory;
    public String searchText = "";
    public int index = 0;

    public AlchemicalTransmutationMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, (AlchemicalTransmutationBlockEntity) Objects.requireNonNull(inv.player.level().getBlockEntity(extraData.readBlockPos())),
                new RegisterInventory(inv.player, 64));
    }

    public AlchemicalTransmutationMenu(int containerId, Inventory playerInventory, AlchemicalTransmutationBlockEntity blockEntity, RegisterInventory registerInventory) {
        super(ModMenuTypes.ALCHEMICAL_TRANSMUTATION_MENU.get(), containerId);
        this.blockEntity = blockEntity;
        this.level = playerInventory.player.level();
        this.data = new SimpleContainerData(2);
        this.player = playerInventory.player;
        this.extractInventory = new ExtractInventory(12 + 80, player, this);
        this.registerInventory = registerInventory;

        // Add player inventory slots
        addPlayerMainInventorySlots(playerInventory, 24, 140);
        addPlayerHotbarSlots(playerInventory, 24, 198);

        // Add custom slots
        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(h -> {
            this.handler = h;
            addCustomSlots(h);
        });

        // Register the data container
        addDataSlots(data);
        setSearchText("");
    }

    private void addCustomSlots(IItemHandler handler) {
        addRegisterSlot(registerInventory, 50, 105, 120); //Add Recipe & Consume
        addSlot(new RemoveSlot(otherInventory, 51, 87, 120, player, this)); //Recipe Removal
        addRegisterSlot(registerInventory, 52, 41, 19); //Register Items
        addRegisterSlot(registerInventory, 53, 15, 30);
        addRegisterSlot(registerInventory, 54, 66, 30);
        addRegisterSlot(registerInventory, 55, 28, 41);
        addRegisterSlot(registerInventory, 56, 51, 41);
        addRegisterSlot(registerInventory, 57, 6, 54);
        addRegisterSlot(registerInventory, 58, 27, 67);
        addRegisterSlot(registerInventory, 59, 54, 67);
        addRegisterSlot(registerInventory, 60, 74, 54);
        addRegisterSlot(registerInventory, 61, 15, 79);
        addRegisterSlot(registerInventory, 62, 66, 79);
        addRegisterSlot(registerInventory, 63, 41, 89);
        addExtractSlot(handler, 64, 144, 13); //Get Items
        addExtractSlot(handler, 65, 114, 25);
        addExtractSlot(handler, 66, 174, 25);
        addExtractSlot(handler, 67, 144, 33);
        addExtractSlot(handler, 68, 102, 55);
        addExtractSlot(handler, 69, 122, 55);
        addExtractSlot(handler, 70, 166, 55);
        addExtractSlot(handler, 71, 186, 55);
        addExtractSlot(handler, 72, 144, 76);
        addExtractSlot(handler, 73, 114, 85);
        addExtractSlot(handler, 74, 174, 85);
        addExtractSlot(handler, 75, 144, 97);
    }

    private void addPlayerMainInventorySlots(Inventory playerInventory, int x, int y) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, x + col * 18, y + row * 18));
            }
        }
    }

    private void addPlayerHotbarSlots(Inventory playerInventory, int x, int y) {
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, x + col * 18, y));
        }
    }

    private void addRegisterSlot(IItemHandler handler, int index, int x, int y) {
        this.addSlot(new SlotItemHandler(handler, index, x, y));
    }

    private void addExtractSlot(IItemHandler handler, int index, int x, int y) {
        this.addSlot(new SlotItemHandler(handler, index, x, y));
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            int containerSize = handler.getSlots();
            if (index < containerSize) {
                if (!this.moveItemStackTo(itemstack1, containerSize, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, containerSize, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            // Check if the item is from the extract slots
            if (index >= 12 && index <= 23) {
                EMCSystem.DecreaseEmcToPlayer(playerIn, itemstack1);
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.alchemical_transmutation.get());
    }

    public long getStoredEMC() {
        return EMCSystem.GetEMCFromPlayer(player);
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void sortBySearch() {
        if (searchText.isEmpty()) return;

        CompoundTag playerNbt = player.saveWithoutId(new CompoundTag());
        if (playerNbt.contains("havenalchemy")) {
            CompoundTag copy = playerNbt.copy();
            CompoundTag items = new CompoundTag();
            CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
            if (havenAlchemyTag.contains("registered_items")) {
                items = havenAlchemyTag.getCompound("registered_items");
            }

            List<String> ids = new ArrayList<>(items.getAllKeys());

            for (String id : ids) {
                ItemStack itemStack = new ItemStack(ItemUtil.fromId(new ResourceLocation(id)));
                if (!id.contains(searchText) && !itemStack.getHoverName().getString().contains(searchText)) {
                    if (EMCSystem.GetEMCFromPlayer(player) < EMCSystem.GetEmc(itemStack.getItem())) {
                        items.remove(id);
                    }
                }
            }

            havenAlchemyTag.put("registered_items", items);
            playerNbt.put("havenalchemy", havenAlchemyTag);

            player.load(playerNbt);
            updateExtractInventory(); // Ensure this method is implemented to refresh the extract slots
            player.load(copy);
        }
    }

    public void nextExtractSlots() {
        if (ItemUtil.count(extractInventory.player) < 12 * (index + 1) + 1) return;
        index++;
        if (searchText.isEmpty())
            extractInventory.placeExtractSlots();
        else
            sortBySearch();
    }

    public void prevExtractSlots() {
        if (index <= 0) return;
        index--;
        if (searchText.isEmpty())
            extractInventory.placeExtractSlots();
        else
            sortBySearch();
    }

    public void updateExtractInventory() {
        extractInventory.placeExtractSlots();
    }
}