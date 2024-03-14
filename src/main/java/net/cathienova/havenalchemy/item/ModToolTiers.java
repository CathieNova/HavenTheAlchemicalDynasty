package net.cathienova.havenalchemy.item;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers
{
    public static final Tier dark_matter = TierSortingRegistry.registerTier(
            new ForgeTier(5, 4062, 5f, 5f, 25,
                    ModTags.Blocks.needs_dark_matter_tool, () -> Ingredient.of(ModItems.dark_matter.get())),
            new ResourceLocation(HavenAlchemy.MOD_ID, "dark_matter"), List.of(Tiers.NETHERITE), List.of());

    public static final Tier red_matter = TierSortingRegistry.registerTier(
            new ForgeTier(6, 8124, 6f, 6f, 25,
                    ModTags.Blocks.needs_dark_matter_tool, () -> Ingredient.of(ModItems.red_matter.get())),
            new ResourceLocation(HavenAlchemy.MOD_ID, "red_matter"), List.of(dark_matter), List.of());

    public static final Tier NEOSPHERE = TierSortingRegistry.registerTier(
            new ForgeTier(7, -1, 7f, 7, 25,
                    ModTags.Blocks.needs_neosphore_tool, () -> Ingredient.of(ModItems.red_matter.get())),
            new ResourceLocation(HavenAlchemy.MOD_ID, "neosphere"), List.of(red_matter), List.of());
}
