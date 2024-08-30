package net.cathienova.havenalchemy.screen;

import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.block.entity.AlchemicalTransmutationBlockEntity;
import net.cathienova.havenalchemy.capabilities.EmcHandler;
import net.cathienova.havenalchemy.capabilities.IEmcHandler;
import net.cathienova.havenalchemy.capabilities.PlayerEmcProvider;
import net.cathienova.havenalchemy.gui.RegisterInventory;
import net.cathienova.havenalchemy.networking.ModMessages;
import net.cathienova.havenalchemy.networking.packet.LearnedItemsSyncPacket;
import net.cathienova.havenalchemy.util.EMCSystem;
import net.cathienova.havenalchemy.util.ExtractInventory;
import net.cathienova.havenalchemy.util.ItemUtil;
import net.cathienova.havenalchemy.util.RemoveSlot;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AlchemicalTransmutationMenu extends AbstractContainerMenu {
    public final AlchemicalTransmutationBlockEntity blockEntity;
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
        updateExtractInventory();
    }

    private void addCustomSlots(IItemHandler handler) {
        addRegisterSlot(registerInventory, 50, 105, 120); // Add Recipe & Consume
        addSlot(new RemoveSlot(otherInventory, 51, 87, 120, player, this)); // Recipe Removal
        addRegisterSlot(registerInventory, 52, 41, 19); // Register Items
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
        addExtractSlot(handler, 64, 144, 13); // Get Items
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
        this.addSlot(new SlotItemHandler(handler, index, x, y) {
            @Override
            public void setChanged() {
                super.setChanged();
                if (index == 50) {
                    try {
                        handleEMCAndNBTUpdates();
                    } catch (StackOverflowError e) {
                        System.err.println("StackOverflowError while updating NBT data.");
                    }
                }
            }
        });
    }

    private void handleEMCAndNBTUpdates() {
        if (blockEntity != null) {
            // Calculate EMC value
            long emcValue = blockEntity.calculateEMC();
            EMCSystem.IncrementEmc(player, emcValue);

            //CompoundTag playerData = player.getPersistentData();
            //CompoundTag havenAlchemyTag = playerData.getCompound("havenalchemy");

            //havenAlchemyTag.putLong("emc", emcValue);

            //playerData.put("havenalchemy", havenAlchemyTag);
        }
    }

    private void addExtractSlot(IItemHandler handler, int index, int x, int y) {
        this.addSlot(new SlotItemHandler(handler, index, x, y) {
            @Override
            public boolean mayPickup(Player playerIn) {
                ItemStack stack = getItem();
                long emc = EMCSystem.GetEmc(stack.getItem()) * stack.getCount();
                return EMCSystem.GetEMCFromPlayer(playerIn) >= emc;
            }

            @Override
            public void onTake(@NotNull Player player, @NotNull ItemStack stack) {
                long emc = EMCSystem.GetEmc(stack.getItem()) * stack.getCount();
                EMCSystem.DecreaseEmcToPlayer(player, stack);
                super.onTake(player, stack);
                updateExtractInventory();
            }

            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
        });
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
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
            if (index >= 64 && index <= 75) {
                EMCSystem.DecreaseEmcToPlayer(playerIn, itemstack1);
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.alchemical_transmutation.get());
    }

    public long getStoredEMC() {
        return EMCSystem.GetEMCFromPlayer(player);
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
        sortBySearch();
    }

    public void sortItemsByEmc() {
        List<ItemStack> allItems = getLearnedItems();

        if (!searchText.isEmpty()) {
            allItems = allItems.stream()
                    .filter(itemStack -> itemStack.getHoverName().getString().toLowerCase().contains(searchText.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Ensure no infinite loop in sorting
        allItems.sort((itemStack1, itemStack2) -> {
            long emc1 = EMCSystem.GetEmc(itemStack1.getItem());
            long emc2 = EMCSystem.GetEmc(itemStack2.getItem());
            return Long.compare(emc2, emc1);
        });

        // Set the sorted items in the ExtractInventory
        extractInventory.setSortedItems(allItems);
    }

    public void sortBySearch() {
        if (searchText.isEmpty()) {
            sortItemsByEmc();
            return;
        }

        CompoundTag playerNbt = player.getPersistentData();
        if (playerNbt.contains("havenalchemy")) {
            CompoundTag items = player.getPersistentData();
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

            updateExtractInventory(); // Ensure this method is implemented to refresh the extract slots
        }

        sortItemsByEmc();
    }

    public void nextExtractSlots() {
        int totalItems = extractInventory.getSortedItems().size();
        int itemsPerPage = 12;
        if (totalItems <= itemsPerPage * (index + 1)) return;
        index++;
        updateExtractInventory();
    }

    public void prevExtractSlots() {
        if (index <= 0) return;
        index--;
        updateExtractInventory();
    }

    public void updateExtractInventory() {
        extractInventory.setSortedItems(extractInventory.getSortedItems());
    }

    public List<ItemStack> getLearnedItems() {
        List<ItemStack> learnedItems = new ArrayList<>();
        CompoundTag playerNbt = player.getPersistentData();
        if (playerNbt.contains("havenalchemy")) {
            CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
            if (havenAlchemyTag.contains("registered_items")) {
                CompoundTag items = havenAlchemyTag.getCompound("registered_items");
                for (String key : items.getAllKeys()) {
                    ResourceLocation id = new ResourceLocation(key);
                    ItemStack stack = new ItemStack(ItemUtil.fromId(id));
                    learnedItems.add(stack);
                }
            }
        }
        return learnedItems;
    }

    public void syncLearnedItemsToClient() {
        if (player instanceof ServerPlayer serverPlayer) {
            List<ItemStack> learnedItems = getLearnedItems();
            CompoundTag tag = new CompoundTag();
            ListTag itemsList = new ListTag();

            for (ItemStack stack : learnedItems) {
                CompoundTag itemTag = new CompoundTag();
                stack.save(itemTag);
                itemsList.add(itemTag);
            }

            tag.put("registered_items", itemsList);

            // Send the packet to the client
            ModMessages.sendToPlayer(new LearnedItemsSyncPacket(tag), serverPlayer);
        }
    }
}
