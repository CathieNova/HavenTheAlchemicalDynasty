package net.cathienova.havenalchemy.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.screen.renderer.EnergyInfoArea;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;
import java.util.Optional;

public class AlchemicalChamberScreen extends AbstractContainerScreen<AlchemicalChamberMenu>
{
    private static final ResourceLocation BACKGROUND = new ResourceLocation(HavenAlchemy.MOD_ID, "textures/gui/alchemical_chamber.png");
    private EnergyInfoArea energyInfoArea;

    public AlchemicalChamberScreen(AlchemicalChamberMenu pMenu, Inventory pPlayerInventory, Component pTitle)
    {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init()
    {
        super.init();
        assignEnergyInfoArea();
        this.inventoryLabelY = 73;
        this.inventoryLabelX = 8;
        this.titleLabelY = 10000;
        this.titleLabelX = 10000;
    }

    private void assignEnergyInfoArea()
    {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        energyInfoArea = new EnergyInfoArea(x + 116, y + 9, menu.blockEntity.getEnergyStorage());
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY)
    {
        super.renderLabels(graphics, mouseX, mouseY);
        renderEnergyAreaTooltips(graphics, mouseX, mouseY);
    }

    private void renderEnergyAreaTooltips(GuiGraphics graphics, int mouseX, int mouseY) {
        int guiLeft = (this.width - this.imageWidth) / 2;
        int guiTop = (this.height - this.imageHeight) / 2;

        int energyX = guiLeft + 116; // Adjusted for GUI position
        int energyY = guiTop + 9; // Adjusted for GUI position
        int energyWidth = 45;
        int energyHeight = 6;

        // Check if mouse is over the energy area
        if (mouseX >= energyX && mouseX <= energyX + energyWidth && mouseY >= energyY && mouseY <= energyY + energyHeight) {
            List<Component> tooltips = energyInfoArea.getTooltips();

            if (!tooltips.isEmpty()) {
                // Adjust mouse coordinates to be relative to the tooltip rendering context
                int tooltipX = mouseX - guiLeft;
                int tooltipY = mouseY - guiTop;

                assert minecraft != null;
                graphics.renderTooltip(minecraft.font, tooltips, Optional.empty(), tooltipX, tooltipY);
            }
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        int x = (width - 176) / 2;
        int y = (height - 166) / 2;

        graphics.blit(BACKGROUND, x, y, 0, 0, 176, 166);

        energyInfoArea.draw(graphics);
        renderProgressArrow(graphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics graphics, int x, int y) {
        if(menu.isCrafting()) {
            graphics.blit(BACKGROUND, x + 66, y + 36, 176, 0, menu.getScaledProgress(), 4);
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, delta);
        renderTooltip(graphics, mouseX, mouseY);
    }
}
