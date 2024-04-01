package net.cathienova.havenalchemy.networking.packet;

import net.cathienova.havenalchemy.block.entity.AlchemicalChamberBlockEntity;
import net.cathienova.havenalchemy.block.entity.AlchemicalProcessorBlockEntity;
import net.cathienova.havenalchemy.screen.AlchemicalChamberMenu;
import net.cathienova.havenalchemy.screen.AlchemicalProcessorMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EnergySyncS2CPacket {
    private final int energy;
    private final BlockPos pos;

    public EnergySyncS2CPacket(int energy, BlockPos pos) {
        this.energy = energy;
        this.pos = pos;
    }

    public EnergySyncS2CPacket(FriendlyByteBuf buf) {
        this.energy = buf.readInt();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(energy);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof AlchemicalChamberBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof AlchemicalChamberMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }

            if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof AlchemicalProcessorBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if (Minecraft.getInstance().player.containerMenu instanceof AlchemicalProcessorMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
        });
        return true;
    }
}