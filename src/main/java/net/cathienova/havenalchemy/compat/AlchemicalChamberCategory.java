package net.cathienova.havenalchemy.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.recipe.AlchemicalChamberRecipe;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;

public class AlchemicalChamberCategory implements IRecipeCategory<AlchemicalChamberRecipe>
{
    public static final ResourceLocation UID = new ResourceLocation(HavenAlchemy.MOD_ID, "alchemical_chamber");
    public static final ResourceLocation TEXTURE = new ResourceLocation(HavenAlchemy.MOD_ID, "textures/gui/alchemical_chamber.png");

    public static final RecipeType<AlchemicalChamberRecipe> ALCHEMICAL_CHAMBER_RECIPE_RECIPE_TYPE = new RecipeType<>(UID, AlchemicalChamberRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public AlchemicalChamberCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE, 3, 4, 169, 72);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.alchemical_chamber.get()));
    }

    @Override
    public RecipeType<AlchemicalChamberRecipe> getRecipeType()
    {
        return ALCHEMICAL_CHAMBER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle()
    {
        return Component.translatable("block.havenalchemy.alchemical_chamber");
    }

    @Override
    public IDrawable getBackground()
    {
        return this.background;
    }

    @Override
    public IDrawable getIcon()
    {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AlchemicalChamberRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 64, 43).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 90, 43).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 14, 18).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 39, 18).addIngredients(recipe.getIngredients().get(3));
        builder.addSlot(RecipeIngredientRole.INPUT, 14, 43).addIngredients(recipe.getIngredients().get(4));
        builder.addSlot(RecipeIngredientRole.INPUT, 39, 43).addIngredients(recipe.getIngredients().get(5));
        builder.addSlot(RecipeIngredientRole.INPUT, 115, 18).addIngredients(recipe.getIngredients().get(6));
        builder.addSlot(RecipeIngredientRole.INPUT, 140, 18).addIngredients(recipe.getIngredients().get(7));
        builder.addSlot(RecipeIngredientRole.INPUT, 115, 43).addIngredients(recipe.getIngredients().get(8));
        builder.addSlot(RecipeIngredientRole.INPUT, 140, 43).addIngredients(recipe.getIngredients().get(9));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 77, 9).addItemStack(recipe.getResultItem(null));
    }

    @Override
    public void draw(AlchemicalChamberRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY)
    {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);

        Rect2i area = new Rect2i(113, 5, 45, 6);
        final int width = area.getWidth();

        int filledWidth = (int) (width * (25000 / (float) 100000));
        graphics.fill(area.getX(), area.getY(), area.getX() + area.getWidth(), area.getY() + area.getHeight(), 0xff330000);
        graphics.fillGradient(area.getX(), area.getY(), area.getX() + filledWidth, area.getY() + area.getHeight(),0xffb51500, 0xff600b00);
    }
}
