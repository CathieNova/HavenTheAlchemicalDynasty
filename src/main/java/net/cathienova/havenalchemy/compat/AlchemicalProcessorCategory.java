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
import net.cathienova.havenalchemy.recipe.AlchemicalProcessorRecipe;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class AlchemicalProcessorCategory implements IRecipeCategory<AlchemicalProcessorRecipe>
{
    public static final ResourceLocation UID = new ResourceLocation(HavenAlchemy.MOD_ID, "alchemical_processor");
    public static final ResourceLocation TEXTURE = new ResourceLocation(HavenAlchemy.MOD_ID, "textures/gui/alchemical_processor.png");

    public static final RecipeType<AlchemicalProcessorRecipe> ALCHEMICAL_PROCESSOR_RECIPE_RECIPE_TYPE = new RecipeType<>(UID, AlchemicalProcessorRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public AlchemicalProcessorCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE, 3, 4, 167, 68);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.alchemical_processor.get()));
    }

    @Override
    public RecipeType<AlchemicalProcessorRecipe> getRecipeType()
    {
        return ALCHEMICAL_PROCESSOR_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle()
    {
        return Component.translatable("block.havenalchemy.alchemical_processor");
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
    public void setRecipe(IRecipeLayoutBuilder builder, AlchemicalProcessorRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 38, 26).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 26).addItemStack(recipe.getResultItem(null));
    }

    @Override
    public void draw(AlchemicalProcessorRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY)
    {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);
        Rect2i area = new Rect2i(7, 6, 11, 56);
        // Calculate the height of the filled part based on the energy stored.
        int filledHeight = (int) (area.getHeight() * (10000 / (float) 42000));
        graphics.fill(area.getX(), area.getY(), area.getX() + area.getWidth(), area.getY() + area.getHeight(), 0xff330000);
        graphics.fillGradient(area.getX(), area.getY(), area.getX() + area.getWidth(), area.getY() + filledHeight,0xff600b00, 0xffb51500);
    }
}
