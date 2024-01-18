package com.thefreak.botsmod.objects.customobjects;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.math.Quaternion;
import com.thefreak.botsmod.API.IAmProstheticComponent;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.client.Rendering.models.MechanicalArmProstheticModel;
import com.thefreak.botsmod.client.entity.BotsModLayers;
import com.thefreak.botsmod.init.customregistries.ProstheticRegistry;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Vector;

public class MechanicalArmRender extends ProstheticRender{
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(BotsMod.MOD_ID, "textures/prosthetic/mechanical_arm_frame.png");


    public MechanicalArmRender() {

    }

    @Override
    public void renderProsthetic(EntityModelSet modelSet, ItemStack stack, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, Player player, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, EntityModel parentModel) {
        PlayerModel playerModel = (PlayerModel) parentModel;
        pMatrixStack.pushPose();
        MechanicalArmProstheticModel model = new MechanicalArmProstheticModel<>(modelSet.bakeLayer(BotsModLayers.MECHANICAL_ARM));
        model.copyFromArm(playerModel.rightArm);
        model.renderToBuffer(pMatrixStack,pBuffer.getBuffer(RenderType.armorCutoutNoCull(TEXTURE_LOCATION)),pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1F);
        pMatrixStack.popPose();
        CompoundTag nbt = stack.getOrCreateTag();
        ItemStackHandler parts = new ItemStackHandler(6);
        parts.deserializeNBT(nbt.getCompound("parts"));
        for (int i = 0; i < parts.getSlots(); i++) {
            if (parts.getStackInSlot(i).getItem() instanceof IAmProstheticComponent pc) {
                ProstheticRender renderer = ProstheticRegistry.PROSTHETIC_RENDERERS.get(pc.getRendererID(stack,player));
                renderer.renderProsthetic(modelSet, stack, pMatrixStack, pBuffer, pPackedLight, player, pLimbSwing, pLimbSwingAmount, pPartialTicks, pAgeInTicks, pNetHeadYaw, pHeadPitch, parentModel);
            }
        }
     super.renderProsthetic(modelSet, stack, pMatrixStack, pBuffer, pPackedLight, player, pLimbSwing, pLimbSwingAmount, pPartialTicks, pAgeInTicks, pNetHeadYaw, pHeadPitch, parentModel);

    }
}
