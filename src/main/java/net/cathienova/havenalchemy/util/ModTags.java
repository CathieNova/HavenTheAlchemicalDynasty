package net.cathienova.havenalchemy.util;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags
{
    public static class Blocks{
        public static final TagKey<Block> needs_dark_matter_tool = tag("needs_dark_matter_tool");
        public static final TagKey<Block> needs_red_matter_tool = tag("needs_red_matter_tool");
        public static final TagKey<Block> needs_neosphore_tool = tag("needs_neosphore_tool");
        public static final TagKey<Block> basphalt_stone = tag("basphalt_stone");
        public static final TagKey<Block> suspicious_basphalt = tag("suspicious_basphalt");

        private static TagKey<Block> tag(String name)
        {
            return BlockTags.create(new ResourceLocation(HavenAlchemy.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> spirits = tag("spirits");
        public static final TagKey<Item> seeds = tag("seeds");
        public static final TagKey<Item> bark = tag("bark");
        public static final TagKey<Item> dyes = tag("dyes");
        public static final TagKey<Item> coral = tag("coral");
        public static final TagKey<Item> alchemy_stones = tag("alchemy_stones");
        public static final TagKey<Item> forgeCopperDust = forgeTag("dusts/copper");
        public static final TagKey<Item> forgeTinDust = forgeTag("dusts/tin");
        public static final TagKey<Item> forgeNetheriteDust = forgeTag("dusts/netherite");
        public static final TagKey<Item> forgeIronDust = forgeTag("dusts/iron");
        public static final TagKey<Item> forgeGoldDust = forgeTag("dusts/gold");
        public static final TagKey<Item> forgeLeadDust = forgeTag("dusts/lead");
        public static final TagKey<Item> forgeSilverDust = forgeTag("dusts/silver");
        public static final TagKey<Item> forgeNickelDust = forgeTag("dusts/nickel");
        public static final TagKey<Item> forgeUraniumDust = forgeTag("dusts/uranium");
        public static final TagKey<Item> forgeOsmiumDust = forgeTag("dusts/osmium");
        public static final TagKey<Item> forgeZincDust = forgeTag("dusts/zinc");
        public static final TagKey<Item> forgeCopperIngot = forgeTag("ingots/copper");
        public static final TagKey<Item> forgeTinIngot = forgeTag("ingots/tin");
        public static final TagKey<Item> forgeNetheriteIngot = forgeTag("ingots/netherite");
        public static final TagKey<Item> forgeIronIngot = forgeTag("ingots/iron");
        public static final TagKey<Item> forgeGoldIngot = forgeTag("ingots/gold");
        public static final TagKey<Item> forgeLeadIngot = forgeTag("ingots/lead");
        public static final TagKey<Item> forgeSilverIngot = forgeTag("ingots/silver");
        public static final TagKey<Item> forgeNickelIngot = forgeTag("ingots/nickel");
        public static final TagKey<Item> forgeUraniumIngot = forgeTag("ingots/uranium");
        public static final TagKey<Item> forgeOsmiumIngot = forgeTag("ingots/osmium");
        public static final TagKey<Item> forgeZincIngot = forgeTag("ingots/zinc");
        public static final TagKey<Item> forgeRawCopper = forgeTag("raw_materials/copper");
        public static final TagKey<Item> forgeRawTin = forgeTag("raw_materials/tin");
        public static final TagKey<Item> forgeRawIron = forgeTag("raw_materials/iron");
        public static final TagKey<Item> forgeRawGold = forgeTag("raw_materials/gold");
        public static final TagKey<Item> forgeRawLead = forgeTag("raw_materials/lead");
        public static final TagKey<Item> forgeRawSilver = forgeTag("raw_materials/silver");
        public static final TagKey<Item> forgeRawNickel = forgeTag("raw_materials/nickel");
        public static final TagKey<Item> forgeRawUranium = forgeTag("raw_materials/uranium");
        public static final TagKey<Item> forgeRawOsmium = forgeTag("raw_materials/osmium");
        public static final TagKey<Item> forgeRawZinc = forgeTag("raw_materials/zinc");
        public static final TagKey<Item> oreHammers = tag("ore_hammers");
        public static final TagKey<Item> forgeCopperBlocks = forgeTag("storage_blocks/copper");
        public static final TagKey<Item> forgeTinBlocks = forgeTag("storage_blocks/tin");
        public static final TagKey<Item> forgeNetheriteBlocks = forgeTag("storage_blocks/netherite");
        public static final TagKey<Item> forgeIronBlocks = forgeTag("storage_blocks/iron");
        public static final TagKey<Item> forgeGoldBlocks = forgeTag("storage_blocks/gold");
        public static final TagKey<Item> forgeLeadBlocks = forgeTag("storage_blocks/lead");
        public static final TagKey<Item> forgeSilverBlocks = forgeTag("storage_blocks/silver");
        public static final TagKey<Item> forgeNickelBlocks = forgeTag("storage_blocks/nickel");
        public static final TagKey<Item> forgeUraniumBlocks = forgeTag("storage_blocks/uranium");
        public static final TagKey<Item> forgeOsmiumBlocks = forgeTag("storage_blocks/osmium");
        public static final TagKey<Item> forgeZincBlocks = forgeTag("storage_blocks/zinc");
        public static final TagKey<Item> forgeDiamondBlocks = forgeTag("storage_blocks/diamond");
        public static final TagKey<Item> leather = forgeTag("leather");
        public static final TagKey<Item> chests = forgeTag("chests");
        public static final TagKey<Item> bricks = forgeTag("ingots/bricks");
        public static final TagKey<Item> glass = forgeTag("glass");
        public static final TagKey<Item> aiots = forgeTag("tools/aiots");

        private static TagKey<Item> tag(String name)
        {
            return ItemTags.create(new ResourceLocation(HavenAlchemy.MOD_ID, name));
        }
        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
