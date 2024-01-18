package com.thefreak.botsmod.objects.customobjects;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.client.Rendering.models.ModuleAttachmentComponentModel;
import com.thefreak.botsmod.client.Rendering.models.UpperOuterSteelPlateModel;
import com.thefreak.botsmod.client.entity.BotsModLayers;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ModuleAttachmentComponentRender extends ProstheticRender{
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(BotsMod.MOD_ID, "textures/prosthetic/module_attachment_component_texture.png");

    public ModuleAttachmentComponentRender() {

    }
    @Override
    public void renderProsthetic(EntityModelSet modelSet, ItemStack stack, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, Player player, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, EntityModel parentModel) {
        PlayerModel playerModel = (PlayerModel) parentModel;
        pMatrixStack.pushPose();
        ModuleAttachmentComponentModel model = new ModuleAttachmentComponentModel<>(modelSet.bakeLayer(BotsModLayers.MODULE_ATTACHMENT_COMPONENT));
        model.copyFromArm(playerModel.rightArm);
        model.setupAnim(player,pLimbSwing,pLimbSwingAmount,pAgeInTicks,pNetHeadYaw,pHeadPitch);
        model.renderToBuffer(pMatrixStack,pBuffer.getBuffer(RenderType.armorCutoutNoCull(TEXTURE_LOCATION)),pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1F);
        pMatrixStack.popPose();
        super.renderProsthetic(modelSet, stack, pMatrixStack, pBuffer, pPackedLight, player, pLimbSwing, pLimbSwingAmount, pPartialTicks, pAgeInTicks, pNetHeadYaw, pHeadPitch, parentModel);
    }
}
