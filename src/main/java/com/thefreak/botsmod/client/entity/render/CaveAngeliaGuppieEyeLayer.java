package com.thefreak.botsmod.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.entities.CaveAngeliaGuppie;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class CaveAngeliaGuppieEyeLayer extends GeoLayerRenderer {
    private static final ResourceLocation LAYER = new ResourceLocation("botsmod", "textures/entities/cave_angelia_guppie_glow.png");
    private static final ResourceLocation MODEL = new ResourceLocation("botsmod", "geo/cave_angelia_guppie.geo.json");

    public CaveAngeliaGuppieEyeLayer(IGeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }
    
    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, Entity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        RenderType cameo = RenderType.armorCutoutNoCull(LAYER);
        CaveAngeliaGuppie drainedEntity = (CaveAngeliaGuppie) entityLivingBaseIn;
        matrixStackIn.pushPose();
        matrixStackIn.scale(1.0F, 1.0F, 1.0F);
        matrixStackIn.translate(0.0D, 0.0D, 0.0D);
        cameo = RenderType.eyes(LAYER);
        this.getRenderer().render(this.getEntityModel().getModel(MODEL), entityLivingBaseIn, partialTicks, cameo, matrixStackIn, bufferIn, bufferIn.getBuffer(cameo), packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

       matrixStackIn.popPose();
        /*render(matrixStackIn,bufferIn,packedLightIn,entityLivingBaseIn,limbSwing,limbSwingAmount,partialTicks,ageInTicks,netHeadYaw,headPitch);
        BotsMod.glowTarget.bindRead();
        render(matrixStackIn,bufferIn,packedLightIn,entityLivingBaseIn,limbSwing,limbSwingAmount,partialTicks,ageInTicks,netHeadYaw,headPitch);
        BotsMod.glowTarget.unbindRead();
        Minecraft.getInstance().getMainRenderTarget().bindWrite(true);
        */

    }
}
