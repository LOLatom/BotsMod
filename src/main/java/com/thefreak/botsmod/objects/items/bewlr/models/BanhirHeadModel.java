package com.thefreak.botsmod.objects.items.bewlr.models;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.objects.items.loreandclueitems.BanhirHead;
import com.thefreak.botsmod.objects.items.loreandclueitems.GodKillerHand;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BanhirHeadModel extends AnimatedGeoModel<BanhirHead> {
    @Override
    public ResourceLocation getModelLocation(BanhirHead godkiller) {
        return new ResourceLocation(BotsMod.MOD_ID, "geo/items/banhir_head.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(BanhirHead godkiller) {
        return new ResourceLocation(BotsMod.MOD_ID, "textures/items/special/banhir_head.png");

    }

    @Override
    public ResourceLocation getAnimationFileLocation(BanhirHead godkiller) {
        return new ResourceLocation(BotsMod.MOD_ID, "animations/items/banhir_head.animation.json");
    }
}
