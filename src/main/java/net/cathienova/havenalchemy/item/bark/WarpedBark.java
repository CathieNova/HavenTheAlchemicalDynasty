package net.cathienova.havenalchemy.item.bark;

import net.minecraft.world.level.block.Blocks;

public class WarpedBark extends BarkItem
{

    public WarpedBark(Properties pProperties)
    {
        super(pProperties);
        this.Log = Blocks.WARPED_STEM;
        this.StrippedLog = Blocks.STRIPPED_WARPED_STEM;

        this.WoodenLog = Blocks.WARPED_HYPHAE;
        this.WoodenStrippedLog = Blocks.STRIPPED_WARPED_HYPHAE;
    }
}
