package com.thefreak.botsmod.objects.effect;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.thefreak.botsmod.API.EffectRender.IEffectSpecialRenderings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.Language;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.util.TriConsumer;

import javax.swing.*;

public class ExplosiveEffect extends Effect implements IEffectSpecialRenderings {
    public ExplosiveEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {

        super.applyEffectTick(entityLivingBaseIn, amplifier);
    }

    @Override
    public boolean hasCustomBackground(EffectInstance effectInstance) {
        return true;
    }

    @Override
    public ResourceLocation hasCustomBackgroundLocation(EffectInstance effectInstance) {
        return new ResourceLocation("botsmod:textures/gui/effects/explosiv_effect_background.png");
    }

    @Override
    public int hasCustomBackgroundHeight(EffectInstance effectInstance) {
        return 45;
    }

    @Override
    public int customBackgroundHeightAdded(EffectInstance effectInstance) {
        return -4;
    }

    @Override
    public boolean hasCustomIconBackground(EffectInstance effectInstance) {
        return true;
    }

    @Override
    public ResourceLocation hasCustomIconBackgroundLocation(EffectInstance effectInstance) {
        return new ResourceLocation("botsmod:textures/gui/effects/icons/explosiv_effect_i_background.png");
    }
}
