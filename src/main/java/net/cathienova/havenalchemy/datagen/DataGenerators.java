package net.cathienova.havenalchemy.datagen;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.datagen.loot.ModBlockLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DataGenerators
{
    @Mod.EventBusSubscriber(modid = HavenAlchemy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModRecipeProvider
    {
        @SubscribeEvent
        public static void gatherData(GatherDataEvent event) {
            DataGenerator generator = event.getGenerator();
            PackOutput output = generator.getPackOutput();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
            CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

            generator.addProvider(event.includeClient(), new ModEngLangProvider(output));
            generator.addProvider(event.includeServer(), new net.cathienova.havenalchemy.datagen.ModRecipeProvider(output));

            generator.addProvider(event.includeServer(), ModLootTableProvider.create(output));

            generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, existingFileHelper));
            generator.addProvider(event.includeClient(), new ModItemModelProvider(output, existingFileHelper));

            ModBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(),
                    new ModBlockTagGenerator(output, lookupProvider, existingFileHelper));
            generator.addProvider(event.includeServer(), new ModItemTagGenerator(output, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

            generator.addProvider(event.includeServer(), new ModWorldGenProvider(output, lookupProvider));

            generator.addProvider(event.includeServer(), new ModGlobalLootModifiersProvider(output));

        }
    }
}
