package com.thefreak.botsmod.objects.blocks.effect;

import com.thefreak.botsmod.API.EffectRender.IEffectSpecialRenderings;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;

public class DecayingEffect extends MobEffect implements IEffectSpecialRenderings {
    public DecayingEffect(MobEffectCategory p_i50391_1_, int p_i50391_2_) {
        super(p_i50391_1_, p_i50391_2_);
    }


    @Override
    public ResourceLocation hasCustomBackgroundLocation(MobEffectInstance effectInstance) {
        return new ResourceLocation("botsmod:textures/gui/effects/decaying_effect_background.png");
    }

    @Override
    public boolean hasCustomBackground(MobEffectInstance effectInstance) {
        return true;
    }

    @Override
    public int hasCustomBackgroundHeight(MobEffectInstance effectInstance) {
        return 44;
    }

    @Override
    public int customBackgroundHeightAdded(MobEffectInstance effectInstance) {
        return -9;
    }


    @Override
    public int durationBring(MobEffectInstance effectInstance) {
        return -2;
    }
}
