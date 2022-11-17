package com.thefreak.botsmod.client.entity.model;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.KrasiaEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KrasiaModel extends AnimatedGeoModel<KrasiaEntity> {
    @Override
    public ResourceLocation getModelLocation(KrasiaEntity krasiaEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/krasia.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(KrasiaEntity krasiaEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/entities/krasia.png");

    }

    @Override
    public ResourceLocation getAnimationFileLocation(KrasiaEntity krasiaEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/krasia.animation.json");
    }


}
