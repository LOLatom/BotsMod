package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.entities.WanderingSpecterEntity;
import com.thefreak.botsmod.init.EffectInitNew;
import com.thefreak.botsmod.init.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;
import java.util.Random;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PossesedEffectEvent {
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
    public static void testEvent(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        boolean isPlayer = entity instanceof Player;
    }


    @SubscribeEvent
    public static void RenderWithPossesion(TickEvent.RenderTickEvent event) {
        final Player playerEntity = ClassReferences.getPlayer();
        if (playerEntity != null) {
            if (entityHasEffect((LivingEntity) playerEntity/*.getEntity()*/, EffectInitNew.POSSESION.get())) {
                    // TODO: check
                    playerEntity.yRotO += Math.sin(playerEntity.tickCount /5) / 5;
                    playerEntity.xRotO += Math.cos(playerEntity.tickCount /5) / 5;
                    Random random = new Random();
                    BlockPos pos = playerEntity.blockPosition();
                    Level world = playerEntity.level;
                    if (random.nextInt(100)==2) {
                        playerEntity.jumpFromGround();
                    }


            }
        }
    }


    @SubscribeEvent
    public static void T(LivingDeathEvent event) {
        LivingEntity livingEntity = event.getEntityLiving();
        Entity entity = event.getEntity();
        BlockPos pos = entity.blockPosition();
        Level world = livingEntity.getCommandSenderWorld();

        if (entityHasEffect(livingEntity, EffectInitNew.POSSESION.get())) {
            WanderingSpecterEntity wanderingSpecterEntity = ModEntityTypes.WANDERING_SPECTER.get().create(world);
            wanderingSpecterEntity.setPos(pos.getX(),pos.getY(),pos.getZ());

            world.addFreshEntity(wanderingSpecterEntity);
        }
    }
}
