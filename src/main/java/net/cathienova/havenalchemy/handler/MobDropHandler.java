package net.cathienova.havenalchemy.handler;

import net.cathienova.havenalchemy.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

import static java.lang.System.in;

public class MobDropHandler
{
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event){
        var entity = event.getEntity();
        var level = event.getEntity().level();

        if(!level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) return;

        var drops = event.getDrops();
        int balanceShardDropChance = 4; // Out of 100
        Random random = new Random();

        if (entity instanceof Monster && random.nextInt(0, 100) < balanceShardDropChance)
            drops.add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.essence_shard.get())));

        if (entity instanceof Warden)
        {
            drops.add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.sculk_soul.get())));
            drops.add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(Items.ECHO_SHARD)));

            for (ItemStack item : entity.getKillCredit().getArmorSlots())
            {
                if (item.getItem().equals(ModItems.sculkerite_chestplate_uncharged.get()))
                {
                    item.getItem().getAllEnchantments(item);
                    ItemStack newStack = new ItemStack(ModItems.sculkerite_chestplate.get());
                    newStack.setHoverName(item.getHoverName());
                    newStack.setDamageValue(item.getDamageValue());
                    newStack.setTag(item.getTag());
                    item.shrink(1);
                    entity.getKillCredit().setItemSlot(EquipmentSlot.CHEST, newStack);
                    break;
                }
            }
        }
    }
}