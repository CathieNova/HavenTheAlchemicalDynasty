package net.cathienova.havenalchemy.item;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.events.FluidInit;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HavenAlchemy.MOD_ID);

    public static String havenalchemy_tab_title = "itemGroup.havenalchemy.havenalchemy_tab";
    public static final RegistryObject<CreativeModeTab> HAVENALCHEMY_TAB = CREATIVE_MODE_TABS.register("havenalchemy_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.alchemy_stone_fractured.get()))
                    .title(Component.translatable("itemGroup.havenalchemy.havenalchemy_tab"))
                    .displayItems((pParameters, add) -> {
                        //Items
                        add.accept(new ItemStack(ModItems.alchemy_stone_fractured.get()));
                        add.accept(new ItemStack(ModItems.alchemy_stone.get()));
                        add.accept(new ItemStack(ModItems.alchemy_dust.get()));
                        add.accept(new ItemStack(ModItems.alchemy_dust_low.get()));
                        add.accept(new ItemStack(ModItems.alchemy_dust_medium.get()));
                        add.accept(new ItemStack(ModItems.alchemy_dust_high.get()));
                        add.accept(new ItemStack(ModItems.essence_shard.get()));
                        add.accept(new ItemStack(ModItems.dark_matter.get()));
                        add.accept(new ItemStack(ModBlocks.dark_matter_block.get()));
                        add.accept(new ItemStack(ModItems.red_matter.get()));
                        add.accept(new ItemStack(ModBlocks.red_matter_block.get()));
                        add.accept(new ItemStack(ModItems.void_matter.get()));
                        add.accept(new ItemStack(ModItems.trowel.get()));
                        add.accept(new ItemStack(ModItems.experience_orb.get()));
                        add.accept(new ItemStack(ModItems.stone_hammer.get()));
                        add.accept(new ItemStack(ModItems.iron_hammer.get()));
                        add.accept(new ItemStack(ModItems.golden_hammer.get()));
                        add.accept(new ItemStack(ModItems.diamond_hammer.get()));
                        add.accept(new ItemStack(ModItems.netherite_hammer.get()));

                        add.accept(new ItemStack(ModBlocks.crushed_end_stone.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.crushed_netherrack.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.dust.get().asItem()));

                        add.accept(new ItemStack(ModBlocks.havenite_ore.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.andesite_havenite_ore.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.diorite_havenite_ore.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.granite_havenite_ore.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.deepslate_havenite_ore.get().asItem()));
                        add.accept(new ItemStack(ModItems.raw_havenite.get()));
                        add.accept(new ItemStack(ModBlocks.raw_havenite_block.get().asItem()));
                        add.accept(new ItemStack(ModItems.havenite_dust.get()));
                        add.accept(new ItemStack(ModItems.havenite_nugget.get()));
                        add.accept(new ItemStack(ModItems.havenite_ingot.get()));
                        add.accept(new ItemStack(ModBlocks.havenite_block.get().asItem()));

                        add.accept(new ItemStack(ModBlocks.speed_plate_i.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.speed_plate_ii.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.speed_plate_iii.get().asItem()));
                        add.accept(new ItemStack(FluidInit.acid_fluid.bucket.get().asItem()));

                        add.accept(new ItemStack(ModBlocks.generator_block.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.charger_block.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.cable_block.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.facade_block.get().asItem()));

                        add.accept(new ItemStack(ModItems.research_tier_basic.get()));
                        add.accept(new ItemStack(ModItems.research_tier_intermediate.get()));
                        add.accept(new ItemStack(ModItems.research_tier_advanced.get()));
                        add.accept(new ItemStack(ModItems.research_tier_elite.get()));
                        add.accept(new ItemStack(ModItems.research_tier_ultimate.get()));

                        add.accept(new ItemStack(ModItems.wooden_shears.get()));

                        add.accept(new ItemStack(ModItems.stone_crusher.get()));
                        add.accept(new ItemStack(ModItems.iron_crusher.get()));
                        add.accept(new ItemStack(ModItems.golden_crusher.get()));
                        add.accept(new ItemStack(ModItems.diamond_crusher.get()));
                        add.accept(new ItemStack(ModItems.netherite_crusher.get()));
                        add.accept(new ItemStack(ModItems.havenite_crusher.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_crusher.get()));
                        add.accept(new ItemStack(ModItems.red_matter_crusher.get()));
                        add.accept(new ItemStack(ModItems.neosphore_crusher.get()));

                        add.accept(new ItemStack(ModBlocks.alchemical_chamber.get()));
                        add.accept(new ItemStack(ModBlocks.alchemical_processor.get()));
                        add.accept(new ItemStack(ModBlocks.alchemical_condenser.get().asItem()));
                        //add.accept(new ItemStack(ModBlocks.alchemical_transmutation.get().asItem()));

                        add.accept(new ItemStack(ModBlocks.dirt_chest.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.stone_chest.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.copper_chest.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.iron_chest.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.gold_chest.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.obsidian_chest.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.diamond_chest.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.alchemical_chest.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.emerald_chest.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.netherite_chest.get().asItem()));

                        add.accept(new ItemStack(ModItems.mini_coal.get()));
                        add.accept(new ItemStack(ModItems.mini_charcoal.get()));

                        add.accept(new ItemStack(ModItems.alchemical_coal.get()));
                        add.accept(new ItemStack(ModItems.ethern_coal.get()));
                        add.accept(new ItemStack(ModItems.aether_fuel.get()));
                        add.accept(new ItemStack(ModBlocks.alchemical_coal_block_item.get()));
                        add.accept(new ItemStack(ModBlocks.ethern_coal_block_item.get()));
                        add.accept(new ItemStack(ModBlocks.aether_fuel_block_item.get()));

                        //Tools
                        add.accept(new ItemStack(ModItems.dark_matter_pickaxe.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_axe.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_shovel.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_hoe.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_shears.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_hammer.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_sword.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_shield.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_helmet.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_chestplate.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_leggings.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_boots.get()));

                        add.accept(new ItemStack(ModItems.red_matter_pickaxe.get()));
                        add.accept(new ItemStack(ModItems.red_matter_axe.get()));
                        add.accept(new ItemStack(ModItems.red_matter_shovel.get()));
                        add.accept(new ItemStack(ModItems.red_matter_hoe.get()));
                        add.accept(new ItemStack(ModItems.red_matter_shears.get()));
                        add.accept(new ItemStack(ModItems.red_matter_hammer.get()));
                        add.accept(new ItemStack(ModItems.red_matter_sword.get()));
                        add.accept(new ItemStack(ModItems.red_matter_shield.get()));
                        add.accept(new ItemStack(ModItems.red_matter_helmet.get()));
                        add.accept(new ItemStack(ModItems.red_matter_chestplate.get()));
                        add.accept(new ItemStack(ModItems.red_matter_leggings.get()));
                        add.accept(new ItemStack(ModItems.red_matter_boots.get()));

                        add.accept(new ItemStack(ModItems.raw_neosphore.get()));
                        add.accept(new ItemStack(ModBlocks.raw_neosphore_block.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.neosphore_ore.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.neosphore_block.get().asItem()));
                        add.accept(new ItemStack(ModItems.neosphore_smithing_template.get()));
                        add.accept(new ItemStack(ModItems.neosphore_ingot.get()));
                        add.accept(new ItemStack(ModItems.neosphore_nugget.get()));
                        add.accept(new ItemStack(ModItems.neosphore_pickaxe.get()));
                        add.accept(new ItemStack(ModItems.neosphore_axe.get()));
                        add.accept(new ItemStack(ModItems.neosphore_shovel.get()));
                        add.accept(new ItemStack(ModItems.neosphore_hoe.get()));
                        add.accept(new ItemStack(ModItems.neosphore_shears.get()));
                        add.accept(new ItemStack(ModItems.neosphore_hammer.get()));
                        add.accept(new ItemStack(ModItems.neosphore_sword.get()));
                        add.accept(new ItemStack(ModItems.neosphore_shield.get()));
                        add.accept(new ItemStack(ModItems.neosphore_helmet.get()));
                        add.accept(new ItemStack(ModItems.neosphore_chestplate.get()));
                        add.accept(new ItemStack(ModItems.neosphore_leggings.get()));
                        add.accept(new ItemStack(ModItems.neosphore_boots.get()));

                        //Foods
                        add.accept(new ItemStack(ModItems.essence_apple.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_apple.get()));
                        add.accept(new ItemStack(ModItems.red_matter_apple.get()));

                        add.accept(new ItemStack(ModItems.acacia_bark.get()));
                        add.accept(new ItemStack(ModItems.birch_bark.get()));
                        add.accept(new ItemStack(ModItems.dark_oak_bark.get()));
                        add.accept(new ItemStack(ModItems.jungle_bark.get()));
                        add.accept(new ItemStack(ModItems.oak_bark.get()));
                        add.accept(new ItemStack(ModItems.spruce_bark.get()));
                        add.accept(new ItemStack(ModItems.warped_bark.get()));
                        add.accept(new ItemStack(ModItems.crimson_bark.get()));
                        add.accept(new ItemStack(ModItems.charmel_bark.get()));

                        //Ore Processing
                        add.accept(new ItemStack(ModItems.copper_ore_hammer.get()));
                        add.accept(new ItemStack(ModItems.iron_ore_hammer.get()));
                        add.accept(new ItemStack(ModItems.gold_ore_hammer.get()));
                        add.accept(new ItemStack(ModItems.diamond_ore_hammer.get()));
                        add.accept(new ItemStack(ModItems.havenite_ore_hammer.get()));
                        add.accept(new ItemStack(ModItems.neosphore_ore_hammer.get()));
                        add.accept(new ItemStack(ModItems.copper_dust.get()));
                        add.accept(new ItemStack(ModItems.tin_dust.get()));
                        add.accept(new ItemStack(ModItems.tin_ingot.get()));
                        add.accept(new ItemStack(ModItems.netherite_dust.get()));
                        add.accept(new ItemStack(ModItems.iron_dust.get()));
                        add.accept(new ItemStack(ModItems.gold_dust.get()));
                        add.accept(new ItemStack(ModItems.lead_dust.get()));
                        add.accept(new ItemStack(ModItems.lead_ingot.get()));
                        add.accept(new ItemStack(ModItems.silver_dust.get()));
                        add.accept(new ItemStack(ModItems.silver_ingot.get()));
                        add.accept(new ItemStack(ModItems.nickel_dust.get()));
                        add.accept(new ItemStack(ModItems.nickel_ingot.get()));
                        add.accept(new ItemStack(ModItems.uranium_dust.get()));
                        add.accept(new ItemStack(ModItems.uranium_ingot.get()));
                        add.accept(new ItemStack(ModItems.osmium_dust.get()));
                        add.accept(new ItemStack(ModItems.osmium_ingot.get()));
                        add.accept(new ItemStack(ModItems.zinc_dust.get()));
                        add.accept(new ItemStack(ModItems.zinc_ingot.get()));
                        add.accept(new ItemStack(ModItems.aluminum_dust.get()));
                        add.accept(new ItemStack(ModItems.aluminum_ingot.get()));

                        //Seeds
                        add.accept(new ItemStack(ModItems.air_seeds.get()));
                        add.accept(new ItemStack(ModItems.amethyst_seeds.get()));
                        add.accept(new ItemStack(ModItems.coal_seeds.get()));
                        add.accept(new ItemStack(ModItems.copper_seeds.get()));
                        add.accept(new ItemStack(ModItems.coral_seeds.get()));
                        add.accept(new ItemStack(ModItems.deepslate_seeds.get()));
                        add.accept(new ItemStack(ModItems.diamond_seeds.get()));
                        add.accept(new ItemStack(ModItems.dirt_seeds.get()));
                        add.accept(new ItemStack(ModItems.dye_seeds.get()));
                        add.accept(new ItemStack(ModItems.earth_seeds.get()));
                        add.accept(new ItemStack(ModItems.emerald_seeds.get()));
                        add.accept(new ItemStack(ModItems.end_seeds.get()));
                        add.accept(new ItemStack(ModItems.essentia_seeds.get()));
                        add.accept(new ItemStack(ModItems.experience_seeds.get()));
                        add.accept(new ItemStack(ModItems.fire_seeds.get()));
                        add.accept(new ItemStack(ModItems.glowstone_seeds.get()));
                        add.accept(new ItemStack(ModItems.gold_seeds.get()));
                        add.accept(new ItemStack(ModItems.havenite_seeds.get()));
                        add.accept(new ItemStack(ModItems.honey_seeds.get()));
                        add.accept(new ItemStack(ModItems.ice_seeds.get()));
                        add.accept(new ItemStack(ModItems.iron_seeds.get()));
                        add.accept(new ItemStack(ModItems.lapis_seeds.get()));
                        add.accept(new ItemStack(ModItems.nature_seeds.get()));
                        add.accept(new ItemStack(ModItems.nether_quartz_seeds.get()));
                        add.accept(new ItemStack(ModItems.nether_seeds.get()));
                        add.accept(new ItemStack(ModItems.netherite_seeds.get()));
                        add.accept(new ItemStack(ModItems.obsidian_seeds.get()));
                        add.accept(new ItemStack(ModItems.prismarine_seeds.get()));
                        add.accept(new ItemStack(ModItems.redstone_seeds.get()));
                        add.accept(new ItemStack(ModItems.stone_seeds.get()));
                        add.accept(new ItemStack(ModItems.water_seeds.get()));
                        add.accept(new ItemStack(ModItems.wood_seeds.get()));

                        //Spirits
                        add.accept(new ItemStack(ModItems.air_spirit.get()));
                        add.accept(new ItemStack(ModItems.amethyst_spirit.get()));
                        add.accept(new ItemStack(ModItems.celestium_spirit.get()));
                        add.accept(new ItemStack(ModItems.coal_spirit.get()));
                        add.accept(new ItemStack(ModItems.copper_spirit.get()));
                        add.accept(new ItemStack(ModItems.coral_spirit.get()));
                        add.accept(new ItemStack(ModItems.deepslate_spirit.get()));
                        add.accept(new ItemStack(ModItems.diamond_spirit.get()));
                        add.accept(new ItemStack(ModItems.dirt_spirit.get()));
                        add.accept(new ItemStack(ModItems.dye_spirit.get()));
                        add.accept(new ItemStack(ModItems.earth_spirit.get()));
                        add.accept(new ItemStack(ModItems.emerald_spirit.get()));
                        add.accept(new ItemStack(ModItems.end_spirit.get()));
                        add.accept(new ItemStack(ModItems.essentia_spirit.get()));
                        add.accept(new ItemStack(ModItems.eternium_spirit.get()));
                        add.accept(new ItemStack(ModItems.experience_spirit.get()));
                        add.accept(new ItemStack(ModItems.fire_spirit.get()));
                        add.accept(new ItemStack(ModItems.glowstone_spirit.get()));
                        add.accept(new ItemStack(ModItems.gold_spirit.get()));
                        add.accept(new ItemStack(ModItems.havenite_spirit.get()));
                        add.accept(new ItemStack(ModItems.honey_spirit.get()));
                        add.accept(new ItemStack(ModItems.ice_spirit.get()));
                        add.accept(new ItemStack(ModItems.iron_spirit.get()));
                        add.accept(new ItemStack(ModItems.lapis_spirit.get()));
                        add.accept(new ItemStack(ModItems.mysterium_spirit.get()));
                        add.accept(new ItemStack(ModItems.nature_spirit.get()));
                        add.accept(new ItemStack(ModItems.nether_quartz_spirit.get()));
                        add.accept(new ItemStack(ModItems.nether_spirit.get()));
                        add.accept(new ItemStack(ModItems.netherite_spirit.get()));
                        add.accept(new ItemStack(ModItems.obsidian_spirit.get()));
                        add.accept(new ItemStack(ModItems.prismarine_spirit.get()));
                        add.accept(new ItemStack(ModItems.redstone_spirit.get()));
                        add.accept(new ItemStack(ModItems.stone_spirit.get()));
                        add.accept(new ItemStack(ModItems.vitalium_spirit.get()));
                        add.accept(new ItemStack(ModItems.water_spirit.get()));
                        add.accept(new ItemStack(ModItems.wood_spirit.get()));

                        add.accept(new ItemStack(ModBlocks.essentia_spirit_block.get()));
                        add.accept(new ItemStack(ModBlocks.mysterium_spirit_block.get()));
                        add.accept(new ItemStack(ModBlocks.vitalium_spirit_block.get()));
                        add.accept(new ItemStack(ModBlocks.celestium_spirit_block.get()));
                        add.accept(new ItemStack(ModBlocks.eternium_spirit_block.get()));

                        //Artifacts
                        add.accept(new ItemStack(ModItems.magnet.get()));
                        add.accept(new ItemStack(ModItems.mending_necklace.get()));
                        add.accept(new ItemStack(ModItems.boots_of_meow.get()));
                        add.accept(new ItemStack(ModItems.gloves_of_rawr.get()));
                        add.accept(new ItemStack(ModItems.ring_of_haste.get()));
                        add.accept(new ItemStack(ModItems.ring_of_love.get()));
                        add.accept(new ItemStack(ModItems.regeneration_pendant.get()));
                        add.accept(new ItemStack(ModItems.fire_pendant.get()));
                        add.accept(new ItemStack(ModItems.water_pendant.get()));
                        add.accept(new ItemStack(ModItems.nightvision_goggles.get()));

                        add.accept(new ItemStack(ModBlocks.charmel_sapling.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.charmel_leaves.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.charmel_log.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.charmel_wood.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.stripped_charmel_log.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.stripped_charmel_wood.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.charmel_planks.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.charmel_slab.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.charmel_stairs.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.charmel_fence.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.charmel_fence_gate.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.charmel_button.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.charmel_pressure_plate.get().asItem()));

                        add.accept(new ItemStack(ModBlocks.asphalt.get()));
                        add.accept(new ItemStack(ModBlocks.asphalt_bricks.get()));

                        add.accept(new ItemStack(ModBlocks.basphalt_stone.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_fence.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_fence_gate.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_stairs.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_slabs.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_wall.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_button.get().asItem()));

                        add.accept(new ItemStack(ModBlocks.basphalt_cobblestone.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_cobblestone_fence.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_cobblestone_fence_gate.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_cobblestone_stairs.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_cobblestone_slabs.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_cobblestone_wall.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_cobblestone_button.get().asItem()));

                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_button.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_fence_gate.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_1.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_2.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_3.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_4.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_5.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_6.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_7.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_8.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_9.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_10.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_11.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_12.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_13.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_14.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_15.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_16.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_17.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_18.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_19.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_20.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_21.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_22.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_23.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_24.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_25.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_26.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_27.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_28.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_fence.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_stairs.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_slabs.get().asItem()));
                        add.accept(new ItemStack(ModBlocks.basphalt_stone_bricks_wall.get().asItem()));

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
