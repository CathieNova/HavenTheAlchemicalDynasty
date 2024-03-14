package net.cathienova.havenalchemy.item;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HavenAlchemy.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("havenalchemy_tab",
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
                        add.accept(new ItemStack(ModItems.alchemical_coal.get()));
                        add.accept(new ItemStack(ModItems.ethern_coal.get()));
                        add.accept(new ItemStack(ModItems.aether_fuel.get()));
                        add.accept(new ItemStack(ModItems.magnet.get()));
                        add.accept(new ItemStack(ModItems.mending_necklace.get()));
                        add.accept(new ItemStack(ModItems.essence_shard.get()));
                        add.accept(new ItemStack(ModItems.dark_matter.get()));
                        add.accept(new ItemStack(ModItems.red_matter.get()));

                        //Blocks
                        add.accept(new ItemStack(ModBlocks.alchemical_coal_block.get()));
                        add.accept(new ItemStack(ModBlocks.ethern_coal_block.get()));
                        add.accept(new ItemStack(ModBlocks.aether_fuel_block.get()));
                        add.accept(new ItemStack(ModBlocks.asphalt.get()));
                        add.accept(new ItemStack(ModBlocks.asphalt_bricks.get()));
                        //add.accept(new ItemStack(ModBlocks.suspicious_basphalt.get()));
                        add.accept(new ItemStack(ModBlocks.alchemical_chamber.get()));
                        add.accept(new ItemStack(ModBlocks.essentia_spirit_block.get()));
                        add.accept(new ItemStack(ModBlocks.mysterium_spirit_block.get()));
                        add.accept(new ItemStack(ModBlocks.vitalium_spirit_block.get()));
                        add.accept(new ItemStack(ModBlocks.celestial_spirit_block.get()));
                        add.accept(new ItemStack(ModBlocks.eternium_spirit_block.get()));

                        //Armor
                        add.accept(new ItemStack(ModItems.dark_matter_helmet.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_chestplate.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_leggings.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_boots.get()));
                        add.accept(new ItemStack(ModItems.red_matter_helmet.get()));
                        add.accept(new ItemStack(ModItems.red_matter_chestplate.get()));
                        add.accept(new ItemStack(ModItems.red_matter_leggings.get()));
                        add.accept(new ItemStack(ModItems.red_matter_boots.get()));
                        add.accept(new ItemStack(ModItems.neosphore_helmet.get()));
                        add.accept(new ItemStack(ModItems.neosphore_chestplate.get()));
                        add.accept(new ItemStack(ModItems.neosphore_leggings.get()));
                        add.accept(new ItemStack(ModItems.neosphore_boots.get()));

                        //Tools
                        add.accept(new ItemStack(ModItems.dark_matter_pickaxe.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_axe.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_shovel.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_hoe.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_sword.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_shears.get()));
                        add.accept(new ItemStack(ModItems.red_matter_pickaxe.get()));
                        add.accept(new ItemStack(ModItems.red_matter_axe.get()));
                        add.accept(new ItemStack(ModItems.red_matter_shovel.get()));
                        add.accept(new ItemStack(ModItems.red_matter_hoe.get()));
                        add.accept(new ItemStack(ModItems.red_matter_sword.get()));
                        add.accept(new ItemStack(ModItems.red_matter_shears.get()));

                        //Foods
                        add.accept(new ItemStack(ModItems.essence_apple.get()));
                        add.accept(new ItemStack(ModItems.dark_matter_apple.get()));
                        add.accept(new ItemStack(ModItems.red_matter_apple.get()));

                        //Crops
                        add.accept(new ItemStack(ModItems.essentia_seeds.get()));
                        add.accept(new ItemStack(ModItems.stone_seeds.get()));
                        add.accept(new ItemStack(ModItems.coal_seeds.get()));
                        add.accept(new ItemStack(ModItems.coral_seeds.get()));
                        add.accept(new ItemStack(ModItems.diamond_seeds.get()));
                        add.accept(new ItemStack(ModItems.dirt_seeds.get()));
                        add.accept(new ItemStack(ModItems.dye_seeds.get()));
                        add.accept(new ItemStack(ModItems.emerald_seeds.get()));
                        add.accept(new ItemStack(ModItems.end_seeds.get()));
                        add.accept(new ItemStack(ModItems.experience_seeds.get()));
                        add.accept(new ItemStack(ModItems.fire_seeds.get()));
                        add.accept(new ItemStack(ModItems.glowstone_seeds.get()));
                        add.accept(new ItemStack(ModItems.gold_seeds.get()));
                        add.accept(new ItemStack(ModItems.ice_seeds.get()));
                        add.accept(new ItemStack(ModItems.iron_seeds.get()));
                        add.accept(new ItemStack(ModItems.lapis_seeds.get()));
                        add.accept(new ItemStack(ModItems.nature_seeds.get()));
                        add.accept(new ItemStack(ModItems.nether_seeds.get()));
                        add.accept(new ItemStack(ModItems.nether_quartz_seeds.get()));
                        add.accept(new ItemStack(ModItems.obsidian_seeds.get()));
                        add.accept(new ItemStack(ModItems.redstone_seeds.get()));
                        add.accept(new ItemStack(ModItems.water_seeds.get()));
                        add.accept(new ItemStack(ModItems.wood_seeds.get()));
                        add.accept(new ItemStack(ModItems.amethyst_seeds.get()));
                        add.accept(new ItemStack(ModItems.copper_seeds.get()));
                        add.accept(new ItemStack(ModItems.honey_seeds.get()));
                        add.accept(new ItemStack(ModItems.prismarine_seeds.get()));
                        add.accept(new ItemStack(ModItems.netherite_seeds.get()));
                        add.accept(new ItemStack(ModItems.air_seeds.get()));
                        add.accept(new ItemStack(ModItems.earth_seeds.get()));

                        //Spirits
                        add.accept(new ItemStack(ModItems.essentia_spirit.get()));
                        add.accept(new ItemStack(ModItems.mysterium_spirit.get()));
                        add.accept(new ItemStack(ModItems.vitalium_spirit.get()));
                        add.accept(new ItemStack(ModItems.celestium_spirit.get()));
                        add.accept(new ItemStack(ModItems.eternium_spirit.get()));
                        add.accept(new ItemStack(ModItems.stone_spirit.get()));
                        add.accept(new ItemStack(ModItems.coal_spirit.get()));
                        add.accept(new ItemStack(ModItems.coral_spirit.get()));
                        add.accept(new ItemStack(ModItems.deepslate_spirit.get()));
                        add.accept(new ItemStack(ModItems.diamond_spirit.get()));
                        add.accept(new ItemStack(ModItems.dirt_spirit.get()));
                        add.accept(new ItemStack(ModItems.dye_spirit.get()));
                        add.accept(new ItemStack(ModItems.emerald_spirit.get()));
                        add.accept(new ItemStack(ModItems.end_spirit.get()));
                        add.accept(new ItemStack(ModItems.experience_spirit.get()));
                        add.accept(new ItemStack(ModItems.fire_spirit.get()));
                        add.accept(new ItemStack(ModItems.glowstone_spirit.get()));
                        add.accept(new ItemStack(ModItems.gold_spirit.get()));
                        add.accept(new ItemStack(ModItems.ice_spirit.get()));
                        add.accept(new ItemStack(ModItems.iron_spirit.get()));
                        add.accept(new ItemStack(ModItems.lapis_spirit.get()));
                        add.accept(new ItemStack(ModItems.nature_spirit.get()));
                        add.accept(new ItemStack(ModItems.nether_spirit.get()));
                        add.accept(new ItemStack(ModItems.nether_quartz_spirit.get()));
                        add.accept(new ItemStack(ModItems.obsidian_spirit.get()));
                        add.accept(new ItemStack(ModItems.redstone_spirit.get()));
                        add.accept(new ItemStack(ModItems.water_spirit.get()));
                        add.accept(new ItemStack(ModItems.wood_spirit.get()));
                        add.accept(new ItemStack(ModItems.amethyst_spirit.get()));
                        add.accept(new ItemStack(ModItems.copper_spirit.get()));
                        add.accept(new ItemStack(ModItems.honey_spirit.get()));
                        add.accept(new ItemStack(ModItems.prismarine_spirit.get()));
                        add.accept(new ItemStack(ModItems.netherite_spirit.get()));
                        add.accept(new ItemStack(ModItems.air_spirit.get()));
                        add.accept(new ItemStack(ModItems.earth_spirit.get()));

                        add.accept(ModItems.raw_neosphore.get());
                        add.accept(ModItems.neosphore_ingot.get());
                        add.accept(ModItems.neosphore_nugget.get());
                        add.accept(ModItems.neosphore_axe.get());
                        add.accept(ModItems.neosphore_pickaxe.get());
                        add.accept(ModItems.neosphore_sword.get());
                        add.accept(ModItems.neosphore_shovel.get());
                        add.accept(ModItems.neosphore_hoe.get());
                        add.accept(ModItems.neosphore_shears.get());
                        add.accept(ModBlocks.neosphore_block.get().asItem());
                        add.accept(ModBlocks.raw_neosphore_block.get().asItem());
                        add.accept(ModBlocks.neosphore_ore.get().asItem());
                        add.accept(ModItems.neosphore_smithing_template.get());

                        add.accept(ModBlocks.charmel_leaves.get().asItem());
                        add.accept(ModBlocks.charmel_sapling.get().asItem());
                        add.accept(ModBlocks.charmel_log.get().asItem());
                        add.accept(ModBlocks.charmel_wood.get().asItem());
                        add.accept(ModBlocks.stripped_charmel_log.get().asItem());
                        add.accept(ModBlocks.stripped_charmel_wood.get().asItem());
                        add.accept(ModBlocks.charmel_planks.get().asItem());
                        add.accept(ModBlocks.charmel_slab.get().asItem());
                        add.accept(ModBlocks.charmel_stairs.get().asItem());
                        add.accept(ModBlocks.charmel_fence.get().asItem());
                        add.accept(ModBlocks.charmel_fence_gate.get().asItem());
                        add.accept(ModBlocks.charmel_button.get().asItem());
                        add.accept(ModBlocks.charmel_pressure_plate.get().asItem());
                        add.accept(ModBlocks.catacombs_portal.get().asItem());

                        add.accept(ModBlocks.basphalt_stone.get().asItem());
                        add.accept(ModBlocks.basphalt_stone_fence.get().asItem());
                        add.accept(ModBlocks.basphalt_stone_fence_gate.get().asItem());
                        add.accept(ModBlocks.basphalt_stone_stairs.get().asItem());
                        add.accept(ModBlocks.basphalt_stone_slabs.get().asItem());
                        add.accept(ModBlocks.basphalt_stone_wall.get().asItem());
                        add.accept(ModBlocks.basphalt_stone_button.get().asItem());

                        add.accept(ModBlocks.basphalt_cobblestone.get().asItem());
                        add.accept(ModBlocks.basphalt_cobblestone_fence.get().asItem());
                        add.accept(ModBlocks.basphalt_cobblestone_fence_gate.get().asItem());
                        add.accept(ModBlocks.basphalt_cobblestone_stairs.get().asItem());
                        add.accept(ModBlocks.basphalt_cobblestone_slabs.get().asItem());
                        add.accept(ModBlocks.basphalt_cobblestone_wall.get().asItem());
                        add.accept(ModBlocks.basphalt_cobblestone_button.get().asItem());

                        add.accept(ModBlocks.basphalt_stone_bricks_button.get().asItem());
                        add.accept(ModBlocks.basphalt_stone_bricks_fence_gate.get().asItem());
                        add.accept(ModBlocks.basphalt_stone_bricks.get().asItem());
                        add.accept(ModBlocks.basphalt_stone_bricks_fence.get().asItem());
                        add.accept(ModBlocks.basphalt_stone_bricks_stairs.get().asItem());
                        add.accept(ModBlocks.basphalt_stone_bricks_slabs.get().asItem());
                        add.accept(ModBlocks.basphalt_stone_bricks_wall.get().asItem());

                        //Artifacts
                        add.accept(ModItems.boots_of_meow.get());
                        add.accept(ModItems.gloves_of_rawr.get());
                        add.accept(ModItems.ring_of_haste.get());
                        add.accept(ModItems.ring_of_love.get());
                        add.accept(ModItems.regeneration_pendant.get());
                        add.accept(ModItems.fire_pendant.get());
                        add.accept(ModItems.water_pendant.get());
                        add.accept(ModItems.nightvision_goggles.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
