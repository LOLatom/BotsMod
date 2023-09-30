package com.thefreak.botsmod.mixins.client.prosthetic;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.BotsMod;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.annotation.Target;

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin extends Screen {
    private ResourceLocation PROSTHETIC_ARM_GUI_TEXTURE = new ResourceLocation(BotsMod.MOD_ID, "textures/gui/prosthetic/prosthetic_arm_gui.png");



    protected InventoryScreenMixin(Component pTitle) {
        super(pTitle);
    }

    @Inject(method = "renderBg", at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/screens/inventory/InventoryScreen;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V", shift = At.Shift.AFTER))
    public void rendermixin(PoseStack pPoseStack, float pPartialTick, int pX, int pY, CallbackInfo ci) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, PROSTHETIC_ARM_GUI_TEXTURE);
        GuiComponent.blit(pPoseStack,this.width/2 - 61 - 62 + 177,this.height/2 - 101 + 61,0,0,44,41,44,41);
    }
}
