package com.thefreak.botsmod.objects.items;

import com.thefreak.botsmod.entities.PinkPurifiedSaltItemEntity;
import com.thefreak.botsmod.entities.WanderingSpecterEntity;
import com.thefreak.botsmod.init.EffectInitNew;

import com.thefreak.botsmod.init.ModEntityTypes;


import java.util.Collection;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PinkPurifiedSalt extends Item {
    public PinkPurifiedSalt(Properties properties) {
        super(properties);
    }
    private static boolean entityHasEffect(LivingEntity entity, MobEffect effect) {
        Collection<MobEffectInstance> entityEffects = entity.getActiveEffects();
        for (MobEffectInstance entityEffect : entityEffects) {
            if (entityEffect.getEffect() == effect) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entityLiving) {
        BlockPos pos = entityLiving.blockPosition();
        if (entityHasEffect(entityLiving, EffectInitNew.POSSESION.get())) {
            WanderingSpecterEntity wanderingSpecterEntity = ModEntityTypes.WANDERING_SPECTER.get().create(world);
            wanderingSpecterEntity.setPos(pos.getX(),pos.getY(),pos.getZ());
            world.addFreshEntity(wanderingSpecterEntity);
            entityLiving.removeEffect(EffectInitNew.POSSESION.get());
        }
        return super.finishUsingItem(stack, world, entityLiving);
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }



    public PinkPurifiedSaltItemEntity createEntity(Level world, Entity location, ItemStack itemstack)
    {
        return new PinkPurifiedSaltItemEntity(world, location.getX(), location.getY(), location.getZ(), itemstack) {
            @Override
            public boolean hasPickUpDelay() {
                return true;
            }

        };
    }

}
