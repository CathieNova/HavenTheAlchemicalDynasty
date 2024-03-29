package net.cathienova.havenalchemy.datagen;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.item.ModItems;
import net.cathienova.havenalchemy.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
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
        this.tag(ItemTags.LOGS)
                .add(ModBlocks.charmel_log.get().asItem())
                .add(ModBlocks.stripped_charmel_log.get().asItem())
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

        this.tag(ModTags.Items.dyes)
                .add(Items.BLACK_DYE)
                .add(Items.BLUE_DYE)
                .add(Items.BROWN_DYE)
                .add(Items.CYAN_DYE)
                .add(Items.GRAY_DYE)
                .add(Items.GREEN_DYE)
                .add(Items.LIGHT_BLUE_DYE)
                .add(Items.LIGHT_GRAY_DYE)
                .add(Items.LIME_DYE)
                .add(Items.MAGENTA_DYE)
                .add(Items.ORANGE_DYE)
                .add(Items.PINK_DYE)
                .add(Items.PURPLE_DYE)
                .add(Items.RED_DYE)
                .add(Items.WHITE_DYE)
                .add(Items.YELLOW_DYE)
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

        this.tag(ModTags.Items.bark)
                .add(ModItems.oak_bark.get())
                .add(ModItems.spruce_bark.get())
                .add(ModItems.birch_bark.get())
                .add(ModItems.jungle_bark.get())
                .add(ModItems.acacia_bark.get())
                .add(ModItems.dark_oak_bark.get())
                .add(ModItems.crimson_bark.get())
                .add(ModItems.warped_bark.get())
                .add(ModItems.charmel_bark.get())
                .add(ModItems.cherry_bark.get())
                .add(ModItems.mangrove_bark.get())
                ;

        this.tag(ModTags.Items.coral)
                .add(Items.DEAD_BRAIN_CORAL_BLOCK)
                .add(Items.DEAD_BUBBLE_CORAL_BLOCK)
                .add(Items.DEAD_FIRE_CORAL_BLOCK)
                .add(Items.DEAD_HORN_CORAL_BLOCK)
                .add(Items.DEAD_TUBE_CORAL_BLOCK)
                .add(Items.BRAIN_CORAL_BLOCK)
                .add(Items.BUBBLE_CORAL_BLOCK)
                .add(Items.FIRE_CORAL_BLOCK)
                .add(Items.HORN_CORAL_BLOCK)
                .add(Items.TUBE_CORAL_BLOCK)
                .add(Items.DEAD_BRAIN_CORAL)
                .add(Items.DEAD_BUBBLE_CORAL)
                .add(Items.DEAD_FIRE_CORAL)
                .add(Items.DEAD_HORN_CORAL)
                .add(Items.DEAD_TUBE_CORAL)
                ;

        this.tag(ModTags.Items.alchemy_stones)
                .add(ModItems.alchemy_stone.get())
                .add(ModItems.alchemy_stone_fractured.get())
                ;

        this.tag(ModTags.Items.forgeCopperDust).add(ModItems.copper_dust.get());
        this.tag(ModTags.Items.forgeGoldDust).add(ModItems.gold_dust.get());
        this.tag(ModTags.Items.forgeIronDust).add(ModItems.iron_dust.get());
        this.tag(ModTags.Items.forgeLeadDust).add(ModItems.lead_dust.get());
        this.tag(ModTags.Items.forgeNickelDust).add(ModItems.nickel_dust.get());
        this.tag(ModTags.Items.forgeOsmiumDust).add(ModItems.osmium_dust.get());
        this.tag(ModTags.Items.forgeSilverDust).add(ModItems.silver_dust.get());
        this.tag(ModTags.Items.forgeTinDust).add(ModItems.tin_dust.get());
        this.tag(ModTags.Items.forgeUraniumDust).add(ModItems.uranium_dust.get());
        this.tag(ModTags.Items.forgeZincDust).add(ModItems.zinc_dust.get());
        this.tag(ModTags.Items.forgeNetheriteDust).add(ModItems.netherite_dust.get());

        this.tag(ModTags.Items.oreHammers)
                .add(ModItems.copper_ore_hammer.get())
                .add(ModItems.iron_ore_hammer.get())
                .add(ModItems.gold_ore_hammer.get())
                .add(ModItems.diamond_ore_hammer.get())
                .add(ModItems.neosphore_ore_hammer.get())
                ;

        this.tag(ModTags.Items.forgeLeadIngot).add(ModItems.lead_ingot.get());
        this.tag(ModTags.Items.forgeNickelIngot).add(ModItems.nickel_ingot.get());
        this.tag(ModTags.Items.forgeOsmiumIngot).add(ModItems.osmium_ingot.get());
        this.tag(ModTags.Items.forgeSilverIngot).add(ModItems.silver_ingot.get());
        this.tag(ModTags.Items.forgeTinIngot).add(ModItems.tin_ingot.get());
        this.tag(ModTags.Items.forgeUraniumIngot).add(ModItems.uranium_ingot.get());
        this.tag(ModTags.Items.forgeZincIngot).add(ModItems.zinc_ingot.get());

    }
}
