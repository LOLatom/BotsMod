package com.thefreak.botsmod.mixins.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ForgeIngameGuiMixin.class)
public class ForgeIngameGuiMixin extends Gui {


    private static final int WHITE = 0xFFFFFF;
    @Shadow
    protected int overlayMessageTime;
    @Final
    @Shadow
    protected Minecraft minecraft;

    @Shadow
    protected boolean animateOverlayMessageColor;

    private Font font = null;

    public ForgeIngameGuiMixin(Minecraft pMinecraft) {
        super(pMinecraft);
    }


    public void renderTextSpecial(int width, int height, float partialTick, PoseStack pStack)
    {
        if (overlayMessageTime > 0)
        {
            minecraft.getProfiler().push("overlayMessage");
            float hue = (float)overlayMessageTime - partialTick;
            int opacity = (int)(hue * 255.0F / 20.0F);
            if (opacity > 255) opacity = 255;

            if (opacity > 8)
            {
                pStack.pushPose();
                pStack.translate(width / 2D, height - 68, 0.0D);
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                int color = (animateOverlayMessageColor ? Mth.hsvToRgb(hue / 50.0F, 0.7F, 0.6F) & WHITE : WHITE);
                drawBackdrop(pStack, font, -4, font.width(overlayMessageString), 16777215 | (opacity << 24));
                font.draw(pStack, overlayMessageString.getVisualOrderText(), -font.width(overlayMessageString) / 2, -4, color | (opacity << 24));
                RenderSystem.disableBlend();
                pStack.popPose();
            }

            minecraft.getProfiler().pop();
        }
    }

}
