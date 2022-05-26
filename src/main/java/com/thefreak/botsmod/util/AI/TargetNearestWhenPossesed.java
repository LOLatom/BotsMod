package com.thefreak.botsmod.util.AI;

import com.thefreak.botsmod.init.EffectInitNew;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.EnumSet;
import java.util.function.Predicate;

public class TargetNearestWhenPossesed<T extends LivingEntity> extends TargetGoal {
    protected final Class<T> targetClass;
    protected final int targetChance;
    protected LivingEntity nearestTarget;
    /** This filter is applied to the Entity search. Only matching entities will be targeted. */
    protected TargetingConditions targetEntitySelector;

    public TargetNearestWhenPossesed(Mob goalOwnerIn, Class<T> targetClassIn, boolean checkSight) {
        this(goalOwnerIn, targetClassIn, checkSight, false);
    }

    public TargetNearestWhenPossesed(Mob goalOwnerIn, Class<T> targetClassIn, boolean checkSight, boolean nearbyOnlyIn) {
        this(goalOwnerIn, targetClassIn, 10, checkSight, nearbyOnlyIn, (Predicate<LivingEntity>)null);
    }

    public TargetNearestWhenPossesed(Mob goalOwnerIn, Class<T> targetClassIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn, @Nullable Predicate<LivingEntity> targetPredicate) {
        super(goalOwnerIn, checkSight, nearbyOnlyIn);
        this.targetClass = targetClassIn;
        this.targetChance = targetChanceIn;
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
        this.targetEntitySelector = (TargetingConditions.forCombat()).range(this.getFollowDistance()).selector(targetPredicate);
    }
    private static boolean entityHasEffect(LivingEntity entity, MobEffect effect) {
        Collection<MobEffectInstance> entityEffects = entity.getActiveEffects();
        for (MobEffectInstance entityEffect : entityEffects) {
            if (entityEffect.getEffect() == effect) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        if (entityHasEffect((LivingEntity) this.mob/*.getEntity()*/, EffectInitNew.POSSESION.get())) {
            if (this.targetChance > 0 && this.mob.getRandom().nextInt(this.targetChance) != 0) {
                return false;
            } else {
                this.findNearestTarget();
                return this.nearestTarget != null;
            }
        } else return false;
    }

    protected AABB getTargetableArea(double targetDistance) {
        return this.mob.getBoundingBox().inflate(targetDistance, 4.0D, targetDistance);
    }

    protected void findNearestTarget() {
        if (this.targetClass != Player.class && this.targetClass != ServerPlayer.class) {
            this.nearestTarget = this.mob.level.getNearestEntity(this.targetClass, this.targetEntitySelector, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ(), this.getTargetableArea(this.getFollowDistance()));
        } else {
            this.nearestTarget = this.mob.level.getNearestPlayer(this.targetEntitySelector, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        }

    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.mob.setTarget(this.nearestTarget);
        super.start();
    }

    public void setNearestTarget(@Nullable LivingEntity target) {
        this.nearestTarget = target;
    }
}
