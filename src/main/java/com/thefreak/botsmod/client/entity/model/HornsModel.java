package com.thefreak.botsmod.client.entity.model;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.KrasiaEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HornsModel extends AnimatedGeoModel<KrasiaEntity> {
    @Override
    public ResourceLocation getModelLocation(KrasiaEntity krasiaEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/horns.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(KrasiaEntity krasiaEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/entities/player/thefreakhorns.png");

    }

    @Override
    public ResourceLocation getAnimationFileLocation(KrasiaEntity krasiaEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/horns.animation.json");
    }


}
