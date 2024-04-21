package net.cathienova.havenalchemy.item;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers
{
    public static final Tier stone = TierSortingRegistry.registerTier(
            new ForgeTier(1, 131, 2f, 1f, 25,
                    ModTags.Blocks.needs_stone_tool, () -> Ingredient.of(Tags.Items.STONE)),
            new ResourceLocation(HavenAlchemy.MOD_ID, "stone"), List.of(Tiers.WOOD), List.of());

    public static final Tier iron = TierSortingRegistry.registerTier(
            new ForgeTier(2, 250, 3f, 2f, 25,
                    ModTags.Blocks.needs_iron_tool, () -> Ingredient.of(Tags.Items.INGOTS_IRON)),
            new ResourceLocation(HavenAlchemy.MOD_ID, "iron"), List.of(Tiers.STONE), List.of());

    public static final Tier gold = TierSortingRegistry.registerTier(
            new ForgeTier(3, 169, 4f, 3f, 25,
                    ModTags.Blocks.needs_gold_tool, () -> Ingredient.of(Tags.Items.INGOTS_GOLD)),
            new ResourceLocation(HavenAlchemy.MOD_ID, "gold"), List.of(Tiers.IRON), List.of());

    public static final Tier diamond = TierSortingRegistry.registerTier(
            new ForgeTier(4, 1561, 5f, 4f, 25,
                    ModTags.Blocks.needs_diamond_tool, () -> Ingredient.of(Tags.Items.GEMS_DIAMOND)),
            new ResourceLocation(HavenAlchemy.MOD_ID, "diamond"), List.of(Tiers.GOLD), List.of());

    public static final Tier netherite = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2031, 6f, 5f, 25,
                    ModTags.Blocks.needs_netherite_tool, () -> Ingredient.of(Tags.Items.INGOTS_NETHERITE)),
            new ResourceLocation(HavenAlchemy.MOD_ID, "netherite"), List.of(Tiers.DIAMOND), List.of());

    public static final Tier dark_matter = TierSortingRegistry.registerTier(
            new ForgeTier(5, 4062, 6f, 5f, 25,
                    ModTags.Blocks.needs_dark_matter_tool, () -> Ingredient.of(ModItems.dark_matter.get())),
            new ResourceLocation(HavenAlchemy.MOD_ID, "dark_matter"), List.of(Tiers.NETHERITE), List.of());

    public static final Tier red_matter = TierSortingRegistry.registerTier(
            new ForgeTier(6, 8124, 8f, 6f, 25,
                    ModTags.Blocks.needs_red_matter_tool, () -> Ingredient.of(ModItems.red_matter.get())),
            new ResourceLocation(HavenAlchemy.MOD_ID, "red_matter"), List.of(dark_matter), List.of());

    public static final Tier neosphore = TierSortingRegistry.registerTier(
            new ForgeTier(7, -1, 10f, 7, 25,
                    ModTags.Blocks.needs_neosphore_tool, () -> Ingredient.of(ModItems.red_matter.get())),
            new ResourceLocation(HavenAlchemy.MOD_ID, "neosphere"), List.of(red_matter), List.of());
}
