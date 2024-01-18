package com.thefreak.botsmod.client.entity.model.blockentity;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.CaveAngeliaGuppie;
import com.thefreak.botsmod.tileentity.ArmFactoryTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ArmFactoryModel extends AnimatedGeoModel<ArmFactoryTileEntity> {
    @Override
    public ResourceLocation getModelLocation(ArmFactoryTileEntity armFactoryTileEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/arm_factory.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(ArmFactoryTileEntity armFactoryTileEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/tileentities/arm_factory_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ArmFactoryTileEntity armFactoryTileEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/arm_factory.animation.json");
    }


}
