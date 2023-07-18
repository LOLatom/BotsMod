package com.thefreak.botsmod.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.thefreak.botsmod.client.entity.model.KrasiaModel;
import com.thefreak.botsmod.entities.KrasiaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class KrasiaRender extends GeoEntityRenderer<KrasiaEntity> {
    public KrasiaRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new KrasiaModel());
        this.shadowRadius = 1.2F;
    }
    @Override
    public RenderType getRenderType(KrasiaEntity animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityCutout(new ResourceLocation("botsmod:textures/entities/krasia.png"));    }


    @Override
    protected float getDeathMaxRotation(KrasiaEntity entityLivingBaseIn) {
        return 0F;
    }
}
