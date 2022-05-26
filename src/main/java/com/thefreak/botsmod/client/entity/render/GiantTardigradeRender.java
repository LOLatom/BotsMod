package com.thefreak.botsmod.client.entity.render;

import com.thefreak.botsmod.client.entity.model.GiantTardigradeModel;
import com.thefreak.botsmod.entities.GiantTardigradeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class GiantTardigradeRender extends GeoEntityRenderer<GiantTardigradeEntity> {
    public GiantTardigradeRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GiantTardigradeModel());
        this.shadowRadius = 4F;
    }

    @Override
    public ResourceLocation getTextureLocation(GiantTardigradeEntity entity) {
        return null;
    }

    @Override
    protected float getDeathMaxRotation(GiantTardigradeEntity entityLivingBaseIn) {
        return 0F;
    }
}
