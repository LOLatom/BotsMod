package com.thefreak.botsmod.objects.items.organs;

import com.thefreak.botsmod.API.IHaveSpecialTooltip;
import net.minecraft.world.item.Item;

import java.awt.*;

public class PerishedHeartItem extends Item implements IHaveSpecialTooltip {
    public PerishedHeartItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hasCustomBackGroundColor() {
        return true;
    }

    @Override
    public Color backgroundColorTop() {
        return new Color(82, 47, 47, 252);
    }

    @Override
    public Color backgroundColorBottom() {
        return new Color(58, 34, 34, 223);
    }

    @Override
    public Color colorTop() {
        return new Color(87, 63, 51);
    }

    @Override
    public Color colorBottom() {
        return new Color(71, 51, 42);
    }
}
