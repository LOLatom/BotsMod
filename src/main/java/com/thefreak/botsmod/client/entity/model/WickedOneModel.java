package com.thefreak.botsmod.client.entity.model;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.DrainedEntity;
import com.thefreak.botsmod.entities.WickedOne;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WickedOneModel extends AnimatedGeoModel<WickedOne> {
    @Override
    public ResourceLocation getModelLocation(WickedOne wickedOne) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/wicked_one.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(WickedOne wickedOne) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/entities/wicked_one/wicked_one.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WickedOne wickedOne) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/wicked_one.animation.json");
    }


}
