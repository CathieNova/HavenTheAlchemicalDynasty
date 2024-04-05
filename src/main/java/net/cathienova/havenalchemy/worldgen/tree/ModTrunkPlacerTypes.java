package net.cathienova.havenalchemy.worldgen.tree;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.worldgen.tree.custom.CharmelTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, HavenAlchemy.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<CharmelTrunkPlacer>> charmel_trunk_placer =
            TRUNK_PLACER.register("charmel_trunk_placer", () -> new TrunkPlacerType<>(CharmelTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACER.register(eventBus);
    }
}