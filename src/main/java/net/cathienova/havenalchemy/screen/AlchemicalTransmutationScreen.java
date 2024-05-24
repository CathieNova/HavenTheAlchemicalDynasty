package net.cathienova.havenalchemy.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.networking.ModMessages;
import net.cathienova.havenalchemy.networking.packet.NetworkPacket;
import net.cathienova.havenalchemy.networking.packet.SearchPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class AlchemicalTransmutationScreen extends AbstractContainerScreen<AlchemicalTransmutationMenu> {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(HavenAlchemy.MOD_ID, "textures/gui/alchemical_transmutation.png");
    public EditBox searchBox;

    public AlchemicalTransmutationScreen(AlchemicalTransmutationMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 208;
        this.imageHeight = 222;
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.inventoryLabelX = 10000;
        this.titleLabelY = 10000;
        this.titleLabelX = 10000;

        this.searchBox = new EditBox(this.font, this.leftPos + 85, this.topPos + 5, 60, 9, Component.literal(""));
        this.searchBox.setMaxLength(2048);
        this.searchBox.setBordered(true);
        this.searchBox.setVisible(true);
        this.searchBox.setTextColor(0xFFFFFF);
        this.addRenderableWidget(this.searchBox);

        // Send packet to navigate to previous page
        Button prevButton = new ImageButton(this.leftPos + 136, this.topPos + 120, 16, 16, 208, 0, 16, BACKGROUND, 256, 256, button ->
        {
            sendControlPacket(0); // Send packet to navigate to previous page
        });

        // Send packet to navigate to next page
        Button nextButton = new ImageButton(this.leftPos + 156, this.topPos + 120, 16, 16, 224, 0, 16, BACKGROUND, 256, 256, button ->
        {
            sendControlPacket(1); // Send packet to navigate to next page
        });

        this.addRenderableWidget(prevButton);
        this.addRenderableWidget(nextButton);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderLabels(guiGraphics, mouseX, mouseY);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.searchBox.isFocused()) {
            return this.searchBox.keyPressed(keyCode, scanCode, modifiers) || this.searchBox.canConsumeInput();
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (this.searchBox.isFocused()) {
            return this.searchBox.charTyped(codePoint, modifiers);
        }
        return super.charTyped(codePoint, modifiers);
    }

    @Override
    public void removed() {
        super.removed();
        searchBox.setFocused(false);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        if (searchBox.isFocused()) {
            if (keyCode != 256) {
                String searchText = searchBox.getValue();
                ModMessages.sendToServer(new SearchPacket(searchText));

                AlchemicalTransmutationMenu menu = this.menu;
                menu.setSearchText(searchText);
                menu.sortBySearch();
            }
        }
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(BACKGROUND, x, y, 0, 0, this.imageWidth, this.imageHeight);

        long emc = menu.getStoredEMC();
        guiGraphics.drawString(font, "" + String.format("%,d", emc), x + 180, y + 10, 65535);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private void sendControlPacket(int control) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("control", control);
        ModMessages.sendToServer(new NetworkPacket(nbt));
    }
}
