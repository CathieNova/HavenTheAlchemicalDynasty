package net.cathienova.havenalchemy.events;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes
{
    public static final ResourceKey<DamageType> ACID = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(HavenAlchemy.MOD_ID, "acid"));

    public static DamageSource causeAcidDamage(RegistryAccess registryAccess) {
        return new DamageSourceAcid(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(ACID));
    }
}
