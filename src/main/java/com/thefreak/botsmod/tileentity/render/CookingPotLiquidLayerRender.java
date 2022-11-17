package com.thefreak.botsmod.tileentity.render;


import com.thefreak.botsmod.tileentity.CookingPotTileEntity;
import com.thefreak.botsmod.tileentity.model.CookingPotLiquidFirstLayerModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class CookingPotLiquidLayerRender extends GeoBlockRenderer<CookingPotTileEntity> {


    public CookingPotLiquidLayerRender(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new CookingPotLiquidFirstLayerModel());
    }

    
}
