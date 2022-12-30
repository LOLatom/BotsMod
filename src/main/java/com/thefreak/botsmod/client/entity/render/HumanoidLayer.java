package com.thefreak.botsmod.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.thefreak.botsmod.API.IAmDivine;
import com.thefreak.botsmod.API.IPlayerRenderables;
import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import com.thefreak.botsmod.objects.items.ItemType.AmuletItem;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import software.bernie.geckolib3.resource.GeckoLibCache;

public class HumanoidLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> implements IGeoRenderer {
   private static final ResourceLocation LAYER = new ResourceLocation("botsmod", "textures/entities/player/thefreakhorns.png");
   private static final ResourceLocation EYE_LAYER = new ResourceLocation("botsmod", "textures/entities/player/thefreakeyes.png");
    private static final ResourceLocation AMULET_ATTACHEMENT_TEXTURE = new ResourceLocation("botsmod", "textures/entities/player/body_attachment.png");
    private static final ResourceLocation ENDER_AMULET_TEXTURE = new ResourceLocation("botsmod", "textures/entities/player/ender_bestial_amulet_texture.png");
    private static final ResourceLocation ENDER_AMULET_GLOW = new ResourceLocation("botsmod", "textures/entities/player/ender_bestial_amulet_texture_glow.png");

    private static final ResourceLocation CAVE_AMULET_TEXTURE = new ResourceLocation("botsmod", "textures/entities/player/cave_bestial_amulet_texture.png");
    private static final ResourceLocation CAVE_AMULET_GLOW = new ResourceLocation("botsmod", "textures/entities/player/cave_bestial_amulet_texture_glow.png");

    private static final ResourceLocation GROUND_AMULET_TEXTURE = new ResourceLocation("botsmod", "textures/entities/player/ground_bestial_amulet_texture.png");
    private static final ResourceLocation GROUND_AMULET_GLOW = new ResourceLocation("botsmod", "textures/entities/player/ground_bestial_amulet_texture_glow.png");

    private static final ResourceLocation MODEL = new ResourceLocation("botsmod", "geo/horns.geo.json");
    private static final ResourceLocation AMULET_ATTACHEMENT_MODEL = new ResourceLocation("botsmod", "geo/player/body_attachment.geo.json");
    private static final ResourceLocation AMULET_MODEL = new ResourceLocation("botsmod", "geo/player/amulet.geo.json");
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

