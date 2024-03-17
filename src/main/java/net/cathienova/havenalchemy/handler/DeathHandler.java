package net.cathienova.havenalchemy.handler;

import net.cathienova.havenalchemy.item.ModItemArmor;
import net.cathienova.havenalchemy.item.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DeathHandler
{
    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event){
        var entity = event.getEntity();
        ItemStack itemStack = null;

        if (entity instanceof Player)
        {
            Player player = (Player) entity;
            for (ItemStack stack : player.getArmorSlots())
            {
                Item item = stack.getItem();
                if (item.equals(ModItems.sculkerite_chestplate.get()))
                {
                    ItemStack newStack = new ItemStack(ModItems.sculkerite_chestplate_uncharged.get());
                    newStack.setHoverName(stack.getHoverName());
                    newStack.setDamageValue(stack.getDamageValue());
                    newStack.setTag(stack.getTag());

                    itemStack = stack.copy();
                    stack.shrink(1);
                    player.setItemSlot(EquipmentSlot.CHEST, newStack);
                    break;
                }
            }
            if (itemStack != null)
            {
                if (entity instanceof ServerPlayer)
                {
                    ServerPlayer serverPlayerEntity = (ServerPlayer) entity;
                    serverPlayerEntity.awardStat(Stats.ITEM_USED.get(ModItems.sculkerite_chestplate.get()));
                    CriteriaTriggers.USED_TOTEM.trigger(serverPlayerEntity, new ItemStack(ModItems.sculkerite_chestplate_uncharged.get()));
                }

                entity.setHealth(1.0F);
                entity.removeAllEffects();
                entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
                entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
                entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));
                entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 600, 0));
                entity.level().broadcastEntityEvent(entity, (byte) 35);
                event.setCanceled(true);
            }
            else
            {
                event.setCanceled(false);
            }
        }

        if (entity instanceof Warden)
        {
            Warden warden = (Warden) entity;
            if (warden.getKillCredit() instanceof Player)
            {
                Player player = (Player) warden.getKillCredit();
                if (player.getInventory().armor.get(2).getItem() instanceof ModItemArmor)
                {
                    player.getInventory().armor.get(2).shrink(1);
                    player.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ModItems.sculkerite_chestplate.get()));
                }
            }
        }
    }
}
