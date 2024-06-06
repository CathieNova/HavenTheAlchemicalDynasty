package net.cathienova.havenalchemy.datagen;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.item.ModCreativeModTabs;
import net.cathienova.havenalchemy.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEngLangProvider extends LanguageProvider
{
    public ModEngLangProvider(PackOutput output)
    {
        super(output, HavenAlchemy.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        add(ModCreativeModTabs.havenalchemy_tab_title, "Haven Alchemy");

        addBlock(ModBlocks.alchemical_coal_block, "Alchemical Coal Block");
        addBlock(ModBlocks.ethern_coal_block, "Ethern Coal Block");
        addBlock(ModBlocks.aether_fuel_block, "Aether Fuel Block");
        addBlock(ModBlocks.dirt_chest, "Dirt Chest");
        addBlock(ModBlocks.stone_chest, "Stone Chest");
        addBlock(ModBlocks.copper_chest, "Copper Chest");
        addBlock(ModBlocks.iron_chest, "Iron Chest");
        addBlock(ModBlocks.gold_chest, "Gold Chest");
        addBlock(ModBlocks.obsidian_chest, "Obsidian Chest");
        addBlock(ModBlocks.diamond_chest, "Diamond Chest");
        addBlock(ModBlocks.emerald_chest, "Emerald Chest");
        addBlock(ModBlocks.netherite_chest, "Netherite Chest");
        addBlock(ModBlocks.alchemical_chest, "Alchemical Chest");
        addBlock(ModBlocks.alchemical_condenser, "Alchemical Condenser");
        addBlock(ModBlocks.alchemical_transmutation, "Alchemical Transmutation");
        //addBlock(ModBlocks.alchemical_tablet, "Alchemical Tablet");
        //addBlock(ModBlocks.pedestal, "Pedestal");
        addBlock(ModBlocks.asphalt, "Asphalt");
        addBlock(ModBlocks.asphalt_bricks, "Asphalt Bricks");
        addBlock(ModBlocks.dark_matter_block, "Dark Matter Block");
        addBlock(ModBlocks.red_matter_block, "Red Matter Block");
        addBlock(ModBlocks.essentia_spirit_block, "Essentia Spirit Block");
        addBlock(ModBlocks.mysterium_spirit_block, "Mysterium Spirit Block");
        addBlock(ModBlocks.vitalium_spirit_block, "Vitalium Spirit Block");
        addBlock(ModBlocks.celestium_spirit_block, "Celestium Spirit Block");
        addBlock(ModBlocks.eternium_spirit_block, "Eternium Spirit Block");

        addBlock(ModBlocks.speed_plate_i, "Speed Plate I");
        addBlock(ModBlocks.speed_plate_ii, "Speed Plate II");
        addBlock(ModBlocks.speed_plate_iii, "Speed Plate III");
        add("block.havenalchemy.speed_plate.tooltip", "Moves entities in the Arrow direction.");

        addBlock(ModBlocks.charmel_log, "Charmel Log");
        addBlock(ModBlocks.charmel_leaves, "Charmel Leaves");
        addBlock(ModBlocks.charmel_planks, "Charmel Planks");
        addBlock(ModBlocks.stripped_charmel_log, "Stripped Charmel Log");
        addBlock(ModBlocks.stripped_charmel_wood, "Stripped Charmel Wood");
        addBlock(ModBlocks.charmel_wood, "Charmel Wood");
        addBlock(ModBlocks.charmel_slab, "Charmel Slab");
        addBlock(ModBlocks.charmel_stairs, "Charmel Stairs");
        addBlock(ModBlocks.charmel_fence, "Charmel Fence");
        addBlock(ModBlocks.charmel_fence_gate, "Charmel Fence Gate");
        addBlock(ModBlocks.charmel_pressure_plate, "Charmel Pressure Plate");
        addBlock(ModBlocks.charmel_button, "Charmel Button");
        addBlock(ModBlocks.charmel_sapling, "Charmel Sapling");
        addBlock(ModBlocks.raw_neosphore_block, "Raw Neosphore Block");
        addBlock(ModBlocks.neosphore_block, "Neosphore Block");
        addBlock(ModBlocks.neosphore_ore, "Neosphore Ore");

        addBlock(ModBlocks.basphalt_stone, "Basphalt Stone");
        addBlock(ModBlocks.basphalt_stone_button, "Basphalt Stone Button");
        addBlock(ModBlocks.basphalt_stone_fence_gate, "Basphalt Stone Fence Gate");
        addBlock(ModBlocks.basphalt_stone_slabs, "Basphalt Stone Brick Slabs");
        addBlock(ModBlocks.basphalt_stone_stairs, "Basphalt Stone Brick Stairs");
        addBlock(ModBlocks.basphalt_stone_wall, "Basphalt Stone Brick Wall");
        addBlock(ModBlocks.basphalt_stone_fence, "Basphalt Stone Fence");

        addBlock(ModBlocks.basphalt_cobblestone, "Basphalt Cobblestone");
        addBlock(ModBlocks.basphalt_cobblestone_button, "Basphalt Stone Button");
        addBlock(ModBlocks.basphalt_cobblestone_fence_gate, "Basphalt Stone Fence Gate");
        //addBlock(ModBlocks.basphalt_cobblestone_pressure_plate, "Basphalt Stone Pressure Plate");
        addBlock(ModBlocks.basphalt_cobblestone_slabs, "Basphalt Stone Brick Slab");
        addBlock(ModBlocks.basphalt_cobblestone_stairs, "Basphalt Stone Brick Stairs");
        addBlock(ModBlocks.basphalt_cobblestone_wall, "Basphalt Stone Brick Wall");
        addBlock(ModBlocks.basphalt_cobblestone_fence, "Basphalt Stone Fence");

        addBlock(ModBlocks.basphalt_stone_bricks_button, "Basphalt Stone Button");
        addBlock(ModBlocks.basphalt_stone_bricks, "Basphalt Stone Bricks");
        addBlock(ModBlocks.basphalt_stone_bricks_1, "Basphalt Stone Brick 1");
        addBlock(ModBlocks.basphalt_stone_bricks_2, "Basphalt Stone Brick 2");
        addBlock(ModBlocks.basphalt_stone_bricks_3, "Basphalt Stone Brick 3");
        addBlock(ModBlocks.basphalt_stone_bricks_4, "Basphalt Stone Brick 4");
        addBlock(ModBlocks.basphalt_stone_bricks_5, "Basphalt Stone Brick 5");
        addBlock(ModBlocks.basphalt_stone_bricks_6, "Basphalt Stone Brick 6");
        addBlock(ModBlocks.basphalt_stone_bricks_7, "Basphalt Stone Brick 7");
        addBlock(ModBlocks.basphalt_stone_bricks_8, "Basphalt Stone Brick 8");
        addBlock(ModBlocks.basphalt_stone_bricks_9, "Basphalt Stone Brick 9");
        addBlock(ModBlocks.basphalt_stone_bricks_10, "Basphalt Stone Brick 10");
        addBlock(ModBlocks.basphalt_stone_bricks_11, "Basphalt Stone Brick 11");
        addBlock(ModBlocks.basphalt_stone_bricks_12, "Basphalt Stone Brick 12");
        addBlock(ModBlocks.basphalt_stone_bricks_13, "Basphalt Stone Brick 13");
        addBlock(ModBlocks.basphalt_stone_bricks_14, "Basphalt Stone Brick 14");
        addBlock(ModBlocks.basphalt_stone_bricks_15, "Basphalt Stone Brick 15");
        addBlock(ModBlocks.basphalt_stone_bricks_16, "Basphalt Stone Brick 16");
        addBlock(ModBlocks.basphalt_stone_bricks_17, "Basphalt Stone Brick 17");
        addBlock(ModBlocks.basphalt_stone_bricks_18, "Basphalt Stone Brick 18");
        addBlock(ModBlocks.basphalt_stone_bricks_19, "Basphalt Stone Brick 19");
        addBlock(ModBlocks.basphalt_stone_bricks_20, "Basphalt Stone Brick 20");
        addBlock(ModBlocks.basphalt_stone_bricks_21, "Basphalt Stone Brick 21");
        addBlock(ModBlocks.basphalt_stone_bricks_22, "Basphalt Stone Brick 22");
        addBlock(ModBlocks.basphalt_stone_bricks_23, "Basphalt Stone Brick 23");
        addBlock(ModBlocks.basphalt_stone_bricks_24, "Basphalt Stone Brick 24");
        addBlock(ModBlocks.basphalt_stone_bricks_25, "Basphalt Stone Brick 25");
        addBlock(ModBlocks.basphalt_stone_bricks_26, "Basphalt Stone Brick 26");
        addBlock(ModBlocks.basphalt_stone_bricks_27, "Basphalt Stone Brick 27");
        addBlock(ModBlocks.basphalt_stone_bricks_28, "Basphalt Stone Brick 28");
        addBlock(ModBlocks.basphalt_stone_bricks_slabs, "Basphalt Stone Brick Slab");
        addBlock(ModBlocks.basphalt_stone_bricks_stairs, "Basphalt Stone Brick Stairs");
        addBlock(ModBlocks.basphalt_stone_bricks_wall, "Basphalt Stone Brick Wall");
        addBlock(ModBlocks.basphalt_stone_bricks_fence, "Basphalt Stone Fence");
        addBlock(ModBlocks.basphalt_stone_bricks_fence_gate, "Basphalt Stone Fence Gate");

        //addBlock(ModBlocks.catacombs_portal, "Catacombs Portal");
        //addBlock(ModBlocks.suspicious_basphalt, "Suspicious Basphalt");
        addBlock(ModBlocks.alchemical_chamber, "Alchemical Chamber");
        addBlock(ModBlocks.alchemical_processor, "Alchemical Processor");

        addItem(ModItems.alchemy_stone, "Alchemy Stone");
        addItem(ModItems.alchemy_stone_fractured, "Fractured Alchemy Stone");
        add("item.havenalchemy.alchemy_stone.title", "Alchemy Crafting Table");
        addItem(ModItems.alchemical_coal, "Alchemical Coal");
        addItem(ModItems.ethern_coal, "Ethern Coal");
        addItem(ModItems.aether_fuel, "Aether Fuel");
        addItem(ModItems.alchemy_dust, "Alchemy Dust");
        addItem(ModItems.alchemy_dust_low, "Alchemy Dust Low");
        addItem(ModItems.alchemy_dust_medium, "Alchemy Dust Medium");
        addItem(ModItems.alchemy_dust_high, "Alchemy Dust High");
        addItem(ModItems.essence_shard, "Essence Shard");
        addItem(ModItems.dark_matter, "Dark Matter");
        addItem(ModItems.red_matter, "Red Matter");
        addItem(ModItems.void_matter, "Void Matter");
        addItem(ModItems.dark_matter_apple, "Dark Matter Apple");
        addItem(ModItems.red_matter_apple, "Red Matter Apple");
        addItem(ModItems.essence_apple, "Essence Apple");
        addItem(ModItems.trowel, "Trowel");
        add("item.havenalchemy.trowel.tooltip", "Right click to place random blocks from the hotbar.");
        addItem(ModItems.experience_orb, "Experience Orb");
        add("item.havenalchemy.experience_orb.tooltip", "Grants 10% experience when used.");

        addItem(ModItems.magnet, "Magnet");
        add("havenalchemy.tooltip.item.magnet", "Attracts items if equipped with a range of 5 blocks.");
        addItem(ModItems.mending_necklace, "Mending Necklace");
        addItem(ModItems.gloves_of_rawr, "Gloves of Rawr");
        add("tooltip.havenalchemy.gloves_of_rawr", "Increases the wearer's attack damage.");
        addItem(ModItems.boots_of_meow, "Boots of Meow");
        add("tooltip.havenalchemy.boots_of_meow", "Prevents fall damage and increases movement speed.");
        addItem(ModItems.ring_of_haste, "Ring of Haste");
        add("tooltip.havenalchemy.ring_of_haste", "Increases the wearer's mining speed.");
        addItem(ModItems.ring_of_love, "Ring of Love");
        add("tooltip.havenalchemy.ring_of_love", "Increases the wearer's maximum health.");
        addItem(ModItems.regeneration_pendant, "Regeneration Pendant");
        add("tooltip.havenalchemy.regeneration_pendant", "Regenerates the wearer's health.");
        addItem(ModItems.fire_pendant, "Fire Pendant");
        add("tooltip.havenalchemy.fire_pendant", "Grants the wearer immunity to fire damage.");
        addItem(ModItems.water_pendant, "Water Pendant");
        add("tooltip.havenalchemy.water_pendant", "Grants the wearer immunity to drowning.");
        addItem(ModItems.nightvision_goggles, "Nightvision Goggles");
        add("tooltip.havenalchemy.nightvision_goggles", "Grants the wearer night vision.");
        add("havenalchemy.tooltip.item.mending_necklace", "Passively mends the durability on your items when equipped.");

        add("tooltip.havenalchemy.alchemystone.0", "Press Shift for more info!");
        add("tooltip.havenalchemy.alchemystone.1", "Can transmute basic blocks and sheep on right click.");
        add("tooltip.havenalchemy.alchemystone.2", " ");
        add("tooltip.havenalchemy.alchemystone.3", "Right click air to open Crafting Table. (wip)");
        add("tooltip.havenalchemy.facade.is_facading", "Block: %s");
        add("tooltip.havenalchemy.facade", "Right click to change the facade to facade a different block");
        add("tooltip.havenalchemy.neosphore_armor", "Full set grants flight");
        add("tooltip.havenalchemy.indestructable", "Indestructable");
        add("tooltip.havenalchemy.bark", "Obtained by stripping logs.");
        add("tooltip.havenalchemy.generator", "Generates 64 RF/tick.");
        add("tooltip.havenalchemy.battery", "Stores 100,000 RF.");
        add("tooltip.havenalchemy.cable", "Transfers 128 RF/tick.");
        add("tooltip.havenalchemy.asphalt", "Grants speed I when walked on.");
        add("tooltip.havenalchemy.asphalt_bricks", "Grants speed II when walked on.");
        add("tooltip.havenalchemy.tier1", "§2Tier 1");
        add("tooltip.havenalchemy.tier2", "§6Tier 2");
        add("tooltip.havenalchemy.tier3", "§4Tier 3");
        add("tooltip.havenalchemy.tier4", "§3Tier 4");
        add("tooltip.havenalchemy.tier5", "§5Tier 5");
        add("tooltip.havenalchemy.essence_shard", "Drops from various mobs.");
        add("tooltip.havenalchemy.ore_hammer", "Used to craft raw ore into dust.");
        add("tooltip.havenalchemy.ore_hammer.durability", "Durability: %s");
        add("tooltip.havenalchemy.ore_hammer.durability.inf", "Durability: §2Infinite");
        add("tooltip.havenalchemy.alchemical_condenser", "Condenses items with EMC values.");
        add("tooltip.havenalchemy.hammer", "Breaks a 3x3 area.");
        add("tooltip.havenalchemy.hammer.durability", "Durability: %s");

        addItem(ModItems.copper_ore_hammer, "Copper Ore Hammer");
        addItem(ModItems.iron_ore_hammer, "Iron Ore Hammer");
        addItem(ModItems.gold_ore_hammer, "Gold Ore Hammer");
        addItem(ModItems.diamond_ore_hammer, "Diamond Ore Hammer");
        addItem(ModItems.havenite_ore_hammer, "Havenite Ore Hammer");
        addItem(ModItems.neosphore_ore_hammer, "Neosphore Ore Hammer");
        addItem(ModItems.copper_dust, "Copper Dust");
        addItem(ModItems.tin_dust, "Tin Dust");
        addItem(ModItems.netherite_dust, "Netherite Dust");
        addItem(ModItems.iron_dust, "Iron Dust");
        addItem(ModItems.gold_dust, "Gold Dust");
        addItem(ModItems.lead_dust, "Lead Dust");
        addItem(ModItems.silver_dust, "Silver Dust");
        addItem(ModItems.nickel_dust, "Nickel Dust");
        addItem(ModItems.uranium_dust, "Uranium Dust");
        addItem(ModItems.osmium_dust, "Osmium Dust");
        addItem(ModItems.zinc_dust, "Zinc Dust");
        addItem(ModItems.aluminum_dust, "Aluminum Dust");
        addItem(ModItems.tin_ingot, "Tin Ingot");
        addItem(ModItems.lead_ingot, "Lead Ingot");
        addItem(ModItems.silver_ingot, "Silver Ingot");
        addItem(ModItems.nickel_ingot, "Nickel Ingot");
        addItem(ModItems.uranium_ingot, "Uranium Ingot");
        addItem(ModItems.osmium_ingot, "Osmium Ingot");
        addItem(ModItems.zinc_ingot, "Zinc Ingot");
        addItem(ModItems.aluminum_ingot, "Aluminum Ingot");

        addItem(ModItems.stone_hammer, "Stone Hammer");
        addItem(ModItems.iron_hammer, "Iron Hammer");
        addItem(ModItems.golden_hammer, "Golden Hammer");
        addItem(ModItems.diamond_hammer, "Diamond Hammer");
        addItem(ModItems.netherite_hammer, "Netherite Hammer");

        addItem(ModItems.mini_coal, "Mini Coal");
        addItem(ModItems.mini_charcoal, "Mini Charcoal");
        add("item.havenalchemy.acid_fluid_bucket", "Acid Bucket");
        add("block.havenalchemy.acid_fluid", "Acid");

        addItem(ModItems.stone_crusher, "Stone Crusher");
        addItem(ModItems.iron_crusher, "Iron Crusher");
        addItem(ModItems.golden_crusher, "Golden Crusher");
        addItem(ModItems.diamond_crusher, "Diamond Crusher");
        addItem(ModItems.netherite_crusher, "Netherite Crusher");
        addItem(ModItems.havenite_crusher, "Havenite Crusher");
        addItem(ModItems.dark_matter_crusher, "Dark Matter Crusher");
        addItem(ModItems.red_matter_crusher, "Red Matter Crusher");
        addItem(ModItems.neosphore_crusher, "Neosphore Crusher");

        addBlock(ModBlocks.dust, "Dust");
        addBlock(ModBlocks.crushed_netherrack, "Crushed Netherrack");
        addBlock(ModBlocks.crushed_end_stone, "Crushed End Stone");

        addItem(ModItems.research_tier_basic, "Basic Research Tier");
        addItem(ModItems.research_tier_intermediate, "Intermediate Research Tier");
        addItem(ModItems.research_tier_advanced, "Advanced Research Tier");
        addItem(ModItems.research_tier_elite, "Elite Research Tier");
        addItem(ModItems.research_tier_ultimate, "Ultimate Research Tier");

        addItem(ModItems.havenite_dust, "Havenite Dust");
        addItem(ModItems.raw_havenite, "Raw Havenite");
        addItem(ModItems.havenite_ingot, "Havenite Ingot");
        addItem(ModItems.havenite_nugget, "Havenite Nugget");
        addBlock(ModBlocks.havenite_block, "Havenite Block");
        addBlock(ModBlocks.havenite_ore, "Havenite Ore");
        addBlock(ModBlocks.andesite_havenite_ore, "Andesite Havenite Ore");
        addBlock(ModBlocks.diorite_havenite_ore, "Diorite Havenite Ore");
        addBlock(ModBlocks.granite_havenite_ore, "Granite Havenite Ore");
        addBlock(ModBlocks.deepslate_havenite_ore, "Deepslate Havenite Ore");
        addBlock(ModBlocks.raw_havenite_block, "Raw Havenite Block");

        addItem(ModItems.raw_neosphore, "Raw Neosphore");
        addItem(ModItems.neosphore_ingot, "Neosphore Ingot");
        addItem(ModItems.neosphore_nugget, "Neosphore Nugget");
        addItem(ModItems.neosphore_helmet, "Neosphore Helmet");
        addItem(ModItems.neosphore_chestplate, "Neosphore Chestplate");
        addItem(ModItems.neosphore_leggings, "Neosphore Leggings");
        addItem(ModItems.neosphore_boots, "Neosphore Boots");
        addItem(ModItems.neosphore_sword, "Neosphore Sword");
        addItem(ModItems.neosphore_shears, "Neosphore Shears");
        addItem(ModItems.neosphore_axe, "Neosphore Axe");
        addItem(ModItems.neosphore_pickaxe, "Neosphore Pickaxe");
        addItem(ModItems.neosphore_shovel, "Neosphore Shovel");
        addItem(ModItems.neosphore_hoe, "Neosphore Hoe");
        addItem(ModItems.neosphore_shield, "Neosphore Shield");
        addItem(ModItems.neosphore_hammer, "Neosphore Hammer");

        addItem(ModItems.wooden_shears, "Wooden Shears");

        addItem(ModItems.dark_matter_helmet, "Dark Matter Helmet");
        addItem(ModItems.dark_matter_chestplate, "Dark Matter Chestplate");
        addItem(ModItems.dark_matter_leggings, "Dark Matter Leggings");
        addItem(ModItems.dark_matter_boots, "Dark Matter Boots");
        addItem(ModItems.red_matter_helmet, "Red Matter Helmet");
        addItem(ModItems.red_matter_chestplate, "Red Matter Chestplate");
        addItem(ModItems.red_matter_leggings, "Red Matter Leggings");
        addItem(ModItems.red_matter_boots, "Red Matter Boots");

        addItem(ModItems.dark_matter_sword, "Dark Matter Sword");
        addItem(ModItems.dark_matter_pickaxe, "Dark Matter Pickaxe");
        addItem(ModItems.dark_matter_axe, "Dark Matter Axe");
        addItem(ModItems.dark_matter_shovel, "Dark Matter Shovel");
        addItem(ModItems.dark_matter_hoe, "Dark Matter Hoe");
        addItem(ModItems.dark_matter_hammer, "Dark Matter Hammer");
        addItem(ModItems.dark_matter_shears, "Dark Matter Shears");
        addItem(ModItems.dark_matter_shield, "Dark Matter Shield");

        addItem(ModItems.red_matter_sword, "Red Matter Sword");
        addItem(ModItems.red_matter_pickaxe, "Red Matter Pickaxe");
        addItem(ModItems.red_matter_axe, "Red Matter Axe");
        addItem(ModItems.red_matter_shovel, "Red Matter Shovel");
        addItem(ModItems.red_matter_hoe, "Red Matter Hoe");
        addItem(ModItems.red_matter_hammer, "Red Matter Hammer");
        addItem(ModItems.red_matter_shears, "Red Matter Shears");
        addItem(ModItems.red_matter_shield, "Red Matter Shield");

        addItem(ModItems.essentia_spirit, "Essentia Spirit");
        addItem(ModItems.mysterium_spirit, "Mysterium Spirit");
        addItem(ModItems.vitalium_spirit, "Vitalium Spirit");
        addItem(ModItems.celestium_spirit, "Celestium Spirit");
        addItem(ModItems.eternium_spirit, "Eternium Spirit");

        addItem(ModItems.coal_spirit, "Coal Spirit");
        addItem(ModItems.amethyst_spirit, "Amethyst Spirit");
        addItem(ModItems.coral_spirit, "Coral Spirit");
        addItem(ModItems.diamond_spirit, "Diamond Spirit");
        addItem(ModItems.dirt_spirit, "Dirt Spirit");
        addItem(ModItems.dye_spirit, "Dye Spirit");
        addItem(ModItems.emerald_spirit, "Emerald Spirit");
        addItem(ModItems.end_spirit, "End Spirit");
        addItem(ModItems.experience_spirit, "Experience Spirit");
        addItem(ModItems.fire_spirit, "Fire Spirit");
        addItem(ModItems.glowstone_spirit, "Glowstone Spirit");
        addItem(ModItems.gold_spirit, "Gold Spirit");
        addItem(ModItems.ice_spirit, "Ice Spirit");
        addItem(ModItems.iron_spirit, "Iron Spirit");
        addItem(ModItems.lapis_spirit, "Lapis Spirit");
        addItem(ModItems.nature_spirit, "Nature Spirit");
        addItem(ModItems.nether_spirit, "Nether Spirit");
        addItem(ModItems.nether_quartz_spirit, "Nether Quartz Spirit");
        addItem(ModItems.obsidian_spirit, "Obsidian Spirit");
        addItem(ModItems.redstone_spirit, "Redstone Spirit");
        addItem(ModItems.stone_spirit, "Stone Spirit");
        addItem(ModItems.water_spirit, "Water Spirit");
        addItem(ModItems.wood_spirit, "Wood Spirit");
        addItem(ModItems.copper_spirit, "Copper Spirit");
        addItem(ModItems.honey_spirit, "Honey Spirit");
        addItem(ModItems.prismarine_spirit, "Prismarine Spirit");
        addItem(ModItems.netherite_spirit, "Netherite Spirit");
        addItem(ModItems.air_spirit, "Air Spirit");
        addItem(ModItems.earth_spirit, "Earth Spirit");
        addItem(ModItems.deepslate_spirit, "Deepslate Spirit");
        addItem(ModItems.havenite_spirit, "Havenite Spirit");

        addItem(ModItems.essentia_seeds, "Essentia Seeds");
        addItem(ModItems.coal_seeds, "Coal Seeds");
        addItem(ModItems.amethyst_seeds, "Amethyst Seeds");
        addItem(ModItems.coral_seeds, "Coral Seeds");
        addItem(ModItems.diamond_seeds, "Diamond Seeds");
        addItem(ModItems.dirt_seeds, "Dirt Seeds");
        addItem(ModItems.dye_seeds, "Dye Seeds");
        addItem(ModItems.emerald_seeds, "Emerald Seeds");
        addItem(ModItems.end_seeds, "End Seeds");
        addItem(ModItems.experience_seeds, "Experience Seeds");
        addItem(ModItems.fire_seeds, "Fire Seeds");
        addItem(ModItems.glowstone_seeds, "Glowstone Seeds");
        addItem(ModItems.gold_seeds, "Gold Seeds");
        addItem(ModItems.ice_seeds, "Ice Seeds");
        addItem(ModItems.iron_seeds, "Iron Seeds");
        addItem(ModItems.lapis_seeds, "Lapis Seeds");
        addItem(ModItems.nature_seeds, "Nature Seeds");
        addItem(ModItems.nether_seeds, "Nether Seeds");
        addItem(ModItems.nether_quartz_seeds, "Nether Quartz Seeds");
        addItem(ModItems.obsidian_seeds, "Obsidian Seeds");
        addItem(ModItems.redstone_seeds, "Redstone Seeds");
        addItem(ModItems.stone_seeds, "Stone Seeds");
        addItem(ModItems.water_seeds, "Water Seeds");
        addItem(ModItems.wood_seeds, "Wood Seeds");
        addItem(ModItems.copper_seeds, "Copper Seeds");
        addItem(ModItems.honey_seeds, "Honey Seeds");
        addItem(ModItems.prismarine_seeds, "Prismarine Seeds");
        addItem(ModItems.netherite_seeds, "Netherite Seeds");
        addItem(ModItems.air_seeds, "Air Seeds");
        addItem(ModItems.earth_seeds, "Earth Seeds");
        addItem(ModItems.deepslate_seeds, "Deepslate Seeds");
        addItem(ModItems.havenite_seeds, "Havenite Seeds");

        addItem(ModItems.acacia_bark, "Acacia Bark");
        addItem(ModItems.birch_bark, "Birch Bark");
        addItem(ModItems.dark_oak_bark, "Dark Oak Bark");
        addItem(ModItems.jungle_bark, "Jungle Bark");
        addItem(ModItems.oak_bark, "Oak Bark");
        addItem(ModItems.spruce_bark, "Spruce Bark");
        addItem(ModItems.warped_bark, "Warped Bark");
        addItem(ModItems.crimson_bark, "Crimson Bark");
        addItem(ModItems.mangrove_bark, "Mangrove Bark");
        addItem(ModItems.cherry_bark, "Cherry Bark");
        addItem(ModItems.charmel_bark, "Charmel Bark");

        addBlock(ModBlocks.generator_block, "Energy Generator");
        addBlock(ModBlocks.charger_block, "Energy Battery");
        addBlock(ModBlocks.cable_block, "Energy Cable");
        addBlock(ModBlocks.facade_block, "Energy Cable Facade");
        add("havenalchemy.screen.generator", "Generator");

        add("item.havenalchemy.smithing_template.neosphore_upgrade.applies_to", "Red Matter Equipment");
        add("item.havenalchemy.smithing_template.neosphore_upgrade.ingredients", "Neosphore Ingot");

        add("upgrade.havenalchemy.neosphore_upgrade", "Neosphore Upgrade");

        add("curios.identifier.feet", "Feet");



        add("advancement.havenalchemy.dark_matter.title", "Dark Matter");
        add("advancement.havenalchemy.dark_matter.toast", "Obtain Dark Matter");
    }
}
