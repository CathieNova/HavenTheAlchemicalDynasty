package net.cathienova.havenalchemy.screen;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.entity.GeneratorBlockEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class GeneratorScreen extends AbstractContainerScreen<GeneratorMenu>
{

    private static final int ENERGY_LEFT = 108;
    private static final int ENERGY_WIDTH = 58;
    private static final int ENERGY_TOP = 10;
    private static final int ENERGY_HEIGHT = 6;

    private final ResourceLocation GUI = new ResourceLocation(HavenAlchemy.MOD_ID, "textures/gui/generator.png");

    public GeneratorScreen(GeneratorMenu container, Inventory inventory, Component title) {
        super(container, inventory, title);
        this.titleLabelY = 11;
        this.titleLabelX = 11;
        this.inventoryLabelY = this.imageHeight - 106;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
        graphics.blit(GUI, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);
        int power = menu.getPower();
        int p = (int) ((power / (float) GeneratorBlockEntity.CAPACITY) * ENERGY_WIDTH);
        graphics.fillGradient(leftPos + ENERGY_LEFT, topPos + ENERGY_TOP, leftPos + ENERGY_LEFT + p, topPos + ENERGY_TOP + ENERGY_HEIGHT, 0xffff0000, 0xff000000);
        graphics.fill(leftPos + ENERGY_LEFT + p, topPos + ENERGY_TOP, leftPos + ENERGY_LEFT + ENERGY_WIDTH, topPos + ENERGY_TOP + ENERGY_HEIGHT, 0xff330000);
    }

    @Override
    public void render(GuiGraphics graphics, int mousex, int mousey, float partialTick) {
        super.render(graphics, mousex, mousey, partialTick);
        // Render tooltip with power if in the energy box
        if (mousex >= leftPos + ENERGY_LEFT && mousex < leftPos + ENERGY_LEFT + ENERGY_WIDTH && mousey >= topPos + ENERGY_TOP && mousey < topPos + ENERGY_TOP + ENERGY_HEIGHT) {
            int power = menu.getPower();
            int maxPower = GeneratorBlockEntity.CAPACITY;
            graphics.renderTooltip(this.font, Component.literal(power + "/" + maxPower + " FE"), mousex, mousey);
        }
    }
}
