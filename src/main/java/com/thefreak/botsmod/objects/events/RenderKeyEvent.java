package com.thefreak.botsmod.objects.events;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.API.IDuelMode;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.util.BOTSEvent;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.player.KeyboardInput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import java.awt.event.KeyEvent;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class RenderKeyEvent {

    @SubscribeEvent
    public static void keyRender(RenderGameOverlayEvent event) {
        if (ClassReferences.getPlayer() instanceof IDuelMode duelMode) {
            if (duelMode.isInDuel()) {
                PoseStack poseStack = event.getMatrixStack();

                ResourceLocation texture = new ResourceLocation(BotsMod.MOD_ID,
                        "textures/gui/key/duel_keys.png");

                RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
                RenderSystem.setShaderTexture(0, texture);

                ClassReferences.getClientMC().getTextureManager().getTexture(texture).bind();

                poseStack.pushPose();

                RenderSystem.enableBlend();

                poseStack.translate(0, 0, 410.0);

                int keyPressedF = InputConstants.isKeyDown(ClassReferences.getClientMC().getWindow().getWindow(), InputConstants.KEY_F) ? 0 : 18;
                int keyPressedG = InputConstants.isKeyDown(ClassReferences.getClientMC().getWindow().getWindow(), InputConstants.KEY_G) ? 0 : 18;
                int keyPressedH = InputConstants.isKeyDown(ClassReferences.getClientMC().getWindow().getWindow(), InputConstants.KEY_H) ? 0 : 18;
                GuiComponent.blit(poseStack, event.getWindow().getGuiScaledWidth() /2 - 27, event.getWindow().getGuiScaledHeight() /2 - 5,keyPressedF , 0, 16, 16, 34, 48);
                GuiComponent.blit(poseStack, event.getWindow().getGuiScaledWidth() /2 - 27 + 16, event.getWindow().getGuiScaledHeight() /2 - 5,keyPressedG , 16, 16, 16, 34, 48);
                GuiComponent.blit(poseStack, event.getWindow().getGuiScaledWidth() /2 - 27 + 32, event.getWindow().getGuiScaledHeight() /2 - 5,keyPressedH , 32, 16, 16, 34, 48);

                poseStack.popPose();

            }
        }
    }
}
