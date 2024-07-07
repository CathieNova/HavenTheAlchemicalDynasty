package net.cathienova.havenalchemy;

import com.mojang.logging.LogUtils;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.block.entity.ModBlockEntities;
import net.cathienova.havenalchemy.commands.ModCommands;
import net.cathienova.havenalchemy.config.CommonConfig;
import net.cathienova.havenalchemy.events.FluidInit;
import net.cathienova.havenalchemy.events.HammerRendering;
import net.cathienova.havenalchemy.events.TrowelRendering;
import net.cathienova.havenalchemy.handler.BootsofMeowHandler;
import net.cathienova.havenalchemy.handler.MobDropHandler;
import net.cathienova.havenalchemy.item.ModCreativeModTabs;
import net.cathienova.havenalchemy.item.ModItems;
import net.cathienova.havenalchemy.loot.ModLootModifier;
import net.cathienova.havenalchemy.networking.ModMessages;
import net.cathienova.havenalchemy.recipe.ModRecipes;
import net.cathienova.havenalchemy.screen.*;
import net.cathienova.havenalchemy.screen.chests.*;
import net.cathienova.havenalchemy.worldgen.tree.ModFoliagePlacers;
import net.cathienova.havenalchemy.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;

import static net.cathienova.havenalchemy.util.EMCSystem.loadEmcValues;

@Mod(HavenAlchemy.MOD_ID)
public class HavenAlchemy
{
    public static final String MOD_ID = "havenalchemy";
    public static final Logger LOGGER = LogUtils.getLogger();
    static final ForgeConfigSpec commonSpec;
    public static final CommonConfig c_config;

    static {
        final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        commonSpec = specPair.getRight();
        c_config = specPair.getLeft();
    }

    public HavenAlchemy()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonSpec, HavenAlchemy.MOD_ID + "/HavenAlchemy-Config.toml");
        bus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        ModBlocks.register(bus);
        ModItems.register(bus);
        ModCreativeModTabs.register(bus);
        ModLootModifier.register(bus);
        ModBlockEntities.register(bus);
        ModTrunkPlacerTypes.register(bus);
        ModFoliagePlacers.register(bus);
        ModMessages.register();
        ModMenuTypes.register(bus);
        FluidInit.FLUID_TYPES.register(bus);
        FluidInit.FLUIDS.register(bus);
        MinecraftForge.EVENT_BUS.register(new MobDropHandler());
        MinecraftForge.EVENT_BUS.register(BootsofMeowHandler.class);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> TrowelRendering::new);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> HammerRendering::new);
        ModRecipes.register(bus);
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
                MenuScreens.register(ModMenuTypes.ALCHEMICAL_PROCESSOR_MENU.get(), AlchemicalProcessorScreen::new);
                MenuScreens.register(ModMenuTypes.ALCHEMICAL_CONDENSER_MENU.get(), AlchemicalCondenserScreen::new);
                MenuScreens.register(ModMenuTypes.ALCHEMICAL_TRANSMUTATION_MENU.get(), AlchemicalTransmutationScreen::new);
                MenuScreens.register(ModMenuTypes.ALCHEMICAL_CHEST_MENU.get(), AlchemicalChestScreen::new);
                MenuScreens.register(ModMenuTypes.GENERATOR_BLOCK_MENU.get(), GeneratorScreen::new);
                loadEmcValues();
            });
        }
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        ModCommands.register(event.getDispatcher());
    }
}
