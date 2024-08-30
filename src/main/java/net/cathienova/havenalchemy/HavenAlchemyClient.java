package net.cathienova.havenalchemy;

import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.cables.client.CableModelLoader;
import net.cathienova.havenalchemy.cables.client.FacadeBlockColor;
import net.cathienova.havenalchemy.util.EMCSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD, value=Dist.CLIENT)
public class HavenAlchemyClient
{
    private static final Minecraft CLIENT = Minecraft.getInstance();

    //private static CompoundTag havenAlchemyNbt;

    public HavenAlchemyClient()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {

    }

    @SubscribeEvent
    public static void registerBlockColor(RegisterColorHandlersEvent.Block event) {
        event.register(new FacadeBlockColor(), ModBlocks.facade_block.get());
    }

    @SubscribeEvent
    public static void modelInit(ModelEvent.RegisterGeometryLoaders event) {
        CableModelLoader.register(event);
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGuiOverlayEvent event)
    {
        Level level = CLIENT.level;
        assert level != null;
        int x = 0;
        int y = 0;
        Color color = new Color(255, 255, 255);

        long playerEMC = getClientPlayerEMC();
        Component textComponent;
        if (playerEMC > 0)
        {
            textComponent = Component.nullToEmpty("EMC: " + playerEMC);
        }
        else
        {
            textComponent = Component.nullToEmpty("");
        }
        Font font = CLIENT.font;
        event.getGuiGraphics().drawString(font, textComponent, x, y, color.getRGB());
    }

    public static long getClientPlayerEMC() {
        /*
        long emc = EMCSystem.GetEMCFromPlayer(CLIENT.player);
        if (havenAlchemyNbt != null && havenAlchemyNbt.contains("emc")) {
            emc = havenAlchemyNbt.getLong("emc");
        }
         */
        return EMCSystem.GetEMCFromPlayer(CLIENT.player);
    }

    public static List<Component> getEmcText(ItemStack stack) {
        List<Component> list = new ArrayList<>();
        long emc;
        try {
            emc = EMCSystem.getMap().getOrDefault(EMCSystem.itemToId(stack.getItem()), 0L);
        } catch (Exception e) {
            emc = 0;
        }
        if (emc == 0) {
            return list;
        }
        list.add(Component.literal("§bEMC: §2" + String.format("%,d", emc)));
        if (stack.getCount() > 1) {
            list.add(Component.literal("§bEMC Stack: §2" + String.format("%,d", emc * stack.getCount())));
        }
        return list;
    }
}
