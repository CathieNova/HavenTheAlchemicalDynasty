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
        public static final TagKey<Block> needs_sculkerite_tool = tag("needs_sculkerite_tool");

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

        private static TagKey<Item> tag(String name)
        {
            return ItemTags.create(new ResourceLocation(HavenAlchemy.MOD_ID, name));
        }
    }
}
