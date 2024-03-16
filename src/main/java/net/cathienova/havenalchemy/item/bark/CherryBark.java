package net.cathienova.havenalchemy.item.bark;

import net.minecraft.world.level.block.Blocks;

public class CherryBark extends BarkItem
{

    public CherryBark(Properties pProperties)
    {
        super(pProperties);
        this.Log = Blocks.CHERRY_LOG;
        this.StrippedLog = Blocks.STRIPPED_CHERRY_LOG;

        this.WoodenLog = Blocks.CHERRY_WOOD;
        this.WoodenStrippedLog = Blocks.STRIPPED_CHERRY_WOOD;
    }
}
