package net.cathienova.havenalchemy.mixin;

import net.cathienova.havenalchemy.item.ModItems;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @Inject(method = "findTotem", at = @At("HEAD"), cancellable = true)
    private static void getActiveTotemOfUndyingCallback(Player player, CallbackInfoReturnable<ItemStack> cir)
    {
        for (ItemStack stack : player.getArmorSlots())
        {
            Item item = stack.getItem();
            if (item.equals(ModItems.sculkerite_chestplate.get()))
            {
                cir.setReturnValue(stack);
            }
        }
    }
}
