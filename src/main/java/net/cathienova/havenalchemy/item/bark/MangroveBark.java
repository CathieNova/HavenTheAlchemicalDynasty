package net.cathienova.havenalchemy.item.bark;

import net.minecraft.world.level.block.Blocks;

public class MangroveBark extends BarkItem
{

    public MangroveBark(Properties pProperties)
    {
        super(pProperties);
        this.Log = Blocks.MANGROVE_LOG;
        this.StrippedLog = Blocks.STRIPPED_MANGROVE_LOG;

        this.WoodenLog = Blocks.MANGROVE_WOOD;
        this.WoodenStrippedLog = Blocks.STRIPPED_MANGROVE_WOOD;
    }
}
