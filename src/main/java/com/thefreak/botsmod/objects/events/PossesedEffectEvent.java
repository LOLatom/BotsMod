package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.WanderingSpecterEntity;
import com.thefreak.botsmod.init.EffectInitNew;
import com.thefreak.botsmod.init.ModEntityTypes;
import com.thefreak.botsmod.util.AI.AttackWhenPossesedGoal;
import com.thefreak.botsmod.util.AI.TargetNearestWhenPossesed;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;
import java.util.Random;

import static net.minecraft.entity.ai.attributes.Attributes.ATTACK_DAMAGE;
import static net.minecraft.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PossesedEffectEvent {
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
    public static void testEvent(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        boolean isPlayer = entity instanceof PlayerEntity;
        if (!isPlayer && entity instanceof MobEntity) {
            MobEntity mobEntity = (MobEntity) entity;
            mobEntity.goalSelector.addGoal(1, new AttackWhenPossesedGoal(mobEntity,1D, true));
            mobEntity.targetSelector.addGoal(1, new TargetNearestWhenPossesed<>(mobEntity, PlayerEntity.class, true));
        }
    }


    @SubscribeEvent
    public static void RenderWithPossesion(TickEvent.RenderTickEvent event) {
        Minecraft mc = Minecraft.getInstance();
        final ClientPlayerEntity playerEntity = mc.player;
        if (playerEntity != null) {
            if (entityHasEffect((LivingEntity) playerEntity.getEntity(), EffectInitNew.POSSESION.get())) {
                    playerEntity.yRot += Math.sin(playerEntity.tickCount /5) / 5;
                    playerEntity.xRot += Math.cos(playerEntity.tickCount /5) / 5;
                    Random random = new Random();
                    BlockPos pos = playerEntity.blockPosition();
                    World world = playerEntity.level;
                    if (random.nextInt(100)==2) {
                        playerEntity.jumpFromGround();
                    }


            }
        }
    }


    @SubscribeEvent
    public static void T(LivingDeathEvent event) {
        System.out.println("DIED !");
        LivingEntity livingEntity = event.getEntityLiving();
        Entity entity = event.getEntity();
        BlockPos pos = entity.blockPosition();
        World world = livingEntity.getCommandSenderWorld();
        boolean isInstanceOfPlayer = livingEntity.getEntity() instanceof PlayerEntity;

        if (entityHasEffect(livingEntity, EffectInitNew.POSSESION.get())) {
            WanderingSpecterEntity wanderingSpecterEntity = ModEntityTypes.WANDERING_SPECTER.get().create(world);
            wanderingSpecterEntity.setPos(pos.getX(),pos.getY(),pos.getZ());

            world.addFreshEntity(wanderingSpecterEntity);
        }
    }
}
