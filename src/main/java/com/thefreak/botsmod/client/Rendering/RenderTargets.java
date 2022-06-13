package com.thefreak.botsmod.client.Rendering;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import net.minecraft.client.Minecraft;

public class RenderTargets {
    public static final RenderTarget BlockTarget = new TextureTarget(1, 1, true, Minecraft.ON_OSX);
}
