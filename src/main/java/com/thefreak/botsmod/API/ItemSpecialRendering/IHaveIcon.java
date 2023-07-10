package com.thefreak.botsmod.API.ItemSpecialRendering;

import net.minecraft.resources.ResourceLocation;

public interface IHaveIcon {
    default boolean hasIcon() {
        return false;
    }
    default ResourceLocation iconTex() {
        return new ResourceLocation("botsmod:textures/icons/empty_icon.png");
    }


}
