package com.thefreak.botsmod.mixins.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.thefreak.botsmod.API.Animation.IHandlePoseable;
import com.thefreak.botsmod.ClassReferences;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandLayer.class)
public abstract class HeldItemLayerMixin<T extends LivingEntity, M extends EntityModel<T> & ArmedModel> extends RenderLayer<T, M> {


    public HeldItemLayerMixin(RenderLayerParent<T, M> p_i50926_1_) {
        super(p_i50926_1_);
    }

    @Inject(method = "renderArmWithItem" , at = @At("HEAD"), cancellable = true)
    private void renderingArmWithItem(LivingEntity p_117185_, ItemStack p_117186_, ItemTransforms.TransformType p_117187_, HumanoidArm p_117188_, PoseStack p_117189_, MultiBufferSource p_117190_, int p_117191_, CallbackInfo ci) {
        InteractionHand main = InteractionHand.OFF_HAND;
        InteractionHand off = InteractionHand.MAIN_HAND;

       if (!(p_117186_.getItem() instanceof IHandlePoseable)) {
           if (!p_117186_.isEmpty()) {
               p_117189_.pushPose();
               this.getParentModel().translateToHand(p_117188_, p_117189_);
               p_117189_.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
               p_117189_.mulPose(Vector3f.YP.rotationDegrees(180.0F));
               boolean flag = p_117188_ == HumanoidArm.LEFT;
               p_117189_.translate((double) ((float) (flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);

               ClassReferences.getItemInHandRenderer().renderItem(p_117185_, p_117186_, p_117187_, flag, p_117189_, p_117190_, p_117191_);
               p_117189_.popPose();
           }
       } else {
           if ( ((IHandlePoseable) p_117186_.getItem()).isItemAnimatedD(p_117185_.getUsedItemHand(),p_117186_, p_117185_)) {
               if(p_117185_ instanceof Player) {
                   Player clientPlayerEntity = (Player) p_117185_;
                   p_117189_.pushPose();
                   this.getParentModel().translateToHand(p_117188_, p_117189_);
                   ((IHandlePoseable) p_117186_.getItem()).getItemPoser(p_117185_.getUsedItemHand(), p_117186_, p_117185_).accept(p_117189_, clientPlayerEntity);
                   boolean flag = p_117188_ == HumanoidArm.LEFT;
                   ClassReferences.getItemInHandRenderer().renderItem(p_117185_, p_117186_, p_117187_, flag, p_117189_, p_117190_, p_117191_);
                   p_117189_.popPose();
               }
           } else {
               if (!p_117186_.isEmpty()) {
                   p_117189_.pushPose();
                   this.getParentModel().translateToHand(p_117188_, p_117189_);
                   p_117189_.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
                   p_117189_.mulPose(Vector3f.YP.rotationDegrees(180.0F));
                   boolean flag = p_117188_ == HumanoidArm.LEFT;
                   p_117189_.translate((double) ((float) (flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);
                   ClassReferences.getItemInHandRenderer().renderItem(p_117185_, p_117186_, p_117187_, flag, p_117189_, p_117190_, p_117191_);
                   p_117189_.popPose();
               }
           }


       }
       ci.cancel();
    }
}
