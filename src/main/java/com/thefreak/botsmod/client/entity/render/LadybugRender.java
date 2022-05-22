package com.thefreak.botsmod.client.entity.render;

import com.thefreak.botsmod.client.entity.model.GiantTardigradeModel;
import com.thefreak.botsmod.client.entity.model.LadybugModel;
import com.thefreak.botsmod.entities.GiantTardigradeEntity;
import com.thefreak.botsmod.entities.LadybugEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class LadybugRender extends GeoEntityRenderer<LadybugEntity> {
    public LadybugRender(EntityRendererManager renderManager) {
        super(renderManager, new LadybugModel());
        this.shadowRadius = 0.25F;
    }

    @Override
    protected float getDeathMaxRotation(LadybugEntity entityLivingBaseIn) {
        return 0F;
    }
}
