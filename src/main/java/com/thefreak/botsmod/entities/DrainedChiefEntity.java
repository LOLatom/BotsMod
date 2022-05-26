package com.thefreak.botsmod.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.ArrayList;
import java.util.EnumSet;

public class DrainedChiefEntity extends Monster implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);


    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("animation.drained_chief_model.idle");
    public static AnimationBuilder WALK_ANIM = new AnimationBuilder().addAnimation("animation.drained_chief_model.walk");



    public DrainedChiefEntity(EntityType<? extends Monster> p_i48553_1_, Level p_i48553_2_) {
        super(p_i48553_1_, p_i48553_2_);
    }

    public static AttributeSupplier.Builder setCustomAttributes()
    {

        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.12D)
                .add(Attributes.ATTACK_DAMAGE, 2D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.2D);
    }


    private <E extends DrainedChiefEntity> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        AnimationBuilder anim = IDLE_ANIM;
        if (this.xOld != this.position().x || this.zOld != this.position().z) {
                anim = WALK_ANIM;
        }
        controller.setAnimation(anim);
        return PlayState.CONTINUE;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);

    }

    @Override
    public boolean hurt(DamageSource damageSource, float p_70097_2_) {
        if (damageSource.getDirectEntity() != null) {
            if (damageSource.getDirectEntity() instanceof Arrow) {
                // TODO: select a reason
                damageSource.getDirectEntity().remove(RemovalReason.DISCARDED);
                this.level.playLocalSound(damageSource.getDirectEntity().getX(), damageSource.getDirectEntity().getY(), damageSource.getDirectEntity().getZ(),
                        SoundEvents.PLAYER_BURP, SoundSource.HOSTILE,1F,1F,true);
                return false;
            } else {
                return super.hurt(damageSource, p_70097_2_);
            }
        } else {
            return super.hurt(damageSource, p_70097_2_);
        }

    }



    @Override
    protected void registerGoals() {
        goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this,1D));
        targetSelector.addGoal(1,new NearestAttackableTargetGoal(this, Player.class, false));
        super.registerGoals();
    }

    @Override
    protected void defineSynchedData() {

        super.defineSynchedData();
    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 1, this::predicate));
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }


}
