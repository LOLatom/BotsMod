package com.thefreak.botsmod.mixins.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.thefreak.botsmod.API.ItemSpecialRendering.IHaveIcon;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.BakedItemModel;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public class RenderIconMixin {
    @Shadow
    @Final
    private TextureManager textureManager;

    @Shadow
    public float blitOffset;

    @Inject(at = @At("TAIL"), method = "renderGuiItem(Lnet/minecraft/world/item/ItemStack;IILnet/minecraft/client/resources/model/BakedModel;)V")
    public void renderIconForInter(ItemStack pStack, int pX, int pY, BakedModel pBakedmodel, CallbackInfo ci) {
        if (pStack.getItem() instanceof IHaveIcon iHaveIcon) {
            if (iHaveIcon.hasIcon()) {
                ResourceLocation customTexture = iHaveIcon.iconTex();
                Minecraft minecraft = Minecraft.getInstance();
                TextureAtlasSprite sprite = minecraft.getModelManager().getAtlas(TextureAtlas.LOCATION_BLOCKS).getSprite(customTexture);


                PoseStack poseStack = new PoseStack();


                RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
                RenderSystem.setShaderTexture(0, customTexture);

                poseStack.pushPose();
                poseStack.translate(11,-3,101 + this.blitOffset);
                RenderSystem.enableBlend();
                ClassReferences.getClientMC().getTextureManager().getTexture(customTexture).bind();

                int texWidth = GlStateManager._getTexLevelParameter(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_WIDTH);
                int texHeight = GlStateManager._getTexLevelParameter(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_HEIGHT);
                GuiComponent.blit(poseStack, pX, pY , 0, 0, 7, 7, texWidth, texHeight);

                RenderSystem.disableBlend();
                poseStack.popPose();

            }
        }
    }

}
