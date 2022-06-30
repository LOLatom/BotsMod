package com.thefreak.botsmod.tileentity.render;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.thefreak.botsmod.tileentity.PostMortalAltarTileEntity;
import com.thefreak.botsmod.tileentity.model.PostMortalAltarModel;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.example.block.tile.BotariumTileEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import javax.annotation.Nullable;

public class PostMortalAltarRender extends GeoBlockRenderer<PostMortalAltarTileEntity> {


    public PostMortalAltarRender(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new PostMortalAltarModel());
    }
}
