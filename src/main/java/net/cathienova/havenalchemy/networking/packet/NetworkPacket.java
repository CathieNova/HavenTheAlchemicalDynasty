package net.cathienova.havenalchemy.networking.packet;

import net.cathienova.havenalchemy.screen.AlchemicalTransmutationMenu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class NetworkPacket {
    private final CompoundTag nbt;

    public NetworkPacket(CompoundTag nbt) {
        this.nbt = nbt;
    }

    public NetworkPacket(FriendlyByteBuf buf) {
        this.nbt = buf.readNbt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(this.nbt);
    }

    public static void handle(NetworkPacket message, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null && player.containerMenu instanceof AlchemicalTransmutationMenu menu) {
                CompoundTag nbt = message.nbt;
                if (nbt.contains("control")) {
                    int pageIndex = nbt.getInt("control");
                    if (pageIndex == 0) {
                        menu.prevExtractSlots();
                    } else if (pageIndex == 1) {
                        menu.nextExtractSlots();
                    }
                }
                menu.broadcastChanges();  // Ensure changes are broadcasted to client
            }
        });
        context.setPacketHandled(true);
    }
}
