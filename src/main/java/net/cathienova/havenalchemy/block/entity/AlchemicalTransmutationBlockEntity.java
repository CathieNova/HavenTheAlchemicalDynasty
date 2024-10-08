package net.cathienova.havenalchemy.block.entity;

import net.cathienova.havenalchemy.gui.RegisterInventory;
import net.cathienova.havenalchemy.networking.ModMessages;
import net.cathienova.havenalchemy.networking.packet.SearchPacket;
import net.cathienova.havenalchemy.screen.AlchemicalCondenserMenu;
import net.cathienova.havenalchemy.screen.AlchemicalTransmutationMenu;
import net.cathienova.havenalchemy.util.EMCSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AlchemicalTransmutationBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(80) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (level != null && !level.isClientSide) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    protected final ContainerData data;

    public AlchemicalTransmutationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.alchemical_transmutation.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return 0;
            }

            @Override
            public void set(int index, int value) {}

            @Override
            public int getCount() {
                return 80;
            }
        };
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.havenalchemy.alchemical_transmutation");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer)
    {
        return new AlchemicalTransmutationMenu(pContainerId, pPlayerInventory, this, new RegisterInventory(pPlayer, 64));
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction side) {
        if (capability == ForgeCapabilities.ITEM_HANDLER) {
            return handler.cast();
        }
        return super.getCapability(capability, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        handler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        handler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        try {
            tag.put("inventory", itemHandler.serializeNBT());
            super.saveAdditional(tag);
        } catch (StackOverflowError e) {
            System.err.println("StackOverflowError while saving NBT data.");
        }
    }

    @Override
    public void load(CompoundTag tag) {
        try {
            super.load(tag);
            if (tag.contains("inventory")) {
                itemHandler.deserializeNBT(tag.getCompound("inventory"));
            }
        } catch (StackOverflowError e) {
            System.err.println("StackOverflowError while loading NBT data.");
        }
    }

    public void tick() {
        if (this.level.isClientSide) return;
    }

    public long calculateEMC() {
        long totalEMC = 0;
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (!stack.isEmpty()) {
                long emcValue = EMCSystem.GetEmc(stack);
                totalEMC += emcValue * stack.getCount();
            }
        }
        return totalEMC;
    }
}
