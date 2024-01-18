package com.thefreak.botsmod.client.entity.model.demons.sadface;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.demons.SadMan;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;

public class SadManModel extends AnimatedGeoModel<SadMan> {
    @Override
    public ResourceLocation getModelLocation(SadMan object) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/demons/sadface/sad_man.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SadMan object) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/entities/demons/sadface/sad_man.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SadMan animatable) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/demons/sadface/sad_man.animation.json");
    }
}
