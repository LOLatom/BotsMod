package com.thefreak.botsmod.tileentity.render;


import com.thefreak.botsmod.tileentity.PostMortalAltarTileEntity;
import com.thefreak.botsmod.tileentity.model.PostMortalAltarModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class PostMortalAltarRender extends GeoBlockRenderer<PostMortalAltarTileEntity> {


    public PostMortalAltarRender(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new PostMortalAltarModel());
    }
}
