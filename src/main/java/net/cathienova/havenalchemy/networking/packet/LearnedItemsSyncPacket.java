package net.cathienova.havenalchemy.networking.packet;

import net.cathienova.havenalchemy.screen.AlchemicalTransmutationScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static net.cathienova.havenalchemy.util.ExtractInventory.traverseNBT;

public class LearnedItemsSyncPacket {
    private final CompoundTag tag;

    public LearnedItemsSyncPacket(CompoundTag tag) {
        this.tag = tag;
    }

    public LearnedItemsSyncPacket(FriendlyByteBuf buf) {
        this.tag = buf.readNbt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(tag);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            CompoundTag tag = this.tag;
            List<ItemStack> learnedItems = new ArrayList<>();
            ListTag itemsList = tag.getList("registered_items", 10);

            for (int i = 0; i < itemsList.size(); i++) {
                CompoundTag itemTag = itemsList.getCompound(i);
                ItemStack stack = ItemStack.of(itemTag);
                learnedItems.add(stack);
            }

            // Debug logging
            System.out.println("Learned Items received on client: " + learnedItems);

            // Prevent potential recursion by checking depth
            traverseNBT(tag, 0);

            // Update the ExtractInventory with the learned items
            if (AlchemicalTransmutationScreen.currentMenu() != null) {
                AlchemicalTransmutationScreen.currentMenu().extractInventory.setSortedItems(learnedItems);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
