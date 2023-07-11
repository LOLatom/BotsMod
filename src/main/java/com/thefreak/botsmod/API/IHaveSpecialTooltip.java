package com.thefreak.botsmod.API;

import com.thefreak.botsmod.BotsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.awt.*;

public interface IHaveSpecialTooltip {

    default Color colorTop() {
        return new Color(255, 255, 255,255);
    }

    default Color colorBottom() {
        return new Color(162, 162, 162,255);
    }

    default Color backgroundColorTop() {
        return new Color(255, 255, 255,255);
    }

    default Color backgroundColorBottom() {
        return new Color(162, 162, 162,255);
    }

    default boolean hasCustomBackGroundColor() {
        return false;
    }

    default boolean hasAdvancedCustomToolTip(ItemStack stack) {return false;}

    default boolean isCoin(ItemStack stack) {return false;}
    default ResourceLocation AdvancedCustomToolTipLocation(ItemStack stack) {
        return new ResourceLocation(BotsMod.MOD_ID,
                "textures/gui/tooltip/" + stack.getItem().getRegistryName().getPath() + ".png");
    }
    default ResourceLocation coinTexture(ItemStack stack) {
        return new ResourceLocation(BotsMod.MOD_ID,
                "textures/gui/coins/" + stack.getItem().getRegistryName().getPath() + ".png");
    }
    default int coinWidth() {
        return 32;
    }
    default int coinHeight() {
        return 32;
    }
}
