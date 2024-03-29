package net.cathienova.havenalchemy.screen.chests;

import com.mojang.blaze3d.systems.RenderSystem;
import net.cathienova.havenalchemy.HavenAlchemy;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class IronChestScreen extends AbstractContainerScreen<IronChestMenu>
{
    private static final ResourceLocation BACKGROUND = new ResourceLocation(HavenAlchemy.MOD_ID, "textures/gui/iron_chest.png");

    public IronChestScreen(IronChestMenu pMenu, Inventory pPlayerInventory, Component pTitle)
    {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init()
    {
        super.init();
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 102;
        this.titleLabelX = 8;
        this.titleLabelY = -20;
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
        int y = (height - 222) / 2;

        guiGraphics.blit(BACKGROUND, x, y, 0, 0, 176, 222);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
