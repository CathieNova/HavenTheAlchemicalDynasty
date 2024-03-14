package net.cathienova.havenalchemy.item.artifacts;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class RingOfLove extends ArtifactBase
{
    public RingOfLove(Properties properties) {
        super(properties);
    }

    MobEffectInstance effect = new MobEffectInstance(MobEffects.HEALTH_BOOST, -1, 0, false, false, false);

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack)
    {
        Player player = (Player) slotContext.entity();

        if (player != null)
        {
            Level level = player.level();
            if (!level.isClientSide())
            {
                ServerPlayer serverPlayer = (ServerPlayer) player;

                if (serverPlayer.tickCount % 20 == 0)
                {
                    serverPlayer.addEffect(effect);
                }
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack)
    {
        Player player = (Player) slotContext.entity();

        if (player != null)
        {
            Level level = player.level();
            if (!level.isClientSide())
            {
                ServerPlayer serverPlayer = (ServerPlayer) player;
                serverPlayer.removeEffect(effect.getEffect());
            }
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag isAdvanced)
    {
        tooltip.add(Component.translatable("tooltip.havenalchemy.ring_of_love").withStyle(ChatFormatting.GOLD));
    }

    @Override
    protected boolean isCosmetic()
    {
        return false;
    }
}
