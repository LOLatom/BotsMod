package com.thefreak.botsmod.tileentity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.thefreak.botsmod.tileentity.PostMortalAltarTileEntity;
import com.thefreak.botsmod.tileentity.model.PostMortalAltarModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderTooltipEvent;
import software.bernie.example.block.tile.FertilizerTileEntity;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import javax.annotation.Nullable;

public class PostMortalAltarRender extends GeoBlockRenderer<PostMortalAltarTileEntity> {
    public PostMortalAltarRender(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new PostMortalAltarModel());
    }

    @Override
    public RenderType getRenderType(PostMortalAltarTileEntity animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {

        return RenderType.entityTranslucent(this.getTextureLocation(animatable));
    }


}
