package com.thefreak.botsmod.entities;


import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BansheeScreamEntity extends LivingEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    public static AnimationBuilder EXPAND = new AnimationBuilder().addAnimation("animation.banshee_scream.expand");

    public BansheeScreamEntity(EntityType<? extends LivingEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    public static AttributeSupplier.Builder setCustomAttributes()
    {

        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1D)
                .add(Attributes.MOVEMENT_SPEED, 0D)
                .add(Attributes.ATTACK_DAMAGE, 0D)
                .add(Attributes.ATTACK_KNOCKBACK, 0D);
    }


    public boolean hurt(DamageSource source, float amount) {
        return false;
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return null;
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot slotIn) {
        return null;
    }

    @Override
    public void setItemSlot(EquipmentSlot slotIn, ItemStack stack) {

    }

    @Override
    public HumanoidArm getMainArm() {
        return null;
    }


    private <E extends BansheeScreamEntity> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        AnimationBuilder anim = EXPAND;
        controller.setAnimation(anim);
        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 5, this::predicate));

    }

    @Override
    public boolean canCollideWith(Entity entity) {
        return false;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
