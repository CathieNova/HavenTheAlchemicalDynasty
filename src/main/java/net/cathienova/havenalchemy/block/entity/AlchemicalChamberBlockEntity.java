package net.cathienova.havenalchemy.block.entity;

import net.cathienova.havenalchemy.recipe.AlchemicalChamberRecipe;
import net.cathienova.havenalchemy.screen.AlchemicalChamberMenu;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AlchemicalChamberBlockEntity extends BlockEntity implements MenuProvider
{
    private final ItemStackHandler stackHandler = new ItemStackHandler(11)
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

    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int INPUT_SLOT_3 = 2;
    private static final int INPUT_SLOT_4 = 3;
    private static final int INPUT_SLOT_5 = 4;
    private static final int INPUT_SLOT_6 = 5;
    private static final int INPUT_SLOT_7 = 6;
    private static final int INPUT_SLOT_8 = 7;
    private static final int INPUT_SLOT_9 = 8;
    private static final int INPUT_SLOT_10 = 9;

    private static final int OUTPUT_SLOT = 10;

    private LazyOptional<IItemHandler> handler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;

    public AlchemicalChamberBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntities.alchemical_chamber.get(), pPos, pBlockState);
        this.data = new ContainerData()
        {
            @Override
            public int get(int pIndex)
            {
                return switch (pIndex)
                        {
                            case 0 -> AlchemicalChamberBlockEntity.this.progress;
                            case 1 -> AlchemicalChamberBlockEntity.this.maxProgress;
                            default -> 0;
                        };
            }

            @Override
            public void set(int pIndex, int pValue)
            {
                switch (pIndex)
                {
                    case 0 -> AlchemicalChamberBlockEntity.this.progress = pValue;
                    case 1 -> AlchemicalChamberBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount()
            {
                return 11;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
        {
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
        return Component.translatable("block.havenalchemy.alchemical_chamber");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer)
    {
        return new AlchemicalChamberMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag)
    {
        pTag.put("inventory", stackHandler.serializeNBT());
        pTag.putInt("alchemical_chamber.progress", progress);


        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag)
    {
        super.load(pTag);
        stackHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("alchemical_chamber.progress");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);

            if(hasProgressFinished()) {
                craft();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private boolean hasRecipe()
    {
        Optional<AlchemicalChamberRecipe> recipe = getCurrentRecipe();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<AlchemicalChamberRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.stackHandler.getSlots());
        for(int i = 0; i < stackHandler.getSlots(); i++) {
            inventory.setItem(i, this.stackHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(AlchemicalChamberRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item)
    {
        return this.stackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.stackHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count)
    {
        return this.stackHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.stackHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private void increaseCraftingProgress()
    {
        progress++;
    }

    private boolean hasProgressFinished()
    {
        return progress >= maxProgress;
    }

    private void craft() {
        Optional<AlchemicalChamberRecipe> recipe = getCurrentRecipe();
        if (recipe.isPresent()) {
            ItemStack result = recipe.get().getResultItem(null);
            boolean canCraft = true;

            // Verify that all required items for the recipe are present
            for (int i = 0; i < 10; i++) { // slot indexes are 0, 1, 2 for inputs
                if (this.stackHandler.getStackInSlot(i).isEmpty()) {
                    canCraft = false;
                    break;
                }
            }

            if (canCraft) {
                for (int i = 0; i < 10; i++) {
                    this.stackHandler.extractItem(i, 1, false);
                }

                ItemStack outputStack = this.stackHandler.getStackInSlot(OUTPUT_SLOT);
                this.stackHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                        outputStack.getCount() + result.getCount()));
            }
        }
    }

    private void resetProgress()
    {
        progress = 0;
    }


}
