package net.cathienova.havenalchemy.block.entity;

import net.cathienova.havenalchemy.handler.InputSlotHandler;
import net.cathienova.havenalchemy.handler.OutputSlotHandler;
import net.cathienova.havenalchemy.networking.ModMessages;
import net.cathienova.havenalchemy.networking.packet.EMCSyncS2CPacket;
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
import net.minecraft.world.level.block.entity.BlockEntity;
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

public class AlchemicalCondenserBlockEntity extends ChestBlockEntity implements MenuProvider
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

    public long storedEMC = 0;
    public long maxEMC = 10000000;

    public int coolDown = 0;

    public int getMaxCoolDown() {
        // Ticks
        return 1;
    }

    public AlchemicalCondenserBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntities.alchemical_condenser.get(), pPos, pBlockState);
        this.data = new ContainerData()
        {
            @Override
            public int get(int pIndex) {
                return switch (pIndex)
                        {
                            case 0 -> (int) (AlchemicalCondenserBlockEntity.this.storedEMC); // Low int of storedEMC
                            case 1 -> (int) (AlchemicalCondenserBlockEntity.this.maxEMC); // Low int of maxEMC
                            default -> 0;
                        };
            }

            @Override
            public void set(int pIndex, int pValue)
            {
                switch (pIndex)
                {
                    case 0 -> AlchemicalCondenserBlockEntity.this.storedEMC = pValue;
                    case 1 -> AlchemicalCondenserBlockEntity.this.maxEMC = pValue;
                }
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
        storedEMC = data.get(0);
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
        return new AlchemicalCondenserMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag)
    {
        pTag.put("inventory", stackHandler.serializeNBT());
        pTag.putLong("alchemical_condenser.stored_emc", storedEMC);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag)
    {
        super.load(pTag);
        stackHandler.deserializeNBT(pTag.getCompound("inventory"));
        storedEMC = pTag.getInt("alchemical_condenser.stored_emc");
    }

    public ItemStack getTargetStack() {
        return stackHandler.getStackInSlot(0);
    }

    public void tick() {
        if (this.level.isClientSide) return;

        long oldStoredEMC = this.storedEMC;
        long oldMaxEMC = this.maxEMC;

        ItemStack targetStack = this.stackHandler.getStackInSlot(0);
        if (EMCSystem.GetEmc(targetStack.getItem()) <= 0) return;

        // Correctly handle EMC not increasing past maxEMC.
        if (this.storedEMC > this.maxEMC) {
            this.storedEMC = this.maxEMC; // Ensure stored EMC doesn't exceed maxEMC.
        }

        // Consuming items for EMC, but ensuring it doesn't exceed maxEMC.
        if (!targetStack.isEmpty() && this.coolDown == 0) {
            for (int i = 1; i < this.stackHandler.getSlots(); ++i) {
                ItemStack stack = this.stackHandler.getStackInSlot(i);
                if (stack.isEmpty() || stack.getItem() == targetStack.getItem()) continue;

                long emcValue = EMCSystem.GetEmc(stack.getItem());
                // Check if adding this item's EMC will exceed maxEMC.
                if (emcValue > 0 && this.storedEMC + emcValue <= this.maxEMC) {
                    this.storedEMC += emcValue; // Safe to add EMC value.
                    stack.shrink(1);
                    this.stackHandler.setStackInSlot(i, stack);
                    break; // Exit after processing one item for EMC.
                }
            }
        }

        // Handling output production regardless of EMC storage state.
        long useEMC = EMCSystem.GetEmc(targetStack.getItem());
        if (useEMC <= 0) useEMC = 1;
        if (this.storedEMC >= useEMC && !targetStack.isEmpty()) {
            ItemStack newStack = targetStack.copy();
            newStack.setCount(1);
            if (insertItem(newStack)) {
                this.storedEMC -= useEMC;
                if (this.storedEMC < 0) {
                    this.storedEMC = 0;
                }
            }
        }

        // Handling cooldown and syncing EMC values if they have changed.
        if (this.coolDown++ >= this.getMaxCoolDown()) {
            this.coolDown = 0;
        }

        if (oldStoredEMC != this.storedEMC || oldMaxEMC != this.maxEMC) {
            this.setChanged();
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
            syncEMCValues();
        }
    }

    public boolean insertItem(ItemStack insertStack) {
        if (insertStack.isEmpty()) return false;
        for (int i = 1; i < this.stackHandler.getSlots(); ++i) { // Start from 1 to skip the target slot
            ItemStack stackInSlot = this.stackHandler.getStackInSlot(i);
            if (stackInSlot.isEmpty()) {
                this.stackHandler.insertItem(i, insertStack, false);
                return true;
            } else if (ItemHandlerHelper.canItemStacksStack(stackInSlot, insertStack)) {
                int insertAmount = Math.min(this.stackHandler.getSlotLimit(i) - stackInSlot.getCount(), insertStack.getCount());
                if (insertAmount > 0) {
                    ItemStack copy = insertStack.copy();
                    copy.setCount(insertAmount);
                    this.stackHandler.insertItem(i, copy, false);
                    insertStack.shrink(insertAmount);
                    return true;
                }
            }
        }
        return false;
    }

    public long getEMC() {
        return this.storedEMC;
    }

    public void syncEMCValues() {
        if (!this.level.isClientSide) { // Ensure we are on the server
            EMCSyncS2CPacket packet = new EMCSyncS2CPacket(this.storedEMC, this.maxEMC, this.worldPosition);
            // Send to all players watching this chunk
            ModMessages.INSTANCE.send(PacketDistributor.NEAR.with(() -> new PacketDistributor.TargetPoint(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ(), 64, this.level.dimension())), packet);
        }
    }
}
