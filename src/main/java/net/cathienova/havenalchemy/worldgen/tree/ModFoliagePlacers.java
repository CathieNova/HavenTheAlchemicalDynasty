package net.cathienova.havenalchemy.worldgen.tree;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.worldgen.tree.custom.CharmelFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, HavenAlchemy.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<CharmelFoliagePlacer>> charmel_foliage_placer =
            FOLIAGE_PLACERS.register("charmel_foliage_placer", () -> new FoliagePlacerType<>(CharmelFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACERS.register(eventBus);
    }
}