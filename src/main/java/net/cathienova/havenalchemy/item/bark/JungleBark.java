package net.cathienova.havenalchemy.item.bark;

import net.minecraft.world.level.block.Blocks;

public class JungleBark extends BarkItem
{

    public JungleBark(Properties pProperties)
    {
        super(pProperties);
        this.Log = Blocks.JUNGLE_LOG;
        this.StrippedLog = Blocks.STRIPPED_JUNGLE_LOG;

        this.WoodenLog = Blocks.JUNGLE_WOOD;
        this.WoodenStrippedLog = Blocks.STRIPPED_JUNGLE_WOOD;
    }
}
