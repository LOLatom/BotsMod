package com.thefreak.botsmod.client.entity.model;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.CaveAngeliaGuppie;
import com.thefreak.botsmod.entities.LadybugEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CaveAngeliaGuppieModel extends AnimatedGeoModel<CaveAngeliaGuppie> {
    @Override
    public ResourceLocation getModelLocation(CaveAngeliaGuppie caveAngeliaGuppie) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/cave_angelia_guppie.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(CaveAngeliaGuppie caveAngeliaGuppie) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/entities/cave_angelia_guppie.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CaveAngeliaGuppie caveAngeliaGuppie) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/cave_angelia_guppie.animation.json");
    }


}
