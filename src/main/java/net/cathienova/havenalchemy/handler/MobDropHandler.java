package net.cathienova.havenalchemy.handler;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.item.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Animal;
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
        int balanceShardDropChance = HavenAlchemy.c_config.essenceShardDropChance.get();
        Random random = new Random();

        if (entity != null && random.nextInt(0, 100) < balanceShardDropChance)
        {
            if (entity instanceof Monster || entity instanceof Animal)
                drops.add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.essence_shard.get())));
        }
    }
}