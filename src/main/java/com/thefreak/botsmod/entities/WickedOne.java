package com.thefreak.botsmod.entities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

public class WickedOne extends Monster implements NeutralMob, IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    public static AnimationBuilder WALK_ANIM = new AnimationBuilder().addAnimation("animation.wicked_one.walk");
    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("animation.wicked_one.idle");
    public static AnimationBuilder SCREAMING_ANIM = new AnimationBuilder().addAnimation("animation.wicked_one.scream");
    public static AnimationBuilder SCREAM_ATTACK_ANIM = new AnimationBuilder().addAnimation("animation.wicked_one.scream_attack");


    private static final EntityDataAccessor<Boolean> DATA_STARED_AT = SynchedEntityData.defineId(WickedOne.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_ANGER = SynchedEntityData.defineId(WickedOne.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_SCREAMING = SynchedEntityData.defineId(WickedOne.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_ISPERFORMING_SCREAMATTACK = SynchedEntityData.defineId(WickedOne.class, EntityDataSerializers.BOOLEAN);


    private int targetChangeTime;
    private int tick;





    public WickedOne(EntityType<? extends Monster> p_33002_, Level p_33003_) {

        super(p_33002_, p_33003_);
        this.tick = 0;
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 3.40F;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.1F)
                .add(Attributes.ATTACK_DAMAGE, 11.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new WickedOneScreamAttack(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.5D, true));
        this.goalSelector.addGoal(2, new WickedOneLookForPlayerGoal(this, this::isAngryAt));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
        super.registerGoals();
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return 0;
    }

    @Override
    public void setRemainingPersistentAngerTime(int pTime) {

    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return null;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID pTarget) {

    }


    @Override
    public void startPersistentAngerTimer() {

    }

    public void setBeingStaredAt() {
        this.entityData.set(DATA_STARED_AT, true);
        if (!this.isScreaming() && !isAngered()) {
            this.entityData.set(DATA_SCREAMING, true);
            this.entityData.set(DATA_ANGER, true);
        }
    }


    public boolean isPerformingScreamAttack() {
        return entityData.get(DATA_ISPERFORMING_SCREAMATTACK);
    }

    public void setPerformingScreamAttack(boolean attacking) {
        entityData.set(DATA_ISPERFORMING_SCREAMATTACK, attacking);
    }


    public void setTarget(@javax.annotation.Nullable LivingEntity pLivingEntity) {
        AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
        if (pLivingEntity == null) {
            this.targetChangeTime = 0;
            this.entityData.set(DATA_STARED_AT, false);
            this.entityData.set(DATA_ANGER, true);
        } else {
            this.targetChangeTime = this.tickCount;
        }

        super.setTarget(pLivingEntity); //Forge: Moved down to allow event handlers to write data manager values.
    }


    private <E extends DrainedEntity> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        AnimationBuilder anim = IDLE_ANIM;
        if(this.xOld != this.position().x || this.zOld != this.position().z) {
            anim = WALK_ANIM;
        }else {
            anim = IDLE_ANIM;
        }

        if(this.isScreaming() == true) {
            anim = SCREAMING_ANIM;
        }

        if(this.isPerformingScreamAttack()) {
            anim = SCREAM_ATTACK_ANIM;
        }


        controller.setAnimation(anim);
        return PlayState.CONTINUE;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if(pSource.getEntity() instanceof Player) {
            this.setAggressive(true);
        }
        return super.hurt(pSource, pAmount);
    }
    protected SoundEvent getAmbientSound() {
        return this.isScreaming() ? SoundEvents.ENDERMAN_SCREAM : SoundEvents.ENDERMAN_AMBIENT;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 10, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
    boolean isLookingAtMe(Player pPlayer) {
            Vec3 vec3 = pPlayer.getViewVector(1.0F).normalize();
            Vec3 vec31 = new Vec3(this.getX() - pPlayer.getX(), this.getEyeY() - pPlayer.getEyeY(), this.getZ() - pPlayer.getZ());
            double d0 = vec31.length();
            vec31 = vec31.normalize();
            double d1 = vec3.dot(vec31);
            return d1 > 1.0D - 0.025D / d0 ? pPlayer.hasLineOfSight(this) : false;
    }



    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_STARED_AT, false);
        this.entityData.define(DATA_ANGER, false);
        this.entityData.define(DATA_SCREAMING, false);
        this.entityData.define(DATA_ISPERFORMING_SCREAMATTACK, false);
    }

    public boolean isAngered() {
        return this.entityData.get(DATA_ANGER);
    }

    public void setAngered(boolean angered) {
        this.entityData.set(DATA_ANGER, angered);
    }

    public boolean isScreaming() {
        return this.entityData.get(DATA_SCREAMING);
    }

    public void setScreaming(boolean screaming) {
        if(this.tick <= 60) {
            this.entityData.set(DATA_SCREAMING, screaming);
        }
    }

    public void stopScreaming() {
            this.entityData.set(DATA_SCREAMING, false);
    }

    @Override
    public void tick() {

        if (this.tick <= 60 && this.isAngered()) {
            this.tick++;



            AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
            this.setScreaming(true);
            attributeinstance.setBaseValue(0);



        } else {
            if (this.isScreaming()) {
                AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);

                this.stopScreaming();
                attributeinstance.setBaseValue((double) 0.1F);



            }
            super.tick();
        }
    }

    public static class WickedOneScreamAttack extends Goal {
        WickedOne wickedOne;
        private int ticking;
        private Player Target;
        private Random random;

        public WickedOneScreamAttack(WickedOne entity) {
        this.ticking = 0;
        this.wickedOne = entity;
        this.random = new Random();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Flag.JUMP, Flag.LOOK));
        }

        @Override
        public boolean canUse() {



            return this.wickedOne.getTarget() != null &&
                    random.nextInt(210) == 1 &&
                    !this.wickedOne.isScreaming() &&
                    this.wickedOne.isAngered();
        }

        @Override
        public void start() {
            super.start();
            AttributeInstance attributeinstance = this.wickedOne.getAttribute(Attributes.MOVEMENT_SPEED);
            attributeinstance.setBaseValue(0);
            this.wickedOne.setPerformingScreamAttack(true);
        }

        @Override
        public boolean canContinueToUse() {
            return this.ticking <= 60;
        }

        @Override
        public void tick() {
            this.ticking++;

            if(this.ticking == 20 || this.ticking == 21 || this.ticking == 22) {

                        /*DEBUG ("TEXT") */
                System.out.println("debug");

                this.wickedOne.getTarget().getLevel().playLocalSound(this.wickedOne.getX(),this.wickedOne.getY(),this.wickedOne.getZ(),SoundEvents.ENDERMAN_SCREAM, SoundSource.HOSTILE, 2F,0.5F, false);
                this.wickedOne.getTarget().getLevel().playLocalSound(this.wickedOne.getTarget().getX(),this.wickedOne.getTarget().getY(),this.wickedOne.getTarget().getZ(),SoundEvents.ENDERMAN_SCREAM, SoundSource.HOSTILE, 2F,0.5F, false);

                this.wickedOne.level.playSound((Player) this.wickedOne.getTarget(),this.wickedOne.getX(),this.wickedOne.getY(),this.wickedOne.getZ(),SoundEvents.ENDERMAN_SCREAM, SoundSource.HOSTILE, 2F,0.5F);

            }
            if (this.wickedOne.getTarget() != null && this.wickedOne.getTarget() != this.wickedOne && this.ticking >= 20) {
                this.wickedOne.lookAt((Entity) this.wickedOne.getTarget(), 180F, 180F);
                this.wickedOne.getTarget().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 140, 100));
                this.wickedOne.getTarget().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 140, 100));
            }
            super.tick();

        }

        @Override
        public void stop() {
            super.stop();
            AttributeInstance attributeinstance = this.wickedOne.getAttribute(Attributes.MOVEMENT_SPEED);
            attributeinstance.setBaseValue((double) 0.1F);
            this.wickedOne.setPerformingScreamAttack(false);
            this.ticking = 0;
        }


    }

    static class WickedOneLookForPlayerGoal extends NearestAttackableTargetGoal<Player> {
        /** The player */
        WickedOne wickedOne;
        @javax.annotation.Nullable
        private Player pendingTarget;
        private Player newTarget;
        private int aggroTime;
        private int tick;
        private int teleportTime;
        private final TargetingConditions startAggroTargetConditions;
        private final TargetingConditions continueAggroTargetConditions = TargetingConditions.forCombat().ignoreLineOfSight();

        public WickedOneLookForPlayerGoal(WickedOne p_32573_, @javax.annotation.Nullable Predicate<LivingEntity> p_32574_) {
            super(p_32573_, Player.class, 10, false, false, p_32574_);
            wickedOne = p_32573_;
            tick = 0;
            this.startAggroTargetConditions = TargetingConditions.forCombat().range(this.getFollowDistance()).selector((p_32578_) -> {
                return p_32573_.isLookingAtMe((Player)p_32578_);
            });
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            this.pendingTarget = this.wickedOne.level.getNearestPlayer(this.startAggroTargetConditions, this.wickedOne);
            if (this.newTarget == null) {
                this.newTarget = this.pendingTarget;
            }
            return this.pendingTarget != null;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.aggroTime = this.adjustedTickDelay(5);
            this.teleportTime = 0;
            this.wickedOne.setBeingStaredAt();
            this.wickedOne.setTarget(this.newTarget);
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            this.pendingTarget = null;
            super.stop();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {

                    this.wickedOne.lookAt(this.pendingTarget, 10.0F, 10.0F);
                return this.target != null && this.continueAggroTargetConditions.test(this.wickedOne, this.target) ? true : super.canContinueToUse();

        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.wickedOne.getTarget() != null) {
                this.wickedOne.lookAt((Entity) this.newTarget, 180F, 180F);

            }
                super.tick();

            }

        }
    }

