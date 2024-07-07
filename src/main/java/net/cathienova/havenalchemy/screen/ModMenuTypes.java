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

    public static final RegistryObject<MenuType<AlchemicalTransmutationMenu>> ALCHEMICAL_TRANSMUTATION_MENU =
            registerMenuType("alchemical_transmutation_menu", AlchemicalTransmutationMenu::new);

    public static final RegistryObject<MenuType<GeneratorMenu>> GENERATOR_BLOCK_MENU =
            registerMenuType("generator_block_menu", (windowId, inv, data) -> new GeneratorMenu(windowId, inv.player, data.readBlockPos()));

    public static final RegistryObject<MenuType<AlchemicalChestMenu>> ALCHEMICAL_CHEST_MENU =
            registerMenuType("alchemical_chest_menu", AlchemicalChestMenu::new);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
