package com.thefreak.botsmod.mixins.client;

import com.thefreak.botsmod.API.Animation.IHandlePoseable;
import com.thefreak.botsmod.init.ItemInit;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedModel.class)
public abstract class BipedModelMixin<T extends LivingEntity> extends AgeableModel<T> implements IHasArm, IHasHead {
    @Shadow
    public ModelRenderer head;
    @Shadow
    public ModelRenderer hat;
    @Shadow
    public ModelRenderer body;
    @Shadow
    public ModelRenderer rightArm;
    @Shadow
    public ModelRenderer leftArm;
    @Shadow
    public ModelRenderer rightLeg;
    @Shadow
    public ModelRenderer leftLeg;
    @Shadow
    public BipedModel.ArmPose leftArmPose = BipedModel.ArmPose.EMPTY;
    @Shadow
    public BipedModel.ArmPose rightArmPose = BipedModel.ArmPose.EMPTY;
    @Shadow
    public boolean crouching;
    @Shadow
    public float swimAmount;

    @Inject(method = "poseLeftArm", at = @At("TAIL"))
    private void leftArmpos(T p_241655_1_, CallbackInfo ci) {

        LivingEntity livingEntity = (LivingEntity) p_241655_1_;
        if (livingEntity instanceof PlayerEntity ) {
            PlayerEntity playerEntity = (PlayerEntity) p_241655_1_;
            Hand main = Hand.OFF_HAND;
            Hand off = Hand.MAIN_HAND;
            if (playerEntity.getUsedItemHand() == Hand.MAIN_HAND) {
                main = Hand.MAIN_HAND;
                off = Hand.OFF_HAND;
            }
            if (playerEntity.getUsedItemHand() == Hand.OFF_HAND) {
                main = Hand.OFF_HAND;
                off = Hand.MAIN_HAND;
            }
            ItemStack stack = playerEntity.getItemInHand(main);
            ItemStack stack1 = playerEntity.getItemInHand(off);
            if (stack1.getItem() instanceof IHandlePoseable) {


                ((IHandlePoseable) stack1.getItem()).getLeftArmPoser(off, stack1, livingEntity).accept((BipedModel) (Object) this, (AbstractClientPlayerEntity) playerEntity);

            } else if (stack.getItem() instanceof IHandlePoseable) {

                ((IHandlePoseable) stack.getItem()).getLeftArmPoser(main, stack, livingEntity).accept((BipedModel) (Object) this, (AbstractClientPlayerEntity) playerEntity);

            }
        }
    }



    @Inject(method = "poseRightArm", at = @At("TAIL"))
    private void rightArmpos(T p_241655_1_, CallbackInfo ci) {


        LivingEntity livingEntity = (LivingEntity) p_241655_1_;
        if (livingEntity instanceof PlayerEntity ) {
            PlayerEntity playerEntity = (PlayerEntity) p_241655_1_;
            Hand main = Hand.OFF_HAND;
            Hand off = Hand.MAIN_HAND;
            if (playerEntity.getUsedItemHand() == Hand.MAIN_HAND) {
                main = Hand.MAIN_HAND;
                off = Hand.OFF_HAND;
            }
            if (playerEntity.getUsedItemHand() == Hand.OFF_HAND) {
                main = Hand.OFF_HAND;
                off = Hand.MAIN_HAND;
            }
            ItemStack stack = playerEntity.getItemInHand(main);
            ItemStack stack1 = playerEntity.getItemInHand(off);
            if (stack1.getItem() instanceof IHandlePoseable) {



                ((IHandlePoseable) stack1.getItem()).getRightArmPoser(off, stack1, livingEntity).accept((BipedModel) (Object) this, (AbstractClientPlayerEntity) playerEntity);

            } else if (stack.getItem() instanceof IHandlePoseable) {


                ((IHandlePoseable) stack.getItem()).getRightArmPoser(main, stack, livingEntity).accept((BipedModel) (Object) this, (AbstractClientPlayerEntity) playerEntity);

            }
        }
    }

}
