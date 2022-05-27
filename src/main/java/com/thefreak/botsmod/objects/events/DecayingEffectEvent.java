package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.init.EffectInitNew;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;
import java.util.List;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DecayingEffectEvent {
    private static boolean entityHasEffect(LivingEntity entity, MobEffect effect) {
        Collection<MobEffectInstance> entityEffects = entity.getActiveEffects();
        for (MobEffectInstance entityEffect : entityEffects) {
            if (entityEffect.getEffect() == effect) {
                return true;
            }
        }
        return false;
    }

    @SubscribeEvent
    public static void testEvent(TickEvent.PlayerTickEvent event) {
        LivingEntity playerEntity = (LivingEntity) event.player/*.getEntity()*/;
        if (entityHasEffect(playerEntity, EffectInitNew.DECAYING.get())) {
            List<LivingEntity> nearbyMob = event.player.level.getEntitiesOfClass(LivingEntity.class, event.player.getBoundingBox().inflate(1D, 1D, 1D));
            for(LivingEntity livingEntity : nearbyMob) {
                if (livingEntity != event.player) {
                    livingEntity.addEffect(new MobEffectInstance(EffectInitNew.DECAYING.get(), 200, 0));
                }
            }
        }
    }

    @SubscribeEvent
    public static void testEventtwo(TickEvent.PlayerTickEvent event) {
        LivingEntity playerEntity = (LivingEntity) event.player/*.getEntity()*/;
        if (entityHasEffect(playerEntity, EffectInitNew.DECAYING.get())) {
            List<LivingEntity> nearbyMob = event.player.level.getEntitiesOfClass(LivingEntity.class, event.player.getBoundingBox().inflate(3.5D, 3.5D, 3.5D));
            for(LivingEntity livingEntity : nearbyMob) {
                if (livingEntity != event.player) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 2));
                }
            }
        }
    }

}
