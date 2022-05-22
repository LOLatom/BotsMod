package com.thefreak.botsmod.mixins.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.thefreak.botsmod.API.Animation.IHandlePoseable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.vector.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemLayer.class)
public abstract class HeldItemLayerMixin<T extends LivingEntity, M extends EntityModel<T> & IHasArm> extends LayerRenderer<T, M> {


    public HeldItemLayerMixin(IEntityRenderer<T, M> p_i50926_1_) {
        super(p_i50926_1_);
    }

    @Inject(method = "renderArmWithItem" , at = @At("HEAD"), cancellable = true)
    private void renderingArmWithItem(LivingEntity p_229135_1_, ItemStack p_229135_2_, ItemCameraTransforms.TransformType p_229135_3_, HandSide p_229135_4_, MatrixStack p_229135_5_, IRenderTypeBuffer p_229135_6_, int p_229135_7_, CallbackInfo ci) {
        Hand main = Hand.OFF_HAND;
        Hand off = Hand.MAIN_HAND;

       if (!(p_229135_2_.getItem() instanceof IHandlePoseable)) {
           if (!p_229135_2_.isEmpty()) {
               p_229135_5_.pushPose();
               this.getParentModel().translateToHand(p_229135_4_, p_229135_5_);
               p_229135_5_.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
               p_229135_5_.mulPose(Vector3f.YP.rotationDegrees(180.0F));
               boolean flag = p_229135_4_ == HandSide.LEFT;
               p_229135_5_.translate((double) ((float) (flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);
               Minecraft.getInstance().getItemInHandRenderer().renderItem(p_229135_1_, p_229135_2_, p_229135_3_, flag, p_229135_5_, p_229135_6_, p_229135_7_);
               p_229135_5_.popPose();
           }
       } else {
           if ( ((IHandlePoseable) p_229135_2_.getItem()).isItemAnimatedD(p_229135_1_.getUsedItemHand(),p_229135_2_, p_229135_1_)) {
               if(p_229135_1_ instanceof PlayerEntity) {
                   ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity) p_229135_1_;
                   p_229135_5_.pushPose();
                   this.getParentModel().translateToHand(p_229135_4_, p_229135_5_);
                   ((IHandlePoseable) p_229135_2_.getItem()).getItemPoser(p_229135_1_.getUsedItemHand(), p_229135_2_, p_229135_1_).accept(p_229135_5_, clientPlayerEntity);
                   boolean flag = p_229135_4_ == HandSide.LEFT;
                   Minecraft.getInstance().getItemInHandRenderer().renderItem(p_229135_1_, p_229135_2_, p_229135_3_, flag, p_229135_5_, p_229135_6_, p_229135_7_);
                   p_229135_5_.popPose();
               }
           } else {
               if (!p_229135_2_.isEmpty()) {
                   p_229135_5_.pushPose();
                   this.getParentModel().translateToHand(p_229135_4_, p_229135_5_);
                   p_229135_5_.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
                   p_229135_5_.mulPose(Vector3f.YP.rotationDegrees(180.0F));
                   boolean flag = p_229135_4_ == HandSide.LEFT;
                   p_229135_5_.translate((double) ((float) (flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);
                   Minecraft.getInstance().getItemInHandRenderer().renderItem(p_229135_1_, p_229135_2_, p_229135_3_, flag, p_229135_5_, p_229135_6_, p_229135_7_);
                   p_229135_5_.popPose();
               }
           }


       }
       ci.cancel();
    }
}
