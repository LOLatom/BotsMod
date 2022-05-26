package com.thefreak.botsmod.client.entity.model;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.BansheeScreamEntity;
import com.thefreak.botsmod.entities.WanderingSpecterEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BansheeScreamModel extends AnimatedGeoModel<BansheeScreamEntity> {
    @Override
    public ResourceLocation getModelLocation(BansheeScreamEntity bansheeScreamEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/banshee_scream.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(BansheeScreamEntity bansheeScreamEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/entities/banshee_scream.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BansheeScreamEntity bansheeScreamEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/banshee_scream.animation.json");
    }


}
