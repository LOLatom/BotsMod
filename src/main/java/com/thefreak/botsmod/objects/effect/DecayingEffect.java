package com.thefreak.botsmod.objects.effect;

import com.thefreak.botsmod.API.EffectRender.IEffectSpecialRenderings;
import com.thefreak.botsmod.init.EffectInitNew;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class DecayingEffect extends Effect implements IEffectSpecialRenderings {
    public DecayingEffect(EffectType p_i50391_1_, int p_i50391_2_) {
        super(p_i50391_1_, p_i50391_2_);
    }


    @Override
    public ResourceLocation hasCustomBackgroundLocation(EffectInstance effectInstance) {
        return new ResourceLocation("botsmod:textures/gui/effects/decaying_effect_background.png");
    }

    @Override
    public boolean hasCustomBackground(EffectInstance effectInstance) {
        return true;
    }

    @Override
    public int hasCustomBackgroundHeight(EffectInstance effectInstance) {
        return 44;
    }

    @Override
    public int customBackgroundHeightAdded(EffectInstance effectInstance) {
        return -9;
    }


    @Override
    public int durationBring(EffectInstance effectInstance) {
        return -2;
    }
}
