package com.thefreak.botsmod.client.gui.tablet;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;

import javax.swing.text.html.StyleSheet;
import java.util.List;

public class TabletScreen extends Screen {
    public ResourceLocation TABLET_LOCATION;
    public static final ResourceLocation BOOK_LOCATION = new ResourceLocation("botsmod:" +"textures/gui/empty_tablet_gui.png");
    public static final ResourceLocation BUTTON_LOCATION = new ResourceLocation("botsmod:" +"textures/gui/tablet_buttons.png");

    protected static final int IMAGE_WIDTH = 84;
    protected static final int IMAGE_HEIGHT = 96;


    public TabletScreen(ResourceLocation texture) {

        super(Component.nullToEmpty("e"));
        this.TABLET_LOCATION = texture;
        //this.addWidget(new TabletCloseButton((this.width/2)+30, (this.height/2) - 50, (button) -> {
        //    this.onClose();
        //}));
    }


    @Override
    protected void init() {
        this.addRenderableWidget(new ImageButton((this.width/2)+30, (this.height/2) - 50, 11, 6,0,0,6, BUTTON_LOCATION, 11, 12, (button) -> {
            this.onClose();
        }));
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
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
