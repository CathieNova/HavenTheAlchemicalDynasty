package net.cathienova.havenalchemy.item.equipment;

import net.cathienova.havenalchemy.item.ModItemArmor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NeosphoreArmor extends ModItemArmor// implements IItemExtension
{
    public NeosphoreArmor(ArmorMaterial material, ArmorItem.Type type, Item.Properties properties)
    {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected)
    {
        /*if (pEntity instanceof Player player)
        {
            Item boots = player.getItemBySlot(EquipmentSlot.FEET).getItem();
            Item leggings = player.getItemBySlot(EquipmentSlot.LEGS).getItem();
            Item chestplate = player.getItemBySlot(EquipmentSlot.CHEST).getItem();
            Item helmet = player.getItemBySlot(EquipmentSlot.HEAD).getItem();

            // Check if the player already has flight enabled through another means
            if (player.getAbilities().mayfly) return;

            if (player.isCreative() || player.isSpectator()) return;

            if (boots instanceof NeosphoreArmor && leggings instanceof NeosphoreArmor &&
                    chestplate instanceof NeosphoreArmor && helmet instanceof NeosphoreArmor)
            {
                //player.getAbilities().mayfly = true;
                //player.fallDistance = 0.0f;
            }
            else
            {
                //player.getAbilities().mayfly = false;
            }
        }*/
    }
}
