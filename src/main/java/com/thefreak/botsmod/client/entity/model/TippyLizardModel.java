package com.thefreak.botsmod.client.entity.model;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.LadybugEntity;
import com.thefreak.botsmod.entities.TippyLizardEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TippyLizardModel extends AnimatedGeoModel<TippyLizardEntity> {
    @Override
    public ResourceLocation getModelLocation(TippyLizardEntity tippyLizardEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/tippy_lizard_model.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(TippyLizardEntity tippyLizardEntity) {
        if (tippyLizardEntity.getVarient() == "b1") {
            return tippyLizardEntity.isSleepingFunc() ? new ResourceLocation(BotsMod.MOD_ID, "textures/entities/tippy_lizard/blue_tippy_lizard_eyesclosed.png") : new ResourceLocation(BotsMod.MOD_ID, "textures/entities/tippy_lizard/blue_tippy_lizard.png");

        } else {
            if (tippyLizardEntity.getVarient() == "gecko1") {
                return tippyLizardEntity.isSleepingFunc() ? new ResourceLocation(BotsMod.MOD_ID, "textures/entities/tippy_lizard/tippy_leopard_gecko_eyesclosed.png") : new ResourceLocation(BotsMod.MOD_ID, "textures/entities/tippy_lizard/tippy_leopard_gecko.png");
            } else if (tippyLizardEntity.getVarient() == "devil1") {
                return tippyLizardEntity.isSleepingFunc() ? new ResourceLocation(BotsMod.MOD_ID, "textures/entities/tippy_lizard/tippy_devil_blessed_lizard_eyesclosed.png") : new ResourceLocation(BotsMod.MOD_ID, "textures/entities/tippy_lizard/tippy_devil_blessed_lizard.png");
            } else {
                return tippyLizardEntity.isSleepingFunc() ? new ResourceLocation(BotsMod.MOD_ID, "textures/entities/tippy_lizard/blue_tippy_lizard_eyesclosed.png") : new ResourceLocation(BotsMod.MOD_ID, "textures/entities/tippy_lizard/blue_tippy_lizard.png");

            }

            }

    }

    @Override
    public ResourceLocation getAnimationFileLocation(TippyLizardEntity tippyLizardEntity) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/tippy_lizard.animations.json");
    }


}
