package com.thefreak.botsmod.entities;

import com.thefreak.botsmod.init.ModEntityTypes;
import com.thefreak.botsmod.init.iteminit.FoodItemInit;
import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;
import java.util.Random;

public class TippyLizardEntity extends PathfinderMob implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    protected static final EntityDataAccessor<Boolean> ISSLEEPING = SynchedEntityData.defineId(TippyLizardEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Float> FOODLEVEL = SynchedEntityData.defineId(TippyLizardEntity.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<String> VARIENT = SynchedEntityData.defineId(TippyLizardEntity.class, EntityDataSerializers.STRING);
    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("animation.tippy_lizard.idle");
    public static AnimationBuilder WALK_ANIM = new AnimationBuilder().addAnimation("animation.tippy_lizard.walk");
    public static AnimationBuilder RUN_ANIM = new AnimationBuilder().addAnimation("animation.tippy_lizard.running");
    public static AnimationBuilder SLEEP_ANIM = new AnimationBuilder().addAnimation("animation.tippy_lizard.sit");
    public static Random random = new Random();
    public TippyLizardEntity(EntityType<? extends PathfinderMob> p_i48568_1_, Level p_i48568_2_) {
        super(p_i48568_1_, p_i48568_2_);
    }
    public static AttributeSupplier.Builder setCustomAttributes()
    {

        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 1D)
                .add(Attributes.ATTACK_KNOCKBACK, 0D)
                .add(Attributes.ATTACK_SPEED, 1D);
    }

    private <E extends LadybugEntity> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        AnimationBuilder anim = IDLE_ANIM;
        if (TippyLizardEntity.this.isSleepingFunc()) {
            anim = SLEEP_ANIM;
        } else {
            if (event.isMoving() && TippyLizardEntity.this.entityData.get(FOODLEVEL) < 10) {
                anim = WALK_ANIM;
            } else if (event.isMoving() && TippyLizardEntity.this.entityData.get(FOODLEVEL) > 10) {
                anim = RUN_ANIM;
            } else {
                anim = IDLE_ANIM;
            }
        }
        controller.setAnimation(anim);
        return PlayState.CONTINUE;
    }

    public String getVarient() {
        return this.entityData.get(VARIENT);
    }
    public void setVarient(String s) {
        this.entityData.set(VARIENT, s);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ISSLEEPING, false);
        this.entityData.define(FOODLEVEL, 20F);
        this.entityData.define(VARIENT, "b1");
    }

    @Override
    public void killed(ServerLevel world, LivingEntity livingEntity) {
        if (livingEntity/*.getEntity()*/.getType() == ModEntityTypes.LADYBUG.get()) {
            this.setItemInHand(InteractionHand.MAIN_HAND, ItemInitNew.LADYBUG.get().getDefaultInstance());
        }
        super.killed(world, livingEntity);
    }

    @Override
    public boolean canBeCollidedWith() {
        return isSleepingFunc() ? true : false;
    }

    public boolean isSleepingFunc() {
        return this.entityData.get(ISSLEEPING);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SleepAtNightGoal());
        this.goalSelector.addGoal(1, new eatGoal());
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this,TippyLizardEntity.this.entityData.get(FOODLEVEL) > 10 ? 1.7D : 0.5D));
        this.goalSelector.addGoal(3, new MeleeAttackIfMouthEmptyGoal(this, TippyLizardEntity.this.entityData.get(FOODLEVEL) > 10 ? 1.7D : 0.5D, true));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.0D, TippyLizardEntity.this.entityData.get(FOODLEVEL) > 10 ? 1.7D : 0.5D));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, LadybugEntity.class, true));
        super.registerGoals();
    }

    @Override
    public InteractionResult interactAt(Player playerEntity, Vec3 vector3d, InteractionHand hand) {
        if (playerEntity.getItemInHand(hand).getItem() == FoodItemInit.YELLOW_CANDY.get()) {
            this.entityData.set(VARIENT, "gecko1");
            System.out.println(TippyLizardEntity.this.entityData.get(VARIENT));
        }
        if (playerEntity.getItemInHand(hand).getItem() == Items.SPIDER_EYE && TippyLizardEntity.this.entityData.get(VARIENT) == "gecko1") {
            this.entityData.set(VARIENT, "devil1");
            System.out.println(TippyLizardEntity.this.entityData.get(VARIENT));
        }
        if (playerEntity.getItemInHand(hand).getItem() == FoodItemInit.BLUE_CANDY.get()) {
            this.entityData.set(VARIENT, "b1");
            System.out.println(TippyLizardEntity.this.entityData.get(VARIENT));
        }
        if (playerEntity.getItemInHand(hand).getItem() == ItemInitNew.LADYBUG.get()) {
            this.setItemInHand(hand, playerEntity.getItemInHand(hand));
            System.out.println(this.getMainHandItem());
        }
        return super.interactAt(playerEntity, vector3d, hand);
    }

    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString("Varient", this.getVarient());
    }


    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("Varient")) {
            this.setVarient(nbt.getString("Varient"));
        }
    }


    @Override
    public void tick() {
        if (!TippyLizardEntity.this.entityData.get(ISSLEEPING)) {
            if (TippyLizardEntity.this.entityData.get(FOODLEVEL) > 0 && random.nextInt(300) == 137) {
                TippyLizardEntity.this.entityData.set(FOODLEVEL, TippyLizardEntity.this.entityData.get(FOODLEVEL) - 0.5F);
                System.out.println(TippyLizardEntity.this.entityData.get(FOODLEVEL));
            }
        }
        super.tick();
    }

    class eatGoal extends Goal {
        public int eatingtick = 0;
        eatGoal() {
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Flag.JUMP, Flag.TARGET));
        }

        @Override
        public boolean canUse() {
            return TippyLizardEntity.this.getMainHandItem().getItem() == ItemInitNew.LADYBUG.get() && TippyLizardEntity.this.entityData.get(FOODLEVEL) < 11F;
        }

        @Override
        public boolean canContinueToUse() {
            return TippyLizardEntity.this.getMainHandItem().getItem() == ItemInitNew.LADYBUG.get() && TippyLizardEntity.this.entityData.get(FOODLEVEL) < 11F;
        }

        @Override
        public void tick() {
            if (this.eatingtick < 100) {
                this.eatingtick += 1;
                TippyLizardEntity.this.level.addParticle((ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, TippyLizardEntity.this.getMainHandItem().getContainerItem()), TippyLizardEntity.this.position().x, TippyLizardEntity.this.position().y, TippyLizardEntity.this.position().z, 1, 1, 1);

            }
            else {
                TippyLizardEntity.this.entityData.set(FOODLEVEL, 20F);
            }
            super.tick();
        }

        @Override
        public void start() {
            this.eatingtick = 0;
            super.start();
        }

        @Override
        public void stop() {
            TippyLizardEntity.this.setItemInHand(InteractionHand.MAIN_HAND, Items.AIR.getDefaultInstance());
            super.stop();
        }
    }
    class SleepAtNightGoal extends Goal {

        SleepAtNightGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Flag.JUMP, Flag.TARGET, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return TippyLizardEntity.this.level.isNight();
        }

        @Override
        public boolean canContinueToUse() {
            return TippyLizardEntity.this.level.isNight();
        }

        @Override
        public void start() {
            TippyLizardEntity.this.entityData.set(ISSLEEPING, true);
            super.start();
        }

        @Override
        public void stop() {
            TippyLizardEntity.this.entityData.set(ISSLEEPING, false);
            super.stop();
        }
    }

    class MeleeAttackIfMouthEmptyGoal extends MeleeAttackGoal {
        protected final PathfinderMob mob;
        private final double speedModifier;
        private final boolean followingTargetEvenIfNotSeen;
        private Path path;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;
        private int ticksUntilNextPathRecalculation;
        private int ticksUntilNextAttack;
        private final int attackInterval = 20;
        private long lastCanUseCheck;
        private int failedPathFindingPenalty = 0;
        private boolean canPenalize = false;


        public MeleeAttackIfMouthEmptyGoal(PathfinderMob p_i1636_1_, double p_i1636_2_, boolean p_i1636_4_) {
            super(p_i1636_1_,p_i1636_2_,p_i1636_4_);
            this.mob = p_i1636_1_;
            this.speedModifier = p_i1636_2_;
            this.followingTargetEvenIfNotSeen = p_i1636_4_;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (TippyLizardEntity.this.getMainHandItem().getItem() == Items.AIR) {
                long i = this.mob.level.getGameTime();
                if (i - this.lastCanUseCheck < 20L) {
                    return false;
                } else {
                    this.lastCanUseCheck = i;
                    LivingEntity livingentity = this.mob.getTarget();
                    if (livingentity == null) {
                        return false;
                    } else if (!livingentity.isAlive()) {
                        return false;
                    } else {
                        if (canPenalize) {
                            if (--this.ticksUntilNextPathRecalculation <= 0) {
                                this.path = this.mob.getNavigation().createPath(livingentity, 0);
                                this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                                return this.path != null;
                            } else {
                                return true;
                            }
                        }
                        this.path = this.mob.getNavigation().createPath(livingentity, 0);
                        if (this.path != null) {
                            return true;
                        } else {
                            return this.getAttackReachSqr(livingentity) >= this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                        }
                    }
                }
            } else {
                return false;
            }

        }
        public boolean canContinueToUse() {
            LivingEntity livingentity = this.mob.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else if (!this.followingTargetEvenIfNotSeen) {
                return !this.mob.getNavigation().isDone();
            } else if (!this.mob.isWithinRestriction(livingentity.blockPosition())) {
                return false;
            } else {
                return !(livingentity instanceof Player) || !livingentity.isSpectator() && !((Player)livingentity).isCreative();
            }
        }

        public void start() {
            this.mob.getNavigation().moveTo(this.path, this.speedModifier);
            this.mob.setAggressive(true);
            this.ticksUntilNextPathRecalculation = 0;
            this.ticksUntilNextAttack = 0;
        }

        public void stop() {
            LivingEntity livingentity = this.mob.getTarget();
            if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
                this.mob.setTarget((LivingEntity)null);
            }

            this.mob.setAggressive(false);
            this.mob.getNavigation().stop();
        }

        public void tick() {
            LivingEntity livingentity = this.mob.getTarget();
            this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
            double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
            this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
            if ((this.followingTargetEvenIfNotSeen || this.mob.getSensing().hasLineOfSight(livingentity)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.mob.getRandom().nextFloat() < 0.05F)) {
                this.pathedTargetX = livingentity.getX();
                this.pathedTargetY = livingentity.getY();
                this.pathedTargetZ = livingentity.getZ();
                this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                if (this.canPenalize) {
                    this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
                    if (this.mob.getNavigation().getPath() != null) {
                        Node finalPathPoint = this.mob.getNavigation().getPath().getEndNode();
                        if (finalPathPoint != null && livingentity.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                            failedPathFindingPenalty = 0;
                        else
                            failedPathFindingPenalty += 10;
                    } else {
                        failedPathFindingPenalty += 10;
                    }
                }
                if (d0 > 1024.0D) {
                    this.ticksUntilNextPathRecalculation += 10;
                } else if (d0 > 256.0D) {
                    this.ticksUntilNextPathRecalculation += 5;
                }

                if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier)) {
                    this.ticksUntilNextPathRecalculation += 15;
                }
            }

            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
            this.checkAndPerformAttack(livingentity, d0);
        }

        protected void checkAndPerformAttack(LivingEntity p_190102_1_, double p_190102_2_) {
            double d0 = this.getAttackReachSqr(p_190102_1_);
            if (p_190102_2_ <= d0 && this.ticksUntilNextAttack <= 0) {
                this.resetAttackCooldown();
                this.mob.swing(InteractionHand.MAIN_HAND);
                this.mob.doHurtTarget(p_190102_1_);
            }

        }

        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = 20;
        }

        protected boolean isTimeToAttack() {
            return this.ticksUntilNextAttack <= 0;
        }

        protected int getTicksUntilNextAttack() {
            return this.ticksUntilNextAttack;
        }

        protected int getAttackInterval() {
            return 20;
        }

        protected double getAttackReachSqr(LivingEntity p_179512_1_) {
            return (double)(this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + p_179512_1_.getBbWidth());
        }
    }
}
