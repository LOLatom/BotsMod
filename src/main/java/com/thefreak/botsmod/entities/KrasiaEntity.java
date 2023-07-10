package com.thefreak.botsmod.entities;

import com.thefreak.botsmod.util.misc.DialoguesHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class KrasiaEntity extends LivingEntity implements IAnimatable {
    public KrasiaEntity(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("misc.lying_idle");
    private final NonNullList<ItemStack> tradeItems = NonNullList.withSize(4, ItemStack.EMPTY);


    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return null;
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot pSlot) {
         switch(pSlot.getType()) {
             case HAND:
                return this.tradeItems.get(pSlot.getIndex());
            case ARMOR:
                return ItemStack.EMPTY;
            default:
                return ItemStack.EMPTY;
        }
    }

    @Override
    public InteractionResult interact(Player pPlayer, InteractionHand pHand) {
       openDialogue(pPlayer,pHand);
        return super.interact(pPlayer, pHand);
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public void openDialogue(Player pPlayer, InteractionHand pHand) {
        if (pPlayer.level.isClientSide()) {
            DialoguesHelper dh = new DialoguesHelper(pPlayer, pHand);
            dh.krasiaFirstDialogue();
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.1F)
                .add(Attributes.ATTACK_DAMAGE, 11.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D);
    }

    private <E extends KrasiaEntity> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        AnimationBuilder anim = IDLE_ANIM;

        controller.setAnimation(anim);
        return PlayState.CONTINUE;
    }

    @Override
    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {

    }

    @Override
    public HumanoidArm getMainArm() {
        return null;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        return false;
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
