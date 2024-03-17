package net.cathienova.havenalchemy.enchant;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModDamageEnchantment extends DamageEnchantment {
    public ModDamageEnchantment(Rarity weight, EquipmentSlot... slots) {
        super(weight, 3, slots);
    }

    @Override
    public void doPostAttack(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity livingEntity){
            if(livingEntity.getType().equals(EntityType.WARDEN)){
                target.hurt(target.damageSources().sting(user), (10 + (level * 6)) * 1);
            }
        }
    }

    @Override
    public int getMinCost(int level) {
        return 5 + (level - 1) * 10;
    }

    @Override
    public int getMaxCost(int level) {
        return this.getMinCost(level) + 20;
    }

    @Override
    public boolean checkCompatibility(Enchantment other) {
        return !(other instanceof ModDamageEnchantment);
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }
}