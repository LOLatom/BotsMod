package com.thefreak.botsmod.mixins.client;

import com.mojang.blaze3d.platform.Window;
import com.thefreak.botsmod.client.Rendering.RenderTargets;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public class WindowMixin {
    @Shadow
    private int framebufferWidth;

    @Shadow private int framebufferHeight;

    @Inject(at = @At("HEAD"), method = "onFramebufferResize")
    public void preUpdateFBOSize(CallbackInfo ci) {
        RenderTargets.updateFBOSize(framebufferWidth, framebufferHeight);
    }
}
