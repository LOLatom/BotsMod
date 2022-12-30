package com.thefreak.botsmod.client.entity.render;

import com.thefreak.botsmod.client.entity.model.PuffWormModel;
import com.thefreak.botsmod.entities.PuffWormEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class PuffWormRender extends GeoEntityRenderer<PuffWormEntity> {
    public PuffWormRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PuffWormModel());
        this.shadowRadius = 0.4F;
    }


    @Override
    protected float getDeathMaxRotation(PuffWormEntity entityLivingBaseIn) {
        return 0F;
    }




}
