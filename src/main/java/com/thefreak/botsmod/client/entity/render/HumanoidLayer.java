package com.thefreak.botsmod.client.entity.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.thefreak.botsmod.API.IAmDivine;
import com.thefreak.botsmod.client.entity.model.HornsModel;
import com.thefreak.botsmod.entities.WickedOne;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.exception.GeckoLibException;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import software.bernie.geckolib3.resource.GeckoLibCache;

public class HumanoidLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> implements IGeoRenderer {
   private static final ResourceLocation LAYER = new ResourceLocation("botsmod", "textures/entities/player/thefreakhorns.png");
   private static final ResourceLocation EYE_LAYER = new ResourceLocation("botsmod", "textures/entities/player/thefreakeyes.png");


    private static final ResourceLocation MODEL = new ResourceLocation("botsmod", "geo/horns.geo.json");
    private GeckoLibCache GeckoLib = GeckoLibCache.getInstance();

    RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> pPlayerRender;
    public HumanoidLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> pRenderer) {
        super(pRenderer);
this.pPlayerRender = pRenderer;
    }


    @Override
    public MultiBufferSource getCurrentRTB() {
        return null;
    }

    @Override
    public GeoModelProvider getGeoModelProvider() {
        return null;
    }

    @Override
    public ResourceLocation getTextureLocation(Object instance) {
        return MODEL;
    }

    @Override
    public void render(PoseStack stack, MultiBufferSource pBuffer, int pPackedLight, AbstractClientPlayer pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        Player player = (Player) pLivingEntity;
        boolean isme = pLivingEntity.getUUID().toString().equals("6fe814fa9a6a436d80996c390116a67e") || pLivingEntity.getUUID().toString().equals("6fe814fa-9a6a-436d-8099-6c390116a67e");
       if (player instanceof IAmDivine divine) {
           if (isme && divine.isDivine()) {
               GeoModel model = GeckoLib.getGeoModels().get(MODEL);
               stack.pushPose();
               RenderType renderType = RenderType.armorCutoutNoCull(LAYER);
               float f = 0.27F;
               stack.translate(0.0D, pLivingEntity.isCrouching() ? f : 0, 0.0D);
               stack.mulPose(Vector3f.YP.rotationDegrees(180.0F + Mth.lerp(pPartialTicks, pLivingEntity.yHeadRotO, pLivingEntity.getYHeadRot()) - Mth.lerp(pPartialTicks, pLivingEntity.yBodyRotO, pLivingEntity.yBodyRot)));
               stack.mulPose(Vector3f.XP.rotationDegrees(-Mth.lerp(pPartialTicks, pLivingEntity.xRotO, pLivingEntity.getXRot())));
               stack.scale(1F, -1F, -1F);

               render(model, pLivingEntity, pPartialTicks, renderType, stack, pBuffer, pBuffer.getBuffer(renderType), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
               renderType = RenderType.eyes(EYE_LAYER);
               render(model, pLivingEntity, pPartialTicks, renderType, stack, pBuffer, pBuffer.getBuffer(renderType), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

               stack.popPose();
           }
       }

    }
}
