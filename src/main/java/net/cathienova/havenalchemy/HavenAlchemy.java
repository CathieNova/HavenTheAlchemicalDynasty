package net.cathienova.havenalchemy;

import com.mojang.logging.LogUtils;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.block.entity.ModBlockEntities;
import net.cathienova.havenalchemy.capabilities.EmcHandler;
import net.cathienova.havenalchemy.capabilities.IEmcHandler;
import net.cathienova.havenalchemy.capabilities.PlayerEmcProvider;
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
import net.cathienova.havenalchemy.util.EMCSystem;
import net.cathienova.havenalchemy.worldgen.tree.ModFoliagePlacers;
import net.cathienova.havenalchemy.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;
import org.checkerframework.checker.units.qual.C;
import org.slf4j.Logger;

import java.awt.*;

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

        @SubscribeEvent
        public void onRenderGameOverlay(RenderGuiOverlayEvent event)
        {
            Minecraft CLIENT = Minecraft.getInstance();
            Level level = CLIENT.level;
            assert level != null;
            int x = 1000;
            int y = 750;
            Color color = new Color(255, 255, 255);

            long playerEMC = EMCSystem.GetEMCFromPlayer(CLIENT.player);
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
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        ModCommands.register(event.getDispatcher());
    }

    @SubscribeEvent
    public void onRegisterCompabilities(RegisterCapabilitiesEvent event) {
        event.register(EmcHandler.class);
    }

    // Adds the EMC Handler Capability to the player
    @SubscribeEvent
    public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player player) {
            if(!player.getCapability(PlayerEmcProvider.EMC_HANDLER).isPresent()) {
                event.addCapability(new ResourceLocation(MOD_ID), new PlayerEmcProvider());
            }
        }
    }

    // Transfers EMC from dead player to revived player
    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerEmcProvider.EMC_HANDLER).ifPresent(older ->
                    event.getEntity().getCapability(PlayerEmcProvider.EMC_HANDLER).ifPresent(newer -> {
                        CompoundTag tag = new CompoundTag();
                        older.saveNBTData(tag);
                        newer.loadNBTData(tag);
                    })
            );
        }
    }

    /**
    @SubscribeEvent
    public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if(event.getEntity() instanceof Player player && !event.getEntity().level().isClientSide) {
            IEmcHandler emcHandler = EMCSystem.getEMCHandler(player);
            System.out.println( "Does player know how to create diamonds: " + emcHandler.hasKnowledge(Items.DIAMOND));
            EMCSystem.IncrementEmc(player, 100);
            emcHandler.deconstructItem(new ItemStack(Items.DIAMOND, 1));

            System.out.println(EMCSystem.GetEMCFromPlayer(player));
            System.out.println( "Does player know how to create diamonds: " + emcHandler.hasKnowledge(Items.DIAMOND));
        }
    }
    **/
}
