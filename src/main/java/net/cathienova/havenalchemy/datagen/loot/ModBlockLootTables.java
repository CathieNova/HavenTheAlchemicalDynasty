package net.cathienova.havenalchemy.datagen.loot;

import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.block.horticulture.EssentiaCrop;
import net.cathienova.havenalchemy.block.horticulture.StoneCrop;
import net.cathienova.havenalchemy.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate()
    {
        this.dropSelf(ModBlocks.alchemical_coal_block.get());
        this.dropSelf(ModBlocks.ethern_coal_block.get());
        this.dropSelf(ModBlocks.aether_fuel_block.get());
        this.dropSelf(ModBlocks.asphalt.get());
        this.dropSelf(ModBlocks.asphalt_bricks.get());
        this.dropSelf(ModBlocks.dark_matter_block.get());
        this.dropSelf(ModBlocks.red_matter_block.get());
        this.dropSelf(ModBlocks.essentia_spirit_block.get());
        this.dropSelf(ModBlocks.mysterium_spirit_block.get());
        this.dropSelf(ModBlocks.vitalium_spirit_block.get());
        this.dropSelf(ModBlocks.celestium_spirit_block.get());
        this.dropSelf(ModBlocks.eternium_spirit_block.get());
        this.dropSelf(ModBlocks.generator_block.get());
        this.dropSelf(ModBlocks.charger_block.get());
        this.dropSelf(ModBlocks.cable_block.get());
        this.dropSelf(ModBlocks.facade_block.get());

        this.dropSelf(ModBlocks.alchemical_chamber.get());

        this.add(ModBlocks.neosphore_ore.get(),
                block -> createOreLikeOreDrops(ModBlocks.neosphore_ore.get(), ModItems.raw_neosphore.get()));

        this.add(ModBlocks.basphalt_stone.get(),
                block -> createSilkDrop(ModBlocks.basphalt_stone.get(), ModBlocks.basphalt_cobblestone.get().asItem()));

        this.dropSelf(ModBlocks.charmel_log.get());
        this.dropSelf(ModBlocks.charmel_wood.get());
        this.dropSelf(ModBlocks.stripped_charmel_log.get());
        this.dropSelf(ModBlocks.stripped_charmel_wood.get());
        this.add(ModBlocks.charmel_leaves.get(), block -> createLeavesDrops(ModBlocks.charmel_sapling.get(), ModBlocks.charmel_leaves.get()));
        this.dropSelf(ModBlocks.charmel_planks.get());
        this.dropSelf(ModBlocks.charmel_stairs.get());
        this.dropSelf(ModBlocks.charmel_slab.get());
        this.dropSelf(ModBlocks.charmel_fence.get());
        this.dropSelf(ModBlocks.charmel_fence_gate.get());
        this.dropSelf(ModBlocks.charmel_button.get());
        this.dropSelf(ModBlocks.charmel_pressure_plate.get());
        this.dropSelf(ModBlocks.charmel_sapling.get());

        this.dropSelf(ModBlocks.neosphore_block.get());
        this.dropSelf(ModBlocks.raw_neosphore_block.get());
        this.dropSelf(ModBlocks.basphalt_cobblestone.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks.get());
        this.add(ModBlocks.basphalt_stone_slabs.get(), block -> createSlabItemTable(ModBlocks.basphalt_stone_slabs.get()));
        this.add(ModBlocks.basphalt_cobblestone_slabs.get(), block -> createSlabItemTable(ModBlocks.basphalt_cobblestone_slabs.get()));
        this.add(ModBlocks.basphalt_stone_bricks_slabs.get(), block -> createSlabItemTable(ModBlocks.basphalt_stone_bricks_slabs.get()));
        this.dropSelf(ModBlocks.basphalt_stone_stairs.get());
        this.dropSelf(ModBlocks.basphalt_cobblestone_stairs.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_stairs.get());
        this.dropSelf(ModBlocks.basphalt_stone_button.get());
        this.dropSelf(ModBlocks.basphalt_cobblestone_button.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_button.get());
        this.dropSelf(ModBlocks.basphalt_stone_fence.get());
        this.dropSelf(ModBlocks.basphalt_cobblestone_fence.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_fence.get());
        this.dropSelf(ModBlocks.basphalt_stone_fence_gate.get());
        this.dropSelf(ModBlocks.basphalt_cobblestone_fence_gate.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_fence_gate.get());

        this.dropSelf(ModBlocks.basphalt_stone_wall.get());
        this.dropSelf(ModBlocks.basphalt_cobblestone_wall.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_wall.get());

        //this.dropOther(ModBlocks.suspicious_basphalt.get(), Items.AIR);

        // Add crop drops
        this.add(ModBlocks.coal_crop.get(), createCropDrops(ModBlocks.coal_crop.get(), ModItems.coal_spirit.get(),
                ModItems.coal_seeds.get(), cropDrops(ModBlocks.coal_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.coral_crop.get(), createCropDrops(ModBlocks.coral_crop.get(), ModItems.coral_spirit.get(),
                ModItems.coral_seeds.get(), cropDrops(ModBlocks.coral_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.deepslate_crop.get(), createCropDrops(ModBlocks.deepslate_crop.get(), ModItems.deepslate_spirit.get(),
                ModItems.deepslate_seeds.get(), cropDrops(ModBlocks.deepslate_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.diamond_crop.get(), createCropDrops(ModBlocks.diamond_crop.get(), ModItems.diamond_spirit.get(),
                ModItems.diamond_seeds.get(), cropDrops(ModBlocks.diamond_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.dirt_crop.get(), createCropDrops(ModBlocks.dirt_crop.get(), ModItems.dirt_spirit.get(),
                ModItems.dirt_seeds.get(), cropDrops(ModBlocks.dirt_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.dye_crop.get(), createCropDrops(ModBlocks.dye_crop.get(), ModItems.dye_spirit.get(),
                ModItems.dye_seeds.get(), cropDrops(ModBlocks.dye_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.emerald_crop.get(), createCropDrops(ModBlocks.emerald_crop.get(), ModItems.emerald_spirit.get(),
                ModItems.emerald_seeds.get(), cropDrops(ModBlocks.emerald_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.end_crop.get(), createCropDrops(ModBlocks.end_crop.get(), ModItems.end_spirit.get(),
                ModItems.end_seeds.get(), cropDrops(ModBlocks.end_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.essentia_crop.get(), createCropDrops(ModBlocks.essentia_crop.get(), ModItems.essentia_spirit.get(),
                ModItems.essentia_seeds.get(), cropDrops(ModBlocks.essentia_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.experience_crop.get(), createCropDrops(ModBlocks.experience_crop.get(), ModItems.experience_spirit.get(),
                ModItems.experience_seeds.get(), cropDrops(ModBlocks.experience_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.fire_crop.get(), createCropDrops(ModBlocks.fire_crop.get(), ModItems.fire_spirit.get(),
                ModItems.fire_seeds.get(), cropDrops(ModBlocks.fire_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.glowstone_crop.get(), createCropDrops(ModBlocks.glowstone_crop.get(), ModItems.glowstone_spirit.get(),
                ModItems.glowstone_seeds.get(), cropDrops(ModBlocks.glowstone_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.gold_crop.get(), createCropDrops(ModBlocks.gold_crop.get(), ModItems.gold_spirit.get(),
                ModItems.gold_seeds.get(), cropDrops(ModBlocks.gold_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.ice_crop.get(), createCropDrops(ModBlocks.ice_crop.get(), ModItems.ice_spirit.get(),
                ModItems.ice_seeds.get(), cropDrops(ModBlocks.ice_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.iron_crop.get(), createCropDrops(ModBlocks.iron_crop.get(), ModItems.iron_spirit.get(),
                ModItems.iron_seeds.get(), cropDrops(ModBlocks.iron_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.lapis_crop.get(), createCropDrops(ModBlocks.lapis_crop.get(), ModItems.lapis_spirit.get(),
                ModItems.lapis_seeds.get(), cropDrops(ModBlocks.lapis_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.nature_crop.get(), createCropDrops(ModBlocks.nature_crop.get(), ModItems.nature_spirit.get(),
                ModItems.nature_seeds.get(), cropDrops(ModBlocks.nature_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.nether_crop.get(), createCropDrops(ModBlocks.nether_crop.get(), ModItems.nether_spirit.get(),
                ModItems.nether_seeds.get(), cropDrops(ModBlocks.nether_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.nether_quartz_crop.get(), createCropDrops(ModBlocks.nether_quartz_crop.get(), ModItems.nether_quartz_spirit.get(),
                ModItems.nether_quartz_seeds.get(), cropDrops(ModBlocks.nether_quartz_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.obsidian_crop.get(), createCropDrops(ModBlocks.obsidian_crop.get(), ModItems.obsidian_spirit.get(),
                ModItems.obsidian_seeds.get(), cropDrops(ModBlocks.obsidian_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.redstone_crop.get(), createCropDrops(ModBlocks.redstone_crop.get(), ModItems.redstone_spirit.get(),
                ModItems.redstone_seeds.get(), cropDrops(ModBlocks.redstone_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.stone_crop.get(), createCropDrops(ModBlocks.stone_crop.get(), ModItems.stone_spirit.get(),
                ModItems.stone_seeds.get(), cropDrops(ModBlocks.stone_crop.get(), StoneCrop.AGE, 7)));

        this.add(ModBlocks.water_crop.get(), createCropDrops(ModBlocks.water_crop.get(), ModItems.water_spirit.get(),
                ModItems.water_seeds.get(), cropDrops(ModBlocks.water_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.wood_crop.get(), createCropDrops(ModBlocks.wood_crop.get(), ModItems.wood_spirit.get(),
                ModItems.wood_seeds.get(), cropDrops(ModBlocks.wood_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.amethyst_crop.get(), createCropDrops(ModBlocks.amethyst_crop.get(), ModItems.amethyst_spirit.get(),
                ModItems.amethyst_seeds.get(), cropDrops(ModBlocks.amethyst_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.copper_crop.get(), createCropDrops(ModBlocks.copper_crop.get(), ModItems.copper_spirit.get(),
                ModItems.copper_seeds.get(), cropDrops(ModBlocks.copper_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.honey_crop.get(), createCropDrops(ModBlocks.honey_crop.get(), ModItems.honey_spirit.get(),
                ModItems.honey_seeds.get(), cropDrops(ModBlocks.honey_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.prismarine_crop.get(), createCropDrops(ModBlocks.prismarine_crop.get(), ModItems.prismarine_spirit.get(),
                ModItems.prismarine_seeds.get(), cropDrops(ModBlocks.prismarine_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.netherite_crop.get(), createCropDrops(ModBlocks.netherite_crop.get(), ModItems.netherite_spirit.get(),
                ModItems.netherite_seeds.get(), cropDrops(ModBlocks.netherite_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.air_crop.get(), createCropDrops(ModBlocks.air_crop.get(), ModItems.air_spirit.get(),
                ModItems.air_seeds.get(), cropDrops(ModBlocks.air_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.earth_crop.get(), createCropDrops(ModBlocks.earth_crop.get(), ModItems.earth_spirit.get(),
                ModItems.earth_seeds.get(), cropDrops(ModBlocks.earth_crop.get(), EssentiaCrop.AGE, 7)));
    }

    protected LootItemCondition.Builder cropDrops(Block block, IntegerProperty ageProperty, int age)
    {
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, age));
    }

    protected LootTable.Builder createOreLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected LootTable.Builder createSilkDrop(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}