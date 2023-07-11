package com.thefreak.botsmod.mixins.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.API.IHaveSpecialTooltip;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.util.BOTSEvent;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderTooltipEvent;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(Screen.class)
public class ScreenMixin {
    @Shadow(remap = false)
    private final ItemStack tooltipStack = ItemStack.EMPTY;

    @Inject(method = "renderTooltipInternal", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;blitOffset:F", ordinal = 2, shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void renderTooltipInternal(PoseStack matrix, List<ClientTooltipComponent> components, int preX, int preY, CallbackInfo info, RenderTooltipEvent.Pre pre, int width, int height, int postX, int postY) {
        BOTSEvent.renderToolTip(tooltipStack, matrix, width,height,preX,preY,postX,postY);
    }
    @Inject(method = "renderTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/item/ItemStack;II)V", at = @At("HEAD"), cancellable = true)
    private void renderCoin(PoseStack pPoseStack, ItemStack pItemStack, int pMouseX, int pMouseY, CallbackInfo info) {
        if (pItemStack.getItem() instanceof IHaveSpecialTooltip iHaveSpecialTooltip) {
            if (iHaveSpecialTooltip.isCoin(pItemStack)) {
                pPoseStack.pushPose();

                RenderSystem.enableBlend();

                RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
                RenderSystem.setShaderTexture(0, iHaveSpecialTooltip.coinTexture(pItemStack));

                ClassReferences.getClientMC().getTextureManager().getTexture(iHaveSpecialTooltip.coinTexture(pItemStack)).bind();

                int texWidth = GlStateManager._getTexLevelParameter(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_WIDTH);
                int texHeight = GlStateManager._getTexLevelParameter(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_HEIGHT);
                pPoseStack.translate(0, 0, 410.0);

                GuiComponent.blit(pPoseStack, pMouseX  + 10, pMouseY, 0, 0, iHaveSpecialTooltip.coinWidth(), iHaveSpecialTooltip.coinHeight(), texWidth, texHeight);

                RenderSystem.disableBlend();

                pPoseStack.popPose();
                info.cancel();
            }
        }
    }

}
