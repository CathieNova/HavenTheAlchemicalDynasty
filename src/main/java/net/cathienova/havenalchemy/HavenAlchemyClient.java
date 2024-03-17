package net.cathienova.havenalchemy;

import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.cables.client.CableModelLoader;
import net.cathienova.havenalchemy.cables.client.FacadeBlockColor;
import net.cathienova.havenalchemy.util.EMCSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD, value=Dist.CLIENT)
public class HavenAlchemyClient
{
    private static final Minecraft CLIENT = Minecraft.getInstance();
    private static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("havenalchemy", "main_channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static CompoundTag havenAlchemyNbt;

    public HavenAlchemyClient()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {

    }

    @SubscribeEvent
    public static void modelInit(ModelEvent.RegisterGeometryLoaders event) {
        CableModelLoader.register(event);
    }

    @SubscribeEvent
    public static void registerBlockColor(RegisterColorHandlersEvent.Block event) {
        event.register(new FacadeBlockColor(), ModBlocks.facade_block.get());
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
        Component textComponent = Component.nullToEmpty("");
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

    private static void registerNetworkHandlers() {
        //CHANNEL.registerMessage(0, SomePacket.class, SomePacket::encode, SomePacket::decode, SomePacket::handle);
        // Implement packet registration as needed
    }

    public static long getClientPlayerEMC() {
        long emc = 0;
        if (havenAlchemyNbt != null && havenAlchemyNbt.contains("emc")) {
            emc = havenAlchemyNbt.getLong("emc");
        }
        return emc;
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
