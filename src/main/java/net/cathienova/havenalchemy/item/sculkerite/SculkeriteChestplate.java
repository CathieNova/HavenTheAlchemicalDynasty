package net.cathienova.havenalchemy.item.sculkerite;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SculkeriteChestplate extends ArmorItem
{

    public SculkeriteChestplate(ArmorMaterial pMaterial, Type pType, Properties pProperties)
    {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> components, @NotNull TooltipFlag pIsAdvanced)
    {
        super.appendHoverText(pStack, pLevel, components, pIsAdvanced);

        components.add(Component.translatable("tooltip.havenalchemy.armor.sculkerite_chestplate"));
    }
}
