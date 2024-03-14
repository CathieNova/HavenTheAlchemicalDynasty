package net.cathienova.havenalchemy.block.entity;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
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
}
