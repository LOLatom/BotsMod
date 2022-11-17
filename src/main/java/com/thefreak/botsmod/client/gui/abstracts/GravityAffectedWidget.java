package com.thefreak.botsmod.client.gui.abstracts;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.ClassReferences;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

public class GravityAffectedWidget extends AbstractWidget {

    public boolean isFrozen;
    public int TickPassed;
    private float velocity;
    private boolean simulate;

    public GravityAffectedWidget(int pX, int pY, int pWidth, int pHeight, Component pMessage) {
        super(pX, pY, pWidth, pHeight, pMessage);
        this.isFrozen = true;
        this.TickPassed = 0;
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        if (this.visible) {
            this.isHovered = pMouseX >= this.x && pMouseY >= this.y && pMouseX < this.x + this.width && pMouseY < this.y + this.height;
            this.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
        }
    }

    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        Minecraft minecraft = ClassReferences.getClientMC();
        Font font = minecraft.font;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, WIDGETS_LOCATION);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        int i = this.getYImage(this.isHoveredOrFocused());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        this.blit(pPoseStack, this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
        this.blit(pPoseStack, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
        this.renderBg(pPoseStack, minecraft, pMouseX, pMouseY);
        int j = getFGColor();
        drawCenteredString(pPoseStack, font, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | Mth.ceil(this.alpha * 255.0F) << 24);
        pPoseStack.popPose();

        pPoseStack.pushPose();
    }

    public void updateWidgetState() {
        if (this.isFrozen == true) return;

        if (this.simulate == true) {
            this.TickPassed++;
        }
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void startSimulate() {
    this.simulate = true;
    }

    public void stopSimulate() {
        this.simulate = false;
        this.TickPassed = 0;
    }



    public float lerp(float pos1, float pos2, float pDelta) {
        float f = 1.0F - pDelta;
        return (pos1 * f + pos2 * pDelta);
    }

    public float getVelocity(float pos1, float velocityAdded, int timePassed) {
        return pos1+(velocityAdded * timePassed);
    }
}
