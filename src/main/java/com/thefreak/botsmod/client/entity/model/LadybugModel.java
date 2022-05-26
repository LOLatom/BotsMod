package com.thefreak.botsmod.client.entity.model;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.LadybugEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LadybugModel extends AnimatedGeoModel<LadybugEntity> {
    @Override
    public ResourceLocation getModelLocation(LadybugEntity ladybugEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/ladybug_model.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(LadybugEntity ladybugEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/entities/ladybug_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LadybugEntity ladybugEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/ladybug.animations.json");
    }


}
