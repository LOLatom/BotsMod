package com.thefreak.botsmod.API.ItemSpecialRendering;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public interface IHaveIcon {
    default boolean hasIcon() {
        return false;
    }
    default ResourceLocation iconTex(ItemStack pStack) {
        return new ResourceLocation("botsmod:textures/icons/empty_icon.png");
    }


}
