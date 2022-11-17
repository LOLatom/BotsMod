package com.thefreak.botsmod.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.entities.DrainedEntity;
import com.thefreak.botsmod.entities.WickedOne;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class WickedOneEyeLayer extends GeoLayerRenderer {
    private static final ResourceLocation LAYER = new ResourceLocation("botsmod", "textures/entities/wicked_one/wicked_one_eyes.png");
    private static final ResourceLocation LAYER_ANGRY = new ResourceLocation("botsmod", "textures/entities/wicked_one/wicked_one_anger_eyes.png");
    private static final ResourceLocation MODEL = new ResourceLocation("botsmod", "geo/wicked_one.geo.json");

    public WickedOneEyeLayer(IGeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    // JOKES ON YOU I WAS ALREADY MAKING IT HAHAH

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, Entity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        RenderType cameo = RenderType.armorCutoutNoCull(LAYER);
        WickedOne drainedEntity = (WickedOne) entityLivingBaseIn;
        matrixStackIn.pushPose();
        matrixStackIn.scale(1.0F, 1.0F, 1.0F);
        matrixStackIn.translate(0.0D, 0.0D, 0.0D);
        if (drainedEntity.isAngered()) {
            cameo = RenderType.eyes(LAYER_ANGRY);
            this.getRenderer().render(this.getEntityModel().getModel(MODEL), entityLivingBaseIn, partialTicks, cameo, matrixStackIn, bufferIn, bufferIn.getBuffer(cameo), packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        } else {
            cameo = RenderType.eyes(LAYER);
            this.getRenderer().render(this.getEntityModel().getModel(MODEL), entityLivingBaseIn, partialTicks, cameo, matrixStackIn, bufferIn, bufferIn.getBuffer(cameo), packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        }
       matrixStackIn.popPose();


    }
}
