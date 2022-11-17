package com.thefreak.botsmod.client.gui.tablet;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class HelpmeTabletScreen extends Screen {
    public static final ResourceLocation TABLET_LOCATION = new ResourceLocation("botsmod:" +"textures/gui/tablet_screen.png");
    public static final ResourceLocation BOOK_LOCATION = new ResourceLocation("botsmod:" +"textures/gui/empty_tablet_gui.png");

    protected static final int IMAGE_WIDTH = 84;
    protected static final int IMAGE_HEIGHT = 96;


    public HelpmeTabletScreen() {
        super(Component.nullToEmpty("e"));
    }


    @Override
    protected void init() {

    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TABLET_LOCATION);
        this.blit(pPoseStack,  (this.width/2)- 30, (this.height/2) - 50, 0, 0, 96, 96,101,128);

        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }



}
