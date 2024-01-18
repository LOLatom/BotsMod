package com.thefreak.botsmod.client.entity.render.demons.sadface;

import com.deltateam.deltalib.accessors.MinecraftAccessor;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.entities.demons.SadFace;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.opengl.GL11;

public class SadFaceRenderer extends EntityRenderer<SadFace> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BotsMod.MOD_ID, "textures/entities/demons/sadface/sad_face_killermode.png");

    private static final ResourceLocation TEXTURE_SAD = new ResourceLocation(BotsMod.MOD_ID, "textures/entities/demons/sadface/sad_face_killermode_sad.png");

    private static final ResourceLocation TEXTURE_SAD_GLOW = new ResourceLocation(BotsMod.MOD_ID, "textures/entities/demons/sadface/sad_face_killermode_sad_glow.png");

    private static final ResourceLocation TEXTURE_PURSUIT = new ResourceLocation(BotsMod.MOD_ID, "textures/entities/demons/sadface/sad_face_killermode_chaseface.png");

    public SadFaceRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }

    @Override
    public void render(SadFace pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        RenderType renderType = RenderType.entityCutoutNoCull(TEXTURE);
        RenderType renderTypeGlow = RenderType.eyes(TEXTURE_SAD_GLOW);
        if (pEntity.getMode() == 1) {
            renderType = RenderType.entityCutoutNoCull(TEXTURE_SAD);
            renderTypeGlow = RenderType.eyes(TEXTURE_SAD_GLOW);
        }
        if (pEntity.getMode() == 2) {
            renderType = RenderType.entityCutoutNoCull(TEXTURE_PURSUIT);
            renderTypeGlow = RenderType.eyes(TEXTURE_SAD_GLOW);
        }
        pMatrixStack.pushPose();
        MinecraftAccessor minecraftAccessor = (MinecraftAccessor) ClassReferences.getClientMC();
        float f = 0;
        float f1 = 1;
        float f2 = 0;
        float f3 = 1;
        /*int j = (int)((Mth.sin(f8 + 0.0F) + 1.0F) * 0.5F * 255.0F);
        int k = 255;
        int l = (int)((Mth.sin(f8 + 4.1887903F) + 1.0F) * 0.1F * 255.0F);*/
        pMatrixStack.translate(0.0D, (double)0.4F, 0.0D);
        if (pEntity.getMode() == 1) {
            pMatrixStack.translate(
                    0.05F * Math.sin(pEntity.tickCount + pPartialTicks * 2.5),
                    (double)0.05F * Math.cos(pEntity.tickCount + pPartialTicks * 2.5),
                    0.05F * -Math.sin(pEntity.tickCount + pPartialTicks * 2.5));
        }
        pMatrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
        float f9 = 0.3F;
        pMatrixStack.scale(3.2F, 3.2F, 3.2F);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(renderType);
        PoseStack.Pose posestack$pose = pMatrixStack.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        vertex(vertexconsumer, matrix4f, matrix3f, -0.5F, -0.25F, 255, 255, 255, f, f3, pPackedLight);
        vertex(vertexconsumer, matrix4f, matrix3f, 0.5F, -0.25F, 255, 255, 255, f1, f3, pPackedLight);
        vertex(vertexconsumer, matrix4f, matrix3f, 0.5F, 0.75F, 255, 255, 255, f1, f2, pPackedLight);
        vertex(vertexconsumer, matrix4f, matrix3f, -0.5F, 0.75F, 255, 255, 255, f, f2, pPackedLight);
        if (pEntity.getMode() == 1) {
            VertexConsumer vertexconsumer2 = pBuffer.getBuffer(renderTypeGlow);
            vertex(vertexconsumer2, matrix4f, matrix3f, -0.5F, -0.25F, 255, 255, 255, f, f3, pPackedLight);
            vertex(vertexconsumer2, matrix4f, matrix3f, 0.5F, -0.25F, 255, 255, 255, f1, f3, pPackedLight);
            vertex(vertexconsumer2, matrix4f, matrix3f, 0.5F, 0.75F, 255, 255, 255, f1, f2, pPackedLight);
            vertex(vertexconsumer2, matrix4f, matrix3f, -0.5F, 0.75F, 255, 255, 255, f, f2, pPackedLight);
        }
        pMatrixStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    private static void vertex(VertexConsumer pBuffer, Matrix4f pMatrix, Matrix3f pMatrixNormal, float pX, float pY, int pRed, int pGreen, int pBlue, float pTexU, float pTexV, int pPackedLight) {
        pBuffer.vertex(pMatrix, pX, pY, 0.0F).color(pRed, pGreen, pBlue, 128).uv(pTexU, pTexV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(pPackedLight).normal(pMatrixNormal, 0.0F, 1.0F, 0.0F).endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(SadFace pEntity) {
        return TEXTURE;
    }
}
