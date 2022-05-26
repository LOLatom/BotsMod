package com.thefreak.botsmod.client.entity.model;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.GiantTardigradeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GiantTardigradeModel extends AnimatedGeoModel<GiantTardigradeEntity> {
    @Override
    public ResourceLocation getModelLocation(GiantTardigradeEntity giantTardigradeEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/giant_tardigrade.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GiantTardigradeEntity giantTardigradeEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/entities/giant_tardigrade.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GiantTardigradeEntity giantTardigradeEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/giant_tardigrade.animation.json");
    }


}
