package com.thefreak.botsmod.API;

import com.thefreak.botsmod.BotsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.awt.*;

public interface IHaveSpecialTooltip {

    default public Color colorTop() {
        return new Color(255, 255, 255,255);
    }

    default public Color colorBottom() {
        return new Color(162, 162, 162,255);
    }

    default public Color backgroundColorTop() {
        return new Color(255, 255, 255,255);
    }

    default public Color backgroundColorBottom() {
        return new Color(162, 162, 162,255);
    }

    default public boolean hasCustomBackGroundColor() {
        return false;
    }

    default public boolean hasAdvancedCustomToolTip(ItemStack stack) {return false;}

    default public ResourceLocation AdvancedCustomToolTipLocation(ItemStack stack) {
        return new ResourceLocation(BotsMod.MOD_ID,
                "textures/gui/tooltip/" + stack.getItem().getRegistryName().getPath() + ".png");
    }
}
