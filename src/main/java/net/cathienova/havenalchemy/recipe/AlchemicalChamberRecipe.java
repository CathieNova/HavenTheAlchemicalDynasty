package net.cathienova.havenalchemy.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AlchemicalChamberRecipe implements Recipe<SimpleContainer>
{
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;

    public AlchemicalChamberRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id)
    {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel)
    {
        if (pLevel.isClientSide)
        {
            return false;
        }

        // Create a copy of the input list to track which ingredients have been matched.
        List<Ingredient> requiredIngredients = new ArrayList<>(this.inputItems);

        for (int i = 0; i < pContainer.getContainerSize(); i++)
        {
            ItemStack itemStack = pContainer.getItem(i);
            if (!itemStack.isEmpty())
            {
                // Check each ingredient to see if it matches the current item stack.
                requiredIngredients.removeIf(ingredient -> ingredient.test(itemStack));
            }
        }

        // If all required ingredients have been matched (list is empty), the recipe matches.
        return requiredIngredients.isEmpty();
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

    public static class Type implements RecipeType<AlchemicalChamberRecipe>
    {
        public static final Type INSTANCE = new Type();
        public static final String ID = "alchemical_chamber";
    }

    public static class Serializer implements RecipeSerializer<AlchemicalChamberRecipe>
    {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation("havenalchemy", "alchemical_chamber");

        @Override
        public AlchemicalChamberRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe)
        {
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputItems = NonNullList.withSize(ingredients.size(), Ingredient.EMPTY);

            for (int i = 0; i < ingredients.size(); i++)
            {
                inputItems.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new AlchemicalChamberRecipe(inputItems, result, pRecipeId);
        }

        @Nullable
        @Override
        public AlchemicalChamberRecipe fromNetwork(ResourceLocation pId, FriendlyByteBuf pBuffer)
        {
            NonNullList<Ingredient> inputItems = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputItems.size(); i++)
            {
                inputItems.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack result = pBuffer.readItem();

            return new AlchemicalChamberRecipe(inputItems, result, pId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, AlchemicalChamberRecipe pRecipe)
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
