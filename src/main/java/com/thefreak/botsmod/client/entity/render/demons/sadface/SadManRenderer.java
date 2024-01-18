package com.thefreak.botsmod.client.entity.render.demons.sadface;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.client.entity.model.demons.sadface.SadManModel;
import com.thefreak.botsmod.entities.demons.SadMan;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SadManRenderer extends GeoEntityRenderer<SadMan> {

    private ResourceLocation TEXTURE_SAD = new ResourceLocation(BotsMod.MOD_ID, "textures/entities/demons/sadface/sad_man.png");

    private ResourceLocation TEXTURE_HAPPY = new ResourceLocation(BotsMod.MOD_ID, "textures/entities/demons/sadface/sad_man_happy.png");


    public SadManRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SadManModel());
    }

    @Override
    public RenderType getRenderType(SadMan animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return animatable.isHappy() ? RenderType.entityCutoutNoCull(TEXTURE_HAPPY) : RenderType.entityCutoutNoCull(TEXTURE_SAD);
    }
}
