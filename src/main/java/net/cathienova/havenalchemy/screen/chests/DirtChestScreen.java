package net.cathienova.havenalchemy.screen.chests;

import com.mojang.blaze3d.systems.RenderSystem;
import net.cathienova.havenalchemy.HavenAlchemy;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class DirtChestScreen extends AbstractContainerScreen<DirtChestMenu>
{
    private static final ResourceLocation BACKGROUND = new ResourceLocation(HavenAlchemy.MOD_ID, "textures/gui/dirt_chest.png");

    public DirtChestScreen(DirtChestMenu pMenu, Inventory pPlayerInventory, Component pTitle)
    {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init()
    {
        super.init();
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 58;
        this.titleLabelX = 8;
        this.titleLabelY = 24;
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY)
    {
        super.renderLabels(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        int x = (width - 176) / 2;
        int y = (height - 133) / 2;

        guiGraphics.blit(BACKGROUND, x, y, 0, 0, 176, 133);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
