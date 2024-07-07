package net.cathienova.havenalchemy.events;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.item.TrowelItem;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderHighlightEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HavenAlchemy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class TrowelRendering
{
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void renderBlockHighlight(RenderHighlightEvent.Block event) {
        if (event.getTarget().getType() != HitResult.Type.BLOCK) return;

        BlockHitResult rtr = event.getTarget();
        Entity entity = event.getCamera().getEntity();
        if (!(entity instanceof Player player)) return;

        ItemStack itemStack = player.getMainHandItem();
        if (!(itemStack.getItem() instanceof TrowelItem)) return;

        BlockPos pos = rtr.getBlockPos().relative(rtr.getDirection());

        PoseStack poseStack = event.getPoseStack();
        MultiBufferSource bufferSource = event.getMultiBufferSource();
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.LINES);

        double partialTicks = event.getPartialTick();
        double dx = player.xOld + (player.getX() - player.xOld) * partialTicks;
        double dy = player.yOld + player.getEyeHeight() + (player.getY() - player.yOld) * partialTicks;
        double dz = player.zOld + (player.getZ() - player.zOld) * partialTicks;

        AABB aabb = new AABB(pos).move(-dx, -dy, -dz);

        LevelRenderer.renderLineBox(poseStack, vertexConsumer, aabb, 1.0F, 0.0F, 1.0F, 0.4F);

        event.setCanceled(true);
    }
}
