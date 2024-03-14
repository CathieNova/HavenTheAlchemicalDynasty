package net.cathienova.havenalchemy.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class AsphaltBricks extends Block
{
    public AsphaltBricks(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity)
    {
        if (!level.isClientSide())
        {
            if(entity instanceof LivingEntity livingEntity){
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20,1,false,false));
            }
        }

        super.stepOn(level, pos, state, entity);
    }
}
