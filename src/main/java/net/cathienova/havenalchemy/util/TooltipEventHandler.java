package net.cathienova.havenalchemy.util;

import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.OnlyIn;

@Mod.EventBusSubscriber(bus = Bus.FORGE)
public class TooltipEventHandler {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();

        if (EMCSystem.contains(item))
        {
            long emcValue = EMCSystem.GetEmc(item);
            boolean isShiftDown = Screen.hasShiftDown();

            String formattedEmcValue;
            if (isShiftDown) {
                formattedEmcValue = String.format("%,d", emcValue);
            } else {
                formattedEmcValue = emcValue >= 1_000_000 ? String.format("%.1fM", emcValue / 1_000_000.0) : String.format("%,d", emcValue);
            }
            if (emcValue >= 1_000_000 && !isShiftDown)
                event.getToolTip().add(Component.literal("§3EMC: §d" + formattedEmcValue + " §8(shift)"));
            else
                event.getToolTip().add(Component.literal("§3EMC: §d" + formattedEmcValue));

            if (stack.getCount() > 1) {
                long totalEmcValue = emcValue * stack.getCount();
                String formattedTotalEmcValue;
                if (isShiftDown) {
                    formattedTotalEmcValue = String.format("%,d", totalEmcValue);
                } else {
                    formattedTotalEmcValue = totalEmcValue >= 1_000_000 ? String.format("%.1fM", totalEmcValue / 1_000_000.0) : String.format("%,d", totalEmcValue);
                }
                if (totalEmcValue >= 1_000_000 && !isShiftDown)
                    event.getToolTip().add(Component.literal("§3Total: §d" + formattedTotalEmcValue + " §8(shift)"));
                else
                    event.getToolTip().add(Component.literal("§3Total: §d" + formattedTotalEmcValue));
            }

        }

