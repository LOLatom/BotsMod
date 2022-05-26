package com.thefreak.botsmod.mixins.client;

import com.thefreak.botsmod.API.Animation.IHandlePoseable;
import com.thefreak.botsmod.init.ItemInit;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public abstract class BipedModelMixin<T extends LivingEntity> extends AgeableListModel<T> implements ArmedModel, HeadedModel {
    @Shadow
    public ModelPart head;
    @Shadow
    public ModelPart hat;
    @Shadow
    public ModelPart body;
    @Shadow
    public ModelPart rightArm;
    @Shadow
    public ModelPart leftArm;
    @Shadow
    public ModelPart rightLeg;
    @Shadow
    public ModelPart leftLeg;
    @Shadow
    public HumanoidModel.ArmPose leftArmPose = HumanoidModel.ArmPose.EMPTY;
    @Shadow
    public HumanoidModel.ArmPose rightArmPose = HumanoidModel.ArmPose.EMPTY;
    @Shadow
    public boolean crouching;
    @Shadow
    public float swimAmount;

    @Inject(method = "poseLeftArm", at = @At("TAIL"))
    private void leftArmpos(T p_241655_1_, CallbackInfo ci) {

        LivingEntity livingEntity = (LivingEntity) p_241655_1_;
        if (livingEntity instanceof Player ) {
            Player playerEntity = (Player) p_241655_1_;
            InteractionHand main = InteractionHand.OFF_HAND;
            InteractionHand off = InteractionHand.MAIN_HAND;
            if (playerEntity.getUsedItemHand() == InteractionHand.MAIN_HAND) {
                main = InteractionHand.MAIN_HAND;
                off = InteractionHand.OFF_HAND;
            }
            if (playerEntity.getUsedItemHand() == InteractionHand.OFF_HAND) {
                main = InteractionHand.OFF_HAND;
                off = InteractionHand.MAIN_HAND;
            }
            ItemStack stack = playerEntity.getItemInHand(main);
            ItemStack stack1 = playerEntity.getItemInHand(off);
            if (stack1.getItem() instanceof IHandlePoseable) {


                ((IHandlePoseable) stack1.getItem()).getLeftArmPoser(off, stack1, livingEntity).accept((HumanoidModel) (Object) this, (AbstractClientPlayer) playerEntity);

            } else if (stack.getItem() instanceof IHandlePoseable) {

                ((IHandlePoseable) stack.getItem()).getLeftArmPoser(main, stack, livingEntity).accept((HumanoidModel) (Object) this, (AbstractClientPlayer) playerEntity);

            }
        }
    }



    @Inject(method = "poseRightArm", at = @At("TAIL"))
    private void rightArmpos(T p_241655_1_, CallbackInfo ci) {


        LivingEntity livingEntity = (LivingEntity) p_241655_1_;
        if (livingEntity instanceof Player ) {
            Player playerEntity = (Player) p_241655_1_;
            InteractionHand main = InteractionHand.OFF_HAND;
            InteractionHand off = InteractionHand.MAIN_HAND;
            if (playerEntity.getUsedItemHand() == InteractionHand.MAIN_HAND) {
                main = InteractionHand.MAIN_HAND;
                off = InteractionHand.OFF_HAND;
            }
            if (playerEntity.getUsedItemHand() == InteractionHand.OFF_HAND) {
                main = InteractionHand.OFF_HAND;
                off = InteractionHand.MAIN_HAND;
            }
            ItemStack stack = playerEntity.getItemInHand(main);
            ItemStack stack1 = playerEntity.getItemInHand(off);
            if (stack1.getItem() instanceof IHandlePoseable) {



                ((IHandlePoseable) stack1.getItem()).getRightArmPoser(off, stack1, livingEntity).accept((HumanoidModel) (Object) this, (AbstractClientPlayer) playerEntity);

            } else if (stack.getItem() instanceof IHandlePoseable) {


                ((IHandlePoseable) stack.getItem()).getRightArmPoser(main, stack, livingEntity).accept((HumanoidModel) (Object) this, (AbstractClientPlayer) playerEntity);

            }
        }
    }

}
