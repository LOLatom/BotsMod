package com.thefreak.botsmod.objects.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class ExplosiveEffect extends Effect {
    public ExplosiveEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {

        super.applyEffectTick(entityLivingBaseIn, amplifier);
    }



}
