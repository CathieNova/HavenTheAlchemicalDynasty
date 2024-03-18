package net.cathienova.havenalchemy.block.entity;

import net.cathienova.havenalchemy.handler.OutputSlotHandler;
import net.cathienova.havenalchemy.networking.ModMessages;
import net.cathienova.havenalchemy.networking.packet.EnergySyncS2CPacket;
import net.cathienova.havenalchemy.recipe.AlchemicalChamberRecipe;
import net.cathienova.havenalchemy.screen.AlchemicalChamberMenu;
import net.cathienova.havenalchemy.util.ModEnergyStorage;
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
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    private LazyOptional<IEnergyStorage> energy = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;

    private final ModEnergyStorage energyStorage = new ModEnergyStorage(50000, 128)
    {
        @Override
        public void onEnergyChanged()
        {
            setChanged();
            ModMessages.sendToClients(new EnergySyncS2CPacket(this.energy, getBlockPos()));
        }
    };

    private static final int ENERGY_REQUIRED = 250;

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
        if (cap == ForgeCapabilities.ENERGY)
        {
            return energy.cast();
        }

        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == Direction.DOWN) {
                return LazyOptional.of(() -> new OutputSlotHandler(stackHandler, OUTPUT_SLOT)).cast();
            }
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }



    @Override
    public void onLoad()
    {
        super.onLoad();
        handler = LazyOptional.of(() -> stackHandler);
        energy = LazyOptional.of(() -> energyStorage);
    }

    @Override
    public void invalidateCaps()
    {
        super.invalidateCaps();
        handler.invalidate();
        energy.invalidate();
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

    public void setEnergyLevel(int energy)
    {
        this.energyStorage.setEnergy(energy);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag)
    {
        pTag.put("inventory", stackHandler.serializeNBT());
        pTag.putInt("alchemical_chamber.progress", progress);
        pTag.putInt("alchemical_chamber.energy", energyStorage.getEnergyStored());

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag)
    {
        super.load(pTag);
        stackHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("alchemical_chamber.progress");
        energyStorage.setEnergy(pTag.getInt("alchemical_chamber.energy"));
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState, AlchemicalChamberBlockEntity entity) {
        if (pLevel.isClientSide) {
            return;
        }

        if(hasRecipe() && hasEnoughEnergy(ENERGY_REQUIRED, entity)) {
            increaseCraftingProgress();
            extractEnergy(ENERGY_REQUIRED, entity);
            setChanged(pLevel, pPos, pState);

            if(hasProgressFinished()) {
                craft();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void extractEnergy(int energyRequired, AlchemicalChamberBlockEntity entity)
    {
        entity.energyStorage.extractEnergy(energyRequired, false);
    }

    private boolean hasEnoughEnergy(int energyRequired, AlchemicalChamberBlockEntity entity)
    {
        return energyStorage.getEnergyStored() >= energyRequired * entity.maxProgress;
    }

    public IEnergyStorage getEnergyStorage()
    {
        return energyStorage;
    }

    private boolean hasRecipe() {
        Optional<AlchemicalChamberRecipe> optionalRecipe = getCurrentRecipe();
        if (optionalRecipe.isEmpty()) {
            // No recipe matches the items in the inventory.
            return false;
        }

        AlchemicalChamberRecipe recipe = optionalRecipe.get();
        // Assuming the inventory has been properly set up with items from stackHandler.
        SimpleContainer inventory = new SimpleContainer(stackHandler.getSlots());
        for (int i = 0; i < stackHandler.getSlots(); i++) {
            inventory.setItem(i, stackHandler.getStackInSlot(i));
        }

        // Check if the inventory meets the recipe's requirements.
        if (!recipe.matches(inventory, level)) {
            // The inventory does not match the recipe requirements.
            return false;
        }

        if (!hasDistinctIngredientSlots(recipe, inventory)) {
            return false; // The ingredients do not meet the distinct slot distribution requirement.
        }

        ItemStack result = recipe.getResultItem(level.registryAccess());
        // Check if the output slot can accept the recipe result.
        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean hasDistinctIngredientSlots(AlchemicalChamberRecipe recipe, SimpleContainer inventory) {
        // Conceptually checks if there's at least one of each required ingredient in distinct slots.
        Map<Item, Long> ingredientFrequencyInRecipe = recipe.getIngredients().stream()
                .flatMap(ingredient -> Arrays.stream(ingredient.getItems()))
                .collect(Collectors.groupingBy(ItemStack::getItem, Collectors.counting()));

        Map<Item, Long> inventoryItemFrequency = IntStream.range(0, inventory.getContainerSize())
                .mapToObj(inventory::getItem)
                .filter(stack -> !stack.isEmpty())
                .collect(Collectors.groupingBy(ItemStack::getItem, Collectors.counting()));

        // Ensure every ingredient type in the recipe has a matching count in distinct slots in the inventory.
        for (Map.Entry<Item, Long> entry : ingredientFrequencyInRecipe.entrySet()) {
            Item item = entry.getKey();
            long requiredCount = entry.getValue();
            long availableCount = inventoryItemFrequency.getOrDefault(item, 0L);

            if (availableCount < requiredCount) {
                return false; // Not enough of this ingredient in distinct slots
            }
        }

        return true; // All required ingredients are available in distinct slots
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
        Optional<AlchemicalChamberRecipe> recipeOpt = getCurrentRecipe();
        if (!recipeOpt.isPresent()) {
            return; // No recipe matches the current setup.
        }

        AlchemicalChamberRecipe recipe = recipeOpt.get();
        // Assume getResultItem now properly uses the level's registry access as needed.
        ItemStack result = recipe.getResultItem(level.registryAccess());

        // Check if the output can handle the result item both in type and quantity.
        if (!canInsertItemIntoOutputSlot(result.getItem()) || !canInsertAmountIntoOutputSlot(result.getCount())) {
            return; // Output can't handle the result, don't craft.
        }

        // Consume the ingredients as specified by the recipe.
        consumeRecipeIngredients(recipe);

        // Add the result to the output slot.
        ItemStack outputStack = stackHandler.getStackInSlot(OUTPUT_SLOT);
        if (outputStack.isEmpty()) {
            stackHandler.setStackInSlot(OUTPUT_SLOT, result.copy());
        } else {
            outputStack.grow(result.getCount());
        }
    }

    private void consumeRecipeIngredients(AlchemicalChamberRecipe recipe) {
        // This map keeps track of the quantities still needed for each item.
        Map<Item, Integer> itemsNeeded = new HashMap<>();

        // Populate the map with the total quantities needed for each item.
        for (ItemStack ingredientStack : recipe.getIngredientsAsStacks()) {
            Item item = ingredientStack.getItem();
            int quantity = ingredientStack.getCount();
            itemsNeeded.put(item, itemsNeeded.getOrDefault(item, 0) + quantity);
        }

        // Go through each slot once, consuming items as needed.
        for (int slot = 0; slot < stackHandler.getSlots() - 1; slot++) { // Exclude output slot
            ItemStack stackInSlot = stackHandler.getStackInSlot(slot);
            if (!stackInSlot.isEmpty()) {
                Item itemInSlot = stackInSlot.getItem();
                if (itemsNeeded.containsKey(itemInSlot) && itemsNeeded.get(itemInSlot) > 0) {
                    stackHandler.extractItem(slot, 1, false);
                    // Update the map with the new quantity needed.
                    itemsNeeded.put(itemInSlot, itemsNeeded.get(itemInSlot) - 1);
                }
            }
        }
    }



    private void resetProgress()
    {
        progress = 0;
    }
}
