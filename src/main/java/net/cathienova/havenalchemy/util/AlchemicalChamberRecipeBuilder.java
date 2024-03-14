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

public class AlchemicalChamberRecipeBuilder {
    private final ResourceLocation recipeType;
    private final JsonArray ingredients = new JsonArray();
    private final JsonObject result = new JsonObject();
    // Assume this is your custom recipe serializer that you've registered somewhere in your mod initialization.
    private final RecipeSerializer<?> serializer;

    private AlchemicalChamberRecipeBuilder(ResourceLocation recipeType, RecipeSerializer<?> serializer) {
        this.recipeType = recipeType;
        this.serializer = serializer;
    }

    public static AlchemicalChamberRecipeBuilder customRecipe(ResourceLocation recipeType, RecipeSerializer<?> serializer) {
        return new AlchemicalChamberRecipeBuilder(recipeType, serializer);
    }

    public AlchemicalChamberRecipeBuilder ingredient(ItemLike item) {
        ResourceLocation itemRegistryName = ForgeRegistries.ITEMS.getKey(item.asItem());
        if (itemRegistryName == null) {
            throw new IllegalStateException("Item " + item + " is not registered, so it cannot be added to the recipe.");
        }
        JsonObject ingredientObject = new JsonObject();
        ingredientObject.addProperty("item", itemRegistryName.toString()); // Use toString() to get the full namespace.
        this.ingredients.add(ingredientObject);
        return this;
    }

    public AlchemicalChamberRecipeBuilder result(ItemLike resultItem) {
        ResourceLocation resultRegistryName = ForgeRegistries.ITEMS.getKey(resultItem.asItem());
        if (resultRegistryName == null) {
            throw new IllegalStateException("Result item is not registered.");
        }
        this.result.addProperty("item", resultRegistryName.toString()); // Similarly, use the full namespace.
        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        consumer.accept(new FinishedRecipe() {
            @Override
            public void serializeRecipeData(JsonObject json) {
                json.addProperty("type", BuiltInRegistries.RECIPE_SERIALIZER.getKey(serializer).toString());
                json.add("ingredients", ingredients);
                json.add("result", result);
            }

            @Override
            public ResourceLocation getId() {
                return id;
            }

            @Override
            public RecipeSerializer<?> getType() {
                return serializer; // Return the actual serializer instead of null.
            }

            @Nullable
            @Override
            public JsonObject serializeAdvancement() {
                return null; // If your recipe doesn't use advancements, this is fine.
            }

            @Nullable
            @Override
            public ResourceLocation getAdvancementId() {
                return null; // Similarly, null is okay if no advancements are used.
            }
        });
    }
}
