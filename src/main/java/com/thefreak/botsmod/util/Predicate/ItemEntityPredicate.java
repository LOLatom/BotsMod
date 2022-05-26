package com.thefreak.botsmod.util.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class ItemEntityPredicate extends TargetingConditions {
    public static final ItemEntityPredicate DEFAULT = new ItemEntityPredicate();
    private double distance = -1.0D;
    private boolean allowInvulnerable;
    private boolean friendlyFire;
    private boolean requireLineOfSight;
    private boolean skipAttackChecks;
    private boolean useVisibilityModifier = true;
    private Predicate<ItemEntity> customPredicate;

    public ItemEntityPredicate range(double distanceIn) {
        this.distance = distanceIn;
        return this;
    }

    public ItemEntityPredicate allowInvulnerable() {
        this.allowInvulnerable = true;
        return this;
    }

    public ItemEntityPredicate allowSameTeam() {
        this.friendlyFire = true;
        return this;
    }

    public ItemEntityPredicate allowUnseeable() {
        this.requireLineOfSight = true;
        return this;
    }

    public ItemEntityPredicate allowNonAttackable() {
        this.skipAttackChecks = true;
        return this;
    }

    public ItemEntityPredicate ignoreInvisibilityTesting() {
        this.useVisibilityModifier = false;
        return this;
    }
    public ItemEntityPredicate setCustomItemPredicate(@Nullable Predicate<ItemEntity> customPredicate) {
        this.customPredicate = customPredicate;
        return this;
    }


}
