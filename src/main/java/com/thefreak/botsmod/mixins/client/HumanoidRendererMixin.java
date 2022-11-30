package com.thefreak.botsmod.mixins.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.client.access.IBotsModAnimatable;
import com.thefreak.botsmod.client.entity.render.HumanoidLayer;
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
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

@Mixin(PlayerRenderer.class)
public class HumanoidRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {


    public HumanoidRendererMixin(EntityRendererProvider.Context p_174289_, PlayerModel<AbstractClientPlayer> p_174290_, float p_174291_) {
        super(p_174289_, p_174290_, p_174291_);
    }


    @Inject(method = "<init>" , at = @At("TAIL"))
    public void ConstructorMixin(EntityRendererProvider.Context p_174557_, boolean p_174558_, CallbackInfo ci) {
        ((HumanoidRendererMixin)(Object) this).addLayer(new HumanoidLayer(((HumanoidRendererMixin)(Object) this)));
    }



    @Override
    public ResourceLocation getTextureLocation(AbstractClientPlayer pEntity) {
        return pEntity.getSkinTextureLocation();
    }
    
    @Inject(at = @At("TAIL"), method = "renderHand")
    public void postRenderHand(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pCombinedLight, AbstractClientPlayer pPlayer, ModelPart pRendererArm, ModelPart pRendererArmwear, CallbackInfo ci) {
        if (pPlayer instanceof IBotsModAnimatable botsModAnimatable) {
            botsModAnimatable.getObject().animator().resetAnimation();
        }
    }
}
