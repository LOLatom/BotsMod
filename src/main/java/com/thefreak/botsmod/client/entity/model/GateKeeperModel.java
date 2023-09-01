package com.thefreak.botsmod.client.entity.model;

import com.thefreak.botsmod.BotsMod;

import com.thefreak.botsmod.entities.GateKeeper;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class GateKeeperModel extends AnimatedGeoModel<GateKeeper> {
    @Override
    public ResourceLocation getModelLocation(GateKeeper object) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/gate_keeper.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GateKeeper object) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/entities/gate_keeper_texture.png");    }

    @Override
    public ResourceLocation getAnimationFileLocation(GateKeeper animatable) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/gate_keeper.animation.json");    }


    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setCustomAnimations(GateKeeper animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone head = this.getAnimationProcessor().getBone("Head");

        EntityModelData extraData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY((extraData.netHeadYaw - 52) * ((float) Math.PI / 180F));
            head.setPivotZ(-extraData.headPitch * ((float) Math.PI / 180F));
        }
    }
}
