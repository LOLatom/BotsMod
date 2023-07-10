package com.thefreak.botsmod.objects.items.magic;

import com.thefreak.botsmod.API.IHaveSpecialTooltip;
import com.thefreak.botsmod.API.ItemSpecialRendering.IHaveIcon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.awt.*;

public class SpellCardItem extends Item implements IHaveIcon, IHaveSpecialTooltip {
    public SpellCardItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hasIcon() {
        return true;
    }

    @Override
    public ResourceLocation iconTex() {
        return new ResourceLocation("botsmod:textures/icons/shield_icon.png");
    }

    @Override
    public boolean hasAdvancedCustomToolTip(ItemStack stack) {
        return true;
    }

    @Override
    public boolean hasCustomBackGroundColor() {
        return true;
    }
    public Color backgroundColorTop() {
        return new Color(35, 30, 27, 211);
    }

    @Override
    public Color backgroundColorBottom() {
        return new Color(25, 16, 16, 212);
    }

    @Override
    public Color colorTop() {
        return new Color(52, 53, 37);
    }

    @Override
    public Color colorBottom() {
        return new Color(52, 53, 37, 169);
    }

}
