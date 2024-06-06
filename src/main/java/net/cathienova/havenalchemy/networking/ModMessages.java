package net.cathienova.havenalchemy.networking;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.networking.packet.EMCSyncS2CPacket;
import net.cathienova.havenalchemy.networking.packet.EnergySyncS2CPacket;
import net.cathienova.havenalchemy.networking.packet.LearnedItemsSyncPacket;
import net.cathienova.havenalchemy.networking.packet.NetworkPacket;
import net.cathienova.havenalchemy.networking.packet.SearchPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    public static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(HavenAlchemy.MOD_ID, "main"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(EnergySyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EnergySyncS2CPacket::new)
                .encoder(EnergySyncS2CPacket::toBytes)
                .consumerMainThread(EnergySyncS2CPacket::handle)
                .add();

        net.messageBuilder(EMCSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EMCSyncS2CPacket::new)
                .encoder(EMCSyncS2CPacket::toBytes)
                .consumerMainThread(EMCSyncS2CPacket::handle)
                .add();

        net.messageBuilder(SearchPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SearchPacket::new)
                .encoder(SearchPacket::toBytes)
                .consumerMainThread(SearchPacket::handle)
                .add();

        net.messageBuilder(NetworkPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(NetworkPacket::new)
                .encoder(NetworkPacket::toBytes)
                .consumerMainThread(NetworkPacket::handle)
                .add();

        net.messageBuilder(LearnedItemsSyncPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(LearnedItemsSyncPacket::new)
                .encoder(LearnedItemsSyncPacket::toBytes)
                .consumerMainThread(LearnedItemsSyncPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
