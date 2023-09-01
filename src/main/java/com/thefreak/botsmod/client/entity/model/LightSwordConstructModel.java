package com.thefreak.botsmod.client.entity.model;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.misc.LightSwordConstruct;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LightSwordConstructModel extends AnimatedGeoModel<LightSwordConstruct> {
    @Override
    public ResourceLocation getModelLocation(LightSwordConstruct lightSwordConstruct) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/attacks/light_sword_attack.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LightSwordConstruct lightSwordConstruct) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/entities/projectiles/light_sword_attack.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LightSwordConstruct lightSwordConstruct) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/light_sword_attack.animation.json");
    }
}
