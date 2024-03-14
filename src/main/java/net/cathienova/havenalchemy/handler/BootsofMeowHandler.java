package net.cathienova.havenalchemy.handler;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.item.artifacts.BootsOfMeow;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

@Mod.EventBusSubscriber(modid = HavenAlchemy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BootsofMeowHandler
{
    @SubscribeEvent
    public static void onLivingFall(LivingFallEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            CuriosApi.getCuriosInventory(serverPlayer).ifPresent(curiosInventory -> {
                for (ICurioStacksHandler stacksHandler : curiosInventory.getCurios().values()) {
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stackHandler.getSlots(); ++i) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty() && stack.getItem() instanceof BootsOfMeow) {
                            event.setCanceled(true); // Cancel the fall damage
                            return; // Exit early since we found our item
                        }
                    }
                }
            });
        }
    }
}