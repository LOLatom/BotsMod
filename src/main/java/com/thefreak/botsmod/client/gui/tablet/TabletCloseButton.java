package com.thefreak.botsmod.client.gui.tablet;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.resources.ResourceLocation;


public class TabletCloseButton extends ImageButton {
    public static final ResourceLocation TABLET_LOCATION = new ResourceLocation("botsmod:" +"textures/gui/tablet_buttons.png");

    public TabletCloseButton(int pX, int pY, Button.OnPress press) {
        super(pX, pY, 11, 6, 0, 0, TABLET_LOCATION, press);

                /*DEBUG ("TEXT") */
        System.out.println("Button Exist");

    }

}
