package net.cathienova.havenalchemy.block.entity;

import net.cathienova.havenalchemy.handler.InputSlotHandler;
import net.cathienova.havenalchemy.networking.ModMessages;
import net.cathienova.havenalchemy.networking.packet.EMCSyncS2CPacket;
import net.cathienova.havenalchemy.screen.AlchemicalChestMenu;
import net.cathienova.havenalchemy.screen.AlchemicalCondenserMenu;
import net.cathienova.havenalchemy.util.EMCSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AlchemicalChestBlockEntity extends ChestBlockEntity implements MenuProvider
{
    public final ItemStackHandler stackHandler = new ItemStackHandler(92)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            setChanged();
            assert level != null;
            if (!level.isClientSide())
            {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private LazyOptional<IItemHandler> handler = LazyOptional.empty();

    protected final ContainerData data;

    public AlchemicalChestBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntities.alchemical_chest.get(), pPos, pBlockState);
        this.data = new ContainerData()
        {
            @Override
            public int get(int pIndex)
            {
                return 0;
            }

            @Override
            public void set(int pIndex, int pValue)
            {
            }

            @Override
            public int getCount()
            {
                return 92;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad()
    {
        super.onLoad();
        handler = LazyOptional.of(() -> stackHandler);
    }

    @Override
    public void invalidateCaps()
    {
        super.invalidateCaps();
        handler.invalidate();
    }

    public void drops()
    {
        SimpleContainer inventory = new SimpleContainer(stackHandler.getSlots());
        for (int i = 0; i < stackHandler.getSlots(); i++)
        {
            inventory.setItem(i, stackHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName()
    {
        return Component.translatable("block.havenalchemy.alchemical_condenser");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer)
    {
        return new AlchemicalChestMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag)
    {
        pTag.put("inventory", stackHandler.serializeNBT());

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag)
    {
        super.load(pTag);
        stackHandler.deserializeNBT(pTag.getCompound("inventory"));
    }
}
