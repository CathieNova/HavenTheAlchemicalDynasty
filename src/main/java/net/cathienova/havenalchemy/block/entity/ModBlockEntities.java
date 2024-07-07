package net.cathienova.havenalchemy.block.entity;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.block.chests.*;
import net.cathienova.havenalchemy.cables.blocks.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HavenAlchemy.MOD_ID);

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

    public static final RegistryObject<BlockEntityType<AlchemicalChamberBlockEntity>> alchemical_chamber = BLOCK_ENTITIES.register("alchemical_chamber",
            () -> BlockEntityType.Builder.of(AlchemicalChamberBlockEntity::new, ModBlocks.alchemical_chamber.get()).build(null));

    public static final RegistryObject<BlockEntityType<AlchemicalProcessorBlockEntity>> alchemical_processor = BLOCK_ENTITIES.register("alchemical_processor",
            () -> BlockEntityType.Builder.of(AlchemicalProcessorBlockEntity::new, ModBlocks.alchemical_processor.get()).build(null));

    public static final RegistryObject<BlockEntityType<AlchemicalCondenserBlockEntity>> alchemical_condenser = BLOCK_ENTITIES.register("alchemical_condenser",
            () -> BlockEntityType.Builder.of(AlchemicalCondenserBlockEntity::new, ModBlocks.alchemical_condenser.get()).build(null));

    public static final RegistryObject<BlockEntityType<AlchemicalTransmutationBlockEntity>> alchemical_transmutation = BLOCK_ENTITIES.register("alchemical_transmutation",
            () -> BlockEntityType.Builder.of(AlchemicalTransmutationBlockEntity::new, ModBlocks.alchemical_transmutation.get()).build(null));

    public static final RegistryObject<BlockEntityType<GeneratorBlockEntity>> generator_block_entity = BLOCK_ENTITIES.register("generator_block",
            () -> BlockEntityType.Builder.of(GeneratorBlockEntity::new, ModBlocks.generator_block.get()).build(null));

    public static final RegistryObject<BlockEntityType<ChargerBlockEntity>> charger_block_entity = BLOCK_ENTITIES.register("charger_block",
            () -> BlockEntityType.Builder.of(ChargerBlockEntity::new, ModBlocks.charger_block.get()).build(null));

    public static final RegistryObject<BlockEntityType<CableBlockEntity>> cable_block_entity = BLOCK_ENTITIES.register("cable",
            () -> BlockEntityType.Builder.of(CableBlockEntity::new, ModBlocks.cable_block.get()).build(null));

    public static final RegistryObject<BlockEntityType<FacadeBlockEntity>> facade_block_entity = BLOCK_ENTITIES.register("facade",
            () -> BlockEntityType.Builder.of(FacadeBlockEntity::new, ModBlocks.facade_block.get()).build(null));

    public static final RegistryObject<BlockEntityType<AlchemicalChestBlockEntity>> alchemical_chest = BLOCK_ENTITIES.register("alchemical_chest",
            () -> BlockEntityType.Builder.of(AlchemicalChestBlockEntity::new, ModBlocks.alchemical_chest.get()).build(null));
}
