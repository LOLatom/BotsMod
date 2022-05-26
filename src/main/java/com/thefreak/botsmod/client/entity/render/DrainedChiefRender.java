package com.thefreak.botsmod.client.entity.render;

import com.thefreak.botsmod.client.entity.model.DrainedChiefModel;
import com.thefreak.botsmod.client.entity.model.DrainedModel;
import com.thefreak.botsmod.entities.DrainedChiefEntity;
import com.thefreak.botsmod.entities.DrainedEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class DrainedChiefRender extends GeoEntityRenderer<DrainedChiefEntity> {
    public DrainedChiefRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DrainedChiefModel());
        this.shadowRadius = 1.2F;
    }



    @Override
    protected float getDeathMaxRotation(DrainedChiefEntity entityLivingBaseIn) {
        return 0F;
    }
}
