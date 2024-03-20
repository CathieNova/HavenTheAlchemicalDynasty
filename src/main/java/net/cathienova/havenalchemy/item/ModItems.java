package net.cathienova.havenalchemy.item;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.item.alchemy_stone.Alchemy_Stone;
import net.cathienova.havenalchemy.item.alchemy_stone.Alchemy_Stone_Fractured;
import net.cathienova.havenalchemy.item.artifacts.*;
import net.cathienova.havenalchemy.item.bark.*;
import net.cathienova.havenalchemy.item.fuel.FuelItem;
import net.cathienova.havenalchemy.item.orehammers.*;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HavenAlchemy.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    // Alchemy Stones
    public static final RegistryObject<Item> alchemy_stone = ITEMS.register("alchemy_stone",
            () -> new Alchemy_Stone(new Item.Properties()));

    public static final RegistryObject<Item> alchemy_stone_fractured = ITEMS.register("alchemy_stone_fractured",
            () -> new Alchemy_Stone_Fractured(new Item.Properties()));

    public static final RegistryObject<Item> trowel = ITEMS.register("trowel",
            () -> new TrowelItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).durability(512)));

// Alchemy Dusts & Materials
    public static final RegistryObject<Item> alchemy_dust = ITEMS.register("alchemy_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> alchemy_dust_low = ITEMS.register("alchemy_dust_low",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> alchemy_dust_medium = ITEMS.register("alchemy_dust_medium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> alchemy_dust_high = ITEMS.register("alchemy_dust_high",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> alchemical_coal = ITEMS.register("alchemical_coal",
            () -> new FuelItem(new Item.Properties(), 3200));

    public static final RegistryObject<Item> ethern_coal = ITEMS.register("ethern_coal",
            () -> new FuelItem(new Item.Properties(), 6400));

    public static final RegistryObject<Item> aether_fuel = ITEMS.register("aether_fuel",
            () -> new FuelItem(new Item.Properties(), 12800));

    public static final RegistryObject<Item> dark_matter = ITEMS.register("dark_matter",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> red_matter = ITEMS.register("red_matter",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> void_matter = ITEMS.register("void_matter",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> essence_shard = ITEMS.register("essence_shard",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON)));

    // Dark Matter Armor
    public static final RegistryObject<Item> dark_matter_helmet = ITEMS.register("dark_matter_helmet",
            () -> new ModItemArmor(ModArmorMaterials.dark_matter, ArmorItem.Type.HELMET,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> dark_matter_chestplate = ITEMS.register("dark_matter_chestplate",
            () -> new ModItemArmor(ModArmorMaterials.dark_matter, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> dark_matter_leggings = ITEMS.register("dark_matter_leggings",
            () -> new ModItemArmor(ModArmorMaterials.dark_matter, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> dark_matter_boots = ITEMS.register("dark_matter_boots",
            () -> new ModItemArmor(ModArmorMaterials.dark_matter, ArmorItem.Type.BOOTS,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

// Dark Matter Tools
    public static final RegistryObject<Item> dark_matter_sword = ITEMS.register("dark_matter_sword",
            () -> new SwordItem(ModToolTiers.dark_matter, 3, -3.2F,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> dark_matter_pickaxe = ITEMS.register("dark_matter_pickaxe",
            () -> new PickaxeItem(ModToolTiers.dark_matter, 1, -2.8F,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> dark_matter_axe = ITEMS.register("dark_matter_axe",
            () -> new AxeItem(ModToolTiers.dark_matter, 6, -3.0F,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> dark_matter_shovel = ITEMS.register("dark_matter_shovel",
            () -> new ShovelItem(ModToolTiers.dark_matter, 0, 0,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> dark_matter_hoe = ITEMS.register("dark_matter_hoe",
            () -> new HoeItem(ModToolTiers.dark_matter, 0, 0F,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> dark_matter_shears = ITEMS.register("dark_matter_shears",
            () -> new ShearsItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

// Red Matter Armor
    public static final RegistryObject<Item> red_matter_helmet = ITEMS.register("red_matter_helmet",
            () -> new ModItemArmor(ModArmorMaterials.red_matter, ArmorItem.Type.HELMET,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> red_matter_chestplate = ITEMS.register("red_matter_chestplate",
            () -> new ModItemArmor(ModArmorMaterials.red_matter, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> red_matter_leggings = ITEMS.register("red_matter_leggings",
            () -> new ModItemArmor(ModArmorMaterials.red_matter, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> red_matter_boots = ITEMS.register("red_matter_boots",
            () -> new ModItemArmor(ModArmorMaterials.red_matter, ArmorItem.Type.BOOTS,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

// Red Matter Tools
    public static final RegistryObject<Item> red_matter_sword = ITEMS.register("red_matter_sword",
            () -> new SwordItem(ModToolTiers.red_matter, 3, -3.2F,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> red_matter_pickaxe = ITEMS.register("red_matter_pickaxe",
            () -> new PickaxeItem(ModToolTiers.red_matter, 1, -2.8F,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> red_matter_axe = ITEMS.register("red_matter_axe",
            () -> new AxeItem(ModToolTiers.red_matter, 6, -3.0F,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> red_matter_shovel = ITEMS.register("red_matter_shovel",
            () -> new ShovelItem(ModToolTiers.red_matter, 0, 0F,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> red_matter_hoe = ITEMS.register("red_matter_hoe",
            () -> new HoeItem(ModToolTiers.red_matter, 0, 0F,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> red_matter_shears = ITEMS.register("red_matter_shears",
            () -> new ShearsItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> neosphore_helmet = ITEMS.register("neosphore_helmet",
            () -> new ModItemArmor(ModArmorMaterials.neosphore, ArmorItem.Type.HELMET,
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> neosphore_chestplate = ITEMS.register("neosphore_chestplate",
            () -> new ModItemArmor(ModArmorMaterials.neosphore, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> neosphore_leggings = ITEMS.register("neosphore_leggings",
            () -> new ModItemArmor(ModArmorMaterials.neosphore, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> neosphore_boots = ITEMS.register("neosphore_boots",
            () -> new ModItemArmor(ModArmorMaterials.neosphore, ArmorItem.Type.BOOTS,
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> neosphore_axe = ITEMS.register("neosphore_axe",
            () -> new AxeItem(ModToolTiers.NEOSPHERE, 4.0F, -3.0F,
                    new Item.Properties().stacksTo(1).durability(-1).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> neosphore_pickaxe = ITEMS.register("neosphore_pickaxe",
            () -> new PickaxeItem(ModToolTiers.NEOSPHERE, 2, -2.8F,
                    new Item.Properties().stacksTo(1).durability(-1).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> neosphore_sword = ITEMS.register("neosphore_sword",
            () -> new SwordItem(ModToolTiers.NEOSPHERE, 3, -2.4F,
                    new Item.Properties().stacksTo(1).durability(-1).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> neosphore_shovel = ITEMS.register("neosphore_shovel",
            () -> new ShovelItem(ModToolTiers.NEOSPHERE, 0, 0,
                    new Item.Properties().stacksTo(1).durability(-1).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> neosphore_hoe = ITEMS.register("neosphore_hoe",
            () -> new HoeItem(ModToolTiers.NEOSPHERE, 0, 0,
                    new Item.Properties().stacksTo(1).durability(-1).rarity(Rarity.EPIC)));
    public static final RegistryObject<ShearsItem> neosphore_shears = ITEMS.register("neosphore_shears",
            () -> new ShearsItem(new Item.Properties().stacksTo(1).durability(-1).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> neosphore_smithing_template = ITEMS.register("neosphore_smithing_template",
            HavenSmithingItem::createNeosphoreUpgradeTemplate);

// Foods
    public static final RegistryObject<Item> dark_matter_apple = ITEMS.register("dark_matter_apple",
            () -> new Item(new Item.Properties().food(ModFoods.dark_matter_apple).stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> red_matter_apple = ITEMS.register("red_matter_apple",
            () -> new Item(new Item.Properties().food(ModFoods.red_matter_apple).stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> essence_apple = ITEMS.register("essence_apple",
            () -> new Item(new Item.Properties().food(ModFoods.essence_apple).stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> essentia_seeds = ITEMS.register("essentia_seeds",
            () -> new ItemNameBlockItem(ModBlocks.essentia_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> coal_seeds = ITEMS.register("coal_seeds",
            () -> new ItemNameBlockItem(ModBlocks.coal_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> coral_seeds = ITEMS.register("coral_seeds",
            () -> new ItemNameBlockItem(ModBlocks.coral_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> deepslate_seeds = ITEMS.register("deepslate_seeds",
            () -> new ItemNameBlockItem(ModBlocks.deepslate_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> diamond_seeds = ITEMS.register("diamond_seeds",
            () -> new ItemNameBlockItem(ModBlocks.diamond_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> dirt_seeds = ITEMS.register("dirt_seeds",
            () -> new ItemNameBlockItem(ModBlocks.dirt_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> dye_seeds = ITEMS.register("dye_seeds",
            () -> new ItemNameBlockItem(ModBlocks.dye_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> emerald_seeds = ITEMS.register("emerald_seeds",
            () -> new ItemNameBlockItem(ModBlocks.emerald_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> end_seeds = ITEMS.register("end_seeds",
            () -> new ItemNameBlockItem(ModBlocks.end_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> experience_seeds = ITEMS.register("experience_seeds",
            () -> new ItemNameBlockItem(ModBlocks.experience_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> fire_seeds = ITEMS.register("fire_seeds",
            () -> new ItemNameBlockItem(ModBlocks.fire_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> glowstone_seeds = ITEMS.register("glowstone_seeds",
            () -> new ItemNameBlockItem(ModBlocks.glowstone_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> gold_seeds = ITEMS.register("gold_seeds",
            () -> new ItemNameBlockItem(ModBlocks.gold_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> ice_seeds = ITEMS.register("ice_seeds",
            () -> new ItemNameBlockItem(ModBlocks.ice_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> iron_seeds = ITEMS.register("iron_seeds",
            () -> new ItemNameBlockItem(ModBlocks.iron_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> lapis_seeds = ITEMS.register("lapis_seeds",
            () -> new ItemNameBlockItem(ModBlocks.lapis_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> nature_seeds = ITEMS.register("nature_seeds",
            () -> new ItemNameBlockItem(ModBlocks.nature_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> nether_quartz_seeds = ITEMS.register("nether_quartz_seeds",
            () -> new ItemNameBlockItem(ModBlocks.nether_quartz_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> nether_seeds = ITEMS.register("nether_seeds",
            () -> new ItemNameBlockItem(ModBlocks.nether_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> obsidian_seeds = ITEMS.register("obsidian_seeds",
            () -> new ItemNameBlockItem(ModBlocks.obsidian_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> redstone_seeds = ITEMS.register("redstone_seeds",
            () -> new ItemNameBlockItem(ModBlocks.redstone_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> water_seeds = ITEMS.register("water_seeds",
            () -> new ItemNameBlockItem(ModBlocks.water_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> wood_seeds = ITEMS.register("wood_seeds",
            () -> new ItemNameBlockItem(ModBlocks.wood_crop.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> stone_seeds = ITEMS.register("stone_seeds",
            () -> new ItemNameBlockItem(ModBlocks.stone_crop.get(), new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> amethyst_seeds = ITEMS.register("amethyst_seeds",
            () -> new ItemNameBlockItem(ModBlocks.amethyst_crop.get(), new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> copper_seeds = ITEMS.register("copper_seeds",
            () -> new ItemNameBlockItem(ModBlocks.copper_crop.get(), new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> honey_seeds = ITEMS.register("honey_seeds",
            () -> new ItemNameBlockItem(ModBlocks.honey_crop.get(), new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> prismarine_seeds = ITEMS.register("prismarine_seeds",
            () -> new ItemNameBlockItem(ModBlocks.prismarine_crop.get(), new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> netherite_seeds = ITEMS.register("netherite_seeds",
            () -> new ItemNameBlockItem(ModBlocks.netherite_crop.get(), new Item.Properties().stacksTo(64).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> air_seeds = ITEMS.register("air_seeds",
            () -> new ItemNameBlockItem(ModBlocks.air_crop.get(), new Item.Properties().stacksTo(64).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> earth_seeds = ITEMS.register("earth_seeds",
            () -> new ItemNameBlockItem(ModBlocks.earth_crop.get(), new Item.Properties().stacksTo(64).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> essentia_spirit = ITEMS.register("essentia_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> mysterium_spirit = ITEMS.register("mysterium_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> vitalium_spirit = ITEMS.register("vitalium_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> celestium_spirit = ITEMS.register("celestium_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> eternium_spirit = ITEMS.register("eternium_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> stone_spirit = ITEMS.register("stone_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> coal_spirit = ITEMS.register("coal_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> coral_spirit = ITEMS.register("coral_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> deepslate_spirit = ITEMS.register("deepslate_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> diamond_spirit = ITEMS.register("diamond_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> dirt_spirit = ITEMS.register("dirt_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> dye_spirit = ITEMS.register("dye_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> emerald_spirit = ITEMS.register("emerald_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> end_spirit = ITEMS.register("end_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> experience_spirit = ITEMS.register("experience_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> fire_spirit = ITEMS.register("fire_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> glowstone_spirit = ITEMS.register("glowstone_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> gold_spirit = ITEMS.register("gold_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> ice_spirit = ITEMS.register("ice_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> iron_spirit = ITEMS.register("iron_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> lapis_spirit = ITEMS.register("lapis_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> nature_spirit = ITEMS.register("nature_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> nether_quartz_spirit = ITEMS.register("nether_quartz_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> nether_spirit = ITEMS.register("nether_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> obsidian_spirit = ITEMS.register("obsidian_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> redstone_spirit = ITEMS.register("redstone_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> water_spirit = ITEMS.register("water_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> wood_spirit = ITEMS.register("wood_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> amethyst_spirit = ITEMS.register("amethyst_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> copper_spirit = ITEMS.register("copper_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> honey_spirit = ITEMS.register("honey_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> prismarine_spirit = ITEMS.register("prismarine_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> netherite_spirit = ITEMS.register("netherite_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> air_spirit = ITEMS.register("air_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> earth_spirit = ITEMS.register("earth_spirit",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> raw_neosphore = ITEMS.register("raw_neosphore",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> neosphore_ingot = ITEMS.register("neosphore_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> neosphore_nugget = ITEMS.register("neosphore_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> acacia_bark = ITEMS.register("acacia_bark",
            () -> new AcaciaBark(new Item.Properties()));

    public static final RegistryObject<Item> birch_bark = ITEMS.register("birch_bark",
            () -> new BirchBark(new Item.Properties()));

    public static final RegistryObject<Item> charmel_bark = ITEMS.register("charmel_bark",
            () -> new CharmelBark(new Item.Properties()));

    public static final RegistryObject<Item> cherry_bark = ITEMS.register("cherry_bark",
            () -> new CherryBark(new Item.Properties()));

    public static final RegistryObject<Item> crimson_bark = ITEMS.register("crimson_bark",
            () -> new CrimsonBark(new Item.Properties()));

    public static final RegistryObject<Item> dark_oak_bark = ITEMS.register("dark_oak_bark",
            () -> new DarkOakBark(new Item.Properties()));

    public static final RegistryObject<Item> jungle_bark = ITEMS.register("jungle_bark",
            () -> new JungleBark(new Item.Properties()));

    public static final RegistryObject<Item> mangrove_bark = ITEMS.register("mangrove_bark",
            () -> new MangroveBark(new Item.Properties()));

    public static final RegistryObject<Item> oak_bark = ITEMS.register("oak_bark",
            () -> new OakBark(new Item.Properties()));

    public static final RegistryObject<Item> spruce_bark = ITEMS.register("spruce_bark",
            () -> new SpruceBark(new Item.Properties()));

    public static final RegistryObject<Item> warped_bark = ITEMS.register("warped_bark",
            () -> new WarpedBark(new Item.Properties()));

    public static final RegistryObject<Item> magnet = ITEMS.register("magnet",
            () -> new Magnet(new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 5));

    public static final RegistryObject<Item> mending_necklace = ITEMS.register("mending_necklace",
            () -> new MendingNecklace(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> boots_of_meow = ITEMS.register("boots_of_meow",
            () -> new BootsOfMeow(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> gloves_of_rawr = ITEMS.register("gloves_of_rawr",
            () -> new GlovesOfRawr(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> ring_of_haste = ITEMS.register("ring_of_haste",
            () -> new RingOfHaste(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> ring_of_love = ITEMS.register("ring_of_love",
            () -> new RingOfLove(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item>regeneration_pendant = ITEMS.register("regeneration_pendant",
            () -> new RegenerationPendant(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> fire_pendant = ITEMS.register("fire_pendant",
            () -> new FirePendant(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> water_pendant = ITEMS.register("water_pendant",
            () -> new WaterPendant(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> nightvision_goggles = ITEMS.register("nightvision_goggles",
            () -> new NightvisionGoggles(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> copper_dust = ITEMS.register("copper_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> iron_dust = ITEMS.register("iron_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> gold_dust = ITEMS.register("gold_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> tin_dust = ITEMS.register("tin_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> netherite_dust = ITEMS.register("netherite_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> lead_dust = ITEMS.register("lead_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> silver_dust = ITEMS.register("silver_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> nickel_dust = ITEMS.register("nickel_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> uranium_dust = ITEMS.register("uranium_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> osmium_dust = ITEMS.register("osmium_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> zinc_dust = ITEMS.register("zinc_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> tin_ingot = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> lead_ingot = ITEMS.register("lead_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> silver_ingot = ITEMS.register("silver_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> nickel_ingot = ITEMS.register("nickel_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> uranium_ingot = ITEMS.register("uranium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> osmium_ingot = ITEMS.register("osmium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> zinc_ingot = ITEMS.register("zinc_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> copper_ore_hammer = ITEMS.register("copper_ore_hammer",
            () -> new CopperOreHammer(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).defaultDurability(64)));

    public static final RegistryObject<Item> iron_ore_hammer = ITEMS.register("iron_ore_hammer",
            () -> new IronOreHammer(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).defaultDurability(128)));

    public static final RegistryObject<Item> gold_ore_hammer = ITEMS.register("gold_ore_hammer",
            () -> new GoldOreHammer(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).defaultDurability(256)));

    public static final RegistryObject<Item> diamond_ore_hammer = ITEMS.register("diamond_ore_hammer",
            () -> new DiamondOreHammer(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).defaultDurability(512)));

    public static final RegistryObject<Item> neosphore_ore_hammer = ITEMS.register("neosphore_ore_hammer",
            () -> new NeosphoreOreHammer(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).defaultDurability(-1)));
}