    public void renderAmulets(PoseStack stack, MultiBufferSource pBuffer, int pPackedLight, AbstractClientPlayer pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        Player player = (Player) pLivingEntity;
        IPlayerRenderables playerRenderables = (IPlayerRenderables) player;
        Inventory pInv = player.getInventory();
        boolean canRenderAttachement = false;
        boolean enderb = false;
        boolean caveb = false;
        boolean groundb = false;
        boolean ie = playerRenderables.enderAmulet();
        boolean ic = playerRenderables.caveAmulet();
        boolean ig = playerRenderables.groundAmulet();

        GeoModel model = GeckoLib.getGeoModels().get(AMULET_ATTACHEMENT_MODEL);
        RenderType renderType = RenderType.armorCutoutNoCull(AMULET_ATTACHEMENT_TEXTURE);
        GeoModel enderbmodel = GeckoLib.getGeoModels().get(AMULET_MODEL);
        RenderType enderbrt = RenderType.armorCutoutNoCull(ENDER_AMULET_TEXTURE);
        RenderType cavert = RenderType.armorCutoutNoCull(CAVE_AMULET_TEXTURE);
        RenderType groundrt = RenderType.armorCutoutNoCull(GROUND_AMULET_TEXTURE);
        for (int i = 0; i < pInv.getContainerSize(); i++) {
            if (pInv.getItem(i).getItem() instanceof AmuletItem) {
                canRenderAttachement = true;
            }
            if (pInv.getItem(i).getItem() == ItemInitNew.ENDER_BESTIAL_AMULET.get()) {
                enderb = true;
            }
            if (pInv.getItem(i).getItem() == ItemInitNew.CAVE_BESTIAL_AMULET.get()) {
                caveb = true;
            }
            if (pInv.getItem(i).getItem() == ItemInitNew.GROUND_BESTIAL_AMULET.get()) {
                groundb = true;
            }
        }

        if (canRenderAttachement) {
            stack.pushPose();
            stack.translate(0,-0.77,0);
            render(model, pLivingEntity, pPartialTicks, renderType, stack, pBuffer, pBuffer.getBuffer(renderType), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            stack.translate(0,0.77,0);
            stack.popPose();
        }
        double d0 = Mth.lerp((double)pPartialTicks, pLivingEntity.xCloakO, pLivingEntity.xCloak) - Mth.lerp((double)pPartialTicks, pLivingEntity.xo, pLivingEntity.getX());
        double d1 = Mth.lerp((double)pPartialTicks, pLivingEntity.yCloakO, pLivingEntity.yCloak) - Mth.lerp((double)pPartialTicks, pLivingEntity.yo, pLivingEntity.getY());
        double d2 = Mth.lerp((double)pPartialTicks, pLivingEntity.zCloakO, pLivingEntity.zCloak) - Mth.lerp((double)pPartialTicks, pLivingEntity.zo, pLivingEntity.getZ());
        float f = pLivingEntity.yBodyRotO + (pLivingEntity.yBodyRot - pLivingEntity.yBodyRotO);
        double d3 = (double)Mth.sin(f * ((float)Math.PI / 180F));
        double d4 = (double)(-Mth.cos(f * ((float)Math.PI / 180F)));
        float f1 = (float)-(d1 * 10.0F);
        f1 = Mth.clamp(f1, -160.0F, 6.0F);
        float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
        f2 = Mth.clamp(f2, -150.0F, 0.0F);
        float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;
        f3 = Mth.clamp(f3, -20.0F, 20.0F);
        if (enderb ) {
            stack.pushPose();
            stack.translate(-0.15,0.57,-0.17);
            stack.mulPose(Vector3f.XP.rotationDegrees(6.0F + f2 / 2.0F + f1));
            stack.mulPose(Vector3f.ZP.rotationDegrees(f3 / 2.0F));
            stack.mulPose(Vector3f.YP.rotationDegrees(180.0F - f3 / 2.0F));
            render(enderbmodel, pLivingEntity, pPartialTicks, enderbrt, stack, pBuffer, pBuffer.getBuffer(enderbrt), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            enderbrt = RenderType.eyes(ENDER_AMULET_GLOW);
            render(enderbmodel, pLivingEntity, pPartialTicks, enderbrt, stack, pBuffer, pBuffer.getBuffer(enderbrt), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            stack.translate(0,-0.77,0);
            stack.popPose();
        }
        if (caveb) {
            stack.pushPose();
            stack.translate(0.05,0.3,-0.17);
            stack.mulPose(Vector3f.XP.rotationDegrees(6.0F + f2 / 2.0F + f1));
            stack.mulPose(Vector3f.ZP.rotationDegrees(f3 / 2.0F));
            stack.mulPose(Vector3f.YP.rotationDegrees(180.0F - f3 / 2.0F));
            render(enderbmodel, pLivingEntity, pPartialTicks, cavert, stack, pBuffer, pBuffer.getBuffer(cavert), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            cavert = RenderType.eyes(CAVE_AMULET_GLOW);
            render(enderbmodel, pLivingEntity, pPartialTicks, cavert, stack, pBuffer, pBuffer.getBuffer(cavert), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            stack.translate(0,-0.77,0);
            stack.popPose();
        }
        if (groundb) {
            stack.pushPose();
            stack.translate(0.2,0.1,-0.17);
            stack.mulPose(Vector3f.XP.rotationDegrees((6.0F + f2 / 2.0F + f1) - 15));
            stack.mulPose(Vector3f.ZP.rotationDegrees(f3 / 2.0F));
            stack.mulPose(Vector3f.YP.rotationDegrees(180.0F - f3 / 2.0F));
            render(enderbmodel, pLivingEntity, pPartialTicks, groundrt, stack, pBuffer, pBuffer.getBuffer(groundrt), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            groundrt = RenderType.eyes(GROUND_AMULET_GLOW);
            render(enderbmodel, pLivingEntity, pPartialTicks, groundrt, stack, pBuffer, pBuffer.getBuffer(groundrt), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            stack.translate(0,-0.77,0);
            stack.popPose();
        }


    }
        @Override
    public void render(PoseStack stack, MultiBufferSource pBuffer, int pPackedLight, AbstractClientPlayer pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        Player player = (Player) pLivingEntity;
        boolean isme = pLivingEntity.getUUID().toString().equals("6fe814fa9a6a436d80996c390116a67e") || pLivingEntity.getUUID().toString().equals("6fe814fa-9a6a-436d-8099-6c390116a67e");
        renderAmulets(stack,pBuffer,pPackedLight,pLivingEntity,pLimbSwing,pLimbSwingAmount,pPartialTicks,pAgeInTicks,pNetHeadYaw,pHeadPitch);
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
