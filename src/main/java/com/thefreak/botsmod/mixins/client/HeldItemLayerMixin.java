package com.thefreak.botsmod.mixins.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.thefreak.botsmod.API.Animation.IHandlePoseable;
import com.thefreak.botsmod.API.IAmProstheticItem;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.objects.items.organs.tools.arms.ArmItem;
import com.thefreak.botsmod.util.capabilities.PlayerLimbsProvider;
import com.thefreak.botsmod.util.capabilities.PlayerProstheticArmProvider;
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

    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/ItemInHandLayer;renderArmWithItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/block/model/ItemTransforms$TransformType;Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
    shift = At.Shift.BEFORE), cancellable = true)
    private void bots_renderMixin(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, CallbackInfo ci) {

        boolean flagx = pLivingEntity.getMainArm() == HumanoidArm.RIGHT;
        ItemStack itemstack = flagx ? pLivingEntity.getOffhandItem() : pLivingEntity.getMainHandItem();
        ItemStack itemstack1 = flagx ? pLivingEntity.getMainHandItem() : pLivingEntity.getOffhandItem();

        if (pLivingEntity instanceof  Player player) {
            player.getCapability(PlayerLimbsProvider.PLAYER_LIMBS).ifPresent(playerLimbsCap -> {
                player.getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM).ifPresent(playerProstheticArmCap -> {

                if (!playerLimbsCap.hasMainArm()) {
                        if (pLivingEntity.getMainArm() == HumanoidArm.LEFT) {
                            this.bots_renderingArm(pLivingEntity, itemstack1, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, pMatrixStack, pBuffer, pPackedLight);
                        } else if (pLivingEntity.getMainArm() == HumanoidArm.RIGHT) {
                            this.bots_renderingArm(pLivingEntity, itemstack, ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, pMatrixStack, pBuffer, pPackedLight);
                        }
                    if (playerProstheticArmCap.getProstheticArm().getItem() instanceof IAmProstheticItem prostheticItem) {
                        if (!prostheticItem.renderItemStack(pLivingEntity.getMainArm() == HumanoidArm.RIGHT ? itemstack1 : itemstack, player)) {
                            pMatrixStack.popPose();
                            ci.cancel();
                        }
                    } else {
                        pMatrixStack.popPose();
                        ci.cancel();
                    }
                }
                });
            });
        }

    }

    @Inject(method = "renderArmWithItem" , at = @At("HEAD"), cancellable = true)
    private void bots_renderingArmWithItem(LivingEntity p_117185_, ItemStack p_117186_, ItemTransforms.TransformType p_117187_, HumanoidArm p_117188_, PoseStack p_117189_, MultiBufferSource p_117190_, int p_117191_, CallbackInfo ci) {
      bots_renderingArm(p_117185_,p_117186_,p_117187_,p_117188_,p_117189_,p_117190_,p_117191_);
       ci.cancel();
    }

    private void bots_renderingArm(LivingEntity p_117185_, ItemStack p_117186_, ItemTransforms.TransformType p_117187_, HumanoidArm p_117188_, PoseStack p_117189_, MultiBufferSource p_117190_, int p_117191_) {
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
    }
}
