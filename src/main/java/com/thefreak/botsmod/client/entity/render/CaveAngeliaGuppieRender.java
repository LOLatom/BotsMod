package com.thefreak.botsmod.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.thefreak.botsmod.client.entity.model.CaveAngeliaGuppieModel;
import com.thefreak.botsmod.entities.CaveAngeliaGuppie;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class CaveAngeliaGuppieRender extends GeoEntityRenderer<CaveAngeliaGuppie> {


    public CaveAngeliaGuppieRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CaveAngeliaGuppieModel());
        this.addLayer(new CaveAngeliaGuppieEyeLayer(this));
    }

    @Override
    public RenderType getRenderType(CaveAngeliaGuppie animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entitySmoothCutout(new ResourceLocation("botsmod:textures/entities/cave_angelia_guppie.png"));    }

}
