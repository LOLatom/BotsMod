package com.thefreak.botsmod.client.entity.render;

import com.thefreak.botsmod.client.entity.model.LightSwordConstructModel;
import com.thefreak.botsmod.entities.misc.LightSwordConstruct;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class LightSwordConstructRender extends GeoEntityRenderer<LightSwordConstruct> {
    public LightSwordConstructRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LightSwordConstructModel());
    }
}
