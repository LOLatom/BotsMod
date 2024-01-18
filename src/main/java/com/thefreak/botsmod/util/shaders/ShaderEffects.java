package com.thefreak.botsmod.util.shaders;

import com.deltateam.deltalib.API.rendering.shader.PostProcessingUtils;
import com.deltateam.deltalib.accessors.ShaderAccessor;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.client.Rendering.RenderTargets;
import com.thefreak.botsmod.fluids.FluidInit;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FogType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;

@OnlyIn(Dist.CLIENT)
public class ShaderEffects {
    static boolean inCellFluid = false;
    public static void shaderEffectCreate(TickEvent.ClientTickEvent event) {
        /*	if (event.phase == TickEvent.Phase.START) {
			if (!PostProcessingUtils.hasPass(new ResourceLocation("deltalib:blur"))) {
				PostPass shader;
				shader = PostProcessingUtils.addPass(new ResourceLocation("deltalib:glow"), new ResourceLocation("deltalib:blur"));
				((ShaderAccessor) shader).addDepthTarget("VanillaDepth", Minecraft.getInstance().getMainRenderTarget());
				((ShaderAccessor) shader).addDepthTarget("GlowDepth", glowTarget);
				((ShaderAccessor) shader).setSourceBuffer(glowTarget);
				((ShaderAccessor) shader).setTargetBuffer(glowTargetSwap);
				shader = PostProcessingUtils.addPass(new ResourceLocation("deltalib:merge"), new ResourceLocation("deltalib:draw"));
				((ShaderAccessor) shader).addAuxTarget("glow", glowTargetSwap);
				((ShaderAccessor) shader).addDepthTarget("GlowDepth", glowTarget);
				shader = PostProcessingUtils.addPass(new ResourceLocation("deltalib:blit"), new ResourceLocation("minecraft:blit"));
			}
		}*/
        if (event.phase == TickEvent.Phase.START && ClassReferences.getClientMC().getEntityRenderDispatcher().camera != null) {


            boolean a = ClassReferences.getClientMC().getEntityRenderDispatcher().camera.getBlockAtCamera().getBlock() == FluidInit.CELL_FLUID.get();
            a = a && ClassReferences.getClientMC().getEntityRenderDispatcher().camera.getFluidInCamera().equals(FogType.WATER);

            if (!inCellFluid && a) {
                inCellFluid = true;

                if (!PostProcessingUtils.hasPass(new ResourceLocation("botsmod:blur_x"))) {
                    PostPass shader = PostProcessingUtils.addPass(new ResourceLocation("botsmod:blur_x"), new ResourceLocation("minecraft:blur"));
                    shader.getEffect().getUniform("BlurDir").set(1f, 0f);
                    shader.getEffect().getUniform("Radius").set(10f);
                }
                if (!PostProcessingUtils.hasPass(new ResourceLocation("botsmod:blur_y"))) {
                    PostPass  shader = PostProcessingUtils.addPass(new ResourceLocation("botsmod:blur_y"), new ResourceLocation("minecraft:blur"));
                    shader.getEffect().getUniform("BlurDir").set(0f, 1f);
                    shader.getEffect().getUniform("Radius").set(10f);
                }
                if (!PostProcessingUtils.hasPass(new ResourceLocation("botsmod:redview"))) {
                    PostPass shader = PostProcessingUtils.addPass(new ResourceLocation("botsmod:redview"), new ResourceLocation("botsmod","redview"));

                }
                if (!PostProcessingUtils.hasPass(new ResourceLocation("botsmod:blur_blit"))) {
                    PostPass shader = PostProcessingUtils.addPass(new ResourceLocation("botsmod:blur_blit"), new ResourceLocation("minecraft:blit"));

                }
            }
            if (inCellFluid && !a) {
                inCellFluid = false;
                if (PostProcessingUtils.hasPass(new ResourceLocation("botsmod:blur_x"))) {
                    PostProcessingUtils.removePass(new ResourceLocation("botsmod:blur_x"));
                }
                if (PostProcessingUtils.hasPass(new ResourceLocation("botsmod:blur_y"))) {
                    PostProcessingUtils.removePass(new ResourceLocation("botsmod:blur_y"));
                }
                if (PostProcessingUtils.hasPass(new ResourceLocation("botsmod:redview"))) {
                    PostProcessingUtils.removePass(new ResourceLocation("botsmod:redview"));
                }
                if (PostProcessingUtils.hasPass(new ResourceLocation("botsmod:blur_blit"))) {
                    PostProcessingUtils.removePass(new ResourceLocation("botsmod:blur_blit"));
                }
            }

            if (!PostProcessingUtils.hasPass(new ResourceLocation("botsmod:freak_blur_x"))) {
                PostPass shader = PostProcessingUtils.addPass(new ResourceLocation("botsmod:freak_blur_x"), new ResourceLocation("blur"));
                shader.getEffect().getUniform("BlurDir").set(1f, 0f);
                shader.getEffect().getUniform("Radius").set(5f);
                ((ShaderAccessor)shader).setSourceBuffer(RenderTargets.FreakPlayer);
                ((ShaderAccessor)shader).setTargetBuffer(RenderTargets.FreakPlayerSwap);
            }
            if (!PostProcessingUtils.hasPass(new ResourceLocation("botsmod:freak_blur_y"))) {
                PostPass  shader = PostProcessingUtils.addPass(new ResourceLocation("botsmod:freak_blur_y"), new ResourceLocation("blur"));
                shader.getEffect().getUniform("BlurDir").set(0f, 1f);
                shader.getEffect().getUniform("Radius").set(5f);
                ((ShaderAccessor)shader).setSourceBuffer(RenderTargets.FreakPlayerSwap);
                ((ShaderAccessor)shader).setTargetBuffer(RenderTargets.FreakPlayer);
            }
            if (!PostProcessingUtils.hasPass(new ResourceLocation("botsmod:freak_blur_merge"))) {
                PostPass shader = PostProcessingUtils.addPass(new ResourceLocation("botsmod:freak_blur_merge"), new ResourceLocation("botsmod:merge"));
//                ((ShaderAccessor)shader).addDepthTarget("DiffuseDepthSampler", ClassReferences.getClientMC().getMainRenderTarget());
                ((ShaderAccessor)shader).addAuxTarget("FreakBlur", RenderTargets.FreakPlayer);
                ((ShaderAccessor)shader).addDepthTarget("FreakBlurDepth", RenderTargets.FreakPlayerDepth);
//                ((ShaderAccessor)shader).setTargetBuffer(Minecraft.getInstance().getMainRenderTarget());
            }
            if (!PostProcessingUtils.hasPass(new ResourceLocation("botsmod:freak_blur_blit"))) {
                PostPass shader = PostProcessingUtils.addPass(new ResourceLocation("botsmod:freak_blur_blit"), new ResourceLocation("minecraft:blit"));
//                ((ShaderAccessor)shader).setSourceBuffer(RenderTargets.FreakPlayer);
//                ((ShaderAccessor)shader).setTargetBuffer(Minecraft.getInstance().getMainRenderTarget());
            }
        }
    }
}
