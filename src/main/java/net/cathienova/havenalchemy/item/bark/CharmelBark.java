package net.cathienova.havenalchemy.item.bark;

import net.cathienova.havenalchemy.block.ModBlocks;
import net.minecraft.world.level.block.Blocks;

public class CharmelBark extends BarkItem
{
    public CharmelBark(Properties pProperties)
    {
        super(pProperties);
        this.Log = ModBlocks.charmel_log.get();
        this.StrippedLog = ModBlocks.stripped_charmel_log.get();

        this.WoodenLog = ModBlocks.charmel_wood.get();
        this.WoodenStrippedLog = ModBlocks.stripped_charmel_wood.get();
    }
}
