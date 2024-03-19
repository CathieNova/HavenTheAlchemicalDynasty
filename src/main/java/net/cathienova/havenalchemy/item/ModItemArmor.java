package net.cathienova.havenalchemy.item;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Map;

public class ModItemArmor extends ArmorItem {
    private static final Map<ArmorMaterial, List<MobEffectInstance>> material_to_effect_map =
            new ImmutableMap.Builder<ArmorMaterial, List<MobEffectInstance>>()
                    .put(ModArmorMaterials.dark_matter, ImmutableList.of(
                            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0, false, false, false)
                    ))
                    .put(ModArmorMaterials.red_matter, ImmutableList.of(
                            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0, false, false, false),
                            new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 0, false, false, false)
                    ))
                    .put(ModArmorMaterials.neosphore, ImmutableList.of(
                            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0, false, false, false),
                            new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 0, false, false, false),
                            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0, false, false, false),
                            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0, false, false, false)
                    ))

                    .build();

    public ModItemArmor(ArmorMaterial material, ArmorItem.Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (!level.isClientSide() && hasFullSuitOfArmorOn(player)) {
            evaluateArmorEffects(player);
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ArmorMaterial material = null;
        for (ItemStack itemStack : player.getInventory().armor) {
            if (itemStack.getItem() instanceof ArmorItem armorItem) {
                if (material == null) {
                    material = armorItem.getMaterial();
                } else if (material != armorItem.getMaterial()) {
                    return false; // Different materials found, not a full suit
                }
            } else {
                return false; // Not all armor slots are filled
            }
        }
        return material != null; // Returns true if all slots are filled with the same material
    }

    private void evaluateArmorEffects(Player player) {
        ArmorMaterial armorMaterial = ((ArmorItem)player.getInventory().armor.get(0).getItem()).getMaterial(); // Assuming hasFullSuitOfArmorOn is true
        List<MobEffectInstance> effects = material_to_effect_map.getOrDefault(armorMaterial, ImmutableList.of());
        effects.forEach(effect -> addStatusEffectForMaterial(player, effect));
    }

    private void addStatusEffectForMaterial(Player player, MobEffectInstance effect) {
        MobEffectInstance currentEffect = player.getEffect(effect.getEffect());
        boolean hasEffect = currentEffect != null;
        int thresholdDuration = 220; // Duration in ticks before reapplying the effect

        if (effect.getEffect() == MobEffects.NIGHT_VISION) {
            if (!hasEffect || (hasEffect && currentEffect.getDuration() < thresholdDuration)) {
                // Reapply the effect with a new duration to avoid blinking
                player.addEffect(new MobEffectInstance(effect.getEffect(), 200 + thresholdDuration, effect.getAmplifier(), effect.isAmbient(), effect.isVisible(), effect.showIcon()));
            }
        } else if (!hasEffect) {
            // For all other effects, apply normally
            player.addEffect(new MobEffectInstance(effect));
        }
    }
}