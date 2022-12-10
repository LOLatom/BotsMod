package com.thefreak.botsmod.mixins.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.API.IAmDivine;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.client.Rendering.RenderTargets;
import com.thefreak.botsmod.client.access.IBotsModAnimatable;
import com.thefreak.botsmod.client.entity.render.HumanoidLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

@Mixin(PlayerRenderer.class)
public class HumanoidRendererMixin{
    private static final ResourceLocation DIVINE_FREAK = new ResourceLocation("botsmod", "textures/entities/player/thefreak_diety.png");


    @Inject(method = "<init>" , at = @At("TAIL"))
    public void ConstructorMixin(EntityRendererProvider.Context p_174557_, boolean p_174558_, CallbackInfo ci) {
        ((PlayerRenderer)(Object)this).addLayer(new HumanoidLayer(((PlayerRenderer)(Object)this)));
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
    @Inject(method = "render(Lnet/minecraft/client/player/AbstractClientPlayer;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("HEAD"))
    public void preRender(AbstractClientPlayer pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, CallbackInfo ci) {
        boolean isme = pEntity.getUUID().toString().equals("6fe814fa9a6a436d80996c390116a67e") || pEntity.getUUID().toString().equals("6fe814fa-9a6a-436d-8099-6c390116a67e");
        if (pEntity instanceof IAmDivine divine) {
            if (divine.isDivine() && isme) {
                Minecraft.getInstance().getMainRenderTarget().unbindWrite();
                RenderTargets.FreakPlayer.bindWrite(true);
            }
        }
    }

    @Inject(method = "render(Lnet/minecraft/client/player/AbstractClientPlayer;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("TAIL"))
    public void postRender(AbstractClientPlayer pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, CallbackInfo ci) {
        boolean isme = pEntity.getUUID().toString().equals("6fe814fa9a6a436d80996c390116a67e") || pEntity.getUUID().toString().equals("6fe814fa-9a6a-436d-8099-6c390116a67e");
        if (pEntity instanceof IAmDivine divine) {
            if (divine.isDivine() && isme) {
                RenderTargets.FreakPlayer.unbindWrite();
                Minecraft.getInstance().getMainRenderTarget().bindWrite(true);
            }
        }
    }



    
    @Inject(at = @At("TAIL"), method = "renderHand")
    public void postRenderHand(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pCombinedLight, AbstractClientPlayer pPlayer, ModelPart pRendererArm, ModelPart pRendererArmwear, CallbackInfo ci) {
        if (pPlayer instanceof IBotsModAnimatable botsModAnimatable) {
            botsModAnimatable.getObject().animator().resetAnimation();
        }
    }
}
