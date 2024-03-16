package net.cathienova.havenalchemy.item.bark;

import net.minecraft.world.level.block.Blocks;

public class DarkOakBark extends BarkItem
{

    public DarkOakBark(Properties pProperties)
    {
        super(pProperties);
        this.Log = Blocks.DARK_OAK_LOG;
        this.StrippedLog = Blocks.STRIPPED_DARK_OAK_LOG;

        this.WoodenLog = Blocks.DARK_OAK_WOOD;
        this.WoodenStrippedLog = Blocks.STRIPPED_DARK_OAK_WOOD;
    }
}
