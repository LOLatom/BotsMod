package com.thefreak.botsmod.tileentity.model;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.tileentity.CookingPotTileEntity;
import com.thefreak.botsmod.tileentity.PostMortalAltarTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CookingPotModel extends AnimatedGeoModel<CookingPotTileEntity> {
    @Override
    public ResourceLocation getModelLocation(CookingPotTileEntity cookingPotTileEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/cooking_pot.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CookingPotTileEntity cookingPotTileEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/tileentities/cooking_pot_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CookingPotTileEntity cookingPotTileEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/cooking_pot.animations.json");
    }
}
