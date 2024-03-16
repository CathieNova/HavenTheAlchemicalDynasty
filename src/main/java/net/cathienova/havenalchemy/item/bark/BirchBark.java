package net.cathienova.havenalchemy.item.bark;

import net.minecraft.world.level.block.Blocks;

public class BirchBark extends BarkItem
{

    public BirchBark(Properties pProperties)
    {
        super(pProperties);
        this.Log = Blocks.BIRCH_LOG;
        this.StrippedLog = Blocks.STRIPPED_BIRCH_LOG;

        this.WoodenLog = Blocks.BIRCH_WOOD;
        this.WoodenStrippedLog = Blocks.STRIPPED_BIRCH_WOOD;
    }
}
