package net.cathienova.havenalchemy.item.bark;

import net.minecraft.world.level.block.Blocks;

public class SpruceBark extends BarkItem
{

    public SpruceBark(Properties pProperties)
    {
        super(pProperties);
        this.Log = Blocks.SPRUCE_LOG;
        this.StrippedLog = Blocks.STRIPPED_SPRUCE_LOG;

        this.WoodenLog = Blocks.SPRUCE_WOOD;
        this.WoodenStrippedLog = Blocks.STRIPPED_SPRUCE_WOOD;
    }
}
