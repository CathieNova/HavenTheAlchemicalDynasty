package net.cathienova.havenalchemy.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static net.cathienova.havenalchemy.HavenAlchemyClient.getEmcText;

@Mixin(Screen.class)
public class ScreenMixin {
    @Inject(method = "getTooltipFromItem", at = @At("TAIL"))
    private static void getTooltipFromItem(Minecraft pMinecraft, ItemStack stack, CallbackInfoReturnable<List<Component>> cir) {
        cir.getReturnValue().addAll(getEmcText(stack));
    }
}