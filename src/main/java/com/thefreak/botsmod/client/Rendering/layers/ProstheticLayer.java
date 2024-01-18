package com.thefreak.botsmod.client.Rendering.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.API.IAmProstheticItem;
import com.thefreak.botsmod.init.customregistries.ProstheticRegistry;
import com.thefreak.botsmod.objects.customobjects.ProstheticRender;
import com.thefreak.botsmod.util.capabilities.PlayerProstheticArmProvider;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ProstheticLayer<T extends LivingEntity, M extends EntityModel<T> & ArmedModel> extends RenderLayer {

    EntityModelSet modelSet;

    public ProstheticLayer(RenderLayerParent<T, M> pRenderer, EntityModelSet modelSet) {
        super(pRenderer);
        this.modelSet = modelSet;
    }

    public EntityModelSet getModelSet() {
        return modelSet;
    }

    @Override
    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, Entity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (pLivingEntity instanceof Player player) {
            player.getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM).ifPresent(playerProstheticArmCap -> {
                if (!playerProstheticArmCap.getProstheticArm().isEmpty()) {
                    IAmProstheticItem prostheticItem = (IAmProstheticItem) playerProstheticArmCap.getProstheticArm().getItem();
                    ProstheticRender prostheticRender = ProstheticRegistry.PROSTHETIC_RENDERERS.get(prostheticItem.getRendererID());

                    prostheticRender.renderProsthetic(getModelSet() ,playerProstheticArmCap.getProstheticArm(), pMatrixStack, pBuffer, pPackedLight, player, pLimbSwing, pLimbSwingAmount, pPartialTicks, pAgeInTicks, pNetHeadYaw, pHeadPitch, getParentModel());
                }
            });

        }
    }
}
