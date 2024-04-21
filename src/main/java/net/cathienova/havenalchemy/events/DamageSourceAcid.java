package net.cathienova.havenalchemy.events;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.config.HavenConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = HavenAlchemy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DamageSourceAcid extends DamageSource
{
    private static final Random random = new Random();

    public DamageSourceAcid(Holder.Reference<DamageType> message) {
        super(message);
    }

    public DamageSourceAcid(Holder.Reference<DamageType> message, Entity source) {
        super(message, source);
    }

    @Override
    public Component getLocalizedDeathMessage(LivingEntity entity) {
        String entityName = entity.getDisplayName().getString();
        AcidDeathMessages[] messages = AcidDeathMessages.values();
        AcidDeathMessages randomMessage = messages[random.nextInt(messages.length)];
        Component message = Component.literal(entityName + " " + randomMessage.getMessage());
        return message;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        Player player = event.player;
        BlockPos playerPos = player.blockPosition();

        if (player.level().getFluidState(playerPos).getType() == FluidInit.acid_fluid.flowing.get())
        {
            MobEffectInstance effect = new MobEffectInstance(MobEffects.POISON, 60, 2);
            player.addEffect(effect);
            if (player.getHealth() <= 1)
                player.hurt(ModDamageTypes.causeAcidDamage(player.level().registryAccess()), 2f);
        }
        else if (player.level().getFluidState(playerPos).getType() == FluidInit.acid_fluid.source.get())
        {
            MobEffectInstance effect = new MobEffectInstance(MobEffects.POISON, 60, 2);
            player.addEffect(effect);
            if (player.getHealth() <= 1)
                player.hurt(ModDamageTypes.causeAcidDamage(player.level().registryAccess()), 2f);
        }

        if (HavenConfig.enableAcidRain && player.level().isRaining() && player.level().canSeeSky(player.blockPosition()))
        {
            MobEffectInstance effect = new MobEffectInstance(MobEffects.POISON, 60, 2);
            player.addEffect(effect);

            if (player.getHealth() <= 1)
                player.hurt(ModDamageTypes.causeAcidDamage(player.level().registryAccess()), 2f);
        }
    }
}
