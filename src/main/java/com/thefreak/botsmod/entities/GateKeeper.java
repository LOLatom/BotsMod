package com.thefreak.botsmod.entities;

import com.thefreak.botsmod.entities.misc.LightSwordConstruct;
import com.thefreak.botsmod.entities.util.BotsMonster;
import com.thefreak.botsmod.init.ModEntityTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class GateKeeper extends BotsMonster implements IAnimatable, Enemy {
    public int desiredKeyStroke;
    public int keyStrokesLeft;
    public int duelLost;
    public int duelWon;
    public boolean wakingup;

    public static AnimationBuilder WALK_ANIM = new AnimationBuilder().addAnimation("animation.gate_keeper.walking");

    public static AnimationBuilder WAKING_ANIM = new AnimationBuilder().addAnimation("animation.gate_keeper.getting_up");

    public static AnimationBuilder SITTING_ANIM = new AnimationBuilder().addAnimation("animation.gate_keeper.sitting");

    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("animation.gate_keeper.idle");


    private static final EntityDataAccessor<Boolean> DATA_AWAKEN = SynchedEntityData.defineId(GateKeeper.class, EntityDataSerializers.BOOLEAN);


    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public GateKeeper(EntityType<? extends BotsMonster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        this.desiredKeyStroke = 0;
        this.keyStrokesLeft = 0;
        this.duelLost = 0;
        this.duelWon = 0;
        this.wakingup = false;

        animations.put(0,SITTING_ANIM);
        animations.put(1,WAKING_ANIM);
        animations.put(2,IDLE_ANIM);
        animations.put(3,WALK_ANIM);
    }

    public void looseDuel(Player duelist) {
        hurt(DamageSource.playerAttack(duelist), 20F);
    }

    public void winDuel(Player duelist) {
        duelist.hurt(DamageSource.mobAttack(this), 50F);
    }

    /**
     * calculateDuelChance() is a method to calculate the odds
     * for a duel to be created
     * there is 1 out of 30 chances for a duel to be created
     * if the GateKeeper has already won a duel the odds of
     * creating a duel will lower it's rarity, and the more duels lost the rarer it will get
     **/
    public boolean calculateDuelChance() {
        if (random.nextInt(30 + this.duelLost - this.duelWon) == 2) {
            return true;
        }
        return false;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new GateKeeper.WakeUpGoal());
        this.goalSelector.addGoal(2, new GateKeeper.RandomLookAroundGateKeeperGoal(this));
        this.goalSelector.addGoal(2, new  GateKeeper.GateKeeperLookAtPlayerGoal(this,Player.class, 8.0F));


    }

    private <E extends GateKeeper> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        AnimationBuilder anim = animations.get(getActionFlags());

        controller.setAnimation(anim);
        return PlayState.CONTINUE;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (pSource.getEntity() instanceof Player player) {
            this.level.playSound(player,this.blockPosition(), SoundEvents.SMITHING_TABLE_USE, SoundSource.HOSTILE,2F,1.5F);
            LightSwordConstruct lightSwordConstruct = new LightSwordConstruct(ModEntityTypes.LIGHT_SWORD_CONSTRUCT.get(), this.level, 100, player);
            lightSwordConstruct.setPos(player.getX(), player.getY() + 5, player.getZ());
            this.level.addFreshEntity(lightSwordConstruct);
        }
        return false;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_AWAKEN, false);
    }

    public boolean getAwakened() {
        return this.entityData.get(DATA_AWAKEN);
    }

    public void wakeUp() {
        this.entityData.set(DATA_AWAKEN,!this.entityData.get(DATA_AWAKEN));
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 3.55F;
    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 10, this::predicate));

    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.1F)
                .add(Attributes.ATTACK_DAMAGE, 11.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.ARMOR, 1D);
    }


    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    class WakeUpGoal extends Goal {


        private int animationTicks;


        public WakeUpGoal() {
            this.animationTicks = 0;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }


        @Override
        public boolean canUse() {
            List<Player> players = GateKeeper.this.level.getEntitiesOfClass(Player.class,
                    new AABB(GateKeeper.this.blockPosition()).inflate(11,11,11));
            AtomicBoolean hasCreativePlayerIn = new AtomicBoolean(false);
            if (!players.isEmpty()) {
                players.stream().forEach((player) -> {
                    if (player.isCreative()) hasCreativePlayerIn.set(true);
                });
            }
            return !GateKeeper.this.getAwakened() && !players.isEmpty() && !hasCreativePlayerIn.get();
        }

        @Override
        public void tick() {
            super.tick();
            this.animationTicks++;
        }

        @Override
        public void start() {
            super.start();
            System.out.println("Starting");
            GateKeeper.this.setActionFlags(1);
            System.out.println(GateKeeper.this.getAwakened());
        }

        @Override
        public boolean canContinueToUse() {
            return this.animationTicks < 120;
        }

        @Override
        public void stop() {
            super.stop();
            GateKeeper.this.setActionFlags(2);
            wakeUp();
            this.animationTicks = 0;
        }
    }

    class SummonSwordOnTargetGoal extends Goal {

        public SummonSwordOnTargetGoal() {

        }
        @Override
        public boolean canUse() {
            return GateKeeper.this.getActionFlags() == 2;
        }
    }
    class RandomLookAroundGateKeeperGoal extends Goal {
        private final GateKeeper mob;
        private double relX;
        private double relZ;
        private int lookTime;

        public RandomLookAroundGateKeeperGoal(GateKeeper pMob) {
            this.mob = pMob;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }


        public boolean canUse() {
            return this.mob.getRandom().nextFloat() < 0.02F && GateKeeper.this.getAwakened();
        }


        public boolean canContinueToUse() {
            return this.lookTime >= 0;
        }


        public void start() {
            double d0 = (Math.PI * 2D) * this.mob.getRandom().nextDouble();
            this.relX = Math.cos(d0);
            this.relZ = Math.sin(d0);
            this.lookTime = 20 + this.mob.getRandom().nextInt(20);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }


        public void tick() {
            --this.lookTime;
            this.mob.getLookControl().setLookAt(this.mob.getX() + this.relX, this.mob.getEyeY(), this.mob.getZ() + this.relZ);
        }
    }

    class GateKeeperLookAtPlayerGoal extends Goal {
        public static final float DEFAULT_PROBABILITY = 0.02F;
        protected final GateKeeper mob;
        @Nullable
        protected Entity lookAt;
        protected final float lookDistance;
        private int lookTime;
        protected final float probability;
        private final boolean onlyHorizontal;
        protected final Class<? extends LivingEntity> lookAtType;
        protected final TargetingConditions lookAtContext;

        public GateKeeperLookAtPlayerGoal(GateKeeper pMob, Class<? extends LivingEntity> pLookAtType, float pLookDistance) {
            this(pMob, pLookAtType, pLookDistance, 0.02F);
        }

        public GateKeeperLookAtPlayerGoal(GateKeeper pMob, Class<? extends LivingEntity> pLookAtType, float pLookDistance, float pProbability) {
            this(pMob, pLookAtType, pLookDistance, pProbability, false);
        }

        public GateKeeperLookAtPlayerGoal(GateKeeper pMob, Class<? extends LivingEntity> pLookAtType, float pLookDistance, float pProbability, boolean pOnlyHorizontal) {
            this.mob = pMob;
            this.lookAtType = pLookAtType;
            this.lookDistance = pLookDistance;
            this.probability = pProbability;
            this.onlyHorizontal = pOnlyHorizontal;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
            if (pLookAtType == Player.class) {
                this.lookAtContext = TargetingConditions.forNonCombat().range((double)pLookDistance).selector((p_25531_) -> {
                    return EntitySelector.notRiding(pMob).test(p_25531_);
                });
            } else {
                this.lookAtContext = TargetingConditions.forNonCombat().range((double)pLookDistance);
            }

        }


        public boolean canUse() {
            if (this.mob.getRandom().nextFloat() >= this.probability) {
                return false;
            } else {
                if (this.mob.getTarget() != null) {
                    this.lookAt = this.mob.getTarget();
                }

                if (this.lookAtType == Player.class) {
                    this.lookAt = this.mob.level.getNearestPlayer(this.lookAtContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
                } else {
                    this.lookAt = this.mob.level.getNearestEntity(this.mob.level.getEntitiesOfClass(this.lookAtType, this.mob.getBoundingBox().inflate((double)this.lookDistance, 3.0D, (double)this.lookDistance), (p_148124_) -> {
                        return true && GateKeeper.this.getAwakened();
                    }), this.lookAtContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
                }

                return this.lookAt != null && GateKeeper.this.getAwakened();
            }
        }


        public boolean canContinueToUse() {
            if (!this.lookAt.isAlive()) {
                return false;
            } else if (this.mob.distanceToSqr(this.lookAt) > (double)(this.lookDistance * this.lookDistance)) {
                return false;
            } else {
                return this.lookTime > 0;
            }
        }


        public void start() {
            this.lookTime = this.adjustedTickDelay(40 + this.mob.getRandom().nextInt(40));
        }


        public void stop() {
            this.lookAt = null;
        }

        public void tick() {
            if (this.lookAt.isAlive()) {
                double d0 = this.onlyHorizontal ? this.mob.getEyeY() : this.lookAt.getEyeY();
                this.mob.getLookControl().setLookAt(this.lookAt.getX(), d0, this.lookAt.getZ());
                --this.lookTime;
            }
        }
    }
}
