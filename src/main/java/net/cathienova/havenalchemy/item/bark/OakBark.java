package net.cathienova.havenalchemy.item.bark;

import net.minecraft.world.level.block.Blocks;

public class OakBark extends BarkItem
{

    public OakBark(Properties pProperties)
    {
        super(pProperties);
        this.Log = Blocks.OAK_LOG;
        this.StrippedLog = Blocks.STRIPPED_OAK_LOG;

        this.WoodenLog = Blocks.OAK_WOOD;
        this.WoodenStrippedLog = Blocks.STRIPPED_OAK_WOOD;
    }
}
