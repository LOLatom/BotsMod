package com.thefreak.botsmod.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.ArrayList;
import java.util.EnumSet;

public class DrainedChiefEntity extends MonsterEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);


    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("animation.drained_chief_model.idle");
    public static AnimationBuilder WALK_ANIM = new AnimationBuilder().addAnimation("animation.drained_chief_model.walk");



    public DrainedChiefEntity(EntityType<? extends MonsterEntity> p_i48553_1_, World p_i48553_2_) {
        super(p_i48553_1_, p_i48553_2_);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {

        return MobEntity.createMobAttributes()
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
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);

    }

    @Override
    public boolean hurt(DamageSource damageSource, float p_70097_2_) {
        if (damageSource.getDirectEntity() != null) {
            if (damageSource.getDirectEntity() instanceof ArrowEntity) {
                damageSource.getDirectEntity().remove();
                this.level.playLocalSound(damageSource.getDirectEntity().getX(), damageSource.getDirectEntity().getY(), damageSource.getDirectEntity().getZ(),
                        SoundEvents.PLAYER_BURP, SoundCategory.HOSTILE,1F,1F,true);
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
        goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this,1D));
        targetSelector.addGoal(1,new NearestAttackableTargetGoal(this, PlayerEntity.class, false));
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
