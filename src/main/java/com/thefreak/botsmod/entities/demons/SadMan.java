package com.thefreak.botsmod.entities.demons;

import com.thefreak.botsmod.entities.GateKeeper;
import com.thefreak.botsmod.entities.util.BotsMonster;
import com.thefreak.botsmod.init.iteminit.FoodItemInit;
import com.thefreak.botsmod.objects.items.relics.SacrificialTongue;
import com.thefreak.botsmod.particles.ModParticleType;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class SadMan extends BotsMonster implements IAnimatable {

    public static AnimationBuilder CRYING_ANIM = new AnimationBuilder().addAnimation("animation.sad_man.crying");



    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("animation.sad_man.getup").addAnimation("animation.sad_man.idle", ILoopType.EDefaultLoopTypes.LOOP);


    private static final EntityDataAccessor<Boolean> DATA_HAPPY = SynchedEntityData.defineId(SadMan.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> DATA_ASKED = SynchedEntityData.defineId(SadMan.class, EntityDataSerializers.INT);


    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public SadMan(EntityType<? extends BotsMonster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        this.animations.put(0, CRYING_ANIM);
        this.animations.put(1, IDLE_ANIM);
    }

    private <E extends GateKeeper> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        AnimationBuilder anim = animations.get(getActionFlags());

        controller.setAnimation(anim);
        return PlayState.CONTINUE;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_HAPPY, false);
        this.entityData.define(DATA_ASKED,0);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 5D)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.2F)
                .add(Attributes.ATTACK_DAMAGE, 0.1D)
                .add(Attributes.FOLLOW_RANGE, 5D)
                .add(Attributes.ARMOR, 0D);
    }

    public void setHappy() {
        this.entityData.set(DATA_HAPPY,true);
    }

    public boolean isHappy() {
        return this.entityData.get(DATA_HAPPY);
    }

    public int getAskedAmount() {
        return this.entityData.get(DATA_ASKED);
    }

    public void askOnce() {
        this.entityData.set(DATA_ASKED, this.getAskedAmount() + 1);
    }

    @Override
    public void worded(String msg, Player player) {
        super.worded(msg, player);
        if (msg.equals("Why are you sad ?")) {
            this.askOnce();
            System.out.println(this.getAskedAmount());
            if (this.getAskedAmount() == 3) {
                this.setActionFlags(1);
                this.setHappy();
                player.getLevel().addFreshEntity(new ItemEntity(player.getLevel(),player.getX(),player.getY(),player.getZ(), Items.DIAMOND.getDefaultInstance()));
            }
        }
    }

    @Override
    public void die(DamageSource pCause) {
        if (pCause.getEntity() instanceof Player player) {
            if (this.isHappy() && player.getMainHandItem().getItem() instanceof SacrificialTongue) {
                getLevel().addParticle(ModParticleType.SAD_FACE_PARTICLE.get(), this.getX() + 0.01, this.getY() + 1, this.getZ()+ 0.01, 0, 0.1, 0);
                System.out.println("AHH");
            }
        }
        super.die(pCause);
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
