package com.thefreak.botsmod.client.entity.model;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.PuffWormEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PuffWormModel extends AnimatedGeoModel<PuffWormEntity> {
    @Override
    public ResourceLocation getModelLocation(PuffWormEntity puffWormEntity) {
        return puffWormEntity.getPuffState() ? new ResourceLocation(BotsMod.MOD_ID, "geo/puffed_puff_worm.geo.json") : new ResourceLocation(BotsMod.MOD_ID, "geo/puff_worm.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(PuffWormEntity puffWormEntity) {
            return puffWormEntity.getPuffState() ? new ResourceLocation(BotsMod.MOD_ID, "textures/entities/puff_worm/puffed_puff_worm_texture.png") : new ResourceLocation(BotsMod.MOD_ID, "textures/entities/puff_worm/puff_worm_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PuffWormEntity puffWormEntity) {
        return puffWormEntity.getPuffState() ? new ResourceLocation(BotsMod.MOD_ID, "animations/puffed_puff_worm.animation.json") : new ResourceLocation(BotsMod.MOD_ID, "animations/puff_worm.animation.json");
    }


}
