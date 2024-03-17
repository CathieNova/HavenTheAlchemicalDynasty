package net.cathienova.havenalchemy.item;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.mobeffects.EcholocateEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects
{
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, HavenAlchemy.MOD_ID);

    public static final RegistryObject<MobEffect> ECHOLOCATE = EFFECTS.register("echolocate", () -> new EcholocateEffect(MobEffectCategory.BENEFICIAL, 3124687));

    public static void register(IEventBus eventBus){
        EFFECTS.register(eventBus);
    }
}
