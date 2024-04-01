package net.cathienova.havenalchemy.datagen;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, HavenAlchemy.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.alchemical_coal_block.get())
                .add(ModBlocks.ethern_coal_block.get())
                .add(ModBlocks.aether_fuel_block.get())
                .add(ModBlocks.asphalt.get())
                .add(ModBlocks.asphalt_bricks.get())
                .add(ModBlocks.dark_matter_block.get())
                .add(ModBlocks.red_matter_block.get())
                .add(ModBlocks.essentia_spirit_block.get())
                .add(ModBlocks.raw_neosphore_block.get())
                .add(ModBlocks.neosphore_block.get())
                .add(ModBlocks.neosphore_ore.get())
                .add(ModBlocks.basphalt_cobblestone.get())
                .add(ModBlocks.basphalt_stone.get())
                .add(ModBlocks.basphalt_stone_bricks.get())
                .add(ModBlocks.basphalt_stone_bricks_1.get())
                .add(ModBlocks.basphalt_stone_bricks_2.get())
                .add(ModBlocks.basphalt_stone_bricks_3.get())
                .add(ModBlocks.basphalt_stone_bricks_4.get())
                .add(ModBlocks.basphalt_stone_bricks_5.get())
                .add(ModBlocks.basphalt_stone_bricks_6.get())
                .add(ModBlocks.basphalt_stone_bricks_7.get())
                .add(ModBlocks.basphalt_stone_bricks_8.get())
                .add(ModBlocks.basphalt_stone_bricks_9.get())
                .add(ModBlocks.basphalt_stone_bricks_10.get())
                .add(ModBlocks.basphalt_stone_bricks_11.get())
                .add(ModBlocks.basphalt_stone_bricks_12.get())
                .add(ModBlocks.basphalt_stone_bricks_13.get())
                .add(ModBlocks.basphalt_stone_bricks_14.get())
                .add(ModBlocks.basphalt_stone_bricks_15.get())
                .add(ModBlocks.basphalt_stone_bricks_16.get())
                .add(ModBlocks.basphalt_stone_bricks_17.get())
                .add(ModBlocks.basphalt_stone_bricks_18.get())
                .add(ModBlocks.basphalt_stone_bricks_19.get())
                .add(ModBlocks.basphalt_stone_bricks_20.get())
                .add(ModBlocks.basphalt_stone_bricks_21.get())
                .add(ModBlocks.basphalt_stone_bricks_22.get())
                .add(ModBlocks.basphalt_stone_bricks_23.get())
                .add(ModBlocks.basphalt_stone_bricks_24.get())
                .add(ModBlocks.basphalt_stone_bricks_25.get())
                .add(ModBlocks.basphalt_stone_bricks_26.get())
                .add(ModBlocks.basphalt_stone_bricks_27.get())
                .add(ModBlocks.basphalt_stone_bricks_28.get())
                .add(ModBlocks.basphalt_stone_stairs.get())
                .add(ModBlocks.basphalt_cobblestone_stairs.get())
                .add(ModBlocks.basphalt_stone_bricks_stairs.get())
                .add(ModBlocks.basphalt_stone_fence.get())
                .add(ModBlocks.basphalt_cobblestone_fence.get())
                .add(ModBlocks.basphalt_stone_bricks_fence.get())
                .add(ModBlocks.basphalt_stone_wall.get())
                .add(ModBlocks.basphalt_cobblestone_wall.get())
                .add(ModBlocks.basphalt_stone_bricks_wall.get())
                .add(ModBlocks.basphalt_stone_slabs.get())
                .add(ModBlocks.basphalt_cobblestone_slabs.get())
                .add(ModBlocks.basphalt_stone_bricks_slabs.get())
                .add(ModBlocks.basphalt_stone_button.get())
                .add(ModBlocks.basphalt_cobblestone_button.get())
                .add(ModBlocks.basphalt_stone_bricks_button.get())
                .add(ModBlocks.basphalt_stone_fence_gate.get())
                .add(ModBlocks.basphalt_cobblestone_fence_gate.get())
                .add(ModBlocks.basphalt_stone_bricks_fence_gate.get())
                .add(ModBlocks.charger_block.get())
                .add(ModBlocks.generator_block.get())
                ;

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.alchemical_coal_block.get())
                .add(ModBlocks.ethern_coal_block.get())
                .add(ModBlocks.aether_fuel_block.get())
                .add(ModBlocks.basphalt_cobblestone.get())
                .add(ModBlocks.basphalt_stone.get())
                .add(ModBlocks.basphalt_cobblestone_stairs.get())
                .add(ModBlocks.basphalt_stone_bricks.get())
                .add(ModBlocks.basphalt_stone_bricks_1.get())
                .add(ModBlocks.basphalt_stone_bricks_2.get())
                .add(ModBlocks.basphalt_stone_bricks_3.get())
                .add(ModBlocks.basphalt_stone_bricks_4.get())
                .add(ModBlocks.basphalt_stone_bricks_5.get())
                .add(ModBlocks.basphalt_stone_bricks_6.get())
                .add(ModBlocks.basphalt_stone_bricks_7.get())
                .add(ModBlocks.basphalt_stone_bricks_8.get())
                .add(ModBlocks.basphalt_stone_bricks_9.get())
                .add(ModBlocks.basphalt_stone_bricks_10.get())
                .add(ModBlocks.basphalt_stone_bricks_11.get())
                .add(ModBlocks.basphalt_stone_bricks_12.get())
                .add(ModBlocks.basphalt_stone_bricks_13.get())
                .add(ModBlocks.basphalt_stone_bricks_14.get())
                .add(ModBlocks.basphalt_stone_bricks_15.get())
                .add(ModBlocks.basphalt_stone_bricks_16.get())
                .add(ModBlocks.basphalt_stone_bricks_17.get())
                .add(ModBlocks.basphalt_stone_bricks_18.get())
                .add(ModBlocks.basphalt_stone_bricks_19.get())
                .add(ModBlocks.basphalt_stone_bricks_20.get())
                .add(ModBlocks.basphalt_stone_bricks_21.get())
                .add(ModBlocks.basphalt_stone_bricks_22.get())
                .add(ModBlocks.basphalt_stone_bricks_23.get())
                .add(ModBlocks.basphalt_stone_bricks_24.get())
                .add(ModBlocks.basphalt_stone_bricks_25.get())
                .add(ModBlocks.basphalt_stone_bricks_26.get())
                .add(ModBlocks.basphalt_stone_bricks_27.get())
                .add(ModBlocks.basphalt_stone_bricks_28.get())
                .add(ModBlocks.basphalt_stone_stairs.get())
                .add(ModBlocks.basphalt_stone_bricks_stairs.get())
                .add(ModBlocks.basphalt_stone_fence.get())
                .add(ModBlocks.basphalt_cobblestone_fence.get())
                .add(ModBlocks.basphalt_stone_bricks_fence.get())
                .add(ModBlocks.basphalt_stone_wall.get())
                .add(ModBlocks.basphalt_cobblestone_wall.get())
                .add(ModBlocks.basphalt_stone_bricks_wall.get())
                .add(ModBlocks.basphalt_stone_slabs.get())
                .add(ModBlocks.basphalt_cobblestone_slabs.get())
                .add(ModBlocks.basphalt_stone_bricks_slabs.get())
                .add(ModBlocks.basphalt_stone_button.get())
                .add(ModBlocks.basphalt_cobblestone_button.get())
                .add(ModBlocks.basphalt_stone_bricks_button.get())
                .add(ModBlocks.basphalt_stone_fence_gate.get())
                .add(ModBlocks.basphalt_cobblestone_fence_gate.get())
                .add(ModBlocks.basphalt_stone_bricks_fence_gate.get())
                .add(ModBlocks.charger_block.get())
                .add(ModBlocks.generator_block.get())
                ;

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                ;

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.essentia_spirit_block.get())
                .add(ModBlocks.dark_matter_block.get())
                .add(ModBlocks.red_matter_block.get())
                .add(ModBlocks.raw_neosphore_block.get())
                .add(ModBlocks.neosphore_block.get())
                .add(ModBlocks.raw_neosphore_block.get())
                ;

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                ;

        this.tag(Tags.Blocks.CHESTS)
                .add(ModBlocks.alchemical_chest.get())
                .add(ModBlocks.dirt_chest.get())
                .add(ModBlocks.stone_chest.get())
                .add(ModBlocks.copper_chest.get())
                .add(ModBlocks.iron_chest.get())
                .add(ModBlocks.gold_chest.get())
                .add(ModBlocks.obsidian_chest.get())
                .add(ModBlocks.diamond_chest.get())
                .add(ModBlocks.emerald_chest.get())
                .add(ModBlocks.netherite_chest.get())
                ;

        this.tag(ModTags.Blocks.needs_dark_matter_tool)
        ;

        this.tag(ModTags.Blocks.needs_red_matter_tool)
                .add(ModBlocks.neosphore_ore.get())
        ;

        this.tag(ModTags.Blocks.needs_neosphore_tool)
        ;

        this.tag(BlockTags.FENCES)
                .add(ModBlocks.basphalt_stone_fence.get())
                .add(ModBlocks.basphalt_cobblestone_fence.get())
                .add(ModBlocks.basphalt_stone_bricks_fence.get())
                .add(ModBlocks.charmel_fence.get())
        ;

        this.tag(BlockTags.WALLS)
                .add(ModBlocks.basphalt_stone_wall.get())
                .add(ModBlocks.basphalt_cobblestone_wall.get())
                .add(ModBlocks.basphalt_stone_bricks_wall.get())
        ;

        this.tag(BlockTags.SLABS)
                .add(ModBlocks.basphalt_stone_slabs.get())
                .add(ModBlocks.basphalt_cobblestone_slabs.get())
                .add(ModBlocks.basphalt_stone_bricks_slabs.get())
                .add((ModBlocks.charmel_slab.get()))
        ;

        this.tag(BlockTags.STAIRS)
                .add(ModBlocks.basphalt_stone_stairs.get())
                .add(ModBlocks.basphalt_cobblestone_stairs.get())
                .add(ModBlocks.basphalt_stone_bricks_stairs.get())
                .add(ModBlocks.charmel_stairs.get())
        ;

        this.tag(BlockTags.BUTTONS)
                .add(ModBlocks.basphalt_stone_button.get())
                .add(ModBlocks.basphalt_cobblestone_button.get())
                .add(ModBlocks.basphalt_stone_bricks_button.get())
                .add(ModBlocks.charmel_button.get())
        ;

        this.tag(BlockTags.PRESSURE_PLATES)
                .add(ModBlocks.charmel_pressure_plate.get())
        ;

        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.basphalt_stone_fence_gate.get())
                .add(ModBlocks.basphalt_cobblestone_fence_gate.get())
                .add(ModBlocks.basphalt_stone_bricks_fence_gate.get())
                .add(ModBlocks.charmel_fence_gate.get())
        ;

        this.tag(ModTags.Blocks.basphalt_stone)
                .add(ModBlocks.basphalt_stone.get())
        ;

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.charmel_log.get())
                .add(ModBlocks.charmel_wood.get())
                .add(ModBlocks.stripped_charmel_log.get())
                .add(ModBlocks.stripped_charmel_wood.get())
        ;

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.charmel_planks.get())
        ;

        this.tag(ModTags.Blocks.suspicious_basphalt)
                .add(ModBlocks.basphalt_stone.get())
        ;

        this.tag(BlockTags.CROPS)
                .add(ModBlocks.air_crop.get())
                .add(ModBlocks.fire_crop.get())
                .add(ModBlocks.water_crop.get())
                .add(ModBlocks.earth_crop.get())
                .add(ModBlocks.essentia_crop.get())
                .add(ModBlocks.amethyst_crop.get())
                .add(ModBlocks.coal_crop.get())
                .add(ModBlocks.copper_crop.get())
                .add(ModBlocks.coral_crop.get())
                .add(ModBlocks.deepslate_crop.get())
                .add(ModBlocks.diamond_crop.get())
                .add(ModBlocks.dirt_crop.get())
                .add(ModBlocks.dye_crop.get())
                .add(ModBlocks.emerald_crop.get())
                .add(ModBlocks.end_crop.get())
                .add(ModBlocks.experience_crop.get())
                .add(ModBlocks.glowstone_crop.get())
                .add(ModBlocks.gold_crop.get())
                .add(ModBlocks.honey_crop.get())
                .add(ModBlocks.ice_crop.get())
                .add(ModBlocks.iron_crop.get())
                .add(ModBlocks.lapis_crop.get())
                .add(ModBlocks.nature_crop.get())
                .add(ModBlocks.nether_crop.get())
                .add(ModBlocks.netherite_crop.get())
                .add(ModBlocks.nether_quartz_crop.get())
                .add(ModBlocks.obsidian_crop.get())
                .add(ModBlocks.prismarine_crop.get())
                .add(ModBlocks.redstone_crop.get())
                .add(ModBlocks.stone_crop.get())
                .add(ModBlocks.wood_crop.get())
                ;
    }
}