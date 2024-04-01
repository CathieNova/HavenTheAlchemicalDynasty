package net.cathienova.havenalchemy.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.recipe.*;
import net.cathienova.havenalchemy.screen.*;
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
        registration.addRecipeCategories(new AlchemicalProcessorCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        RecipeManager recipeManager = Minecraft.getInstance().getConnection().getRecipeManager();

        List<AlchemicalChamberRecipe> chamberRecipes = recipeManager.getAllRecipesFor(AlchemicalChamberRecipe.Type.INSTANCE);
        registration.addRecipes(AlchemicalChamberCategory.ALCHEMICAL_CHAMBER_RECIPE_RECIPE_TYPE, chamberRecipes);

        List<AlchemicalProcessorRecipe> processorRecipes = recipeManager.getAllRecipesFor(AlchemicalProcessorRecipe.Type.INSTANCE);
        registration.addRecipes(AlchemicalProcessorCategory.ALCHEMICAL_PROCESSOR_RECIPE_RECIPE_TYPE, processorRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration)
    {
        registration.addRecipeClickArea(AlchemicalChamberScreen.class, 65, 34, 48, 8,
                AlchemicalChamberCategory.ALCHEMICAL_CHAMBER_RECIPE_RECIPE_TYPE);

        registration.addRecipeClickArea(AlchemicalProcessorScreen.class, 65, 34, 48, 8,
                AlchemicalProcessorCategory.ALCHEMICAL_PROCESSOR_RECIPE_RECIPE_TYPE);
    }
}
