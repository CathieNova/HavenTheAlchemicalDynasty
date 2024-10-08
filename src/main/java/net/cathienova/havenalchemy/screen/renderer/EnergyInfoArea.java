package net.cathienova.havenalchemy.screen.renderer;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.List;

public class EnergyInfoArea extends InfoArea
{
    private final IEnergyStorage energy;

    public EnergyInfoArea(int xMin, int yMin, IEnergyStorage energy)
    {
        super(new Rect2i(xMin, yMin, 45, 6));
        this.energy = energy;
    }

    @Override
    protected void fillTooltipOverArea(int mouseX, int mouseY, List<Component> tooltip)
    {
        tooltip.add(Component.literal(energy.getEnergyStored()+"/"+energy.getMaxEnergyStored()+" FE"));
    }

    public List<Component> getTooltips() {
        return List.of(Component.literal(energy.getEnergyStored()+"/"+energy.getMaxEnergyStored()+" FE"));
    }

    @Override
    public void draw(GuiGraphics graphics) {
        final int width = area.getWidth();
        // Calculate the width of the filled part based on the energy stored.
        int filledWidth = (int) (width * (energy.getEnergyStored() / (float) energy.getMaxEnergyStored()));
        graphics.fill(area.getX(), area.getY(), area.getX() + area.getWidth(), area.getY() + area.getHeight(), 0xff330000);
        graphics.fillGradient(area.getX(), area.getY(), area.getX() + filledWidth, area.getY() + area.getHeight(),0xffb51500, 0xff600b00);
    }
}
