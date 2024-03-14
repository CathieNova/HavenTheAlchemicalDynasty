package net.cathienova.havenalchemy.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.recipe.AlchemicalChamberRecipe;
import net.cathienova.havenalchemy.screen.AlchemicalChamberScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class JEIHavenAlchemyPlugin implements IModPlugin
{

    @Override
    public ResourceLocation getPluginUid()
    {
        return new ResourceLocation(HavenAlchemy.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration)
    {
        registration.addRecipeCategories(new AlchemicalChamberCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        RecipeManager recipeManager = Minecraft.getInstance().getConnection().getRecipeManager();

        List<AlchemicalChamberRecipe> recipes = recipeManager.getAllRecipesFor(AlchemicalChamberRecipe.Type.INSTANCE);
        registration.addRecipes(AlchemicalChamberCategory.ALCHEMICAL_CHAMBER_RECIPE_RECIPE_TYPE, recipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration)
    {
        registration.addRecipeClickArea(AlchemicalChamberScreen.class, 103, 59, 24, 17,
                AlchemicalChamberCategory.ALCHEMICAL_CHAMBER_RECIPE_RECIPE_TYPE);
    }
}
