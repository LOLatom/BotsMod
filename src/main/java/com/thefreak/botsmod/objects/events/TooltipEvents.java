package com.thefreak.botsmod.objects.events;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import com.thefreak.botsmod.API.IHaveSpecialTooltip;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.util.botsevent.TooltipDisplayEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import java.awt.*;


@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TooltipEvents {

    @SubscribeEvent
    public static void renderPostTooltip(TooltipDisplayEvent event) {
        ItemStack stack = event.getStack();
        if (!(stack.getItem() instanceof IHaveSpecialTooltip)) {
            return;
        }
        IHaveSpecialTooltip stStack = (IHaveSpecialTooltip) stack.getItem();
        PoseStack poseStack = event.getPoseStack();

        int width = event.getWidth();
        int height = event.getHeight();

        int x = event.getPreX();
        int y = event.getPreY();

        ResourceLocation texture = new ResourceLocation(BotsMod.MOD_ID,
                "textures/gui/tooltip/" + stack.getItem().getRegistryName().getPath() + ".png");
        if (stStack.hasAdvancedCustomToolTip(stack)) {
            texture = stStack.AdvancedCustomToolTipLocation(stack);
        }

        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, texture);

        ClassReferences.getClientMC().getTextureManager().getTexture(texture).bind();

        int texWidth = GlStateManager._getTexLevelParameter(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_WIDTH);
        int texHeight = GlStateManager._getTexLevelParameter(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_HEIGHT);

        if (texHeight == 0 || texWidth == 0)
            return;

        poseStack.pushPose();

        RenderSystem.enableBlend();

        poseStack.translate(0, 0, 410.0);
        GuiComponent.blit(poseStack, x - 4, y - 31 + 3, 1, 1 % texHeight, 16, 16, texWidth, texHeight);
        GuiComponent.blit(poseStack, x + width + 5 + 7, y - 31 + 3, texWidth - 16 - 1, 1 % texHeight, 16, 16, texWidth, texHeight);

        GuiComponent.blit(poseStack, x - 4, y + height - 12 , 1, 1 % texHeight + 16, 16, 16, texWidth, texHeight);
        GuiComponent.blit(poseStack, x + width + 5 + 7, y + height - 12 , texWidth - 16 - 1, 1 % texHeight + 16, 16, 16, texWidth, texHeight);

        if (width >= 94) {
            GuiComponent.blit(poseStack, x + (width / 2) - 49 + 8 + 6, y - 31, 16 + 2 * texWidth + 1, 1 % texHeight, 94, 16, texWidth, texHeight);
            GuiComponent.blit(poseStack, x + (width / 2) - 49 + 8 + 6, y + height - 9, 16 + 2 * texWidth + 1, 1 % texHeight + 16, 94, 16, texWidth, texHeight);
        }

        RenderSystem.disableBlend();

        poseStack.popPose();

    }

    @SubscribeEvent
    public static void onTooltipColorEvent(RenderTooltipEvent.Color event) {
        ItemStack stack = event.getItemStack();
        if (!(stack.getItem() instanceof IHaveSpecialTooltip)) {
            return;
        }
        IHaveSpecialTooltip item = (IHaveSpecialTooltip) stack.getItem();
        Color color = new Color(stack.getRarity().color.getColor()).darker();
        int top = item.colorTop().getRGB();
        int bottom = item.colorBottom().darker().darker().getRGB();
        if (item.hasCustomBackGroundColor()) {
            int top2 = item.backgroundColorTop().getRGB();
            int bottom2 = item.backgroundColorBottom().darker().darker().getRGB();

            event.setBackground(top2);
            event.setBackgroundEnd(bottom2);
        }
        event.setBorderStart(top);
        event.setBorderEnd(bottom);

    }


}
