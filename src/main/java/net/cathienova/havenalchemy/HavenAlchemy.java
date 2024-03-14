package net.cathienova.havenalchemy;

import com.mojang.logging.LogUtils;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.block.entity.ModBlockEntities;
import net.cathienova.havenalchemy.handler.BootsofMeowHandler;
import net.cathienova.havenalchemy.handler.MobDropHandler;
import net.cathienova.havenalchemy.item.ModCreativeModTabs;
import net.cathienova.havenalchemy.item.ModItems;
import net.cathienova.havenalchemy.loot.ModLootModifier;
import net.cathienova.havenalchemy.networking.ModMessages;
import net.cathienova.havenalchemy.recipe.ModRecipes;
import net.cathienova.havenalchemy.screen.AlchemicalChamberScreen;
import net.cathienova.havenalchemy.screen.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(HavenAlchemy.MOD_ID)
public class HavenAlchemy
{
    public static final String MOD_ID = "havenalchemy";
    public static final Logger LOGGER = LogUtils.getLogger();

    public HavenAlchemy()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeModTabs.register(modEventBus);
        ModLootModifier.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModMessages.register();
        MinecraftForge.EVENT_BUS.register(new MobDropHandler());
        MinecraftForge.EVENT_BUS.register(BootsofMeowHandler.class);
        ModRecipes.register(modEventBus);
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModMenuTypes.ALCHEMICAL_CHAMBER_MENU.get(), AlchemicalChamberScreen::new);
        }
    }
}