        if (event.getItemStack().getItem() == ModItems.neosphore_helmet.get() ||
                event.getItemStack().getItem() == ModItems.neosphore_chestplate.get() ||
                event.getItemStack().getItem() == ModItems.neosphore_leggings.get() ||
                event.getItemStack().getItem() == ModItems.neosphore_boots.get() ||
                event.getItemStack().getItem() == ModItems.neosphore_pickaxe.get() ||
                event.getItemStack().getItem() == ModItems.neosphore_axe.get() ||
                event.getItemStack().getItem() == ModItems.neosphore_shovel.get() ||
                event.getItemStack().getItem() == ModItems.neosphore_sword.get() ||
                event.getItemStack().getItem() == ModItems.neosphore_hoe.get() ||
                event.getItemStack().getItem() == ModItems.neosphore_shears.get())
        {
            event.getToolTip().add(Component.translatable("tooltip.havenalchemy.indestructable").withStyle(net.minecraft.ChatFormatting.GOLD));
        }
        if (event.getItemStack().getItem() == ModItems.acacia_bark.get() ||
                event.getItemStack().getItem() == ModItems.birch_bark.get() ||
                event.getItemStack().getItem() == ModItems.dark_oak_bark.get() ||
                event.getItemStack().getItem() == ModItems.jungle_bark.get() ||
                event.getItemStack().getItem() == ModItems.oak_bark.get() ||
                event.getItemStack().getItem() == ModItems.spruce_bark.get() ||
                event.getItemStack().getItem() == ModItems.warped_bark.get() ||
                event.getItemStack().getItem() == ModItems.crimson_bark.get() ||
                event.getItemStack().getItem() == ModItems.mangrove_bark.get() ||
                event.getItemStack().getItem() == ModItems.cherry_bark.get() ||
                event.getItemStack().getItem() == ModItems.charmel_bark.get())
        {
            event.getToolTip().add(Component.translatable("tooltip.havenalchemy.bark").withStyle(net.minecraft.ChatFormatting.GOLD));
        }
        if (event.getItemStack().getItem() == ModBlocks.cable_block.get().asItem())
        {
            event.getToolTip().add(Component.translatable("tooltip.havenalchemy.cable").withStyle(net.minecraft.ChatFormatting.GOLD));
        }
        if (event.getItemStack().getItem() == ModBlocks.charger_block.get().asItem())
        {
            event.getToolTip().add(Component.translatable("tooltip.havenalchemy.battery").withStyle(net.minecraft.ChatFormatting.GOLD));
        }
        if (event.getItemStack().getItem() == ModBlocks.generator_block.get().asItem())
        {
            event.getToolTip().add(Component.translatable("tooltip.havenalchemy.generator").withStyle(net.minecraft.ChatFormatting.GOLD));
        }
        if (event.getItemStack().getItem() == ModBlocks.asphalt.get().asItem())
        {
            event.getToolTip().add(Component.translatable("tooltip.havenalchemy.asphalt").withStyle(net.minecraft.ChatFormatting.GOLD));
        }
        if (event.getItemStack().getItem() == ModBlocks.asphalt_bricks.get().asItem())
        {
            event.getToolTip().add(Component.translatable("tooltip.havenalchemy.asphalt_bricks").withStyle(net.minecraft.ChatFormatting.GOLD));
        }
        if (event.getItemStack().getItem() == ModItems.essentia_seeds.get() ||
                event.getItemStack().getItem() == ModItems.essentia_spirit.get() ||
                event.getItemStack().getItem() == ModBlocks.essentia_spirit_block.get().asItem() ||
                event.getItemStack().getItem() == ModItems.coal_seeds.get() ||
                event.getItemStack().getItem() == ModItems.coal_spirit.get() ||
                event.getItemStack().getItem() == ModItems.coral_seeds.get() ||
                event.getItemStack().getItem() == ModItems.coral_spirit.get() ||
                event.getItemStack().getItem() == ModItems.dirt_seeds.get() ||
                event.getItemStack().getItem() == ModItems.dirt_spirit.get() ||
                event.getItemStack().getItem() == ModItems.dye_seeds.get() ||
                event.getItemStack().getItem() == ModItems.dye_spirit.get() ||
                event.getItemStack().getItem() == ModItems.fire_seeds.get() ||
                event.getItemStack().getItem() == ModItems.fire_spirit.get() ||
                event.getItemStack().getItem() == ModItems.glowstone_seeds.get() ||
                event.getItemStack().getItem() == ModItems.glowstone_spirit.get() ||
                event.getItemStack().getItem() == ModItems.ice_seeds.get() ||
                event.getItemStack().getItem() == ModItems.ice_spirit.get() ||
                event.getItemStack().getItem() == ModItems.nature_seeds.get() ||
                event.getItemStack().getItem() == ModItems.nature_spirit.get() ||
                event.getItemStack().getItem() == ModItems.nether_seeds.get() ||
                event.getItemStack().getItem() == ModItems.nether_spirit.get() ||
                event.getItemStack().getItem() == ModItems.obsidian_seeds.get() ||
                event.getItemStack().getItem() == ModItems.obsidian_spirit.get() ||
                event.getItemStack().getItem() == ModItems.water_seeds.get() ||
                event.getItemStack().getItem() == ModItems.water_spirit.get() ||
                event.getItemStack().getItem() == ModItems.wood_seeds.get() ||
                event.getItemStack().getItem() == ModItems.wood_spirit.get() ||
                event.getItemStack().getItem() == ModItems.honey_seeds.get() ||
                event.getItemStack().getItem() == ModItems.honey_spirit.get() ||
                event.getItemStack().getItem() == ModItems.air_seeds.get() ||
                event.getItemStack().getItem() == ModItems.air_spirit.get() ||
                event.getItemStack().getItem() == ModItems.earth_seeds.get() ||
                event.getItemStack().getItem() == ModItems.earth_spirit.get() ||
                event.getItemStack().getItem() == ModItems.stone_seeds.get() ||
                event.getItemStack().getItem() == ModItems.stone_spirit.get() ||
                event.getItemStack().getItem() == ModItems.deepslate_seeds.get() ||
                event.getItemStack().getItem() == ModItems.deepslate_spirit.get()
        )
        {
            event.getToolTip().add(Component.translatable("tooltip.havenalchemy.tier1"));
        }
        if (event.getItemStack().getItem() == ModItems.mysterium_spirit.get() ||
                event.getItemStack().getItem() == ModBlocks.mysterium_spirit_block.get().asItem() ||
                event.getItemStack().getItem() == ModItems.end_seeds.get() ||
                event.getItemStack().getItem() == ModItems.end_spirit.get() ||
                event.getItemStack().getItem() == ModItems.prismarine_seeds.get() ||
                event.getItemStack().getItem() == ModItems.prismarine_spirit.get()
        )
        {
            event.getToolTip().add(Component.translatable("tooltip.havenalchemy.tier2"));
        }
        if (event.getItemStack().getItem() == ModItems.vitalium_spirit.get() ||
                event.getItemStack().getItem() == ModBlocks.vitalium_spirit_block.get().asItem() ||
                event.getItemStack().getItem() == ModItems.gold_seeds.get() ||
                event.getItemStack().getItem() == ModItems.gold_spirit.get() ||
                event.getItemStack().getItem() == ModItems.iron_seeds.get() ||
                event.getItemStack().getItem() == ModItems.iron_spirit.get() ||
                event.getItemStack().getItem() == ModItems.lapis_seeds.get() ||
                event.getItemStack().getItem() == ModItems.lapis_spirit.get() ||
                event.getItemStack().getItem() == ModItems.nether_quartz_seeds.get() ||
                event.getItemStack().getItem() == ModItems.nether_quartz_spirit.get() ||
                event.getItemStack().getItem() == ModItems.redstone_seeds.get() ||
                event.getItemStack().getItem() == ModItems.redstone_spirit.get() ||
                event.getItemStack().getItem() == ModItems.copper_seeds.get() ||
                event.getItemStack().getItem() == ModItems.copper_spirit.get()
        )
        {
            event.getToolTip().add(Component.translatable("tooltip.havenalchemy.tier3"));
        }
        if (event.getItemStack().getItem() == ModItems.celestium_spirit.get() ||
                event.getItemStack().getItem() == ModBlocks.celestium_spirit_block.get().asItem() ||
                event.getItemStack().getItem() == ModItems.diamond_seeds.get() ||
                event.getItemStack().getItem() == ModItems.diamond_spirit.get() ||
                event.getItemStack().getItem() == ModItems.emerald_seeds.get() ||
                event.getItemStack().getItem() == ModItems.emerald_spirit.get() ||
                event.getItemStack().getItem() == ModItems.experience_seeds.get() ||
                event.getItemStack().getItem() == ModItems.experience_spirit.get() ||
                event.getItemStack().getItem() == ModItems.amethyst_seeds.get() ||
                event.getItemStack().getItem() == ModItems.amethyst_spirit.get()
        )
        {
            event.getToolTip().add(Component.translatable("tooltip.havenalchemy.tier4"));
        }
        if (event.getItemStack().getItem() == ModItems.eternium_spirit.get() ||
                event.getItemStack().getItem() == ModBlocks.eternium_spirit_block.get().asItem() ||
                event.getItemStack().getItem() == ModItems.netherite_seeds.get() ||
                event.getItemStack().getItem() == ModItems.netherite_spirit.get() ||
                event.getItemStack().getItem() == ModItems.havenite_seeds.get() ||
                event.getItemStack().getItem() == ModItems.havenite_spirit.get()
        )
        {
            event.getToolTip().add(Component.translatable("tooltip.havenalchemy.tier5"));
        }

        if (event.getItemStack().getItem() == ModItems.essence_shard.get())
        {
            event.getToolTip().add(Component.translatable("tooltip.havenalchemy.essence_shard").withStyle(net.minecraft.ChatFormatting.GOLD));
        }

        if (event.getItemStack().getItem() == ModBlocks.alchemical_condenser.get().asItem())
        {
            event.getToolTip().add(Component.translatable("tooltip.havenalchemy.alchemical_condenser").withStyle(net.minecraft.ChatFormatting.GOLD));
        }

        if (event.getItemStack().getItem() == ModBlocks.alchemical_processor.get().asItem())
        {
            event.getToolTip().add(Component.translatable("tooltip.havenalchemy.alchemical_processor").withStyle(net.minecraft.ChatFormatting.GOLD));
        }

        if (event.getItemStack().getItem() == ModBlocks.alchemical_chamber.get().asItem())
        {
            event.getToolTip().add(Component.translatable("tooltip.havenalchemy.alchemical_chamber").withStyle(net.minecraft.ChatFormatting.GOLD));
        }
    }
}