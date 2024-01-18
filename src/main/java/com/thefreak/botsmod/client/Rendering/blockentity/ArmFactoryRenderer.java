package com.thefreak.botsmod.client.Rendering.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.client.entity.model.blockentity.ArmFactoryModel;
import com.thefreak.botsmod.tileentity.ArmFactoryTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class ArmFactoryRenderer extends GeoBlockRenderer<ArmFactoryTileEntity> {
    public ArmFactoryRenderer(BlockEntityRendererProvider.Context rendererProvider) {
        super(rendererProvider, new ArmFactoryModel());
    }

    @Override
    public void render(ArmFactoryTileEntity tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(tile, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public RenderType getRenderType(ArmFactoryTileEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.armorCutoutNoCull(new ResourceLocation(BotsMod.MOD_ID,"textures/tileentities/arm_factory_texture.png"));
    }
}
