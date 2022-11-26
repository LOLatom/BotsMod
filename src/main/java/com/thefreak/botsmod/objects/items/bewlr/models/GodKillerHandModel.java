package com.thefreak.botsmod.objects.items.bewlr.models;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.KrasiaEntity;
import com.thefreak.botsmod.objects.items.bewlr.GodKillerHandBEWLR;
import com.thefreak.botsmod.objects.items.loreandclueitems.GodKillerHand;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GodKillerHandModel extends AnimatedGeoModel<GodKillerHand> {
    @Override
    public ResourceLocation getModelLocation(GodKillerHand godkiller) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/items/god_killer_hand.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(GodKillerHand godkiller) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/items/special/god_killer_hand.png");

    }

    @Override
    public ResourceLocation getAnimationFileLocation(GodKillerHand godkiller) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/items/god_killer_hand.animation.json");
    }


}
