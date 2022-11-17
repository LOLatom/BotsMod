package com.thefreak.botsmod.API;

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
}
