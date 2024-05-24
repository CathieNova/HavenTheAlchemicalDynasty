package net.cathienova.havenalchemy.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {

    public static Item item(ResourceLocation id) {
        return ForgeRegistries.ITEMS.getValue(id);
    }

    public static boolean isEqual(Item item, Item item2) {
        return item == item2;
    }

    public static boolean isOf(ItemStack stack, Item item) {
        return stack.is(item);
    }

    public static boolean isIn(ItemStack stack, TagKey<Item> tagKey) {
        return stack.is(tagKey);
    }

    public static boolean isIn(Item item, TagKey<Item> tagKey) {
        return item.builtInRegistryHolder().is(tagKey);
    }

    public static boolean isExist(ResourceLocation id) {
        return ForgeRegistries.ITEMS.containsKey(id);
    }

    public static ResourceLocation toID(Item item) {
        return ForgeRegistries.ITEMS.getKey(item);
    }

    public static Item fromId(ResourceLocation id) {
        return ForgeRegistries.ITEMS.getValue(id);
    }

    public static List<String> getItemsAsString(Player player) {
        CompoundTag playerNbt = player.saveWithoutId(new CompoundTag());
        CompoundTag items = new CompoundTag();

        if (playerNbt.contains("havenalchemy")) {
            CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
            if (havenAlchemyTag.contains("registered_items")) {
                items = havenAlchemyTag.getCompound("registered_items");
            }
        }
        if (items.isEmpty()) return new ArrayList<>();
        return new ArrayList<>(items.getAllKeys());
    }

    public static List<Item> getItems(Player player) {
        List<Item> items = new ArrayList<>();
        for (String id : getItemsAsString(player)) {
            items.add(ItemUtil.fromId(new ResourceLocation(id)));
        }

        return items;
    }

    public static void setItems(Player player, List<Item> items) {
        List<String> ids = new ArrayList<>();
        for (Item item : items) {
            ids.add(ItemUtil.toID(item).toString());
        }
        setItemsForString(player, ids);
    }

    public static void setItemsForString(Player player, List<String> list) {
        CompoundTag playerNbt = player.saveWithoutId(new CompoundTag());
        CompoundTag items = new CompoundTag();
        for (String id : list) {
            items.putBoolean(id, true);
        }

        if (playerNbt.contains("havenalchemy")) {
            CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
            havenAlchemyTag.put("registered_items", items);
            playerNbt.put("havenalchemy", havenAlchemyTag);
        } else {
            CompoundTag havenAlchemyTag = new CompoundTag();
            havenAlchemyTag.put("registered_items", items);
            playerNbt.put("havenalchemy", havenAlchemyTag);
        }
        player.load(playerNbt);
    }

    public static int count(Player player) {
        return getItemsAsString(player).size();
    }

    public static void add(Player player, String id) {
        List<String> ids = getItemsAsString(player);
        ids.add(id);
        setItemsForString(player, ids);
    }

    public static void add(Player player, Item item) {
        List<Item> items = getItems(player);
        items.add(item);
        setItems(player, items);
    }

    public static void remove(Player player, String id) {
        List<String> ids = getItemsAsString(player);
        ids.remove(id);
        setItemsForString(player, ids);
    }

    public static void remove(Player player, Item item) {
        List<Item> items = getItems(player);
        items.remove(item);
        setItems(player, items);
    }

    public static List<Item> getAllItems() {
        return new ArrayList<>(ForgeRegistries.ITEMS.getValues());
    }
}
