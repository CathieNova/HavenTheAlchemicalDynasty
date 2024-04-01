package net.cathienova.havenalchemy.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlchemicalProcessorRecipe implements Recipe<SimpleContainer>
{
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;

    public AlchemicalProcessorRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id)
    {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide) {
            return false;
        }

        // Map to hold the count of each ingredient found.
        Map<Item, Integer> ingredientCountMap = new HashMap<>();

        // Populate the map with required ingredients and their quantities.
        for (Ingredient ingredient : this.inputItems) {
            for (ItemStack stack : ingredient.getItems()) {
                ingredientCountMap.put(stack.getItem(), ingredientCountMap.getOrDefault(stack.getItem(), 0) + 1);
            }
        }

        // Check items in the container against the required ingredients and quantities.
        for (int i = 0; i < pContainer.getContainerSize(); i++) {
            ItemStack itemStack = pContainer.getItem(i);
            if (!itemStack.isEmpty() && ingredientCountMap.containsKey(itemStack.getItem())) {
                ingredientCountMap.put(itemStack.getItem(), ingredientCountMap.get(itemStack.getItem()) - itemStack.getCount());
            }
        }

        // Verify all required ingredients have been accounted for.
        return ingredientCountMap.values().stream().allMatch(count -> count <= 0);
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        return inputItems;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess)
    {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight)
    {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess)
    {
        return output.copy();
    }

    @Override
    public ResourceLocation getId()
    {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType()
    {
        return Type.INSTANCE;
    }

    public List<ItemStack> getIngredientsAsStacks() {
        Map<Item, Integer> itemCountMap = new HashMap<>();
        for (Ingredient ingredient : this.inputItems) {
            for (ItemStack itemStack : ingredient.getItems()) {
                Item item = itemStack.getItem();
                itemCountMap.put(item, itemCountMap.getOrDefault(item, 0) + 1);
            }
        }

        List<ItemStack> ingredientsWithQuantities = new ArrayList<>();
        itemCountMap.forEach((item, count) -> ingredientsWithQuantities.add(new ItemStack(item, count)));
        return ingredientsWithQuantities;
    }

    public static class Type implements RecipeType<AlchemicalProcessorRecipe>
    {
        public static final Type INSTANCE = new Type();
        public static final String ID = "alchemical_processor";
    }

    public static class Serializer implements RecipeSerializer<AlchemicalProcessorRecipe>
    {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation("havenalchemy", "alchemical_processor");

        @Override
        public AlchemicalProcessorRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe)
        {
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputItems = NonNullList.withSize(ingredients.size(), Ingredient.EMPTY);

            for (int i = 0; i < ingredients.size(); i++)
            {
                inputItems.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new AlchemicalProcessorRecipe(inputItems, result, pRecipeId);
        }

        @Nullable
        @Override
        public AlchemicalProcessorRecipe fromNetwork(ResourceLocation pId, FriendlyByteBuf pBuffer)
        {
            NonNullList<Ingredient> inputItems = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputItems.size(); i++)
            {
                inputItems.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack result = pBuffer.readItem();

            return new AlchemicalProcessorRecipe(inputItems, result, pId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, AlchemicalProcessorRecipe pRecipe)
        {
            pBuffer.writeInt(pRecipe.inputItems.size());

            for (Ingredient ingredient : pRecipe.getIngredients())
            {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }
    }
}
