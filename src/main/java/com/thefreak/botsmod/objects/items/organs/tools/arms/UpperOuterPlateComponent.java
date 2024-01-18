package com.thefreak.botsmod.objects.items.organs.tools.arms;

import com.thefreak.botsmod.API.IAmProstheticComponent;
import com.thefreak.botsmod.API.ItemSpecialRendering.IHaveIcon;
import com.thefreak.botsmod.BotsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class UpperOuterPlateComponent extends Item implements IAmProstheticComponent, IHaveIcon {
    public UpperOuterPlateComponent(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public String getRendererID(ItemStack stack, Player player) {
        return null;
    }

    @Override
    public boolean hasIcon() {
        return true;
    }

    @Override
    public ResourceLocation iconTex(ItemStack pStack) {
        return new ResourceLocation(BotsMod.MOD_ID,"textures/icons/upper_icon.png");
    }
}
