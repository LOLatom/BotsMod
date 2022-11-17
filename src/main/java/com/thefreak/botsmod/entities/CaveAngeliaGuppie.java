package com.thefreak.botsmod.entities;

import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

public class CaveAngeliaGuppie extends AbstractFish implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    private Random random;

    public static AnimationBuilder SWIM_ANIM = new AnimationBuilder().addAnimation("animation.cave_angelia_guppie.swim");
    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("animation.cave_angelia_guppie.idle");


    public CaveAngeliaGuppie(EntityType<? extends AbstractFish> p_27461_, Level p_27462_) {
        super(p_27461_, p_27462_);
        this.random = new Random();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 7.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)1F);
    }


    private <E extends DrainedEntity> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        AnimationBuilder anim = IDLE_ANIM;
        if (event.isMoving()) {
            anim = SWIM_ANIM;
        }
        controller.setAnimation(anim);
        return PlayState.CONTINUE;
    }
    @Override
    protected SoundEvent getFlopSound() {
        return null;
    }

    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        if (pPlayer.getItemInHand(pHand).getItem() == Items.CARROT) {
            this.setDeltaMovement(0,20,0);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return null;
    }

    @Override
    public void tick() {
        super.tick();
        if (random.nextInt(1000) == 1) {
            spawnAtLocation(ItemInitNew.CAVE_ANGELIA_GUPPIE_SCALE.get());

        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 10, this::predicate));

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
