package net.cathienova.havenalchemy.datagen;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.item.ModItems;
import net.cathienova.havenalchemy.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
                               CompletableFuture<TagLookup<Block>> lookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, lookupCompletableFuture, HavenAlchemy.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider)
    {
        this.tag(ItemTags.COALS)
                .add(ModItems.alchemical_coal.get())
                .add(ModItems.ethern_coal.get())
                .add(ModItems.aether_fuel.get())
            ;

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.dark_matter_helmet.get())
                .add(ModItems.dark_matter_chestplate.get())
                .add(ModItems.dark_matter_leggings.get())
                .add(ModItems.dark_matter_boots.get())
                .add(ModItems.red_matter_helmet.get())
                .add(ModItems.red_matter_chestplate.get())
                .add(ModItems.red_matter_leggings.get())
                .add(ModItems.red_matter_boots.get())
                .add(ModItems.neosphore_helmet.get())
                .add(ModItems.neosphore_chestplate.get())
                .add(ModItems.neosphore_leggings.get())
                .add(ModItems.neosphore_boots.get())
                ;

        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.charmel_log.get().asItem())
                .add(ModBlocks.charmel_wood.get().asItem())
                .add(ModBlocks.stripped_charmel_log.get().asItem())
                .add(ModBlocks.stripped_charmel_wood.get().asItem())
            ;

        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.charmel_planks.get().asItem())
            ;

        this.tag(ItemTags.WOODEN_SLABS)
                .add(ModBlocks.charmel_slab.get().asItem())
            ;

        this.tag(ItemTags.WOODEN_STAIRS)
                .add(ModBlocks.charmel_stairs.get().asItem())
            ;

        this.tag(ModTags.Items.spirits)
                .add(ModItems.essentia_spirit.get())
                .add(ModItems.mysterium_spirit.get())
                .add(ModItems.vitalium_spirit.get())
                .add(ModItems.celestium_spirit.get())
                .add(ModItems.eternium_spirit.get())
                .add(ModItems.coal_spirit.get())
                .add(ModItems.coral_spirit.get())
                .add(ModItems.deepslate_spirit.get())
                .add(ModItems.diamond_spirit.get())
                .add(ModItems.dirt_spirit.get())
                .add(ModItems.dye_spirit.get())
                .add(ModItems.emerald_spirit.get())
                .add(ModItems.end_spirit.get())
                .add(ModItems.experience_spirit.get())
                .add(ModItems.fire_spirit.get())
                .add(ModItems.glowstone_spirit.get())
                .add(ModItems.gold_spirit.get())
                .add(ModItems.ice_spirit.get())
                .add(ModItems.iron_spirit.get())
                .add(ModItems.lapis_spirit.get())
                .add(ModItems.nature_spirit.get())
                .add(ModItems.nether_spirit.get())
                .add(ModItems.nether_quartz_spirit.get())
                .add(ModItems.obsidian_spirit.get())
                .add(ModItems.redstone_spirit.get())
                .add(ModItems.stone_spirit.get())
                .add(ModItems.water_spirit.get())
                .add(ModItems.wood_spirit.get())
                .add(ModItems.amethyst_spirit.get())
                .add(ModItems.copper_spirit.get())
                .add(ModItems.honey_spirit.get())
                .add(ModItems.prismarine_spirit.get())
                .add(ModItems.netherite_spirit.get())
                .add(ModItems.air_spirit.get())
                .add(ModItems.earth_spirit.get())
            ;
        this.tag(ModTags.Items.seeds)
                .add(ModItems.essentia_seeds.get())
                .add(ModItems.coal_seeds.get())
                .add(ModItems.coral_seeds.get())
                .add(ModItems.deepslate_seeds.get())
                .add(ModItems.diamond_seeds.get())
                .add(ModItems.dirt_seeds.get())
                .add(ModItems.dye_seeds.get())
                .add(ModItems.emerald_seeds.get())
                .add(ModItems.end_seeds.get())
                .add(ModItems.experience_seeds.get())
                .add(ModItems.fire_seeds.get())
                .add(ModItems.glowstone_seeds.get())
                .add(ModItems.gold_seeds.get())
                .add(ModItems.ice_seeds.get())
                .add(ModItems.iron_seeds.get())
                .add(ModItems.lapis_seeds.get())
                .add(ModItems.nature_seeds.get())
                .add(ModItems.nether_seeds.get())
                .add(ModItems.nether_quartz_seeds.get())
                .add(ModItems.obsidian_seeds.get())
                .add(ModItems.redstone_seeds.get())
                .add(ModItems.stone_seeds.get())
                .add(ModItems.water_seeds.get())
                .add(ModItems.wood_seeds.get())
                .add(ModItems.amethyst_seeds.get())
                .add(ModItems.copper_seeds.get())
                .add(ModItems.honey_seeds.get())
                .add(ModItems.prismarine_seeds.get())
                .add(ModItems.netherite_seeds.get())
                .add(ModItems.air_seeds.get())
                .add(ModItems.earth_seeds.get())
                ;
    }
}
