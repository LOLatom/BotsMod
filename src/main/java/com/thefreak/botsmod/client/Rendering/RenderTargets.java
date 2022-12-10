package com.thefreak.botsmod.client.Rendering;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import net.minecraft.client.Minecraft;

public class RenderTargets {
    public static final RenderTarget BlockTarget = new TextureTarget(1, 1, true, Minecraft.ON_OSX);
    public static final RenderTarget FreakPlayer = new TextureTarget(1,1,true,Minecraft.ON_OSX);
    public static final RenderTarget FreakPlayer2 = new TextureTarget(1,1,true,Minecraft.ON_OSX);


    public static void updateFBOSize(int framebufferWidth, int framebufferHeight) {
        if (framebufferHeight > 0 && framebufferWidth > 0) {
            FreakPlayer.resize(framebufferWidth, framebufferHeight, Minecraft.ON_OSX);
            FreakPlayer2.resize(framebufferWidth, framebufferHeight, Minecraft.ON_OSX);
        }
    }
}
