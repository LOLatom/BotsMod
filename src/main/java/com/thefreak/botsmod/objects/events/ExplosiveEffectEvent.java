package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.init.EffectInitNew;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ExplosiveEffectEvent {
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
    public static void testEvent(LivingDeathEvent event) {
        LivingEntity livingEntity = event.getEntityLiving();
        Level world = livingEntity.getCommandSenderWorld();

        if (entityHasEffect(livingEntity, EffectInitNew.EXPLOSIVE_EFFECT.get())) {
          world.explode(livingEntity, livingEntity.xOld, livingEntity.yOld, livingEntity.zOld, 4.0F, false, Explosion.BlockInteraction.BREAK);
        }
    }
}
