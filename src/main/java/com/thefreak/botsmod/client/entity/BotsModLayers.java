package com.thefreak.botsmod.client.entity;

import com.thefreak.botsmod.BotsMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class BotsModLayers {

    public static final ModelLayerLocation LIGHT_SWORD_ATTACK = register("light_sword_attack");
    public static final ModelLayerLocation MECHANICAL_ARM = register("mechanical_arm");
    public static final ModelLayerLocation REGULAR_PISTON = register("regular_piston");
    public static final ModelLayerLocation UPPER_OUTER_STEEL_PLATE = register("upper_outer_steel_plate");
    public static final ModelLayerLocation LOWER_OUTER_STEEL_PLATE = register("lower_outer_steel_plate");
    public static final ModelLayerLocation MODULE_ATTACHMENT_COMPONENT = register("module_attachment_component");
    public static final ModelLayerLocation LONG_BLADE_MODULE = register("long_blade_module");


    private static ModelLayerLocation register(String loc) {
        return register(loc, "main");
    }

    private static ModelLayerLocation register(String p_171301_, String p_171302_) {
        return new ModelLayerLocation(new ResourceLocation(BotsMod.MOD_ID, p_171301_), p_171302_);
    }
}
