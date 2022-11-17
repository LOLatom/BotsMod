package com.thefreak.botsmod.objects.blocks.effect;

import com.thefreak.botsmod.API.EffectRender.IEffectSpecialRenderings;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import javax.swing.*;

public class ExplosiveEffect extends MobEffect implements IEffectSpecialRenderings {
    public ExplosiveEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {

        super.applyEffectTick(entityLivingBaseIn, amplifier);
    }

    @Override
    public boolean hasCustomBackground(MobEffectInstance MobEffectInstance) {
        return true;
    }

    @Override
    public ResourceLocation hasCustomBackgroundLocation(MobEffectInstance MobEffectInstance) {
        return new ResourceLocation("botsmod:textures/gui/effects/explosiv_effect_background.png");
    }

    @Override
    public int hasCustomBackgroundHeight(MobEffectInstance MobEffectInstance) {
        return 45;
    }

    @Override
    public int customBackgroundHeightAdded(MobEffectInstance MobEffectInstance) {
        return -4;
    }

    @Override
    public boolean hasCustomIconBackground(MobEffectInstance MobEffectInstance) {
        return true;
    }

    @Override
    public ResourceLocation hasCustomIconBackgroundLocation(MobEffectInstance MobEffectInstance) {
        return new ResourceLocation("botsmod:textures/gui/effects/icons/explosiv_effect_i_background.png");
    }
}
