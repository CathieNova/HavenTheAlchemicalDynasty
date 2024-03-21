// EMCSyncS2CPacket.java
package net.cathienova.havenalchemy.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EMCSyncS2CPacket {
    private final long storedEMC;
    private final long maxEMC;
    private final BlockPos pos;

    public EMCSyncS2CPacket(long storedEMC, long maxEMC, BlockPos pos) {
        this.storedEMC = storedEMC;
        this.maxEMC = maxEMC;
        this.pos = pos;
    }

    public EMCSyncS2CPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
        this.storedEMC = buf.readLong();
        this.maxEMC = buf.readLong();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeLong(storedEMC);
        buf.writeLong(maxEMC);
    }

    public static void handle(final EMCSyncS2CPacket message, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            // Client-side processing
        });
        context.setPacketHandled(true);
    }
}