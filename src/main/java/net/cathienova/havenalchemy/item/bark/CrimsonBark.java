package net.cathienova.havenalchemy.item.bark;

import net.minecraft.world.level.block.Blocks;

public class CrimsonBark extends BarkItem
{

    public CrimsonBark(Properties pProperties)
    {
        super(pProperties);
        this.Log = Blocks.CRIMSON_STEM;
        this.StrippedLog = Blocks.STRIPPED_CRIMSON_STEM;

        this.WoodenLog = Blocks.CRIMSON_HYPHAE;
        this.WoodenStrippedLog = Blocks.STRIPPED_CRIMSON_HYPHAE;
    }
}
