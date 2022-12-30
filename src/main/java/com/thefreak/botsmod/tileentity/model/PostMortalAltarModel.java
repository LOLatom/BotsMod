package com.thefreak.botsmod.tileentity.model;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.tileentity.PostMortalAltarTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PostMortalAltarModel extends AnimatedGeoModel<PostMortalAltarTileEntity> {
    @Override
    public ResourceLocation getModelLocation(PostMortalAltarTileEntity postMortalAltarTileEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/postmortal_altar.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PostMortalAltarTileEntity postMortalAltarTileEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/tileentities/postmortal_altar.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PostMortalAltarTileEntity postMortalAltarTileEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/postmortal_altar.animation.json");
    }
}
