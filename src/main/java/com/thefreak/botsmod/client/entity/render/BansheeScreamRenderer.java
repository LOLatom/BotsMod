package com.thefreak.botsmod.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.thefreak.botsmod.client.entity.model.BansheeScreamModel;
import com.thefreak.botsmod.entities.BansheeScreamEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class BansheeScreamRenderer extends GeoEntityRenderer<BansheeScreamEntity> {
    public BansheeScreamRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BansheeScreamModel());
        this.shadowRadius = 0F;

    }


    @Override
    public RenderType getRenderType(BansheeScreamEntity animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.eyes(new ResourceLocation("botsmod:textures/entities/banshee_scream.png"));
    }


    @Override
    public ResourceLocation getTextureLocation(BansheeScreamEntity entity) {
        return null;
    }

    @Override
    protected float getDeathMaxRotation(BansheeScreamEntity entityLivingBaseIn) {
        return 0F;
    }
}
