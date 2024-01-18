package com.thefreak.botsmod.mixins.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.API.IAmDivine;
import com.thefreak.botsmod.client.Rendering.layers.ProstheticLayer;
import com.thefreak.botsmod.client.access.IBotsModAnimatable;
import com.thefreak.botsmod.client.entity.render.HumanoidLayer;
import com.thefreak.botsmod.util.capabilities.PlayerLimbsProvider;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public class HumanoidRendererMixin{
    private static final ResourceLocation DIVINE_FREAK = new ResourceLocation("botsmod", "textures/entities/player/thefreak_diety.png");


    @Inject(method = "<init>" , at = @At("TAIL"))
    public void ConstructorMixin(EntityRendererProvider.Context p_174557_, boolean p_174558_, CallbackInfo ci) {
        ((PlayerRenderer)(Object)this).addLayer(new HumanoidLayer(((PlayerRenderer)(Object)this)));
        ((PlayerRenderer)(Object)this).addLayer(new ProstheticLayer<>((PlayerRenderer)(Object)this, p_174557_.getModelSet()));
    }



    @Inject(method = "getTextureLocation(Lnet/minecraft/client/player/AbstractClientPlayer;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    public void getNewTextureLocation(AbstractClientPlayer pEntity, CallbackInfoReturnable<ResourceLocation> cir) {
        if (pEntity instanceof IAmDivine divine) {
            boolean isme = pEntity.getUUID().toString().equals("6fe814fa9a6a436d80996c390116a67e") || pEntity.getUUID().toString().equals("6fe814fa-9a6a-436d-8099-6c390116a67e");
            if (isme && divine.isDivine()) {
                cir.setReturnValue(DIVINE_FREAK);
                cir.cancel();
            }
        }

    }

    @Inject(at = @At("TAIL"), method = "setModelProperties")
    public void setModelProps(AbstractClientPlayer pClientPlayer, CallbackInfo ci) {
        PlayerModel<AbstractClientPlayer> playermodel = ((PlayerRenderer)(Object) this).getModel();
        pClientPlayer.getCapability(PlayerLimbsProvider.PLAYER_LIMBS).ifPresent(playerLimbsCap -> {
            if (pClientPlayer.getMainArm() == HumanoidArm.RIGHT) {
                playermodel.rightArm.visible = playerLimbsCap.hasMainArm();
                playermodel.rightSleeve.visible = playerLimbsCap.hasMainArm();
            } else if (pClientPlayer.getMainArm() == HumanoidArm.LEFT) {
                playermodel.leftArm.visible = playerLimbsCap.hasMainArm();
                playermodel.leftSleeve.visible = playerLimbsCap.hasMainArm();
            }
        });
    }



    @Inject(at = @At("TAIL"), method = "renderHand")
    public void postRenderHand(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pCombinedLight, AbstractClientPlayer pPlayer, ModelPart pRendererArm, ModelPart pRendererArmwear, CallbackInfo ci) {
        if (pPlayer instanceof IBotsModAnimatable botsModAnimatable) {
            botsModAnimatable.getObject().animator().resetAnimation();
        }
    }
}
