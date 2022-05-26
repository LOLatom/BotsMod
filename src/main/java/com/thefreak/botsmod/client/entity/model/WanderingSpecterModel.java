package com.thefreak.botsmod.client.entity.model;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.WanderingSpecterEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WanderingSpecterModel extends AnimatedGeoModel<WanderingSpecterEntity> {
    @Override
    public ResourceLocation getModelLocation(WanderingSpecterEntity wanderingSpecterEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/wandering_specter.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(WanderingSpecterEntity wanderingSpecterEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/entities/wandering_specter_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WanderingSpecterEntity wanderingSpecterEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/wandering_specter.animations.json");
    }


}
