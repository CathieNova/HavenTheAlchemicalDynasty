package net.cathienova.havenalchemy.util;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.screen.AlchemicalTransmutationMenu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ExtractInventory extends SimpleContainer {
    public Player player;
    public boolean isSettingStack = true;
    public AlchemicalTransmutationMenu screenHandler;
    private final List<ItemStack> sortedItems = new ArrayList<>();

    public ExtractInventory(int size, Player player, AlchemicalTransmutationMenu screenHandler) {
        super(size);
        this.screenHandler = screenHandler;
        this.player = player;
        placeExtractSlots();
    }

    public void placeExtractSlots() {
        if (player.level().isClientSide()) return;

        isSettingStack = true;

        CompoundTag playerNbt = player.getPersistentData();
        CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
        CompoundTag items = havenAlchemyTag.getCompound("registered_items");

        List<String> keys = new ArrayList<>(items.getAllKeys());

        for (int i = 0; i < 12; i++) {
            int id_index = i + (screenHandler.index * 12);
            if (keys.size() < id_index + 1) {
                setItem(i + 64, ItemStack.EMPTY);
                continue;
            }

            ResourceLocation id = new ResourceLocation(keys.get(id_index));
            if (!ForgeRegistries.ITEMS.containsKey(id)) continue;
            ItemStack itemStack = new ItemStack(ItemUtil.fromId(id), 1);
            setItem(i + 64, itemStack);
        }
        isSettingStack = false;
    }

    public void setSortedItems(List<ItemStack> sortedItems) {
        this.sortedItems.clear();
        this.sortedItems.addAll(sortedItems);
        updateExtractSlots();

        // Debug logging
        System.out.println("Sorted items set in ExtractInventory: " + this.sortedItems);
    }

    public static void traverseNBT(CompoundTag tag, int depth) {
        if (depth > 100) { // Arbitrary depth limit to prevent stack overflow
            return;
        }
        for (String key : tag.getAllKeys()) {
            Tag value = tag.get(key);
            if (value instanceof CompoundTag) {
                traverseNBT((CompoundTag) value, depth + 1);
            }
        }
    }

    public void updateExtractSlots() {
        isSettingStack = true;

        // Clear existing items
        for (int i = 64; i < 76; i++) {
            this.setItem(i, ItemStack.EMPTY);
        }

        // Set sorted and paginated items
        int start = screenHandler.index * 12;
        for (int i = 0; i < 12; i++) {
            int itemIndex = start + i;
            if (itemIndex < sortedItems.size()) {
                this.setItem(64 + i, sortedItems.get(itemIndex));
            } else {
                this.setItem(64 + i, ItemStack.EMPTY);
            }
        }

        // Debug logging
        /*for (int i = 64; i < 76; i++) {
            System.out.println("Item in slot " + i + ": " + this.getItem(i));
        }*/

        // Traverse the NBT to ensure no cycles
        CompoundTag playerNbt = player.getPersistentData();
        if (playerNbt.contains("havenalchemy")) {
            CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
            traverseNBT(havenAlchemyTag, 0);
        }

        isSettingStack = false;
    }

    public List<ItemStack> getSortedItems() {
        return sortedItems;
    }
}
