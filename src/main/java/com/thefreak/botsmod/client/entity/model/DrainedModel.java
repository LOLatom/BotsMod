package com.thefreak.botsmod.client.entity.model;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.DrainedEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DrainedModel extends AnimatedGeoModel<DrainedEntity> {
    @Override
    public ResourceLocation getModelLocation(DrainedEntity drainedEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/drained_model.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(DrainedEntity drainedEntity) {
        ResourceLocation TEXTURE;
        if (drainedEntity.hasVase()) {
            TEXTURE = new ResourceLocation(BotsMod.MOD_ID, "textures/entities/drained_texture2.png");
        } else {
            TEXTURE = new ResourceLocation(BotsMod.MOD_ID, "textures/entities/drained_texture1.png");
        }



        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DrainedEntity drainedEntity) {
        return drainedEntity.hasVase() ? new ResourceLocation(BotsMod.MOD_ID, "animations/drainedwithvase.animation.json") : new ResourceLocation(BotsMod.MOD_ID, "animations/drained.animation.json");
    }


}
