package com.thefreak.botsmod.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.thefreak.botsmod.client.entity.model.WickedOneModel;
import com.thefreak.botsmod.entities.WickedOne;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class WickedOneRender extends GeoEntityRenderer<WickedOne> {
    public WickedOneRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WickedOneModel());
        this.addLayer(new WickedOneEyeLayer(this));
        this.shadowRadius = 0.5F;
    }


    @Override
    public RenderType getRenderType(WickedOne animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return animatable.isAngered() == true ? RenderType.entitySmoothCutout(new ResourceLocation("botsmod:textures/entities/wicked_one/wicked_one_anger.png")) : RenderType.entitySmoothCutout(new ResourceLocation("botsmod:textures/entities/wicked_one/wicked_one.png"));
    }

    @Override
    protected float getDeathMaxRotation(WickedOne entityLivingBaseIn) {
        return 0F;
    }
}
