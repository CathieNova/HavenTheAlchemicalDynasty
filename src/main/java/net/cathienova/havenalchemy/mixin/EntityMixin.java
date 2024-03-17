package net.cathienova.havenalchemy.mixin;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.item.ModEffects;
import net.cathienova.havenalchemy.util.SoundUtil;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "playSound", at = @At("HEAD"), cancellable = true)
    public void playSoundCallback(SoundEvent sound, float volume, float pitch, CallbackInfo ci)
    {
        Entity entity = (Entity) (Object) this;
        if (entity instanceof LivingEntity)
        {
            LivingEntity livingEntity = (LivingEntity) (Object) this;
            for (Player player : livingEntity.level().players())
            {
                if ((HavenAlchemy.tendrilEntities.contains(player) || player.hasEffect(ModEffects.ECHOLOCATE.get())))
                {
                    float distance = sound.getRange(volume) * 0.9F;
                    if (player.closerThan(entity, distance, distance * 0.7F))
                    {
                        SoundUtil.entityMap.put(entity, 0);
                        SoundUtil.distanceMap.put(entity, distance);
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 60, 1, false, false, false), player);
                    }
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void tickCallback(CallbackInfo ci){
        Entity entity = (Entity) (Object) this;
        if(SoundUtil.entityMap.containsKey(entity)){
            int ticks = SoundUtil.entityMap.get(entity);
            if(ticks >= 60){
                SoundUtil.entityMap.remove(entity);
                SoundUtil.distanceMap.remove(entity);
            } else {
                ticks++;
                SoundUtil.entityMap.replace(entity, ticks);
            }
        }
    }
}