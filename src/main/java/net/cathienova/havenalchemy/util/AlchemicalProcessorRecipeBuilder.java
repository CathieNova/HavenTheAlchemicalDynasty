package net.cathienova.havenalchemy.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class AlchemicalProcessorRecipeBuilder
{
    private final ResourceLocation recipeType;
    private final JsonArray ingredients = new JsonArray();
    private final JsonObject result = new JsonObject();
    private final RecipeSerializer<?> serializer;

    private AlchemicalProcessorRecipeBuilder(ResourceLocation recipeType, RecipeSerializer<?> serializer)
    {
        this.recipeType = recipeType;
        this.serializer = serializer;
    }

    public static AlchemicalProcessorRecipeBuilder customRecipe(ResourceLocation recipeType, RecipeSerializer<?> serializer)
    {
        return new AlchemicalProcessorRecipeBuilder(recipeType, serializer);
    }

    public AlchemicalProcessorRecipeBuilder ingredient(ItemLike item)
    {
        ResourceLocation itemRegistryName = ForgeRegistries.ITEMS.getKey(item.asItem());
        if (itemRegistryName == null)
        {
            throw new IllegalStateException("Item " + item + " is not registered, so it cannot be added to the recipe.");
        }
        JsonObject ingredientObject = new JsonObject();
        ingredientObject.addProperty("item", itemRegistryName.toString());
        this.ingredients.add(ingredientObject);
        return this;
    }

    public AlchemicalProcessorRecipeBuilder result(ItemLike resultItem)
    {
        ResourceLocation resultRegistryName = ForgeRegistries.ITEMS.getKey(resultItem.asItem());
        if (resultRegistryName == null)
        {
            throw new IllegalStateException("Result item is not registered.");
        }
        this.result.addProperty("item", resultRegistryName.toString()); // Similarly, use the full namespace.
        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id)
    {
        ResourceLocation serializerKey = BuiltInRegistries.RECIPE_SERIALIZER.getKey(serializer);
        if (serializerKey == null) {
            throw new IllegalStateException("Serializer for " + serializer + " is not registered. Cannot save recipe " + id);
        }
        consumer.accept(new FinishedRecipe()
        {
            @Override
            public void serializeRecipeData(JsonObject json)
            {
                json.addProperty("type", BuiltInRegistries.RECIPE_SERIALIZER.getKey(serializer).toString());
                json.add("ingredients", ingredients);
                json.add("result", result);
            }

            @Override
            public ResourceLocation getId()
            {
                return id;
            }

            @Override
            public RecipeSerializer<?> getType()
            {
                return serializer;
            }

            @Nullable
            @Override
            public JsonObject serializeAdvancement()
            {
                return null;
            }

            @Nullable
            @Override
            public ResourceLocation getAdvancementId()
            {
                return null;
            }
        });
    }
}