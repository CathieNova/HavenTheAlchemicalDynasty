package net.cathienova.havenalchemy.datagen;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.item.ModItems;
import net.cathienova.havenalchemy.recipe.AlchemicalChamberRecipe;
import net.cathienova.havenalchemy.util.AlchemicalChamberRecipeBuilder;
import net.cathienova.havenalchemy.util.ModTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }
    private static final List<ItemLike> NEOSPHORE_SMELTABLES = List.of(
            ModItems.raw_neosphore.get(),
            ModBlocks.neosphore_ore.get());

    private static final List<ItemLike> NEOSPHORE_BLOCK_SMELTABLES = List.of(
            ModBlocks.raw_neosphore_block.get());

    private static final List<ItemLike> BASPHALT_STONE_SMELTABLES = List.of(
            ModBlocks.basphalt_cobblestone.get());

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.trowel.get(), 1)
                .pattern(" II")
                .pattern(" SI")
                .pattern("S  ")
                .define('I', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/trowel");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.alchemy_stone_fractured.get(), 1)
                .pattern("LML")
                .pattern("HEH")
                .pattern("MLM")
                .define('L', ModItems.alchemy_dust_low.get())
                .define('M', ModItems.alchemy_dust_medium.get())
                .define('H', ModItems.alchemy_dust_high.get())
                .define('E', ModItems.essence_shard.get())
                .unlockedBy("has_alchemy_dust", has(ModItems.alchemy_dust.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/alchemy_stone_fractured");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.alchemy_stone.get(), 1)
                .pattern("EDE")
                .pattern("RVR")
                .pattern("EDE")
                .define('E', ModItems.ethern_coal.get())
                .define('D', ModItems.alchemy_dust.get())
                .define('R', ModItems.red_matter.get())
                .define('V', ModItems.void_matter.get())
                .unlockedBy("has_void_matter", has(ModItems.void_matter.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/alchemy_stone");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.alchemical_chamber.get(), 1)
                .pattern("BDB")
                .pattern("SSS")
                .pattern("B B")
                .define('B', ModBlocks.basphalt_stone_bricks.get())
                .define('D', ModItems.alchemy_dust.get())
                .define('S', ModBlocks.basphalt_stone.get())
                .unlockedBy("has_alchemy_dust", has(ModItems.alchemy_dust.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/alchemical_chamber");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.alchemy_dust.get(), 1)
                .pattern("LLL")
                .pattern("MMM")
                .pattern("HHH")
                .define('L', ModItems.alchemy_dust_low.get())
                .define('M', ModItems.alchemy_dust_medium.get())
                .define('H', ModItems.alchemy_dust_high.get())
                .unlockedBy("has_alchemy_dust_low", has(ModItems.alchemy_dust_low.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/alchemy_dust");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.alchemy_dust_low.get(), 16)
                .pattern("SSS")
                .pattern("SCS")
                .pattern("SSS")
                .define('S', Items.STONE)
                .define('C', Items.COAL)
                .unlockedBy("has_coal", has(Items.COAL))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/alchemy_dust_low");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.alchemy_dust_medium.get(), 16)
                .pattern(" R ")
                .pattern("RIR")
                .pattern(" R ")
                .define('R', Items.REDSTONE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_redstone", has(Items.REDSTONE))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/alchemy_dust_medium");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.alchemy_dust_high.get(), 16)
                .pattern(" C ")
                .pattern("CDC")
                .pattern(" C ")
                .define('C', Items.COAL)
                .define('D', Items.DIAMOND)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/alchemy_dust_high");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.magnet.get(), 1)
                .pattern("GEG")
                .pattern("EAE")
                .pattern("GEG")
                .define('G', Items.GLOWSTONE_DUST)
                .define('E', Items.ENDER_PEARL)
                .define('A', ModTags.Items.alchemy_stones)
                .unlockedBy("has_ender_pearl", has(Items.ENDER_PEARL))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/magnet");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.mending_necklace.get(), 1)
                .pattern("D D")
                .pattern("GAG")
                .pattern("DGD")
                .define('D', Items.DIAMOND)
                .define('A', ModTags.Items.alchemy_stones)
                .define('G', Items.GOLD_INGOT)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/mending_necklace");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.asphalt.get(), 4)
                .pattern("GSG")
                .pattern("SCS")
                .pattern("GSG")
                .define('G', Items.GRAVEL)
                .define('S', Items.SAND)
                .define('C', Items.COAL)
                .unlockedBy("has_coal", has(Items.COAL))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/asphalt");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.asphalt.get(), 4)
                .pattern("GSG")
                .pattern("SCS")
                .pattern("GSG")
                .define('G', Items.GRAVEL)
                .define('S', Items.SAND)
                .define('C', Items.CHARCOAL)
                .unlockedBy("has_charcoal", has(Items.CHARCOAL))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/asphalt_alt");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.asphalt_bricks.get(), 4)
                .pattern("AAA")
                .pattern("ADA")
                .pattern("AAA")
                .define('A', ModBlocks.asphalt.get())
                .define('D', ModItems.alchemy_dust.get())
                .unlockedBy("has_asphalt", has(ModBlocks.asphalt.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/asphalt_bricks");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.dark_matter.get(), 1)
                .pattern("AAA")
                .pattern("DDD")
                .pattern("AAA")
                .define('A', ModItems.aether_fuel.get())
                .define('D', Items.DIAMOND)
                .unlockedBy("has_aether_fuel", has(ModItems.aether_fuel.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/dark_matter");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.red_matter.get(), 1)
                .pattern("DDD")
                .pattern("AAA")
                .pattern("DDD")
                .define('A', ModItems.aether_fuel.get())
                .define('D', ModItems.dark_matter.get())
                .unlockedBy("has_dark_matter", has(ModItems.dark_matter.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/red_matter");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.void_matter.get(), 1)
                .pattern("DDD")
                .pattern("AAA")
                .pattern("DDD")
                .define('A', ModBlocks.aether_fuel_block.get())
                .define('D', ModItems.red_matter.get())
                .unlockedBy("has_red_matter", has(ModItems.red_matter.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/void_matter");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.essence_apple.get(), 1)
                .pattern("EEE")
                .pattern("EAE")
                .pattern("EEE")
                .define('E', ModItems.essence_shard.get())
                .define('A', Items.APPLE)
                .unlockedBy("has_apple", has(Items.APPLE))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/essence_apple");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.dark_matter_apple.get(), 1)
                .pattern("DDD")
                .pattern("DAD")
                .pattern("DDD")
                .define('D', ModItems.dark_matter.get())
                .define('A', Items.APPLE)
                .unlockedBy("has_apple", has(Items.APPLE))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/dark_matter_apple");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.red_matter_apple.get(), 1)
                .pattern("RRR")
                .pattern("RAR")
                .pattern("RRR")
                .define('R', ModItems.red_matter.get())
                .define('A', Items.APPLE)
                .unlockedBy("has_apple", has(Items.APPLE))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/red_matter_apple");

        TransmuteUp(consumer, Items.COAL, Items.CHARCOAL);
        TransmuteUp(consumer, ModItems.alchemical_coal.get(), Items.COAL);
        TransmuteUp(consumer, ModItems.ethern_coal.get(), ModItems.alchemical_coal.get());
        TransmuteUp(consumer, ModItems.aether_fuel.get(), ModItems.ethern_coal.get());
        TransmuteUp(consumer, Items.IRON_INGOT, Items.ENDER_PEARL);
        TransmuteUp(consumer, Items.IRON_INGOT, Items.COPPER_INGOT);
        TransmuteUp(consumer, Items.GOLD_INGOT, Items.IRON_INGOT);
        TransmuteUp(consumer, Items.DIAMOND, Items.GOLD_INGOT);
        TransmuteUp(consumer, Items.EMERALD, Items.DIAMOND);

        TransmuteXLUp(consumer, ModBlocks.alchemical_coal_block.get(), Items.COAL_BLOCK);
        TransmuteXLUp(consumer, ModBlocks.ethern_coal_block.get(), ModBlocks.alchemical_coal_block.get());
        TransmuteXLUp(consumer, ModBlocks.aether_fuel_block.get(), ModBlocks.ethern_coal_block.get());
        TransmuteXLUp(consumer, Items.IRON_BLOCK, Items.COPPER_BLOCK);
        TransmuteXLUp(consumer, Items.GOLD_BLOCK, Items.IRON_BLOCK);
        TransmuteXLUp(consumer, Items.DIAMOND_BLOCK, Items.GOLD_BLOCK);
        TransmuteXLUp(consumer, Items.EMERALD_BLOCK, Items.DIAMOND_BLOCK);

        TransmuteDown(consumer, Items.CHARCOAL, Items.COAL);
        TransmuteDown(consumer, Items.COAL, ModItems.alchemical_coal.get());
        TransmuteDown(consumer, ModItems.alchemical_coal.get(), ModItems.ethern_coal.get());
        TransmuteDown(consumer, ModItems.ethern_coal.get(), ModItems.aether_fuel.get());
        TransmuteDown(consumer, Items.ENDER_PEARL, Items.IRON_INGOT);
        TransmuteDown(consumer, Items.COPPER_INGOT, Items.IRON_INGOT);
        TransmuteDown(consumer, Items.IRON_INGOT, Items.GOLD_INGOT);
        TransmuteDown(consumer, Items.GOLD_INGOT, Items.DIAMOND);
        TransmuteDown(consumer, Items.DIAMOND, Items.EMERALD);

        BlockRecipe(consumer, ModBlocks.alchemical_coal_block.get(), ModItems.alchemical_coal.get());
        BlockRecipe(consumer, ModBlocks.ethern_coal_block.get(), ModItems.ethern_coal.get());
        BlockRecipe(consumer, ModBlocks.aether_fuel_block.get(), ModItems.aether_fuel.get());
        BlockRecipe(consumer, ModBlocks.dark_matter_block.get(), ModItems.dark_matter.get());
        BlockRecipe(consumer, ModBlocks.red_matter_block.get(), ModItems.red_matter.get());
        BlockRecipe(consumer, ModBlocks.essentia_spirit_block.get(), ModItems.essentia_spirit.get());
        BlockRecipe(consumer, ModBlocks.mysterium_spirit_block.get(), ModItems.mysterium_spirit.get());
        BlockRecipe(consumer, ModBlocks.vitalium_spirit_block.get(), ModItems.vitalium_spirit.get());
        BlockRecipe(consumer, ModBlocks.celestium_spirit_block.get(), ModItems.celestium_spirit.get());
        BlockRecipe(consumer, ModBlocks.eternium_spirit_block.get(), ModItems.eternium_spirit.get());

        UnBlockRecipe(consumer, ModItems.alchemical_coal.get(), ModBlocks.alchemical_coal_block.get());
        UnBlockRecipe(consumer, ModItems.ethern_coal.get(), ModBlocks.ethern_coal_block.get());
        UnBlockRecipe(consumer, ModItems.aether_fuel.get(), ModBlocks.aether_fuel_block.get());
        UnBlockRecipe(consumer, ModItems.dark_matter.get(), ModBlocks.dark_matter_block.get());
        UnBlockRecipe(consumer, ModItems.red_matter.get(), ModBlocks.red_matter_block.get());
        UnBlockRecipe(consumer, ModBlocks.essentia_spirit_block.get(), ModItems.essentia_spirit.get());
        UnBlockRecipe(consumer, ModBlocks.mysterium_spirit_block.get(), ModItems.mysterium_spirit.get());
        UnBlockRecipe(consumer, ModBlocks.vitalium_spirit_block.get(), ModItems.vitalium_spirit.get());
        UnBlockRecipe(consumer, ModBlocks.celestium_spirit_block.get(), ModItems.celestium_spirit.get());
        UnBlockRecipe(consumer, ModBlocks.eternium_spirit_block.get(), ModItems.eternium_spirit.get());

        HelmetCraft(consumer, ModItems.dark_matter_helmet.get(), ModItems.dark_matter.get());
        ChestplateCraft(consumer, ModItems.dark_matter_chestplate.get(), ModItems.dark_matter.get());
        LeggingsCraft(consumer, ModItems.dark_matter_leggings.get(), ModItems.dark_matter.get());
        BootsCraft(consumer, ModItems.dark_matter_boots.get(), ModItems.dark_matter.get());

        HelmetCraft(consumer, ModItems.red_matter_helmet.get(), ModItems.red_matter.get());
        ChestplateCraft(consumer, ModItems.red_matter_chestplate.get(), ModItems.red_matter.get());
        LeggingsCraft(consumer, ModItems.red_matter_leggings.get(), ModItems.red_matter.get());
        BootsCraft(consumer, ModItems.red_matter_boots.get(), ModItems.red_matter.get());

        /*HelmetCraft(consumer, ModItems.neosphore_helmet.get(), ModItems.neosphore_ingot.get());
        ChestplateCraft(consumer, ModItems.neosphore_chestplate.get(), ModItems.neosphore_ingot.get());
        LeggingsCraft(consumer, ModItems.neosphore_leggings.get(), ModItems.neosphore_ingot.get());
        BootsCraft(consumer, ModItems.neosphore_boots.get(), ModItems.neosphore_ingot.get());*/

        PickaxeCraft(consumer, ModItems.dark_matter_pickaxe.get(), ModItems.dark_matter.get(), Items.DIAMOND);
        AxeCraft(consumer, ModItems.dark_matter_axe.get(), ModItems.dark_matter.get(), Items.DIAMOND);
        ShovelCraft(consumer, ModItems.dark_matter_shovel.get(), ModItems.dark_matter.get(), Items.DIAMOND);
        HoeCraft(consumer, ModItems.dark_matter_hoe.get(), ModItems.dark_matter.get(), Items.DIAMOND);
        SwordCraft(consumer, ModItems.dark_matter_sword.get(), ModItems.dark_matter.get(), Items.DIAMOND);
        ShearsCraft(consumer, ModItems.dark_matter_shears.get(), ModItems.dark_matter.get());
        //HammerCraft(consumer, ModItems.dark_matter_hammer.get(), ModItems.dark_matter.get());

        PickaxeCraft(consumer, ModItems.red_matter_pickaxe.get(), ModItems.red_matter.get(), ModItems.dark_matter.get());
        AxeCraft(consumer, ModItems.red_matter_axe.get(), ModItems.red_matter.get(), ModItems.dark_matter.get());
        ShovelCraft(consumer, ModItems.red_matter_shovel.get(), ModItems.red_matter.get(), ModItems.dark_matter.get());
        HoeCraft(consumer, ModItems.red_matter_hoe.get(), ModItems.red_matter.get(), ModItems.dark_matter.get());
        SwordCraft(consumer, ModItems.red_matter_sword.get(), ModItems.red_matter.get(), ModItems.dark_matter.get());
        ShearsCraft(consumer, ModItems.red_matter_shears.get(), ModItems.red_matter.get());
        //HammerCraft(consumer, ModItems.red_matter_hammer.get(), ModItems.red_matter.get());

        /*PickaxeCraft(consumer, ModItems.neosphore_pickaxe.get(), ModItems.neosphore_ingot.get());
        AxeCraft(consumer, ModItems.neosphore_axe.get(), ModItems.neosphore_ingot.get());
        ShovelCraft(consumer, ModItems.neosphore_shovel.get(), ModItems.neosphore_ingot.get());
        HoeCraft(consumer, ModItems.neosphore_hoe.get(), ModItems.neosphore_ingot.get());
        SwordCraft(consumer, ModItems.neosphore_sword.get(), ModItems.neosphore_ingot.get());
        ShearsCraft(consumer, ModItems.neosphore_shears.get(), ModItems.neosphore_ingot.get());*/
        //HammerCraft(consumer, ModItems.neosphore_hammer.get(), ModItems.neosphore_ingot.get());

        upgradeSmithing(consumer, ModItems.neosphore_smithing_template.get(), ModItems.red_matter_pickaxe.get(), ModItems.neosphore_ingot.get(), ModItems.neosphore_pickaxe.get());
        upgradeSmithing(consumer, ModItems.neosphore_smithing_template.get(), ModItems.red_matter_axe.get(), ModItems.neosphore_ingot.get(), ModItems.neosphore_axe.get());
        upgradeSmithing(consumer, ModItems.neosphore_smithing_template.get(), ModItems.red_matter_shovel.get(), ModItems.neosphore_ingot.get(), ModItems.neosphore_shovel.get());
        upgradeSmithing(consumer, ModItems.neosphore_smithing_template.get(), ModItems.red_matter_hoe.get(), ModItems.neosphore_ingot.get(), ModItems.neosphore_hoe.get());
        upgradeSmithing(consumer, ModItems.neosphore_smithing_template.get(), ModItems.red_matter_sword.get(), ModItems.neosphore_ingot.get(), ModItems.neosphore_sword.get());
        upgradeSmithing(consumer, ModItems.neosphore_smithing_template.get(), ModItems.red_matter_shears.get(), ModItems.neosphore_ingot.get(), ModItems.neosphore_shears.get());
        //upgradeSmithing(consumer, ModItems.neosphore_smithing_template.get(), ModItems.red_matter_hammer.get(), ModItems.neosphore_ingot.get(), ModItems.neosphore_hammer.get());
        upgradeSmithing(consumer, ModItems.neosphore_smithing_template.get(), ModItems.red_matter_helmet.get(), ModItems.neosphore_ingot.get(), ModItems.neosphore_helmet.get());
        upgradeSmithing(consumer, ModItems.neosphore_smithing_template.get(), ModItems.red_matter_chestplate.get(), ModItems.neosphore_ingot.get(), ModItems.neosphore_chestplate.get());
        upgradeSmithing(consumer, ModItems.neosphore_smithing_template.get(), ModItems.red_matter_leggings.get(), ModItems.neosphore_ingot.get(), ModItems.neosphore_leggings.get());
        upgradeSmithing(consumer, ModItems.neosphore_smithing_template.get(), ModItems.red_matter_boots.get(), ModItems.neosphore_ingot.get(), ModItems.neosphore_boots.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.neosphore_smithing_template.get(), 2)
                .pattern("SIS")
                .pattern("SNS")
                .pattern("SSS")
                .define('S', ModBlocks.basphalt_stone.get())
                .define('I', ModItems.neosphore_ingot.get())
                .define('N', ModItems.neosphore_smithing_template.get())
                .unlockedBy("has_neosphore_smithing_template", has(ModItems.neosphore_smithing_template.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/neosphore_smithing_template");

        oreSmelting(consumer, NEOSPHORE_SMELTABLES, RecipeCategory.MISC, ModItems.neosphore_ingot.get(), 0.25f, 200, "neosphore_ingot");
        oreBlasting(consumer, NEOSPHORE_SMELTABLES, RecipeCategory.MISC, ModItems.neosphore_ingot.get(), 0.25f, 100, "neosphore_ingot");
        oreSmelting(consumer, NEOSPHORE_BLOCK_SMELTABLES, RecipeCategory.MISC, ModBlocks.neosphore_block.get(), 8.1f, 1800, "neosphore_block");
        oreBlasting(consumer, NEOSPHORE_BLOCK_SMELTABLES, RecipeCategory.MISC, ModBlocks.neosphore_block.get(), 7.3f, 900, "neosphore_block");
        oreSmelting(consumer, BASPHALT_STONE_SMELTABLES, RecipeCategory.MISC, ModBlocks.basphalt_stone.get(), 0, 200, "basphalt_stone");
        oreBlasting(consumer, BASPHALT_STONE_SMELTABLES, RecipeCategory.MISC, ModBlocks.basphalt_stone.get(), 0, 100, "basphalt_stone");

        buttonRecipe(consumer, ModBlocks.basphalt_stone_button.get(), ModBlocks.basphalt_stone.get());
        fenceGateRecipe(consumer, ModBlocks.basphalt_stone_fence_gate.get(), ModBlocks.basphalt_stone.get());
        slabRecipe(consumer, ModBlocks.basphalt_stone_slabs.get(), ModBlocks.basphalt_stone.get());
        stairsRecipe(consumer, ModBlocks.basphalt_stone_stairs.get(), ModBlocks.basphalt_stone.get());
        fenceRecipe(consumer, ModBlocks.basphalt_stone_fence.get(), ModBlocks.basphalt_stone.get());
        wallRecipe(consumer, ModBlocks.basphalt_stone_wall.get(), ModBlocks.basphalt_stone.get());
        buttonRecipe(consumer, ModBlocks.basphalt_cobblestone_button.get(), ModBlocks.basphalt_cobblestone.get());
        fenceGateRecipe(consumer, ModBlocks.basphalt_cobblestone_fence_gate.get(), ModBlocks.basphalt_cobblestone.get());
        slabRecipe(consumer, ModBlocks.basphalt_cobblestone_slabs.get(), ModBlocks.basphalt_cobblestone.get());
        stairsRecipe(consumer, ModBlocks.basphalt_cobblestone_stairs.get(), ModBlocks.basphalt_cobblestone.get());
        fenceRecipe(consumer, ModBlocks.basphalt_cobblestone_fence.get(), ModBlocks.basphalt_cobblestone.get());
        wallRecipe(consumer, ModBlocks.basphalt_cobblestone_wall.get(), ModBlocks.basphalt_cobblestone.get());
        buttonRecipe(consumer, ModBlocks.basphalt_stone_bricks_button.get(), ModBlocks.basphalt_stone_bricks.get());
        fenceGateRecipe(consumer, ModBlocks.basphalt_stone_bricks_fence_gate.get(), ModBlocks.basphalt_stone_bricks.get());
        slabRecipe(consumer, ModBlocks.basphalt_stone_bricks_slabs.get(), ModBlocks.basphalt_stone_bricks.get());
        stairsRecipe(consumer, ModBlocks.basphalt_stone_bricks_stairs.get(), ModBlocks.basphalt_stone_bricks.get());
        fenceRecipe(consumer, ModBlocks.basphalt_stone_bricks_fence.get(), ModBlocks.basphalt_stone_bricks.get());
        wallRecipe(consumer, ModBlocks.basphalt_stone_bricks_wall.get(), ModBlocks.basphalt_stone_bricks.get());
        SmallBlockRecipe(consumer, ModBlocks.basphalt_stone_bricks.get(), ModBlocks.basphalt_stone.get());
        
        BlockRecipe(consumer, ModBlocks.neosphore_block.get(), ModItems.neosphore_ingot.get());
        UnBlockRecipe(consumer, ModItems.neosphore_ingot.get(), ModBlocks.neosphore_block.get());
        BlockRecipe(consumer, ModItems.neosphore_ingot.get(), ModItems.neosphore_nugget.get());
        UnBlockRecipe(consumer, ModItems.neosphore_nugget.get(), ModItems.neosphore_ingot.get());
        BlockRecipe(consumer, ModBlocks.raw_neosphore_block.get(), ModItems.raw_neosphore.get());
        UnBlockRecipe(consumer, ModItems.raw_neosphore.get(), ModBlocks.raw_neosphore_block.get());
        SmallBlockRecipe(consumer, ModBlocks.charmel_wood.get(), ModBlocks.charmel_log.get());
        SmallBlockRecipe(consumer, ModBlocks.charmel_planks.get(), ModBlocks.charmel_log.get());
        UnSmallBlockRecipe(consumer, ModBlocks.charmel_planks.get(), ModBlocks.charmel_wood.get());
        UnSmallBlockRecipe(consumer, ModBlocks.charmel_planks.get(), ModBlocks.stripped_charmel_log.get());
        UnSmallBlockRecipe(consumer, ModBlocks.charmel_planks.get(), ModBlocks.stripped_charmel_wood.get());
        slabRecipe(consumer, ModBlocks.charmel_slab.get(), ModBlocks.charmel_planks.get());
        stairsRecipe(consumer, ModBlocks.charmel_stairs.get(), ModBlocks.charmel_planks.get());
        fenceRecipe(consumer, ModBlocks.charmel_fence.get(), ModBlocks.charmel_planks.get());
        fenceGateRecipe(consumer, ModBlocks.charmel_fence_gate.get(), ModBlocks.charmel_planks.get());
        buttonRecipe(consumer, ModBlocks.charmel_button.get(), ModBlocks.charmel_planks.get());
        pressurePlateRecipe(consumer, ModBlocks.charmel_pressure_plate.get(), ModBlocks.charmel_planks.get());
        stickRecipe(consumer, Items.STICK, ModBlocks.charmel_planks.get());

        spiritCirclePlusCrafting(consumer, ModItems.essentia_seeds.get(), ModItems.essence_shard.get(), Items.WHEAT_SEEDS, 1);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.mysterium_spirit.get(), 1)
                .pattern(" S ")
                .pattern("SNS")
                .pattern(" S ")
                .define('S', ModItems.essentia_spirit.get())
                .define('N', ModItems.essence_shard.get())
                .unlockedBy("has_essentia_spirit", has(ModItems.essentia_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/essentia_spirit_to_mysterium_spirit");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.vitalium_spirit.get(), 1)
                .pattern(" S ")
                .pattern("SNS")
                .pattern(" S ")
                .define('S', ModItems.mysterium_spirit.get())
                .define('N', ModItems.essence_shard.get())
                .unlockedBy("has_mysterium_spirit", has(ModItems.mysterium_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/mysterium_spirit_to_vitalium_spirit");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.celestium_spirit.get(), 1)
                .pattern(" S ")
                .pattern("SNS")
                .pattern(" S ")
                .define('S', ModItems.vitalium_spirit.get())
                .define('N', ModItems.essence_shard.get())
                .unlockedBy("has_vitalium_spirit", has(ModItems.vitalium_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/vitalium_spirit_to_celestium_spirit");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.eternium_spirit.get(), 1)
                .pattern(" S ")
                .pattern("SNS")
                .pattern(" S ")
                .define('S', ModItems.celestium_spirit.get())
                .define('N', ModItems.essence_shard.get())
                .unlockedBy("has_celestium_spirit", has(ModItems.celestium_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/celestium_spirit_to_eternium_spirit");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.essentia_spirit.get(), 4)
                .requires(ModItems.mysterium_spirit.get())
                .unlockedBy("has_mysterium_spirit", has(ModItems.mysterium_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/essentia_spirit_from_mysterium_spirit");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.mysterium_spirit.get(), 4)
                .requires(ModItems.vitalium_spirit.get())
                .unlockedBy("has_vitalium_spirit", has(ModItems.vitalium_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/mysterium_spirit_from_vitalium_spirit");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.vitalium_spirit.get(), 4)
                .requires(ModItems.celestium_spirit.get())
                .unlockedBy("has_celestium_spirit", has(ModItems.celestium_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/vitalium_spirit_from_celestium_spirit");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.celestium_spirit.get(), 4)
                .requires(ModItems.eternium_spirit.get())
                .unlockedBy("has_eternium_spirit", has(ModItems.eternium_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/celestium_spirit_from_eternium_spirit");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.essentia_spirit_block.get(), 4)
                .requires(ModBlocks.mysterium_spirit_block.get())
                .unlockedBy("has_mysterium_spirit_block", has(ModBlocks.mysterium_spirit_block.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/essentia_spirit_block_from_mysterium_spirit_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.mysterium_spirit_block.get(), 4)
                .requires(ModBlocks.vitalium_spirit_block.get())
                .unlockedBy("has_vitalium_spirit_block", has(ModBlocks.vitalium_spirit_block.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/mysterium_spirit_block_from_vitalium_spirit_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.vitalium_spirit_block.get(), 4)
                .requires(ModBlocks.celestium_spirit_block.get())
                .unlockedBy("has_celestium_spirit_block", has(ModBlocks.celestium_spirit_block.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/vitalium_spirit_block_from_celestium_spirit_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.celestium_spirit_block.get(), 4)
                .requires(ModBlocks.eternium_spirit_block.get())
                .unlockedBy("has_eternium_spirit_block", has(ModBlocks.eternium_spirit_block.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/celestium_spirit_block_from_eternium_spirit_block");

        // Spirit Crafting
        coralFanCrafting(consumer, Items.BUBBLE_CORAL_FAN, ModItems.coral_spirit.get(), Items.MAGENTA_DYE);
        coralCrafting(consumer, Items.BUBBLE_CORAL, ModItems.coral_spirit.get(), Items.MAGENTA_DYE);
        coralBlockCrafting(consumer, Blocks.BUBBLE_CORAL_BLOCK, ModItems.coral_spirit.get(), Items.MAGENTA_DYE);
        coralFanCrafting(consumer, Items.TUBE_CORAL_FAN, ModItems.coral_spirit.get(), Items.BLUE_DYE);
        coralCrafting(consumer, Items.TUBE_CORAL, ModItems.coral_spirit.get(), Items.BLUE_DYE);
        coralBlockCrafting(consumer, Blocks.TUBE_CORAL_BLOCK, ModItems.coral_spirit.get(), Items.BLUE_DYE);
        coralFanCrafting(consumer, Items.BRAIN_CORAL_FAN, ModItems.coral_spirit.get(), Items.PINK_DYE);
        coralCrafting(consumer, Items.BRAIN_CORAL, ModItems.coral_spirit.get(), Items.PINK_DYE);
        coralBlockCrafting(consumer, Blocks.BRAIN_CORAL_BLOCK, ModItems.coral_spirit.get(), Items.PINK_DYE);
        coralFanCrafting(consumer, Items.HORN_CORAL_FAN, ModItems.coral_spirit.get(), Items.YELLOW_DYE);
        coralCrafting(consumer, Items.HORN_CORAL, ModItems.coral_spirit.get(), Items.YELLOW_DYE);
        coralBlockCrafting(consumer, Blocks.HORN_CORAL_BLOCK, ModItems.coral_spirit.get(), Items.YELLOW_DYE);
        coralFanCrafting(consumer, Items.FIRE_CORAL_FAN, ModItems.coral_spirit.get(), Items.RED_DYE);
        coralCrafting(consumer, Items.FIRE_CORAL, ModItems.coral_spirit.get(), Items.RED_DYE);
        coralBlockCrafting(consumer, Blocks.FIRE_CORAL_BLOCK, ModItems.coral_spirit.get(), Items.RED_DYE);

        spiritCrafting(consumer, Items.COAL, ModItems.coal_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.CHARCOAL, ModItems.wood_spirit.get(), ModItems.coal_spirit.get(), 12);
        spiritCirclePlusCrafting(consumer, Items.STONE, ModItems.stone_spirit.get(), ModItems.coal_spirit.get(), 20);
        spiritCirclePlusCrafting(consumer, Items.DEEPSLATE, ModItems.deepslate_spirit.get(), ModItems.coal_spirit.get(), 20);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PODZOL, 12)
                .pattern(" D ")
                .pattern("DND")
                .pattern(" D ")
                .define('D', ModItems.dirt_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_dirt_spirit", has(ModItems.dirt_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/podzol");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.MYCELIUM, 20)
                .pattern("DND")
                .pattern("DND")
                .pattern("DND")
                .define('D', ModItems.dirt_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_dirt_spirit", has(ModItems.dirt_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/mycelium");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.SAND, 16)
                .pattern("FD")
                .pattern("DF")
                .define('F', ModItems.fire_spirit.get())
                .define('D', ModItems.dirt_spirit.get())
                .unlockedBy("has_fire_spirit", has(ModItems.fire_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/sand");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GRAVEL, 16)
                .pattern("SD")
                .pattern("DS")
                .define('S', ModItems.stone_spirit.get())
                .define('D', ModItems.dirt_spirit.get())
                .unlockedBy("has_stone_spirit", has(ModItems.stone_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/gravel");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.RED_MUSHROOM, 8)
                .pattern(" N ")
                .pattern(" D ")
                .pattern(" N ")
                .define('D', ModItems.dirt_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_dirt_spirit", has(ModItems.dirt_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/red_mushroom");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.BROWN_MUSHROOM, 8)
                .pattern("NDN")
                .define('D', ModItems.dirt_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_dirt_spirit", has(ModItems.dirt_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/brown_mushroom");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.MUD, 12)
                .pattern(" D ")
                .pattern("DWD")
                .pattern(" D ")
                .define('D', ModItems.dirt_spirit.get())
                .define('W', ModItems.water_spirit.get())
                .unlockedBy("has_dirt_spirit", has(ModItems.dirt_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/mud");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.CLAY, 24)
                .pattern("WD")
                .pattern("DW")
                .define('W', ModItems.water_spirit.get())
                .define('D', ModItems.dirt_spirit.get())
                .unlockedBy("has_water_spirit", has(ModItems.water_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/clay");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.RED_SAND, 16)
                .pattern("FD")
                .pattern("DF")
                .define('F', ModItems.fire_spirit.get())
                .define('D', ModItems.dirt_spirit.get())
                .unlockedBy("has_fire_spirit", has(ModItems.fire_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/red_sand");

        spiritCrafting(consumer, Items.DIRT, ModItems.dirt_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.GRASS_BLOCK, ModItems.dirt_spirit.get(), ModItems.nature_spirit.get(), 20);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GRANITE, 16)
                .pattern(" S ")
                .pattern("SQS")
                .pattern(" S ")
                .define('S', ModItems.stone_spirit.get())
                .define('Q', ModItems.nether_quartz_spirit.get())
                .unlockedBy("has_stone_spirit", has(ModItems.stone_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/granite");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.CALCITE, 20)
                .pattern("SSS")
                .pattern("SAS")
                .pattern("SSS")
                .define('S', ModItems.stone_spirit.get())
                .define('A', ModItems.amethyst_spirit.get())
                .unlockedBy("has_stone_spirit", has(ModItems.stone_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/calcite");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.BLACKSTONE, 24)
                .pattern("NNN")
                .pattern("NSN")
                .pattern("NNN")
                .define('N', ModItems.nether_spirit.get())
                .define('S', ModItems.stone_spirit.get())
                .unlockedBy("has_nether_spirit", has(ModItems.nether_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/blackstone");

        spiritCrafting(consumer, Items.COBBLESTONE, ModItems.stone_spirit.get(), 8);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.FLINT, 8)
                .pattern("SF")
                .pattern("FS")
                .define('S', ModItems.stone_spirit.get())
                .define('F', ModItems.fire_spirit.get())
                .unlockedBy("has_stone_spirit", has(ModItems.stone_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/flint");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.DRIPSTONE_BLOCK, 12)
                .pattern(" S ")
                .pattern("SWS")
                .pattern(" S ")
                .define('S', ModItems.stone_spirit.get())
                .define('W', ModItems.water_spirit.get())
                .unlockedBy("has_stone_spirit", has(ModItems.stone_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/dripstone_block");

        spiritCirclePlusCrafting(consumer, Items.STONE_BRICKS, ModItems.stone_spirit.get(), ModItems.coal_spirit.get(), 20);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.DIORITE, 16)
                .pattern(" Q ")
                .pattern("SSS")
                .pattern(" S ")
                .define('S', ModItems.stone_spirit.get())
                .define('Q', ModItems.nether_quartz_spirit.get())
                .unlockedBy("has_stone_spirit", has(ModItems.stone_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/diorite");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.ANDESITE, 16)
                .pattern(" S ")
                .pattern("SSS")
                .pattern(" Q ")
                .define('S', ModItems.stone_spirit.get())
                .define('Q', ModItems.nether_quartz_spirit.get())
                .unlockedBy("has_stone_spirit", has(ModItems.stone_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/andesite");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.TUFF, 12)
                .pattern(" S ")
                .pattern("SFS")
                .pattern(" S ")
                .define('S', ModItems.stone_spirit.get())
                .define('F', ModItems.fire_spirit.get())
                .unlockedBy("has_stone_spirit", has(ModItems.stone_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/tuff");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.MANGROVE_LOG, 16)
                .pattern("W  ")
                .pattern(" W ")
                .pattern("  W")
                .define('W', ModItems.wood_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/mangrove_log");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.MANGROVE_PROPAGULE, 8)
                .pattern("W  ")
                .pattern(" N ")
                .pattern("  W")
                .define('W', ModItems.wood_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/mangrove_propagule");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.OAK_SAPLING, 8)
                .pattern("WNW")
                .pattern("   ")
                .pattern("   ")
                .define('W', ModItems.wood_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/oak_sapling");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.DARK_OAK_LOG, 16)
                .pattern("  W")
                .pattern("  W")
                .pattern("  W")
                .define('W', ModItems.wood_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/dark_oak_log");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.CHERRY_SAPLING, 8)
                .pattern("  W")
                .pattern(" N ")
                .pattern("W  ")
                .define('W', ModItems.wood_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/cherry_sapling");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.BIRCH_LOG, 16)
                .pattern("   ")
                .pattern("   ")
                .pattern("WWW")
                .define('W', ModItems.wood_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/birch_log");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.OAK_LOG, 16)
                .pattern("WWW")
                .pattern("   ")
                .pattern("   ")
                .define('W', ModItems.wood_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/oak_log");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.JUNGLE_SAPLING, 8)
                .pattern("W  ")
                .pattern("N  ")
                .pattern("W  ")
                .define('W', ModItems.wood_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/jungle_sapling");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.SPRUCE_LOG, 16)
                .pattern("   ")
                .pattern("WWW")
                .pattern("   ")
                .define('W', ModItems.wood_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/spruce_log");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.ACACIA_LOG, 16)
                .pattern(" W ")
                .pattern(" W ")
                .pattern(" W ")
                .define('W', ModItems.wood_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/acacia_log");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.CRIMSON_STEM, 16)
                .pattern("W  ")
                .pattern("N  ")
                .pattern("W  ")
                .define('W', ModItems.wood_spirit.get())
                .define('N', ModItems.nether_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/crimson_stem");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.DARK_OAK_SAPLING, 8)
                .pattern("  W")
                .pattern("  N")
                .pattern("  W")
                .define('W', ModItems.wood_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/dark_oak_sapling");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.SPRUCE_SAPLING, 8)
                .pattern("   ")
                .pattern("WNW")
                .pattern("   ")
                .define('W', ModItems.wood_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/spruce_sapling");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.JUNGLE_LOG, 16)
                .pattern("W  ")
                .pattern("W  ")
                .pattern("W  ")
                .define('W', ModItems.wood_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/jungle_log");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.WARPED_STEM, 16)
                .pattern(" W ")
                .pattern(" N ")
                .pattern(" W ")
                .define('W', ModItems.wood_spirit.get())
                .define('N', ModItems.nether_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/warped_stem");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.BIRCH_SAPLING, 8)
                .pattern("   ")
                .pattern("   ")
                .pattern("WNW")
                .define('W', ModItems.wood_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/birch_sapling");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.ACACIA_SAPLING, 8)
                .pattern(" W ")
                .pattern(" N ")
                .pattern(" W ")
                .define('W', ModItems.wood_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/acacia_sapling");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.CHERRY_LOG, 16)
                .pattern("  W")
                .pattern(" W ")
                .pattern("W  ")
                .define('W', ModItems.wood_spirit.get())
                .unlockedBy("has_wood_spirit", has(ModItems.wood_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/cherry_log");

        BlockRecipe(consumer, Items.PACKED_ICE, ModItems.ice_spirit.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.SNOW_BLOCK, 12)
                .pattern(" I ")
                .pattern("I I")
                .pattern(" I ")
                .define('I', ModItems.ice_spirit.get())
                .unlockedBy("has_ice_spirit", has(ModItems.ice_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/snow_block");

        spiritCirclePlusCrafting(consumer, Items.BASALT, ModItems.nether_spirit.get(), ModItems.ice_spirit.get(), 20);
        spiritCrafting(consumer, Items.ICE, ModItems.ice_spirit.get(), 4);
        spiritCrafting(consumer, Items.COBBLED_DEEPSLATE, ModItems.deepslate_spirit.get(), 8);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.CHORUS_FRUIT, 8)
                .pattern("ENE")
                .pattern("   ")
                .pattern("   ")
                .define('E', ModItems.end_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_end_spirit", has(ModItems.end_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/chorus_fruit");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.CRIMSON_FUNGUS, 4)
                .pattern("N  ")
                .pattern("M  ")
                .pattern("N  ")
                .define('M', ModItems.nether_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nether_spirit", has(ModItems.nether_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/crimson_fungus");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.WARPED_NYLIUM, 20)
                .pattern("MMM")
                .pattern("NNN")
                .pattern("MMM")
                .define('M', ModItems.nether_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nether_spirit", has(ModItems.nether_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/warped_nylium");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.CARROT, 8)
                .pattern("   ")
                .pattern("   ")
                .pattern("NNN")
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nature_spirit", has(ModItems.nature_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/carrot");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.WHEAT, 8)
                .pattern("NNN")
                .pattern("   ")
                .pattern("   ")
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nature_spirit", has(ModItems.nature_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/wheat");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.LILY_PAD, 8)
                .pattern("N N")
                .pattern("NNN")
                .pattern(" N ")
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nature_spirit", has(ModItems.nature_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/lily_pad");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.MELON, 8)
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nature_spirit", has(ModItems.nature_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/melon");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.POISONOUS_POTATO, 1)
                .pattern("PN ")
                .pattern("   ")
                .pattern("   ")
                .define('P', Items.POTATO)
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nature_spirit", has(ModItems.nature_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/poisonous_potato");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.VINE, 12)
                .pattern(" N ")
                .pattern(" N ")
                .pattern(" N ")
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nature_spirit", has(ModItems.nature_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/vine");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.KELP, 8)
                .pattern("NWN")
                .pattern("   ")
                .pattern("   ")
                .define('W', ModItems.water_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nature_spirit", has(ModItems.nature_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/kelp");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.POTATO, 8)
                .pattern("   ")
                .pattern("NNN")
                .pattern("   ")
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nature_spirit", has(ModItems.nature_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/potato");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.BEETROOT, 8)
                .pattern("N  ")
                .pattern("N  ")
                .pattern("N  ")
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nature_spirit", has(ModItems.nature_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/beetroot");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.SUGAR_CANE, 16)
                .pattern(" N ")
                .pattern("NNN")
                .pattern("N N")
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nature_spirit", has(ModItems.nature_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/sugar_cane");

        spiritCrafting(consumer, Items.PUMPKIN, ModItems.nature_spirit.get(), 16);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.CRIMSON_NYLIUM, 20)
                .pattern("MNM")
                .pattern("MNM")
                .pattern("MNM")
                .define('M', ModItems.nether_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nether_spirit", has(ModItems.nether_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/crimson_nylium");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.WARPED_FUNGUS, 4)
                .pattern(" N ")
                .pattern(" M ")
                .pattern(" N ")
                .define('M', ModItems.nether_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nether_spirit", has(ModItems.nether_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/warped_fungus");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.CACTUS, 16)
                .pattern("NNN")
                .pattern(" N ")
                .pattern("NNN")
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nature_spirit", has(ModItems.nature_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/cactus");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.COCOA_BEANS, 8)
                .pattern("  N")
                .pattern("  N")
                .pattern("  N")
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nature_spirit", has(ModItems.nature_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/cocoa_beans");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.APPLE, 8)
                .pattern("N  ")
                .pattern(" N ")
                .pattern("  N")
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nature_spirit", has(ModItems.nature_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/apple");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.NETHER_WART, 8)
                .pattern("MNM")
                .pattern("   ")
                .pattern("   ")
                .define('M', ModItems.nether_spirit.get())
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nether_spirit", has(ModItems.nether_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/nether_wart");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.BAMBOO, 16)
                .pattern("N  ")
                .pattern(" N ")
                .pattern("  N")
                .define('N', ModItems.nature_spirit.get())
                .unlockedBy("has_nature_spirit", has(ModItems.nature_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/bamboo");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.LIME_DYE, 8)
                .pattern("  D")
                .pattern("  D")
                .pattern("  D")
                .define('D', ModItems.dye_spirit.get())
                .unlockedBy("has_dye_spirit", has(ModItems.dye_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/lime_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.MAGENTA_DYE, 8)
                .pattern("   ")
                .pattern("   ")
                .pattern("DDD")
                .define('D', ModItems.dye_spirit.get())
                .unlockedBy("has_dye_spirit", has(ModItems.dye_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/magenta_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GRAY_DYE, 8)
                .pattern("  D")
                .pattern(" D ")
                .pattern("D  ")
                .define('D', ModItems.dye_spirit.get())
                .unlockedBy("has_dye_spirit", has(ModItems.dye_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/gray_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.LIGHT_BLUE_DYE, 8)
                .pattern("D  ")
                .pattern("D  ")
                .pattern("D  ")
                .define('D', ModItems.dye_spirit.get())
                .unlockedBy("has_dye_spirit", has(ModItems.dye_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/light_blue_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GREEN_DYE, 8)
                .pattern("  D")
                .pattern("DD ")
                .pattern("   ")
                .define('D', ModItems.dye_spirit.get())
                .unlockedBy("has_dye_spirit", has(ModItems.dye_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/green_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.BLUE_DYE, 8)
                .pattern("   ")
                .pattern(" D ")
                .pattern("D D")
                .define('D', ModItems.dye_spirit.get())
                .unlockedBy("has_dye_spirit", has(ModItems.dye_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/blue_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.LIGHT_GRAY_DYE, 8)
                .pattern(" D ")
                .pattern(" D ")
                .pattern("  D")
                .define('D', ModItems.dye_spirit.get())
                .unlockedBy("has_dye_spirit", has(ModItems.dye_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/light_gray_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.ORANGE_DYE, 8)
                .pattern("   ")
                .pattern("DDD")
                .pattern("   ")
                .define('D', ModItems.dye_spirit.get())
                .unlockedBy("has_dye_spirit", has(ModItems.dye_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/orange_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PURPLE_DYE, 8)
                .pattern("  D")
                .pattern("  D")
                .pattern(" D ")
                .define('D', ModItems.dye_spirit.get())
                .unlockedBy("has_dye_spirit", has(ModItems.dye_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/purple_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PINK_DYE, 8)
                .pattern("D  ")
                .pattern(" D ")
                .pattern("  D")
                .define('D', ModItems.dye_spirit.get())
                .unlockedBy("has_dye_spirit", has(ModItems.dye_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/pink_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.BLACK_DYE, 8)
                .pattern("   ")
                .pattern("DD ")
                .pattern("  D")
                .define('D', ModItems.dye_spirit.get())
                .unlockedBy("has_dye_spirit", has(ModItems.dye_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/black_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.WHITE_DYE, 8)
                .pattern("DDD")
                .pattern("   ")
                .pattern("   ")
                .define('D', ModItems.dye_spirit.get())
                .unlockedBy("has_dye_spirit", has(ModItems.dye_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/white_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.YELLOW_DYE, 8)
                .pattern(" D ")
                .pattern(" D ")
                .pattern(" D ")
                .define('D', ModItems.dye_spirit.get())
                .unlockedBy("has_dye_spirit", has(ModItems.dye_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/yellow_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.CYAN_DYE, 8)
                .pattern("D D")
                .pattern(" D ")
                .pattern("   ")
                .define('D', ModItems.dye_spirit.get())
                .unlockedBy("has_dye_spirit", has(ModItems.dye_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/cyan_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.BROWN_DYE, 8)
                .pattern("  D")
                .pattern(" D ")
                .pattern("  D")
                .define('D', ModItems.dye_spirit.get())
                .unlockedBy("has_dye_spirit", has(ModItems.dye_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/brown_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.RED_DYE, 8)
                .pattern("  D")
                .pattern(" D ")
                .pattern(" D ")
                .define('D', ModItems.dye_spirit.get())
                .unlockedBy("has_dye_spirit", has(ModItems.dye_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/red_dye");

        spiritCirclePlusCrafting(consumer, Items.SOUL_SAND, ModItems.nether_spirit.get(), ModItems.fire_spirit.get(), 16);
        spiritCrafting(consumer, Items.NETHERRACK, ModItems.nether_spirit.get(), 16);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.NETHER_BRICKS, 8)
                .pattern(" N ")
                .pattern("N N")
                .pattern(" N ")
                .define('N', ModItems.nether_spirit.get())
                .unlockedBy("has_nether_spirit", has(ModItems.nether_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/nether_bricks");

        spiritCirclePlusCrafting(consumer, Items.SOUL_SOIL, ModItems.nether_spirit.get(), ModItems.earth_spirit.get(), 16);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.HONEY_BOTTLE, 1)
                .pattern("HB ")
                .pattern("   ")
                .pattern("   ")
                .define('H', ModItems.honey_spirit.get())
                .define('B', Items.GLASS_BOTTLE)
                .unlockedBy("has_honey_spirit", has(ModItems.honey_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/honey_bottle");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.HONEYCOMB, 8)
                .pattern("HHH")
                .pattern("   ")
                .pattern("   ")
                .define('H', ModItems.honey_spirit.get())
                .unlockedBy("has_honey_spirit", has(ModItems.honey_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/honeycomb");

        spiritCrafting(consumer, Items.AMETHYST_SHARD, ModItems.amethyst_spirit.get(), 4);
        spiritCrafting(consumer, Items.IRON_INGOT, ModItems.iron_spirit.get(), 8);
        spiritCrafting(consumer, Items.COPPER_INGOT, ModItems.copper_spirit.get(), 6);
        spiritCrafting(consumer, Items.QUARTZ, ModItems.nether_quartz_spirit.get(), 8);
        spiritCrafting(consumer, Items.GLOWSTONE_DUST, ModItems.glowstone_spirit.get(), 8);
        spiritCrafting(consumer, Items.REDSTONE, ModItems.redstone_spirit.get(), 8);
        spiritCrafting(consumer, Items.OBSIDIAN, ModItems.obsidian_spirit.get(), 8);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PRISMARINE_SHARD, 8)
                .pattern("PPP")
                .pattern("   ")
                .pattern("   ")
                .define('P', ModItems.prismarine_spirit.get())
                .unlockedBy("has_water_spirit", has(ModItems.water_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/prismarine_shard");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PRISMARINE_CRYSTALS, 8)
                .pattern("   ")
                .pattern("PPP")
                .pattern("   ")
                .define('P', ModItems.prismarine_spirit.get())
                .unlockedBy("has_water_spirit", has(ModItems.water_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/prismarine_crystals");

        spiritCrafting(consumer, Items.GOLD_INGOT, ModItems.gold_spirit.get(), 4);
        spiritCrafting(consumer, Items.LAPIS_LAZULI, ModItems.lapis_spirit.get(), 8);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PURPUR_BLOCK, 8)
                .pattern(" E ")
                .pattern("E E")
                .pattern(" E ")
                .define('E', ModItems.end_spirit.get())
                .unlockedBy("has_end_spirit", has(ModItems.end_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/purpur_block");

        spiritCrafting(consumer, Items.END_STONE, ModItems.end_spirit.get(), 8);
        spiritCrafting(consumer, Items.DIAMOND, ModItems.diamond_spirit.get(), 1);
        spiritCrafting(consumer, Items.EMERALD, ModItems.emerald_spirit.get(), 1);
        spiritCrafting(consumer, Items.NETHERITE_INGOT, ModItems.netherite_spirit.get(), 1);

        spiritCirclePlusCrafting(consumer, Items.BLACK_CONCRETE, Items.BLACK_CONCRETE_POWDER, ModItems.water_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.BLUE_CONCRETE, Items.BLUE_CONCRETE_POWDER, ModItems.water_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.BROWN_CONCRETE, Items.BROWN_CONCRETE_POWDER, ModItems.water_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.CYAN_CONCRETE, Items.CYAN_CONCRETE_POWDER, ModItems.water_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.GRAY_CONCRETE, Items.GRAY_CONCRETE_POWDER, ModItems.water_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.GREEN_CONCRETE, Items.GREEN_CONCRETE_POWDER, ModItems.water_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.LIGHT_BLUE_CONCRETE, Items.LIGHT_BLUE_CONCRETE_POWDER, ModItems.water_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.LIGHT_GRAY_CONCRETE, Items.LIGHT_GRAY_CONCRETE_POWDER, ModItems.water_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.LIME_CONCRETE, Items.LIME_CONCRETE_POWDER, ModItems.water_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.MAGENTA_CONCRETE, Items.MAGENTA_CONCRETE_POWDER, ModItems.water_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.ORANGE_CONCRETE, Items.ORANGE_CONCRETE_POWDER, ModItems.water_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.PINK_CONCRETE, Items.PINK_CONCRETE_POWDER, ModItems.water_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.PURPLE_CONCRETE, Items.PURPLE_CONCRETE_POWDER, ModItems.water_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.RED_CONCRETE, Items.RED_CONCRETE_POWDER, ModItems.water_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.WHITE_CONCRETE, Items.WHITE_CONCRETE_POWDER, ModItems.water_spirit.get(), 8);
        spiritCirclePlusCrafting(consumer, Items.YELLOW_CONCRETE, Items.YELLOW_CONCRETE_POWDER, ModItems.water_spirit.get(), 8);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.WATER_BUCKET, 1)
                .pattern(" W ")
                .pattern("WBW")
                .pattern(" W ")
                .define('W', ModItems.water_spirit.get())
                .define('B', Items.BUCKET)
                .unlockedBy("has_water_spirit", has(ModItems.water_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/water_bucket");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.LAVA_BUCKET, 1)
                .pattern(" L ")
                .pattern("LBL")
                .pattern(" L ")
                .define('L', ModItems.fire_spirit.get())
                .define('B', Items.BUCKET)
                .unlockedBy("has_fire_spirit", has(ModItems.fire_spirit.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/lava_bucket");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PAPER, 1)
                .pattern("BBB")
                .define('B', ModTags.Items.bark)
                .unlockedBy("has_bark", has(ModTags.Items.bark))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/paper");

        createAlchemicalChamberRecipe(consumer, ModItems.coal_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.COAL_BLOCK, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.coral_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.FIRE_CORAL_BLOCK, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.diamond_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.DIAMOND_BLOCK, ModBlocks.celestium_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.dirt_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.DIRT, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.dye_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.WHITE_DYE, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.emerald_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.EMERALD_BLOCK, ModBlocks.celestium_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.end_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.END_STONE, ModBlocks.mysterium_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.experience_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.EXPERIENCE_BOTTLE, ModBlocks.celestium_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.fire_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.MAGMA_BLOCK, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.glowstone_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.GLOWSTONE, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.gold_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.GOLD_BLOCK, ModBlocks.vitalium_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.ice_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.PACKED_ICE, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.iron_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.IRON_BLOCK, ModBlocks.vitalium_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.lapis_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.LAPIS_BLOCK, ModBlocks.vitalium_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.nature_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.GRASS_BLOCK, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.nether_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.NETHERRACK, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.nether_quartz_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.QUARTZ_BLOCK, ModBlocks.vitalium_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.obsidian_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.OBSIDIAN, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.redstone_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.REDSTONE_BLOCK, ModBlocks.vitalium_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.water_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.WATER_BUCKET, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.wood_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.OAK_LOG, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.amethyst_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.AMETHYST_BLOCK, ModBlocks.celestium_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.copper_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.COPPER_BLOCK, ModBlocks.vitalium_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.honey_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.HONEY_BLOCK, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.prismarine_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.PRISMARINE_BRICKS, ModBlocks.mysterium_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.air_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.FEATHER, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.earth_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.DIRT, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.stone_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.STONE, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.deepslate_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.DEEPSLATE, ModBlocks.essentia_spirit_block.get());

        createAlchemicalChamberRecipe(consumer, ModItems.netherite_seeds.get(), Items.WHEAT_SEEDS, ModItems.alchemy_dust.get(),
                Items.NETHERITE_BLOCK, ModBlocks.eternium_spirit_block.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.generator_block.get(), 1)
                .pattern("III")
                .pattern("RFR")
                .pattern("III")
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('F', Items.FURNACE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/generator_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.charger_block.get(), 1)
                .pattern("III")
                .pattern("RGR")
                .pattern("III")
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('G', ModBlocks.generator_block.get())
                .unlockedBy("has_generator_block", has(ModBlocks.generator_block.get()))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/charger_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.cable_block.get(), 8)
                .pattern("III")
                .pattern("RRR")
                .pattern("III")
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/cable_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.facade_block.get(), 1)
                .pattern("SNS")
                .pattern("NCN")
                .pattern("SNS")
                .define('S', Items.STRING)
                .define('N', Items.IRON_NUGGET)
                .define('C', ModBlocks.cable_block.get())
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/facade_block");
    }

    private void createAlchemicalChamberRecipe(Consumer<FinishedRecipe> consumer, Item result, ItemLike seed, ItemLike dust, ItemLike ingredient1, ItemLike ingredient2)
    {
        AlchemicalChamberRecipeBuilder.customRecipe(new ResourceLocation(HavenAlchemy.MOD_ID + "alchemical_chamber"), AlchemicalChamberRecipe.Serializer.INSTANCE)
                .ingredient(seed)
                .ingredient(dust)
                .ingredient(ingredient1)
                .ingredient(ingredient1)
                .ingredient(ingredient1)
                .ingredient(ingredient1)
                .ingredient(ingredient2)
                .ingredient(ingredient2)
                .ingredient(ingredient2)
                .ingredient(ingredient2)
                .result(result)
                .save(consumer, new ResourceLocation(HavenAlchemy.MOD_ID + ":alchemical_chamber/" + getItemName(result)));
    }

    protected static void spiritCirclePlusCrafting(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient, ItemLike ingredient2, int amount)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, amount)
                .pattern("SSS")
                .pattern("SIS")
                .pattern("SSS")
                .define('S', ingredient)
                .define('I', ingredient2)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/" + getItemName(result));
    }

    protected static void coralFanCrafting(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient, ItemLike dye)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 6)
                .pattern("C C")
                .pattern(" D ")
                .pattern("C C")
                .define('C', ingredient)
                .define('D', dye)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/" + getItemName(result));
    }

    protected static void coralCrafting(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient, ItemLike dye)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 8)
                .pattern(" C ")
                .pattern("CDC")
                .pattern(" C ")
                .define('C', ingredient)
                .define('D', dye)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/" + getItemName(result));
    }

    protected static void coralBlockCrafting(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient, ItemLike dye)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 12)
                .pattern("CCC")
                .pattern("CDC")
                .pattern("CCC")
                .define('C', ingredient)
                .define('D', dye)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/" + getItemName(result));
    }

    protected static void spiritCrafting(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient, int amount)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, amount)
                .pattern("SSS")
                .pattern("S S")
                .pattern("SSS")
                .define('S', ingredient)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/spirit/" + getItemName(result));
    }

    protected static void TransmuteUp(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern(" L ")
                .pattern("LAL")
                .pattern(" L ")
                .define('L', ingredient)
                .define('A', ModTags.Items.alchemy_stones)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":transmute/" + getItemName(result) + "_from_" + getItemName(ingredient));
    }

    protected static void TransmuteXLUp(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern("ALA")
                .pattern("L L")
                .pattern("ALA")
                .define('L', ingredient)
                .define('A', ModTags.Items.alchemy_stones)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":transmute_block/" + getItemName(result) + "_from_" + getItemName(ingredient));
    }

    protected static void TransmuteDown(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike pIngredient)
    {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, item, 4)
                .requires(pIngredient, 1)
                .requires(ModTags.Items.alchemy_stones)
                .unlockedBy("has_" + getItemName(pIngredient), has(pIngredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":transmute/" + getItemName(item) + "_from_" + getItemName(pIngredient));
    }

    protected static void BlockRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern("LLL")
                .pattern("LLL")
                .pattern("LLL")
                .define('L', ingredient)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/" + getItemName(ingredient) + "_to_" + getItemName(result));
    }

    protected static void UnBlockRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient)
    {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingredient, 9)
                .requires(result, 1)
                .unlockedBy("has_" + getItemName(result), has(result))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/" + getItemName(ingredient) + "_from_" + getItemName(result));
    }
    
    protected static void SmallBlockRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern("LL")
                .pattern("LL")
                .define('L', ingredient)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/" + getItemName(ingredient) + "_to_" + getItemName(result));
    }
    
    protected static void UnSmallBlockRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient)
    {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingredient, 4)
                .requires(result, 1)
                .unlockedBy("has_" + getItemName(result), has(result))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/" + getItemName(ingredient) + "_from_" + getItemName(result));
    }

    protected static void HelmetCraft(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern("LLL")
                .pattern("L L")
                .define('L', ingredient)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/" + getItemName(result));
    }

    protected static void ChestplateCraft(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern("L L")
                .pattern("LLL")
                .pattern("LLL")
                .define('L', ingredient)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/" + getItemName(result));
    }

    protected static void LeggingsCraft(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern("LLL")
                .pattern("L L")
                .pattern("L L")
                .define('L', ingredient)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/" + getItemName(result));
    }

    protected static void BootsCraft(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern("L L")
                .pattern("L L")
                .define('L', ingredient)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/" + getItemName(result));
    }

    protected static void ShovelCraft(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient, ItemLike ingredient2)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern("L")
                .pattern("S")
                .pattern("S")
                .define('L', ingredient)
                .define('S', ingredient2)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/" + getItemName(result));
    }

    protected static void PickaxeCraft(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient, ItemLike ingredient2)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern("LLL")
                .pattern(" S ")
                .pattern(" S ")
                .define('L', ingredient)
                .define('S', ingredient2)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/" + getItemName(result));
    }

    protected static void AxeCraft(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient, ItemLike ingredient2)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern("LL")
                .pattern("LS")
                .pattern(" S")
                .define('L', ingredient)
                .define('S', ingredient2)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/" + getItemName(result));
    }

    protected static void HoeCraft(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient, ItemLike ingredient2)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern("LL")
                .pattern(" S")
                .pattern(" S")
                .define('L', ingredient)
                .define('S', ingredient2)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/" + getItemName(result));
    }

    protected static void SwordCraft(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient, ItemLike ingredient2)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern("L")
                .pattern("L")
                .pattern("S")
                .define('L', ingredient)
                .define('S', ingredient2)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/" + getItemName(result));
    }

    protected static void ShearsCraft(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern("L ")
                .pattern(" L")
                .define('L', ingredient)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/" + getItemName(result));
    }

    protected static void HammerCraft(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient, ItemLike ingredient2)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern("LLL")
                .pattern("LSL")
                .pattern(" S ")
                .define('L', ingredient)
                .define('S', ingredient2)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenAlchemy.MOD_ID + ":craft/" + getItemName(result));
    }

    protected static void upgradeSmithing(Consumer<FinishedRecipe> consumer, ItemLike template, ItemLike base, ItemLike addition, Item result)
    {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(template), Ingredient.of(base), Ingredient.of(addition), RecipeCategory.MISC, result)
                .unlocks("has_" + getItemName(base), has(base))
                .save(consumer, HavenAlchemy.MOD_ID + ":smithing/" + getItemName(result));
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }
    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  HavenAlchemy.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }


    protected static void stoneCutterRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pResult, ItemLike pIngredient) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(pIngredient), RecipeCategory.MISC, pResult)
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pFinishedRecipeConsumer, HavenAlchemy.MOD_ID + ":" + getItemName(pResult) + "_from_stonecutting_" + getItemName(pIngredient));
    }

    protected static void slabRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pSlab, ItemLike pIngredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pSlab, 6)
                .pattern("III")
                .define('I', pIngredient)
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pFinishedRecipeConsumer, HavenAlchemy.MOD_ID + ":" + getItemName(pSlab) + "_from_" + getItemName(pIngredient));
    }

    protected static void stairsRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pStairs, ItemLike pIngredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pStairs, 4)
                .pattern("I  ")
                .pattern("II ")
                .pattern("III")
                .define('I', pIngredient)
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pFinishedRecipeConsumer, HavenAlchemy.MOD_ID + ":" + getItemName(pStairs) + "_from_" + getItemName(pIngredient));
    }

    protected static void fenceRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pFence, ItemLike pIngredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pFence, 3)
                .pattern("IPI")
                .pattern("IPI")
                .define('I', pIngredient)
                .define('P', Items.STICK)
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pFinishedRecipeConsumer, HavenAlchemy.MOD_ID + ":" + getItemName(pFence) + "_from_" + getItemName(pIngredient));
    }

    protected static void pressurePlateRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pPressurePlate, ItemLike pIngredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pPressurePlate, 1)
                .pattern("II ")
                .pattern("   ")
                .define('I', pIngredient)
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pFinishedRecipeConsumer, HavenAlchemy.MOD_ID + ":" + getItemName(pPressurePlate) + "_from_" + getItemName(pIngredient));
    }

    protected static void fenceGateRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pFenceGate, ItemLike pIngredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pFenceGate)
                .pattern("PSP")
                .pattern("PSP")
                .define('P', Items.STICK)
                .define('S', pIngredient)
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pFinishedRecipeConsumer, HavenAlchemy.MOD_ID + ":" + getItemName(pFenceGate) + "_from_" + getItemName(pIngredient));
    }

    protected static void buttonRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pButton, ItemLike pIngredient) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, pButton)
                .requires(pIngredient)
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pFinishedRecipeConsumer, HavenAlchemy.MOD_ID + ":" + getItemName(pButton) + "_from_" + getItemName(pIngredient));
    }

    protected static void wallRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pWall, ItemLike pIngredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pWall, 6)
                .pattern("III")
                .pattern("III")
                .define('I', pIngredient)
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pFinishedRecipeConsumer, HavenAlchemy.MOD_ID + ":" + getItemName(pWall) + "_from_" + getItemName(pIngredient));
    }

    protected static void stickRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pStick, ItemLike pIngredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pStick, 4)
                .pattern("I")
                .pattern("I")
                .define('I', pIngredient)
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pFinishedRecipeConsumer, HavenAlchemy.MOD_ID + ":" + getItemName(pStick) + "_from_" + getItemName(pIngredient));
    }
}
