package com.thefreak.botsmod.client.entity;

import com.thefreak.botsmod.BotsMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class BotsModLayers {

    public static final ModelLayerLocation LIGHT_SWORD_ATTACK = register("light_sword_attack");

    private static ModelLayerLocation register(String loc) {
        return register(loc, "main");
    }

    private static ModelLayerLocation register(String p_171301_, String p_171302_) {
        return new ModelLayerLocation(new ResourceLocation(BotsMod.MOD_ID, p_171301_), p_171302_);
    }
}
