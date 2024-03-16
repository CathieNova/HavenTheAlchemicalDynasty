package net.cathienova.havenalchemy.item.bark;

import net.minecraft.world.level.block.Blocks;

public class AcaciaBark extends BarkItem
{

    public AcaciaBark(Properties pProperties)
    {
        super(pProperties);
        this.Log = Blocks.ACACIA_LOG;
        this.StrippedLog = Blocks.STRIPPED_ACACIA_LOG;

        this.WoodenLog = Blocks.ACACIA_WOOD;
        this.WoodenStrippedLog = Blocks.STRIPPED_ACACIA_WOOD;
    }
}
