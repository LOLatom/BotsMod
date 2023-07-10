package com.thefreak.botsmod.objects.blocks.effect;

import com.thefreak.botsmod.API.EffectRender.IEffectSpecialRenderings;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;

public class WatchedUpon extends MobEffect implements IEffectSpecialRenderings {
    public WatchedUpon(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }
    @Override
    public ResourceLocation hasCustomBackgroundLocation(MobEffectInstance effectInstance) {
        return new ResourceLocation("botsmod:textures/gui/effects/watched_upon_effect_background.png");
    }

    @Override
    public boolean hasCustomBackground(MobEffectInstance effectInstance) {
        return true;
    }

    @Override
    public boolean showEffectLabelName(MobEffectInstance MobEffectInstance) {
        return false;
    }
    @Override
    public boolean showEffectLabelTime(MobEffectInstance MobEffectInstance) {
        return false;
    }
}
