package net.cathienova.havenalchemy.screen;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.screen.chests.*;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes
{
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, HavenAlchemy.MOD_ID);

    public static final RegistryObject<MenuType<AlchemicalChamberMenu>> ALCHEMICAL_CHAMBER_MENU =
            registerMenuType("alchemical_chamber_menu", AlchemicalChamberMenu::new);

    public static final RegistryObject<MenuType<AlchemicalProcessorMenu>> ALCHEMICAL_PROCESSOR_MENU =
            registerMenuType("alchemical_processor_menu", AlchemicalProcessorMenu::new);

    public static final RegistryObject<MenuType<AlchemicalCondenserMenu>> ALCHEMICAL_CONDENSER_MENU =
            registerMenuType("alchemical_condenser_menu", AlchemicalCondenserMenu::new);

    public static final RegistryObject<MenuType<GeneratorMenu>> GENERATOR_BLOCK_MENU =
            registerMenuType("generator_block_menu", (windowId, inv, data) -> new GeneratorMenu(windowId, inv.player, data.readBlockPos()));

    public static final RegistryObject<MenuType<AlchemicalChestMenu>> ALCHEMICAL_CHEST_MENU =
            registerMenuType("alchemical_chest_menu", AlchemicalChestMenu::new);

    public static final RegistryObject<MenuType<DirtChestMenu>> DIRT_CHEST_MENU =
            registerMenuType("dirt_chest_menu", DirtChestMenu::new);

    public static final RegistryObject<MenuType<StoneChestMenu>> STONE_CHEST_MENU =
            registerMenuType("stone_chest_menu", StoneChestMenu::new);

    public static final RegistryObject<MenuType<CopperChestMenu>> COPPER_CHEST_MENU =
            registerMenuType("copper_chest_menu", CopperChestMenu::new);

    public static final RegistryObject<MenuType<IronChestMenu>> IRON_CHEST_MENU =
            registerMenuType("iron_chest_menu", IronChestMenu::new);

    public static final RegistryObject<MenuType<GoldChestMenu>> GOLD_CHEST_MENU =
            registerMenuType("gold_chest_menu", GoldChestMenu::new);

    public static final RegistryObject<MenuType<DiamondChestMenu>> DIAMOND_CHEST_MENU =
            registerMenuType("diamond_chest_menu", DiamondChestMenu::new);

    public static final RegistryObject<MenuType<ObsidianChestMenu>> OBSIDIAN_CHEST_MENU =
            registerMenuType("obsidian_chest_menu", ObsidianChestMenu::new);

    public static final RegistryObject<MenuType<EmeraldChestMenu>> EMERALD_CHEST_MENU =
            registerMenuType("emerald_chest_menu", EmeraldChestMenu::new);

    public static final RegistryObject<MenuType<NetheriteChestMenu>> NETHERITE_CHEST_MENU =
            registerMenuType("netherite_chest_menu", NetheriteChestMenu::new);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
