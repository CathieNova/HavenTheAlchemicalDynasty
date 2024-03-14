package net.cathienova.havenalchemy.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods
{
    public static final FoodProperties dark_matter_apple = new FoodProperties.Builder().nutrition(20)
            .saturationMod(2f).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 2400), 0f).build();

    public static final FoodProperties red_matter_apple = new FoodProperties.Builder().nutrition(40)
            .saturationMod(3f).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 2400), 0f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 2400), 0f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2400), 0f).build();

    public static final FoodProperties essence_apple = new FoodProperties.Builder().nutrition(10)
            .saturationMod(1f).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 2400), 0f)
            .effect(() -> new MobEffectInstance(MobEffects.LUCK, 2400), 0f).build();
}