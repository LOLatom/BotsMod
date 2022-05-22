package com.thefreak.botsmod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.thefreak.botsmod.client.entity.model.LadybugModel;
import com.thefreak.botsmod.client.entity.model.TippyLizardModel;
import com.thefreak.botsmod.entities.LadybugEntity;
import com.thefreak.botsmod.entities.TippyLizardEntity;
import com.thefreak.botsmod.entities.WanderingSpecterEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class TippyLizardRender extends GeoEntityRenderer<TippyLizardEntity> {
    public TippyLizardRender(EntityRendererManager renderManager) {
        super(renderManager, new TippyLizardModel());
        this.shadowRadius = 0.4F;
    }


    @Override
    protected float getDeathMaxRotation(TippyLizardEntity entityLivingBaseIn) {
        return 0F;
    }

    @Override
    public void renderRecursively(GeoBone bone, MatrixStack stack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("Head")) { // rArmRuff is the name of the bone you to set the item to attach too. Please see Note
            stack.pushPose();

            //You'll need to play around with these to get item to render in the correct orientation
            stack.mulPose(Vector3f.XP.rotationDegrees(-90));
            stack.mulPose(Vector3f.YP.rotationDegrees(0));
            stack.mulPose(Vector3f.ZP.rotationDegrees(0));
            //You'll need to play around with this to render the item in the correct spot.
            stack.translate(0D,0.75D,0.19D);
            //stack.translate(bone.getPositionX(),bone.getPositionY() + 0.7,bone.getPositionZ() + 0.3D);
            //Sets the scaling of the item.
            stack.scale(1.0f, 1.0f, 1.0f);
            // Change mainHand to predefined Itemstack and Mode to what transform you would want to use.
            Minecraft.getInstance().getItemRenderer().renderStatic(mainHand, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, packedLightIn, packedOverlayIn, stack, this.rtb);
            stack.popPose();
            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
        }
        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }


}
