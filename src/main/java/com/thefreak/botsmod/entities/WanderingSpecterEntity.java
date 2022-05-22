package com.thefreak.botsmod.entities;

import com.thefreak.botsmod.init.EffectInitNew;
import com.thefreak.botsmod.util.AI.AvoidItemEntityGoal;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import org.spongepowered.asm.mixin.Overwrite;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.entity.ai.goal.Goal.Flag;

public class WanderingSpecterEntity extends CreatureEntity implements IAnimatable {
    protected static final DataParameter<Boolean> SPECTER_TRANSPARENT = EntityDataManager.defineId(WanderingSpecterEntity.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> SPECTER_HAUNTING = EntityDataManager.defineId(WanderingSpecterEntity.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> SPECTER_POSSES_MONSTER = EntityDataManager.defineId(WanderingSpecterEntity.class, DataSerializers.BOOLEAN);


    private final AnimationFactory factory = new AnimationFactory(this);
    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("animation.wandering_specter.idle");
    public static AnimationBuilder MOVING_ANIM = new AnimationBuilder().addAnimation("animation.wandering_specter.moving");
    public static AnimationBuilder HAUNT_ANIM = new AnimationBuilder().addAnimation("animation.wandering_specter.haunt");
    public static AnimationBuilder POSSES_ANIM = new AnimationBuilder().addAnimation("animation.wandering_specter.possesmonster");


    public WanderingSpecterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveControl = new WanderingSpecterEntity.MoveHelperController(this);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {

        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0D)
                .add(Attributes.ATTACK_DAMAGE, 2D)
                .add(Attributes.ATTACK_KNOCKBACK, 0D);
    }
    public void tick() {
        BlockPos blockPos = WanderingSpecterEntity.this.blockPosition();
        World world = WanderingSpecterEntity.this.level;
        if (world.getBlockState(blockPos).getBlock() != Blocks.AIR && getSpecterTransparentState() == false) {
            setSpecterTransparentState(true);
        } else if(world.getBlockState(blockPos).getBlock() == Blocks.AIR && getSpecterTransparentState() == true){
            setSpecterTransparentState(false);
        }
        this.noPhysics = true;
        super.tick();
        this.noPhysics = false;
        this.setNoGravity(true);

    }


    public Boolean getSpecterTransparentState() {
        return this.entityData.get(SPECTER_TRANSPARENT);
    }

    public void setSpecterTransparentState(Boolean transparentState) {
        this.entityData.set(SPECTER_TRANSPARENT, transparentState);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SPECTER_TRANSPARENT, false);
        this.entityData.define(SPECTER_HAUNTING, false);
        this.entityData.define(SPECTER_POSSES_MONSTER, false);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 5, this::predicate));

    }


    private <E extends WanderingSpecterEntity> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        AnimationBuilder anim = IDLE_ANIM;
        if (event.isMoving()) {
            anim = MOVING_ANIM;
        } if (WanderingSpecterEntity.this.entityData.get(SPECTER_HAUNTING) == true && WanderingSpecterEntity.this.entityData.get(SPECTER_POSSES_MONSTER) == false) {
            anim = HAUNT_ANIM;
        } if (WanderingSpecterEntity.this.entityData.get(SPECTER_POSSES_MONSTER) == true) {
            anim = POSSES_ANIM;
        }
        controller.setAnimation(anim);
        return PlayState.CONTINUE;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(0, new AvoidItemEntityGoal(this, PinkPurifiedSaltItemEntity.class, 15f,10f,10f));
         this.goalSelector.addGoal(3, new WanderingSpecterEntity.MoveRandomGoal());
        this.goalSelector.addGoal(3, new WanderingSpecterEntity.LookAtTargetGoal());
        this.goalSelector.addGoal(2, new HauntAttackGoal());
        this.goalSelector.addGoal(1, new WanderingSpecterEntity.Posses());
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, MobEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        Entity entity = source.getDirectEntity();
        if (entity != null) {
            World world = entity.getCommandSenderWorld();
            if (entity instanceof ArrowEntity) {
                BlockPos EntityPos = entity.blockPosition();
                entity.remove();
                for (int i = 0; i < 20; i++) {
                    world.addParticle(ParticleTypes.CLOUD, EntityPos.getX(), EntityPos.getY(), EntityPos.getZ(), 0D, 0D, 0D);
                }

            }
        }
            return false;
    }



    @Override
    public boolean canCollideWith(Entity entity) {
        return false;
    }
    public void move(MoverType typeIn, Vector3d pos) {
        super.move(typeIn, pos);
        this.checkInsideBlocks();
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
    class MoveHelperController extends MovementController {

        public MoveHelperController(WanderingSpecterEntity specterEntity) {
            super(specterEntity);
        }

        public void tick() {
            if (this.operation == MovementController.Action.MOVE_TO) {
                Vector3d vector3d = new Vector3d(this.wantedX - WanderingSpecterEntity.this.getX(), this.wantedY - WanderingSpecterEntity.this.getY(), this.wantedZ - WanderingSpecterEntity.this.getZ());
                double d0 = vector3d.length();
                if (d0 < WanderingSpecterEntity.this.getBoundingBox().getSize()) {
                    this.operation = MovementController.Action.WAIT;
                    WanderingSpecterEntity.this.setDeltaMovement(WanderingSpecterEntity.this.getDeltaMovement().scale(0.5D));
                } else {
                    WanderingSpecterEntity.this.setDeltaMovement(WanderingSpecterEntity.this.getDeltaMovement().add(vector3d.scale(this.speedModifier * 0.05D / d0)));
                    if (WanderingSpecterEntity.this.getTarget() == null) {
                        Vector3d vector3d1 = WanderingSpecterEntity.this.getDeltaMovement();
                        WanderingSpecterEntity.this.yRot = -((float) MathHelper.atan2(vector3d1.x, vector3d1.z)) * (180F / (float)Math.PI);
                        WanderingSpecterEntity.this.yBodyRot = WanderingSpecterEntity.this.yRot;
                    } else {
                        double d2 = WanderingSpecterEntity.this.getTarget().getX() - WanderingSpecterEntity.this.getX();
                        double d1 = WanderingSpecterEntity.this.getTarget().getZ() - WanderingSpecterEntity.this.getZ();
                        WanderingSpecterEntity.this.yRot = -((float)MathHelper.atan2(d2, d1)) * (180F / (float)Math.PI);
                        WanderingSpecterEntity.this.yBodyRot = WanderingSpecterEntity.this.yRot;
                    }
                }

            }
        }
    }
    class Posses extends Goal {

        @Override
        public boolean canUse() {
            Random random = new Random();
            return WanderingSpecterEntity.this.getTarget() != null && random.nextInt(1000) == 17;
        }


        @Override
        public void start() {
            WanderingSpecterEntity.this.moveControl.setWantedPosition((double) WanderingSpecterEntity.this.getTarget().blockPosition().getX(), (double) WanderingSpecterEntity.this.getTarget().blockPosition().getY(), (double) WanderingSpecterEntity.this.getTarget().blockPosition().getZ(), 10);
            WanderingSpecterEntity.this.entityData.set(SPECTER_HAUNTING, true);
            WanderingSpecterEntity.this.getTarget().addEffect(new EffectInstance(EffectInitNew.POSSESION.get(), 9999999, 1));
            WanderingSpecterEntity.this.remove();
            super.start();
        }
    }

    class LookAtTargetGoal extends Goal {
        protected LivingEntity nearestTarget;

    public LookAtTargetGoal() {
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public boolean canContinueToUse() {
            this.nearestTarget = WanderingSpecterEntity.this.level.getNearestPlayer(WanderingSpecterEntity.this, 6);
            return nearestTarget != null ? true : false;
        }

        @Override
        public void tick() {
            this.nearestTarget = WanderingSpecterEntity.this.level.getNearestPlayer(WanderingSpecterEntity.this, 6);
            if (this.nearestTarget != null) {
                WanderingSpecterEntity.this.getLookControl().setLookAt(nearestTarget,90F,90F);            }
            super.tick();
        }
    }
    class HauntAttackGoal extends Goal {
        int timer = 0;
        static final int DURATION = 100;
        public HauntAttackGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Flag.JUMP, Flag.LOOK));
        }
        @Override
        public boolean canUse() {
            Random random = new Random();
            return WanderingSpecterEntity.this.getTarget() != null && random.nextInt(550) == 17;
        }

        @Override
        public boolean canContinueToUse() {
            return WanderingSpecterEntity.this.getTarget() != null && this.timer < DURATION;
        }

        @Override
        public void stop() {
            WanderingSpecterEntity.this.entityData.set(SPECTER_HAUNTING, false);
            super.stop();
        }

        @Override
        public void tick() {
            this.timer++;
            if (WanderingSpecterEntity.this.getTarget() != null) {
                WanderingSpecterEntity.this.getTarget().addEffect(new EffectInstance(Effects.CONFUSION, 200, 5));
            }
            super.tick();
        }

        @Override
        public void start() {
            this.timer = 0;
            WanderingSpecterEntity.this.entityData.set(SPECTER_HAUNTING, true);
            super.start();
        }
    }

    class MoveRandomGoal extends Goal {
        public MoveRandomGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return !WanderingSpecterEntity.this.getMoveControl().hasWanted() && WanderingSpecterEntity.this.random.nextInt(7) == 0;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return false;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            BlockPos blockpos = WanderingSpecterEntity.this.blockPosition();


            for(int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockpos.offset(WanderingSpecterEntity.this.random.nextInt(15) - 7,
                        WanderingSpecterEntity.this.random.nextInt(11) - 5,
                        WanderingSpecterEntity.this.random.nextInt(15) - 7);

                int y = WanderingSpecterEntity.this.level.getHeight(Heightmap.Type.WORLD_SURFACE, blockpos1.getX(), blockpos1.getZ());
                if (WanderingSpecterEntity.this.level.isEmptyBlock(blockpos1) && blockpos1.getY() < y + 10D ) {
                    WanderingSpecterEntity.this.moveControl.setWantedPosition((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() < y ? y + 3D :
                            (double) blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 0.25D);

                    if (WanderingSpecterEntity.this.getTarget() == null) {

                        WanderingSpecterEntity.this.getLookControl()
                                .setLookAt((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D,
                                        (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                    }
                    break;
                }
            }

        }
    }


}
