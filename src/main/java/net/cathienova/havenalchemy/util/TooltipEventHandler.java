package net.cathienova.havenalchemy.util;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.OnlyIn;

@Mod.EventBusSubscriber(bus = Bus.FORGE)
public class TooltipEventHandler {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();

        /*if (EMCSystem.contains(item))
        {
            long emcValue = EMCSystem.getEmc(item);
            boolean isShiftDown = Screen.hasShiftDown();

            String formattedEmcValue;
            if (isShiftDown) {
                formattedEmcValue = String.format("%,d", emcValue);
            } else {
                formattedEmcValue = emcValue >= 1_000_000 ? String.format("%.1fM", emcValue / 1_000_000.0) : String.format("%,d", emcValue);
            }
            event.getToolTip().add(Component.literal("§3EMC: §d" + formattedEmcValue));

            if (stack.getCount() > 1) {
                long totalEmcValue = emcValue * stack.getCount();
                String formattedTotalEmcValue;
                if (isShiftDown) {
                    formattedTotalEmcValue = String.format("%,d", totalEmcValue);
                } else {
                    formattedTotalEmcValue = totalEmcValue >= 1_000_000 ? String.format("%.1fM", totalEmcValue / 1_000_000.0) : String.format("%,d", totalEmcValue);
                }
                event.getToolTip().add(Component.literal("§3Total: §d" + formattedTotalEmcValue));
            }

        }
        else
        {
            //System.out.println("[HavenAlchemy]: No EMC value found for: " + item);
        }*/
    }
}