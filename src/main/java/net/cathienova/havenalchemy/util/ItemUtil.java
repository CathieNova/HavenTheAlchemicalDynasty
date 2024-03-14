package net.cathienova.havenalchemy.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
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

    public static List<Item> getAllItems() {
        List<Item> items = new ArrayList<>(ForgeRegistries.ITEMS.getValues());
        return items;
    }
}
