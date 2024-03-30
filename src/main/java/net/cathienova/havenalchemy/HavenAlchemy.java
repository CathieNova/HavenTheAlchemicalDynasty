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
import net.cathienova.havenalchemy.screen.*;
import net.cathienova.havenalchemy.screen.chests.*;
import net.cathienova.havenalchemy.util.EMCSystem;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static net.cathienova.havenalchemy.util.EMCSystem.loadEmcValues;

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
        ModMessages.register();
        ModMenuTypes.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(new MobDropHandler());
        MinecraftForge.EVENT_BUS.register(BootsofMeowHandler.class);
        ModRecipes.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        loadEmcValues();
    }

    @SubscribeEvent
    public void onServerJoin(ServerStartingEvent event)
    {
        loadEmcValues();
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            event.enqueueWork(() -> {
                MenuScreens.register(ModMenuTypes.ALCHEMICAL_CHAMBER_MENU.get(), AlchemicalChamberScreen::new);
                MenuScreens.register(ModMenuTypes.ALCHEMICAL_CONDENSER_MENU.get(), AlchemicalCondenserScreen::new);
                MenuScreens.register(ModMenuTypes.DIRT_CHEST_MENU.get(), DirtChestScreen::new);
                MenuScreens.register(ModMenuTypes.STONE_CHEST_MENU.get(), StoneChestScreen::new);
                MenuScreens.register(ModMenuTypes.COPPER_CHEST_MENU.get(), CopperChestScreen::new);
                MenuScreens.register(ModMenuTypes.IRON_CHEST_MENU.get(), IronChestScreen::new);
                MenuScreens.register(ModMenuTypes.GOLD_CHEST_MENU.get(), GoldChestScreen::new);
                MenuScreens.register(ModMenuTypes.OBSIDIAN_CHEST_MENU.get(), ObsidianChestScreen::new);
                MenuScreens.register(ModMenuTypes.DIAMOND_CHEST_MENU.get(), DiamondChestScreen::new);
                MenuScreens.register(ModMenuTypes.ALCHEMICAL_CHEST_MENU.get(), AlchemicalChestScreen::new);
                MenuScreens.register(ModMenuTypes.EMERALD_CHEST_MENU.get(), EmeraldChestScreen::new);
                MenuScreens.register(ModMenuTypes.NETHERITE_CHEST_MENU.get(), NetheriteChestScreen::new);
                MenuScreens.register(ModMenuTypes.GENERATOR_BLOCK_MENU.get(), GeneratorScreen::new);
                //loadEmcValues();
            });
        }
    }
}
