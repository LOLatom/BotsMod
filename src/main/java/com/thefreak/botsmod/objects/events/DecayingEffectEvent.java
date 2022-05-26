package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.init.EffectInitNew;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;
import java.util.List;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DecayingEffectEvent {
    private static boolean entityHasEffect(LivingEntity entity, Effect effect) {
        Collection<EffectInstance> entityEffects = entity.getActiveEffects();
        for (EffectInstance entityEffect : entityEffects) {
            if (entityEffect.getEffect() == effect) {
                return true;
            }
        }
        return false;
    }

    @SubscribeEvent
    public static void testEvent(TickEvent.PlayerTickEvent event) {
        LivingEntity playerEntity = (LivingEntity) event.player.getEntity();
        if (entityHasEffect(playerEntity, EffectInitNew.DECAYING.get())) {
            List<LivingEntity> nearbyMob = event.player.level.getEntitiesOfClass(LivingEntity.class, event.player.getBoundingBox().inflate(1D, 1D, 1D));
            for(LivingEntity livingEntity : nearbyMob) {
                if (livingEntity != event.player) {
                    livingEntity.addEffect(new EffectInstance(EffectInitNew.DECAYING.get(), 200, 0));
                }
            }
        }
    }

    @SubscribeEvent
    public static void testEventtwo(TickEvent.PlayerTickEvent event) {
        LivingEntity playerEntity = (LivingEntity) event.player.getEntity();
        if (entityHasEffect(playerEntity, EffectInitNew.DECAYING.get())) {
            List<LivingEntity> nearbyMob = event.player.level.getEntitiesOfClass(LivingEntity.class, event.player.getBoundingBox().inflate(3.5D, 3.5D, 3.5D));
            for(LivingEntity livingEntity : nearbyMob) {
                if (livingEntity != event.player) {
                    livingEntity.addEffect(new EffectInstance(Effects.CONFUSION, 200, 2));
                }
            }
        }
    }

}
