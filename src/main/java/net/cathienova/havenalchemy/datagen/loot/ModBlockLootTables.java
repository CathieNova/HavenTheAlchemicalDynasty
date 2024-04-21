package net.cathienova.havenalchemy.datagen.loot;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.block.horticulture.EssentiaCrop;
import net.cathienova.havenalchemy.block.horticulture.StoneCrop;
import net.cathienova.havenalchemy.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
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
        this.dropSelf(ModBlocks.speed_plate_i.get());
        this.dropSelf(ModBlocks.speed_plate_ii.get());
        this.dropSelf(ModBlocks.speed_plate_iii.get());

        this.dropSelf(ModBlocks.alchemical_chamber.get());
        this.dropSelf(ModBlocks.alchemical_processor.get());
        this.dropSelf(ModBlocks.alchemical_condenser.get());
        this.dropSelf(ModBlocks.alchemical_chest.get());
        this.dropSelf(ModBlocks.dirt_chest.get());
        this.dropSelf(ModBlocks.stone_chest.get());
        this.dropSelf(ModBlocks.copper_chest.get());
        this.dropSelf(ModBlocks.iron_chest.get());
        this.dropSelf(ModBlocks.gold_chest.get());
        this.dropSelf(ModBlocks.obsidian_chest.get());
        this.dropSelf(ModBlocks.diamond_chest.get());
        this.dropSelf(ModBlocks.emerald_chest.get());
        this.dropSelf(ModBlocks.netherite_chest.get());

        this.add(ModBlocks.neosphore_ore.get(),
                block -> createOreLikeOreDrops(ModBlocks.neosphore_ore.get(), ModItems.raw_neosphore.get()));

        this.add(ModBlocks.havenite_ore.get(),
                block -> createOreLikeOreDrops(ModBlocks.havenite_ore.get(), ModItems.raw_havenite.get()));

        this.add(ModBlocks.andesite_havenite_ore.get(),
                block -> createOreLikeOreDrops(ModBlocks.andesite_havenite_ore.get(), ModItems.raw_havenite.get()));

        this.add(ModBlocks.diorite_havenite_ore.get(),
                block -> createOreLikeOreDrops(ModBlocks.diorite_havenite_ore.get(), ModItems.raw_havenite.get()));

        this.add(ModBlocks.granite_havenite_ore.get(),
                block -> createOreLikeOreDrops(ModBlocks.granite_havenite_ore.get(), ModItems.raw_havenite.get()));

        this.add(ModBlocks.deepslate_havenite_ore.get(),
                block -> createOreLikeOreDrops(ModBlocks.deepslate_havenite_ore.get(), ModItems.raw_havenite.get()));

        this.dropSelf(ModBlocks.havenite_block.get());
        this.dropSelf(ModBlocks.raw_havenite_block.get());

        this.add(ModBlocks.basphalt_stone.get(),
                block -> createSilkDrop(ModBlocks.basphalt_stone.get(), ModBlocks.basphalt_cobblestone.get().asItem()));

        this.dropSelf(ModBlocks.charmel_log.get());
        this.dropSelf(ModBlocks.charmel_wood.get());
        this.dropSelf(ModBlocks.stripped_charmel_log.get());
        this.dropSelf(ModBlocks.stripped_charmel_wood.get());
        this.add(ModBlocks.charmel_leaves.get(), block -> createLeavesDrops(block, ModBlocks.charmel_sapling.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.charmel_planks.get());
        this.dropSelf(ModBlocks.charmel_stairs.get());
        this.dropSelf(ModBlocks.charmel_slab.get());
        this.dropSelf(ModBlocks.charmel_fence.get());
        this.dropSelf(ModBlocks.charmel_fence_gate.get());
        this.dropSelf(ModBlocks.charmel_button.get());
        this.dropSelf(ModBlocks.charmel_pressure_plate.get());
        this.dropSelf(ModBlocks.charmel_sapling.get());

        this.dropSelf(Objects.requireNonNull(ModBlocks.BLOCKS.getEntries().stream().filter(block -> block.getId().getPath().contains("acid_fluid")).findFirst().get().get()));

        this.dropSelf(ModBlocks.neosphore_block.get());
        this.dropSelf(ModBlocks.raw_neosphore_block.get());
        this.dropSelf(ModBlocks.basphalt_cobblestone.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_1.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_2.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_3.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_4.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_5.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_6.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_7.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_8.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_9.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_10.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_11.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_12.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_13.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_14.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_15.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_16.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_17.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_18.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_19.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_20.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_21.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_22.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_23.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_24.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_25.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_26.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_27.get());
        this.dropSelf(ModBlocks.basphalt_stone_bricks_28.get());
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
        this.add(ModBlocks.coal_crop.get(), createLowCropDrops(ModBlocks.coal_crop.get(), ModItems.coal_spirit.get(),
                ModItems.coal_seeds.get(), cropDrops(ModBlocks.coal_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.coral_crop.get(), createLowCropDrops(ModBlocks.coral_crop.get(), ModItems.coral_spirit.get(),
                ModItems.coral_seeds.get(), cropDrops(ModBlocks.coral_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.deepslate_crop.get(), createLowCropDrops(ModBlocks.deepslate_crop.get(), ModItems.deepslate_spirit.get(),
                ModItems.deepslate_seeds.get(), cropDrops(ModBlocks.deepslate_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.diamond_crop.get(), createLowCropDrops(ModBlocks.diamond_crop.get(), ModItems.diamond_spirit.get(),
                ModItems.diamond_seeds.get(), cropDrops(ModBlocks.diamond_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.dirt_crop.get(), createLowCropDrops(ModBlocks.dirt_crop.get(), ModItems.dirt_spirit.get(),
                ModItems.dirt_seeds.get(), cropDrops(ModBlocks.dirt_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.dye_crop.get(), createLowCropDrops(ModBlocks.dye_crop.get(), ModItems.dye_spirit.get(),
                ModItems.dye_seeds.get(), cropDrops(ModBlocks.dye_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.emerald_crop.get(), createLowCropDrops(ModBlocks.emerald_crop.get(), ModItems.emerald_spirit.get(),
                ModItems.emerald_seeds.get(), cropDrops(ModBlocks.emerald_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.end_crop.get(), createLowCropDrops(ModBlocks.end_crop.get(), ModItems.end_spirit.get(),
                ModItems.end_seeds.get(), cropDrops(ModBlocks.end_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.essentia_crop.get(), createLowCropDrops(ModBlocks.essentia_crop.get(), ModItems.essentia_spirit.get(),
                ModItems.essentia_seeds.get(), cropDrops(ModBlocks.essentia_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.experience_crop.get(), createLowCropDrops(ModBlocks.experience_crop.get(), ModItems.experience_spirit.get(),
                ModItems.experience_seeds.get(), cropDrops(ModBlocks.experience_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.fire_crop.get(), createLowCropDrops(ModBlocks.fire_crop.get(), ModItems.fire_spirit.get(),
                ModItems.fire_seeds.get(), cropDrops(ModBlocks.fire_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.glowstone_crop.get(), createLowCropDrops(ModBlocks.glowstone_crop.get(), ModItems.glowstone_spirit.get(),
                ModItems.glowstone_seeds.get(), cropDrops(ModBlocks.glowstone_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.gold_crop.get(), createLowCropDrops(ModBlocks.gold_crop.get(), ModItems.gold_spirit.get(),
                ModItems.gold_seeds.get(), cropDrops(ModBlocks.gold_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.ice_crop.get(), createLowCropDrops(ModBlocks.ice_crop.get(), ModItems.ice_spirit.get(),
                ModItems.ice_seeds.get(), cropDrops(ModBlocks.ice_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.iron_crop.get(), createLowCropDrops(ModBlocks.iron_crop.get(), ModItems.iron_spirit.get(),
                ModItems.iron_seeds.get(), cropDrops(ModBlocks.iron_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.lapis_crop.get(), createLowCropDrops(ModBlocks.lapis_crop.get(), ModItems.lapis_spirit.get(),
                ModItems.lapis_seeds.get(), cropDrops(ModBlocks.lapis_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.nature_crop.get(), createLowCropDrops(ModBlocks.nature_crop.get(), ModItems.nature_spirit.get(),
                ModItems.nature_seeds.get(), cropDrops(ModBlocks.nature_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.nether_crop.get(), createLowCropDrops(ModBlocks.nether_crop.get(), ModItems.nether_spirit.get(),
                ModItems.nether_seeds.get(), cropDrops(ModBlocks.nether_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.nether_quartz_crop.get(), createLowCropDrops(ModBlocks.nether_quartz_crop.get(), ModItems.nether_quartz_spirit.get(),
                ModItems.nether_quartz_seeds.get(), cropDrops(ModBlocks.nether_quartz_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.obsidian_crop.get(), createLowCropDrops(ModBlocks.obsidian_crop.get(), ModItems.obsidian_spirit.get(),
                ModItems.obsidian_seeds.get(), cropDrops(ModBlocks.obsidian_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.redstone_crop.get(), createLowCropDrops(ModBlocks.redstone_crop.get(), ModItems.redstone_spirit.get(),
                ModItems.redstone_seeds.get(), cropDrops(ModBlocks.redstone_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.stone_crop.get(), createLowCropDrops(ModBlocks.stone_crop.get(), ModItems.stone_spirit.get(),
                ModItems.stone_seeds.get(), cropDrops(ModBlocks.stone_crop.get(), StoneCrop.AGE, 7)));

        this.add(ModBlocks.water_crop.get(), createLowCropDrops(ModBlocks.water_crop.get(), ModItems.water_spirit.get(),
                ModItems.water_seeds.get(), cropDrops(ModBlocks.water_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.wood_crop.get(), createLowCropDrops(ModBlocks.wood_crop.get(), ModItems.wood_spirit.get(),
                ModItems.wood_seeds.get(), cropDrops(ModBlocks.wood_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.amethyst_crop.get(), createLowCropDrops(ModBlocks.amethyst_crop.get(), ModItems.amethyst_spirit.get(),
                ModItems.amethyst_seeds.get(), cropDrops(ModBlocks.amethyst_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.copper_crop.get(), createLowCropDrops(ModBlocks.copper_crop.get(), ModItems.copper_spirit.get(),
                ModItems.copper_seeds.get(), cropDrops(ModBlocks.copper_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.honey_crop.get(), createLowCropDrops(ModBlocks.honey_crop.get(), ModItems.honey_spirit.get(),
                ModItems.honey_seeds.get(), cropDrops(ModBlocks.honey_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.prismarine_crop.get(), createLowCropDrops(ModBlocks.prismarine_crop.get(), ModItems.prismarine_spirit.get(),
                ModItems.prismarine_seeds.get(), cropDrops(ModBlocks.prismarine_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.netherite_crop.get(), createLowCropDrops(ModBlocks.netherite_crop.get(), ModItems.netherite_spirit.get(),
                ModItems.netherite_seeds.get(), cropDrops(ModBlocks.netherite_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.air_crop.get(), createLowCropDrops(ModBlocks.air_crop.get(), ModItems.air_spirit.get(),
                ModItems.air_seeds.get(), cropDrops(ModBlocks.air_crop.get(), EssentiaCrop.AGE, 7)));

        this.add(ModBlocks.earth_crop.get(), createLowCropDrops(ModBlocks.earth_crop.get(), ModItems.earth_spirit.get(),
                ModItems.earth_seeds.get(), cropDrops(ModBlocks.earth_crop.get(), EssentiaCrop.AGE, 7)));
    }

    protected LootTable.Builder createLowCropDrops(Block pCropBlock, Item pGrownCropItem, Item pSeedsItem, LootItemCondition.Builder pDropGrownCropCondition) {
        return this.applyExplosionDecay(pCropBlock, LootTable
                .lootTable()
                .withPool(LootPool
                        .lootPool()
                        .add(LootItem
                                .lootTableItem(pGrownCropItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .when(pDropGrownCropCondition)))
                .withPool(LootPool
                        .lootPool()
                        .add(LootItem
                                .lootTableItem(pSeedsItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))))));
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