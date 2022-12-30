package com.thefreak.botsmod.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

public class DrainedEntity extends Monster implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    public static EntityDataAccessor<Boolean> IS_SCARED = SynchedEntityData.defineId(DrainedEntity.class, EntityDataSerializers.BOOLEAN);
    public static EntityDataAccessor<Boolean> VASE = SynchedEntityData.defineId(DrainedEntity.class, EntityDataSerializers.BOOLEAN);
    public static EntityDataAccessor<Boolean> HELMET = SynchedEntityData.defineId(DrainedEntity.class, EntityDataSerializers.BOOLEAN);


    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("animation.drained.idle");
    public static AnimationBuilder WALK_ANIM = new AnimationBuilder().addAnimation("animation.drained.walk");
    public static AnimationBuilder SCARED_ANIM = new AnimationBuilder().addAnimation("animation.drained.scared");



    public DrainedEntity(EntityType<? extends Monster> p_i48553_1_, Level p_i48553_2_) {
        super(p_i48553_1_, p_i48553_2_);
    }

    public static AttributeSupplier.Builder setCustomAttributes()
    {

        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.12D)
                .add(Attributes.ATTACK_DAMAGE, 2D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.2D);
    }

    @Override
    protected InteractionResult mobInteract(Player playerEntity, InteractionHand hand) {
        BlockPos pos = new BlockPos(DrainedEntity.this.position().x,DrainedEntity.this.position().y,DrainedEntity.this.position().z);
        System.out.println(this.entityData.get(IS_SCARED).toString());
        BlockState state = this.level.getBlockState(this.blockPosition());


        if (playerEntity.getItemInHand(hand).getItem() == Items.IRON_HELMET) {
            this.entityData.set(HELMET, true);
        }
        if (playerEntity.isCrouching())  {
            this.entityData.set(VASE, true);
        }


        System.out.println(this.level.getBrightness(LightLayer.BLOCK, this.blockPosition()));
        System.out.println(this.blockPosition());

        return super.mobInteract(playerEntity, hand);

    }

    public boolean hasVase() {
        return this.entityData.get(VASE);
    }

    public void setVase(boolean vase) {
        this.entityData.set(VASE,vase);
    }


    private <E extends DrainedEntity> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        AnimationBuilder anim = IDLE_ANIM;
        if (this.xOld != this.position().x || this.zOld != this.position().z && DrainedEntity.this.entityData.get(IS_SCARED) != true) {
                anim = WALK_ANIM;
        }
        if(DrainedEntity.this.entityData.get(IS_SCARED) == true) {
            anim = SCARED_ANIM;
        }
        controller.setAnimation(anim);
        return PlayState.CONTINUE;
    }

    public boolean hasHelmet() {
        return this.entityData.get(HELMET);
    }

    public void setHelmet(boolean helmet) {
        this.entityData.set(HELMET, helmet);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        nbt.putBoolean("scared", this.entityData.get(IS_SCARED));
        nbt.putBoolean("vase", this.entityData.get(VASE));
        nbt.putBoolean("helmet", this.entityData.get(HELMET));
        super.addAdditionalSaveData(nbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        this.entityData.set(IS_SCARED, nbt.getBoolean("scared"));
        this.entityData.set(VASE, nbt.getBoolean("vase"));
        this.entityData.set(HELMET, nbt.getBoolean("helmet"));
        super.readAdditionalSaveData(nbt);

    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new ScaredOfLightGoal(this));
        goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this,0.4D));
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.4D, false));
        targetSelector.addGoal(1,new NearestAttackableTargetGoal<>(this, Player.class, false));
        super.registerGoals();
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(IS_SCARED, false);
        this.entityData.define(VASE, false);
        this.entityData.define(HELMET, false);
        super.defineSynchedData();
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource, int p_213333_2_, boolean p_213333_3_) {
        Level world = damageSource.getEntity().level;
        ArrayList<ItemStack> itemStacks = new ArrayList<ItemStack>();
        itemStacks.add(Items.DIAMOND.getDefaultInstance());
        itemStacks.add(Items.IRON_INGOT.getDefaultInstance());
        if (this.entityData.get(VASE)) {
            this.level.addFreshEntity(new ItemEntity(this.level,this.getX(),this.getY(),this.getZ(), itemStacks.get(random.nextInt(2))));
        }
        super.dropCustomDeathLoot(damageSource, p_213333_2_, p_213333_3_);
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

    public class ScaredOfLightGoal extends Goal {

        Random random = new Random();

        public ScaredOfLightGoal(Monster entity) {

            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
        }
        @Override
        public boolean canUse() {
            return DrainedEntity.this.level.getBrightness(LightLayer.BLOCK, DrainedEntity.this.blockPosition()) > 8 && !(DrainedEntity.this.entityData.get(HELMET));
        }


        @Override
        public boolean canContinueToUse() {
            return DrainedEntity.this.level.getBrightness(LightLayer.BLOCK, DrainedEntity.this.blockPosition()) > 8 && !(DrainedEntity.this.entityData.get(HELMET));
        }

        @Override
        public void start() {
            super.start();
            if (DrainedEntity.this.entityData.get(IS_SCARED) == false) {

                DrainedEntity.this.entityData.set(IS_SCARED,true);
            }

        }

        @Override
        public void tick() {
            super.tick();
            if (DrainedEntity.this.entityData.get(IS_SCARED) == false && random.nextInt(3) == 2) {
                DrainedEntity.this.entityData.set(IS_SCARED,true);
            }
        }

        @Override
        public void stop() {
            super.stop();
            if (DrainedEntity.this.entityData.get(IS_SCARED) == true) {
                DrainedEntity.this.entityData.set(IS_SCARED,false);
            }
        }

    }

}
