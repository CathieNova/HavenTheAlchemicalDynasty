package net.cathienova.havenalchemy.util;

import net.cathienova.havenalchemy.networking.ModMessages;
import net.cathienova.havenalchemy.screen.AlchemicalTransmutationMenu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtractInventory extends SimpleContainer {
    public Player player;
    public boolean isSettingStack = true;
    @Nullable
    public AlchemicalTransmutationMenu screenHandler;

    public ExtractInventory(int size, Player player, @Nullable AlchemicalTransmutationMenu screenHandler) {
        super(size);
        this.screenHandler = screenHandler;
        this.player = player;
        placeExtractSlots();
    }

    public void placeExtractSlots() {
        if (player.level().isClientSide()) return;

        isSettingStack = true;

        definedStacks.clear();
        int index = screenHandler != null ? screenHandler.index : 0;

        CompoundTag nbtTag = new CompoundTag();
        player.saveWithoutId(nbtTag);

        CompoundTag items = new CompoundTag();

        if (nbtTag.contains("havenalchemy")) {
            CompoundTag havenAlchemyTag = nbtTag.getCompound("havenalchemy");
            if (havenAlchemyTag.contains("registered_items")) {
                items = havenAlchemyTag.getCompound("registered_items");
            }
        }

        List<String> keys = new ArrayList<>(items.getAllKeys());

        if (!keys.isEmpty()) {
            for (int i = 0; i < 12; i++) {
                int id_index = i + (index * 12);
                if (keys.size() < id_index + 1) {
                    setItem(i + 64, ItemStack.EMPTY);
                    continue;
                }

                ResourceLocation id = new ResourceLocation(keys.get(id_index));
                if (!ForgeRegistries.ITEMS.containsKey(id)) continue;
                ItemStack itemStack = new ItemStack(ItemUtil.fromId(id), 1);
                setItem(i + 64, itemStack);
            }
        }
        isSettingStack = false;

        // Log for debugging
        System.out.println("Placed extract slots for player: " + player.getName().getString());
    }


    public Map<Integer, ItemStack> definedStacks = new HashMap<>();

    @Override
    public void setItem(int slot, ItemStack stack) {
        ItemStack definedStack = definedStacks.get(slot);
        if (!stack.isEmpty() && !definedStacks.containsKey(slot)) {
            definedStack = stack.copy();
            definedStacks.put(slot, definedStack);
        }

        super.setItem(slot, stack);
        if (!isSettingStack) {
            super.setItem(slot, stack);
            if (definedStack != null && stack.isEmpty()) {
                EMCSystem.decrementEmc(player, EMCSystem.GetEmc(definedStack));
                super.setItem(slot, definedStack.copy());
            }
        }
    }
}
