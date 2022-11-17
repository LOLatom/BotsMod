package com.thefreak.botsmod.objects.blocks.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class PossesionEffect extends MobEffect {
    public PossesionEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {

        super.applyEffectTick(entityLivingBaseIn, amplifier);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return null;
    }
}
