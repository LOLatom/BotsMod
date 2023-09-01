package com.thefreak.botsmod.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.thefreak.botsmod.client.entity.model.GateKeeperModel;
import com.thefreak.botsmod.client.entity.model.KrasiaModel;
import com.thefreak.botsmod.entities.GateKeeper;
import com.thefreak.botsmod.entities.KrasiaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GateKeeperRender extends GeoEntityRenderer<GateKeeper> {
    public GateKeeperRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GateKeeperModel());
        this.addLayer(new GateKeeperFireLayer(this));
        this.shadowRadius = 1.2F;
    }

    @Override
    public RenderType getRenderType(GateKeeper animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityCutout(new ResourceLocation("botsmod:textures/entities/gate_keeper_texture.png"));    }

    @Override
    protected float getDeathMaxRotation(GateKeeper animatable) {
        return 0;
    }
}
