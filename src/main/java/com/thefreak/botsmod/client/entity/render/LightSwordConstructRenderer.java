package com.thefreak.botsmod.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.client.entity.BotsModLayers;
import com.thefreak.botsmod.client.entity.model.LightSwordAttackModel;
import com.thefreak.botsmod.entities.misc.LightSwordConstruct;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class LightSwordConstructRenderer extends EntityRenderer<LightSwordConstruct> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(BotsMod.MOD_ID, "textures/entities/projectiles/light_sword_attack.png");

    public LightSwordAttackModel model;

    public LightSwordConstructRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
        this.model = new LightSwordAttackModel<>(p_174008_.bakeLayer(BotsModLayers.LIGHT_SWORD_ATTACK));
    }

    @Override
    public void render(LightSwordConstruct pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();
        pMatrixStack.mulPose(Quaternion.fromXYZ(180 * ((float) Math.PI / 180F),0,0));
        pMatrixStack.translate(0,-1.1,0);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.eyes(TEXTURE_LOCATION));
        this.model.renderToBuffer(pMatrixStack,vertexconsumer,pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pMatrixStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(LightSwordConstruct pEntity) {
        return TEXTURE_LOCATION;
    }

}
