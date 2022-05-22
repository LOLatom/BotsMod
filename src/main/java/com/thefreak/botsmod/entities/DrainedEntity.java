package com.thefreak.botsmod.entities;

import com.thefreak.botsmod.init.BlockInitNew;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DrainedEntity extends MonsterEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    public static DataParameter<Boolean> IS_SCARED = EntityDataManager.defineId(DrainedEntity.class, DataSerializers.BOOLEAN);
    public static DataParameter<Boolean> VASE = EntityDataManager.defineId(DrainedEntity.class, DataSerializers.BOOLEAN);
    public static DataParameter<Boolean> HELMET = EntityDataManager.defineId(DrainedEntity.class, DataSerializers.BOOLEAN);


    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("animation.drained.idle");
    public static AnimationBuilder WALK_ANIM = new AnimationBuilder().addAnimation("animation.drained.walk");
    public static AnimationBuilder SCARED_ANIM = new AnimationBuilder().addAnimation("animation.drained.scared");



    public DrainedEntity(EntityType<? extends MonsterEntity> p_i48553_1_, World p_i48553_2_) {
        super(p_i48553_1_, p_i48553_2_);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {

        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.12D)
                .add(Attributes.ATTACK_DAMAGE, 2D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.2D);
    }

    @Override
    protected ActionResultType mobInteract(PlayerEntity playerEntity, Hand hand) {
        BlockPos pos = new BlockPos(DrainedEntity.this.position().x,DrainedEntity.this.position().y,DrainedEntity.this.position().z);
        System.out.println(this.entityData.get(IS_SCARED).toString());
        BlockState state = this.level.getBlockState(this.blockPosition());


        if (playerEntity.getItemInHand(hand).getItem() == Items.IRON_HELMET) {
            this.entityData.set(HELMET, true);
        }
        if (playerEntity.isCrouching())  {
            this.entityData.set(VASE, true);
        }


        System.out.println(this.level.getBrightness(LightType.BLOCK, this.blockPosition()));
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
    public void addAdditionalSaveData(CompoundNBT nbt) {
        nbt.putBoolean("scared", this.entityData.get(IS_SCARED));
        nbt.putBoolean("vase", this.entityData.get(VASE));
        nbt.putBoolean("helmet", this.entityData.get(HELMET));
        super.addAdditionalSaveData(nbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        this.entityData.set(IS_SCARED, nbt.getBoolean("scared"));
        this.entityData.set(VASE, nbt.getBoolean("vase"));
        this.entityData.set(HELMET, nbt.getBoolean("helmet"));
        super.readAdditionalSaveData(nbt);

    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new ScaredOfLightGoal(this));
        goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this,0.4D));
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.4D, false));
        targetSelector.addGoal(1,new NearestAttackableTargetGoal(this, PlayerEntity.class, false));
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
        World world = damageSource.getEntity().level;
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


        public ScaredOfLightGoal(MonsterEntity entity) {

            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
        }
        @Override
        public boolean canUse() {
            return DrainedEntity.this.level.getBrightness(LightType.BLOCK, DrainedEntity.this.blockPosition()) > 8 && !(DrainedEntity.this.entityData.get(HELMET));
        }


        @Override
        public boolean canContinueToUse() {
            return DrainedEntity.this.level.getBrightness(LightType.BLOCK, DrainedEntity.this.blockPosition()) > 8 && !(DrainedEntity.this.entityData.get(HELMET));
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
            if (DrainedEntity.this.entityData.get(IS_SCARED) == false) {
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
