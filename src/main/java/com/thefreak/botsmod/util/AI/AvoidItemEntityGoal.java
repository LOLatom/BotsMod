package com.thefreak.botsmod.util.AI;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.RandomPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class AvoidItemEntityGoal<T extends ItemEntity> extends Goal {
    protected final PathfinderMob entity;
    private final double farSpeed;
    private final double nearSpeed;
    protected List<T> avoidTarget;
    protected final float avoidDistance;
    protected Path path;
    protected final PathNavigation navigation;
    /** Class of entity this behavior seeks to avoid */
    protected final Class<T> classToAvoid;
    protected final Predicate<ItemEntity> avoidTargetSelector;
    protected final Predicate<ItemEntity> predicateOnAvoidEntity;
    private final EntityPredicate builtTargetSelector;

    public AvoidItemEntityGoal(PathfinderMob entityIn, Class<T> classToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
        this(entityIn, classToAvoidIn, (p_200828_0_) -> {
            return true;
        }, avoidDistanceIn, farSpeedIn, nearSpeedIn, EntitySelector.NO_CREATIVE_OR_SPECTATOR::test);
    }

    public AvoidItemEntityGoal(PathfinderMob entityIn, Class<T> avoidClass, Predicate<ItemEntity> targetPredicate, float distance, double nearSpeedIn, double farSpeedIn, Predicate<ItemEntity> p_i48859_9_) {
        this.entity = entityIn;
        this.classToAvoid = avoidClass;
        this.avoidTargetSelector = targetPredicate;
        this.avoidDistance = distance;
        this.farSpeed = nearSpeedIn;
        this.nearSpeed = farSpeedIn;
        this.predicateOnAvoidEntity = p_i48859_9_;
        this.navigation = entityIn.getNavigation();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        // TODO: fix this
        this.builtTargetSelector = (TargetingConditions.forNonCombat()).range((double)distance).selector(p_i48859_9_.and(targetPredicate));
    }

    public AvoidItemEntityGoal(PathfinderMob entityIn, Class<T> avoidClass, float distance, double nearSpeedIn, double farSpeedIn, Predicate<ItemEntity> targetPredicate) {
        this(entityIn, avoidClass, (p_203782_0_) -> {
            return true;
        }, distance, nearSpeedIn, farSpeedIn, targetPredicate);
    }


    public boolean canUse() {
        this.avoidTarget = this.entity.level.getEntitiesOfClass(classToAvoid, this.entity.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
        for (ItemEntity itemEntity : this.avoidTarget) {
            if (itemEntity == null) {
                return false;
            } else {
//                Vector3d vector3d = RandomPositionGenerator.getPosAvoid(this.entity, 16, 7, itemEntity.position());
                Vec3 vector3d = RandomPos.generateRandomPos(this.entity, () -> itemEntity.blockPosition());
                if (vector3d == null) {
                    return false;
                } else if (itemEntity.distanceToSqr(vector3d.x, vector3d.y, vector3d.z) < itemEntity.distanceToSqr(this.entity)) {
                    return false;
                } else {
                    this.path = this.navigation.createPath(vector3d.x, vector3d.y, vector3d.z, 0);
                    return this.path != null;
                }
            }

        }
    return false;
    }


    public boolean canContinueToUse() {
        return !this.navigation.isDone();
    }


    public void start() {
        this.navigation.moveTo(this.path, this.farSpeed);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.avoidTarget = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        for (ItemEntity itemEntity : this.avoidTarget) {
            if (this.entity.distanceToSqr(itemEntity) < 49.0D) {
                this.entity.getNavigation().setSpeedModifier(this.nearSpeed);
            } else {
                this.entity.getNavigation().setSpeedModifier(this.farSpeed);
            }
        }

    }
}