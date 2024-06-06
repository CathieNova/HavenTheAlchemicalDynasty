package net.cathienova.havenalchemy.item.artifacts;

import net.cathienova.havenalchemy.config.HavenConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber
public class MendingNecklace extends ArtifactBase
{
    public MendingNecklace(Properties properties) {
        super(properties);
    }

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

                if (serverPlayer.tickCount % HavenConfig.mendingNecklaceRepairInterval == 0)
                {
                    repairItems(serverPlayer);
                }
            }
        }
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack)
    {
        return true;
    }

    private static void repairItems(ServerPlayer serverPlayer) {
        serverPlayer.getInventory().items.forEach(itemStack -> {
            if (!itemStack.isEmpty() && itemStack.isDamaged()) {
                itemStack.setDamageValue(itemStack.getDamageValue() - 1);
            }
        });

        serverPlayer.getInventory().armor.forEach(itemStack -> {
            if (!itemStack.isEmpty() && itemStack.isDamaged()) {
                itemStack.setDamageValue(itemStack.getDamageValue() - 1);
            }
        });

        serverPlayer.getInventory().offhand.forEach(itemStack -> {
            if (!itemStack.isEmpty() && itemStack.isDamaged()) {
                itemStack.setDamageValue(itemStack.getDamageValue() - 1);
            }
        });
    }

    @Override
    protected boolean isCosmetic()
    {
        return false;
    }
}
