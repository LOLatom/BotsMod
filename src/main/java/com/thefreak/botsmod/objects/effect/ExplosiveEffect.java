package com.thefreak.botsmod.objects.effect;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.thefreak.botsmod.API.EffectRender.IEffectSpecialRenderings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MobEffectInstance;
import net.minecraft.client.resources.Language;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.MobEffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.alchemy.Potion;
import org.apache.logging.log4j.util.TriConsumer;

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
