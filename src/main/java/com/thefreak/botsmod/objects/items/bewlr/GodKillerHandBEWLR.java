package com.thefreak.botsmod.objects.items.bewlr;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.objects.items.bewlr.models.GodKillerHandModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.Collections;

public class GodKillerHandBEWLR extends BlockEntityWithoutLevelRenderer implements IGeoRenderer, IAnimatable {




    private final AnimationFactory factory = new AnimationFactory(this);
    AnimatedGeoModel model;
    ItemStack currentItemStack;
    private static final ResourceLocation MODEL = new ResourceLocation("botsmod", "geo/items/god_killer_hand.geo.json");
    private static final ResourceLocation LAYER = new ResourceLocation("botsmod", "textures/items/special/god_killer_hand.png");

    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("animation.god_killer_hand.firstfinger");



    public GodKillerHandBEWLR(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);
        this.model = new GodKillerHandModel();

    }



    @Override
    public void renderByItem(ItemStack pStack, ItemTransforms.TransformType pTransformType, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        super.renderByItem(pStack, pTransformType, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        GeoModel geoModel = model.getModel(MODEL);
        RenderType renderType = RenderType.armorCutoutNoCull(LAYER);
        currentItemStack = pStack;
        AnimationEvent itemEvent = new AnimationEvent(this, 0, 0, Minecraft.getInstance().getFrameTime(),
                false, Collections.singletonList(pStack));
        model.setLivingAnimations(this, this.getUniqueID(this), itemEvent);
        pPoseStack.pushPose();
        pPoseStack.scale(1.2F,1.2F,1.2F);
        pPoseStack.translate(0.425,-0.02,0.55F);
        render(geoModel,this,0f,renderType,pPoseStack,pBuffer,pBuffer.getBuffer(renderType),pPackedLight, OverlayTexture.NO_OVERLAY,1f,1f,1f,1f);
        pPoseStack.popPose();
    }


    @Override
    public MultiBufferSource getCurrentRTB() {
        return null;
    }

    @Override
    public GeoModelProvider getGeoModelProvider() {
        return this.model;
    }

    @Override
    public ResourceLocation getTextureLocation(Object instance) {
        return LAYER;
    }
    private <E extends GodKillerHandBEWLR> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        AnimationBuilder anim = IDLE_ANIM;
        controller.setAnimation(anim);
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 1, this::predicate));
    }


    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public Integer getUniqueID(GodKillerHandBEWLR animatable) {
        return GeckoLibUtil.getIDFromStack(currentItemStack);
    }
}
