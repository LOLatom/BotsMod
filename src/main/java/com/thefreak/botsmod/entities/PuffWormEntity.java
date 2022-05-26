package com.thefreak.botsmod.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.NodeEvaluator;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.shapes.VoxelShape;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class PuffWormEntity extends PathfinderMob implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    protected static final EntityDataAccessor<Boolean> PUFFED = SynchedEntityData.defineId(PuffWormEntity.class, EntityDataSerializers.BOOLEAN);

    FlyingPathNavigation pathNavigator;

    protected static final EntityDataAccessor<Float> STORED_WATER = SynchedEntityData.defineId(PuffWormEntity.class, EntityDataSerializers.FLOAT);

    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("animation.puff_worm.idle");
    public static AnimationBuilder WALK_ANIM = new AnimationBuilder().addAnimation("animation.puff_worm.walk");
    public static AnimationBuilder PUFF_ANIM_IDLE_ANIM = new AnimationBuilder().addAnimation("animation.puffed_puff_worm.idle");
    public static AnimationBuilder PUFF_ANIM_WALK_ANIM = new AnimationBuilder().addAnimation("animation.puffed_puff_worm.walk");

    public PuffWormEntity(EntityType<? extends PathfinderMob> p_i48575_1_, Level p_i48575_2_) {
        super(p_i48575_1_, p_i48575_2_);
        this.pathNavigator = new FlyingPathNavigation(this, this.level);
        this.moveControl = new PuffMovementController(this, 1, true);
    }
    private <E extends LadybugEntity> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        AnimationBuilder anim = IDLE_ANIM;
        if (this.getPuffState() == false) {
            if (event.isMoving()) {
                anim = WALK_ANIM;
            } else {
                anim = IDLE_ANIM;
            }
        } else {
            if (event.isMoving()) {
                anim = PUFF_ANIM_WALK_ANIM;
            } else {
                anim = PUFF_ANIM_IDLE_ANIM;
            }
        }
        controller.setAnimation(anim);
        return PlayState.CONTINUE;
    }
    public float getWaterStored() {
        return PuffWormEntity.this.entityData.get(STORED_WATER);
    }

    public void setWaterStored(float f) {
        PuffWormEntity.this.entityData.set(STORED_WATER, f);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        PuffWormEntity.this.setPuffState(nbt.getBoolean("IsPuffed"));
        PuffWormEntity.this.setWaterStored(nbt.getFloat("WaterStored"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putFloat("WaterStored", getWaterStored());
        nbt.putBoolean("IsPuffed", getPuffState());
    }

    public boolean getPuffState() {
        return PuffWormEntity.this.entityData.get(PUFFED);
    }
    public void setPuffState(boolean state) {
        PuffWormEntity.this.entityData.set(PUFFED, state);
    }



    public static AttributeSupplier.Builder setCustomAttributes()
    {

        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.ATTACK_DAMAGE, 1D)
                .add(Attributes.ATTACK_KNOCKBACK, 0D)
                .add(Attributes.ARMOR, 0D)
                .add(Attributes.ATTACK_SPEED, 1D)
                .add(Attributes.FLYING_SPEED, 0.4D);
    }
    @Override
    public boolean canBeCollidedWith() {
        return true;
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PUFFED, false);
        this.entityData.define(STORED_WATER, 0F);
    }

    @Override
    protected void actuallyHurt(DamageSource p_70665_1_, float p_70665_2_) {
        setPuffState(true);
        super.actuallyHurt(p_70665_1_, p_70665_2_);
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(1, new PuffAwayFromEntityGoal(this, Player.class,2,2,2));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        super.registerGoals();
    }

    @Override
    protected InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        setCustomAttributes().add(Attributes.ARMOR, 5D);
        setPuffState(true);
        return super.mobInteract(p_230254_1_, p_230254_2_);
    }

   

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 5, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public class PuffGoal extends Goal {

        @Override
        public boolean canUse() {
            boolean notpuffed = !(PuffWormEntity.this.getPuffState());

            return false;
        }
    }

    public class PuffAwayFromEntityGoal<T extends LivingEntity> extends Goal {
        PuffWormEntity instance;
        Class<T> livingentity;
        Level level;
        protected T toAvoid;
        private final TargetingConditions avoidEntityTargeting;
        protected final PathNavigation pathNav;
        protected Path path;
        protected float maxDist;
        private final double walkSpeedModifier;
        private final double sprintSpeedModifier;
        protected final Predicate<LivingEntity> avoidPredicate;
        protected final Predicate<LivingEntity> predicateOnAvoidEntity;

        public PuffAwayFromEntityGoal(PuffWormEntity p_i46404_1_, Class<T> p_i46404_2_, float p_i46404_3_, double p_i46404_4_, double p_i46404_6_) {
            this(p_i46404_1_, p_i46404_2_, (p_200828_0_) -> {
                return true;
            }, p_i46404_3_, p_i46404_4_, p_i46404_6_, EntitySelector.NO_CREATIVE_OR_SPECTATOR::test);
        }



        public PuffAwayFromEntityGoal(PuffWormEntity p_i48859_1_, Class<T> p_i48859_2_, Predicate<LivingEntity> p_i48859_3_, float p_i48859_4_, double p_i48859_5_, double p_i48859_7_, Predicate<LivingEntity> p_i48859_9_) {
            this.instance = p_i48859_1_;
            this.livingentity = p_i48859_2_;
            this.avoidPredicate = p_i48859_3_;
            this.maxDist = p_i48859_4_;
            this.walkSpeedModifier = p_i48859_5_;
            this.sprintSpeedModifier = p_i48859_7_;
            this.predicateOnAvoidEntity = p_i48859_9_;
            this.pathNav = p_i48859_1_.getNavigation();
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
            // TODO: check
            this.avoidEntityTargeting = (TargetingConditions.forNonCombat()).range((double)p_i48859_4_).selector(p_i48859_9_.and(p_i48859_3_));
        }

        @Override
        public boolean canUse() {
            boolean ispuffed = (PuffWormEntity.this.getPuffState());
            boolean livingentitynotnull = this.livingentity != null;
            this.toAvoid = this.instance.level.getNearestEntity(this.livingentity, this.avoidEntityTargeting, this.instance, this.instance.getX(), this.instance.getY(), this.instance.getZ(), this.instance.getBoundingBox().inflate((double)this.maxDist, 3.0D, (double)this.maxDist));
            if (ispuffed && livingentitynotnull) {
                if (this.toAvoid == null) {
                    return false;
                } else {
                    // TODO
                    Vector3d vector3d = RandomPositionGenerator.getPosAvoid(this.instance, 16, 7, this.toAvoid.position());
                    if (vector3d == null) {
                        return false;
                    } else if (this.toAvoid.distanceToSqr(vector3d.x, vector3d.y, vector3d.z) < this.toAvoid.distanceToSqr(this.instance)) {
                        return false;
                    } else {
                        this.path = this.pathNav.createPath(vector3d.x, vector3d.y, vector3d.z, 0);
                        return this.path != null;
                    }
                }
            } else {
                return false;
            }
        }
        @Override
        public boolean canContinueToUse() {
            return !this.pathNav.isDone();
        }

        @Override
        public void start() {
            this.instance.moveControl.setWantedPosition(this.instance.getX(), this.instance.getY() + 2,this.instance.getZ(),2D);
            super.start();
        }

    }


    public class FindForWaterGoal extends Goal {

        @Override
        public boolean canUse() {
            return false;
        }
    }

    public class DrinkWaterGoal extends Goal {

        @Override
        public boolean canUse() {
            boolean isfull = PuffWormEntity.this.getWaterStored() < 200F;
            return false;
        }
    }

    public class PuffWormRandomMovementSwitchGoal extends Goal {

        @Override
        public boolean canUse() {
            return false;
        }
    }

    public class PuffMovementController extends MoveControl {

        private final int maxTurn;
        private final boolean hoversInPlace;

        public PuffMovementController(Mob p_i225710_1_, int p_i225710_2_, boolean p_i225710_3_) {
            super(p_i225710_1_);
            this.maxTurn = p_i225710_2_;
            this.hoversInPlace = p_i225710_3_;
        }
        private boolean isWalkable(float p_234024_1_, float p_234024_2_) {
            PathNavigation pathnavigator = this.mob.getNavigation();
            if (pathnavigator != null) {
                NodeEvaluator nodeprocessor = pathnavigator.getNodeEvaluator();
                if (nodeprocessor != null && nodeprocessor.getBlockPathType(this.mob.level, Mth.floor(this.mob.getX() + (double)p_234024_1_), Mth.floor(this.mob.getY()), Mth.floor(this.mob.getZ() + (double)p_234024_2_)) != BlockPathTypes.WALKABLE) {
                    return false;
                }
            }

            return true;
        }

        public void tick() {
            if (PuffWormEntity.this.getPuffState()) {
                if (this.operation == MoveControl.Operation.MOVE_TO) {
                    this.operation = MoveControl.Operation.WAIT;
                    this.mob.setNoGravity(true);
                    double d0 = this.wantedX - this.mob.getX();
                    double d1 = this.wantedY - this.mob.getY();
                    double d2 = this.wantedZ - this.mob.getZ();
                    double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                    if (d3 < (double) 2.5000003E-7F) {
                        this.mob.setYya(0.0F);
                        this.mob.setZza(0.0F);
                        return;
                    }

                    float f = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
                    this.mob.yRot = this.rotlerp(this.mob.yRot, f, 90.0F);
                    float f1;
                    if (this.mob.isOnGround()) {
                        f1 = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    } else {
                        f1 = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.FLYING_SPEED));
                    }

                    this.mob.setSpeed(f1);
                    double d4 = (double) Mth.sqrt((float) (d0 * d0 + d2 * d2)); // originally this cast wasn't needed, I may have slipped up on something here
                    float f2 = (float) (-(Mth.atan2(d1, d4) * (double) (180F / (float) Math.PI)));
                    this.mob.xRot = this.rotlerp(this.mob.xRot, f2, (float) this.maxTurn);
                    this.mob.setYya(d1 > 0.0D ? f1 : -f1);
                } else {
                    if (!this.hoversInPlace) {
                        this.mob.setNoGravity(false);
                    }

                    this.mob.setYya(0.0F);
                    this.mob.setZza(0.0F);
                }
            } else {
                if (this.operation == MoveControl.Operation.STRAFE) {
                    float f = (float)this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED);
                    float f1 = (float)this.speedModifier * f;
                    float f2 = this.strafeForwards;
                    float f3 = this.strafeRight;
                    float f4 = Mth.sqrt(f2 * f2 + f3 * f3);
                    if (f4 < 1.0F) {
                        f4 = 1.0F;
                    }

                    f4 = f1 / f4;
                    f2 = f2 * f4;
                    f3 = f3 * f4;
                    float f5 = Mth.sin(this.mob.yRot * ((float)Math.PI / 180F));
                    float f6 = Mth.cos(this.mob.yRot * ((float)Math.PI / 180F));
                    float f7 = f2 * f6 - f3 * f5;
                    float f8 = f3 * f6 + f2 * f5;
                    if (!this.isWalkable(f7, f8)) {
                        this.strafeForwards = 1.0F;
                        this.strafeRight = 0.0F;
                    }

                    this.mob.setSpeed(f1);
                    this.mob.setZza(this.strafeForwards);
                    this.mob.setXxa(this.strafeRight);
                    this.operation = MoveControl.Operation.WAIT;
                } else if (this.operation == MoveControl.Operation.MOVE_TO) {
                    this.operation = MoveControl.Operation.WAIT;
                    double d0 = this.wantedX - this.mob.getX();
                    double d1 = this.wantedZ - this.mob.getZ();
                    double d2 = this.wantedY - this.mob.getY();
                    double d3 = d0 * d0 + d2 * d2 + d1 * d1;
                    if (d3 < (double)2.5000003E-7F) {
                        this.mob.setZza(0.0F);
                        return;
                    }

                    float f9 = (float)(Mth.atan2(d1, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.mob.yRot = this.rotlerp(this.mob.yRot, f9, 90.0F);
                    this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                    BlockPos blockpos = this.mob.blockPosition();
                    BlockState blockstate = this.mob.level.getBlockState(blockpos);
                    Block block = blockstate.getBlock();
                    VoxelShape voxelshape = blockstate.getCollisionShape(this.mob.level, blockpos);
                    if (d2 > (double)this.mob.maxUpStep && d0 * d0 + d1 * d1 < (double)Math.max(1.0F, this.mob.getBbWidth()) || !voxelshape.isEmpty() && this.mob.getY() < voxelshape.max(Direction.Axis.Y) + (double)blockpos.getY() && !blockstate.is(BlockTags.DOORS) && !blockstate.is(BlockTags.FENCES)) {
                        this.mob.getJumpControl().jump();
                        this.operation = MoveControl.Operation.JUMPING;
                    }
                } else if (this.operation == MoveControl.Operation.JUMPING) {
                    this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                    if (this.mob.isOnGround()) {
                        this.operation = MoveControl.Operation.WAIT;
                    }
                } else {
                    this.mob.setZza(0.0F);
                }
            }

        }
    }
}
