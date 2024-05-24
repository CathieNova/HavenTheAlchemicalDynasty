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

    public static final RegistryObject<BlockEntityType<DirtChestBlockEntity>> dirt_chest = BLOCK_ENTITIES.register("dirt_chest",
            () -> BlockEntityType.Builder.of(DirtChestBlockEntity::new, ModBlocks.dirt_chest.get()).build(null));

    public static final RegistryObject<BlockEntityType<CopperChestBlockEntity>> copper_chest = BLOCK_ENTITIES.register("copper_chest",
            () -> BlockEntityType.Builder.of(CopperChestBlockEntity::new, ModBlocks.copper_chest.get()).build(null));

    public static final RegistryObject<BlockEntityType<IronChestBlockEntity>> iron_chest = BLOCK_ENTITIES.register("iron_chest",
            () -> BlockEntityType.Builder.of(IronChestBlockEntity::new, ModBlocks.iron_chest.get()).build(null));

    public static final RegistryObject<BlockEntityType<GoldChestBlockEntity>> gold_chest = BLOCK_ENTITIES.register("gold_chest",
            () -> BlockEntityType.Builder.of(GoldChestBlockEntity::new, ModBlocks.gold_chest.get()).build(null));

    public static final RegistryObject<BlockEntityType<DiamondChestBlockEntity>> diamond_chest = BLOCK_ENTITIES.register("diamond_chest",
            () -> BlockEntityType.Builder.of(DiamondChestBlockEntity::new, ModBlocks.diamond_chest.get()).build(null));

    public static final RegistryObject<BlockEntityType<ObsidianChestBlockEntity>> obsidian_chest = BLOCK_ENTITIES.register("obsidian_chest",
            () -> BlockEntityType.Builder.of(ObsidianChestBlockEntity::new, ModBlocks.obsidian_chest.get()).build(null));

    public static final RegistryObject<BlockEntityType<EmeraldChestBlockEntity>> emerald_chest = BLOCK_ENTITIES.register("emerald_chest",
            () -> BlockEntityType.Builder.of(EmeraldChestBlockEntity::new, ModBlocks.emerald_chest.get()).build(null));

    public static final RegistryObject<BlockEntityType<NetheriteChestBlockEntity>> netherite_chest = BLOCK_ENTITIES.register("netherite_chest",
            () -> BlockEntityType.Builder.of(NetheriteChestBlockEntity::new, ModBlocks.netherite_chest.get()).build(null));

    public static final RegistryObject<BlockEntityType<StoneChestBlockEntity>> stone_chest = BLOCK_ENTITIES.register("stone_chest",
            () -> BlockEntityType.Builder.of(StoneChestBlockEntity::new, ModBlocks.stone_chest.get()).build(null));
}
