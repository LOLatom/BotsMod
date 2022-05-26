package com.thefreak.botsmod.client.entity.render;

import com.thefreak.botsmod.client.entity.model.DrainedModel;
import com.thefreak.botsmod.client.entity.model.LadybugModel;
import com.thefreak.botsmod.entities.DrainedEntity;
import com.thefreak.botsmod.entities.LadybugEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class DrainedRender extends GeoEntityRenderer<DrainedEntity> {
    public DrainedRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DrainedModel());
        this.addLayer(new DrainedHelmetLayerRender(this));
        this.shadowRadius = 0.5F;
    }



    @Override
    protected float getDeathMaxRotation(DrainedEntity entityLivingBaseIn) {
        return 0F;
    }
}
