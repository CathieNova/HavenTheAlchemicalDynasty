package net.cathienova.havenalchemy.networking.packet;

import net.cathienova.havenalchemy.screen.AlchemicalTransmutationMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SearchPacket {
    private final String searchText;

    public SearchPacket(String searchText) {
        this.searchText = searchText;
    }

    public SearchPacket(FriendlyByteBuf buf) {
        this.searchText = buf.readUtf(32767);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.searchText);
    }

    public static void handle(SearchPacket message, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null && player.containerMenu instanceof AlchemicalTransmutationMenu menu) {
                System.out.println("Handling SearchPacket for player: " + player.getName().getString() + " with searchText: " + message.searchText);
                menu.setSearchText(message.searchText);
                menu.sortBySearch();
            }
        });
        context.setPacketHandled(true);
    }
}
