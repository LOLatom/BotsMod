package com.thefreak.botsmod.client.entity.model;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.DrainedChiefEntity;
import com.thefreak.botsmod.entities.DrainedEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DrainedChiefModel extends AnimatedGeoModel<DrainedChiefEntity> {
    @Override
    public ResourceLocation getModelLocation(DrainedChiefEntity drainedChiefEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/drained_chief_model.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(DrainedChiefEntity drainedChiefEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/entities/drained_chief_texture.png");

    }

    @Override
    public ResourceLocation getAnimationFileLocation(DrainedChiefEntity drainedChiefEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/drained_chief.animation.json");
    }


}
