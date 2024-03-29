package net.cathienova.havenalchemy.block.chests;

import net.cathienova.havenalchemy.block.entity.ModBlockEntities;
import net.cathienova.havenalchemy.screen.chests.CopperChestMenu;
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
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CopperChestBlockEntity extends ChestBlockEntity implements MenuProvider
{
    private final ChestTypes type = ChestTypes.COPPER;
    public final ItemStackHandler stackHandler = new ItemStackHandler(type.size)
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

    public CopperChestBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntities.copper_chest.get(), pPos, pBlockState);
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
                return type.size;
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
        return Component.translatable(type.getTranslationKey());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer)
    {
        return new CopperChestMenu(pContainerId, pPlayerInventory, this, this.data);
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
