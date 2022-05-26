package com.thefreak.botsmod.entities;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class LadybugEntity extends Animal implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("animation.ladybug.idle");
    public static AnimationBuilder WALK_ANIM = new AnimationBuilder().addAnimation("animation.ladybug.walk");

    public LadybugEntity(EntityType<? extends Animal> p_i48568_1_, Level p_i48568_2_) {
        super(p_i48568_1_, p_i48568_2_);
    }






    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this,0.5D));

        super.registerGoals();
    }
    private <E extends LadybugEntity> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        AnimationBuilder anim = IDLE_ANIM;
        if (event.isMoving()) {
            anim = WALK_ANIM;
        } else {
            anim = IDLE_ANIM;
        }
        controller.setAnimation(anim);
        return PlayState.CONTINUE;
    }

    public static AttributeSupplier.Builder setCustomAttributes()
    {

        // TODO: I forget what this originally was, lol
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return null;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 1, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
