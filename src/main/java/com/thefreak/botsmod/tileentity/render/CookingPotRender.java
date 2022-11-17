package com.thefreak.botsmod.tileentity.render;


import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.tileentity.CookingPotTileEntity;
import com.thefreak.botsmod.tileentity.model.CookingPotLiquidFirstLayerModel;
import com.thefreak.botsmod.tileentity.model.CookingPotLiquidSecondLayerModel;
import com.thefreak.botsmod.tileentity.model.CookingPotLiquidThirdLayerModel;
import com.thefreak.botsmod.tileentity.model.CookingPotModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class CookingPotRender extends GeoBlockRenderer<CookingPotTileEntity> {


    public CookingPotRender(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new CookingPotModel());
    }

    @Override
    public void render(CookingPotTileEntity tile, float partialTicks, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn) {


        super.render(tile, partialTicks, stack, bufferIn, packedLightIn);

        if (tile.getLiquidAmount() == 1) {
            addLayer(stack, new CookingPotLiquidFirstLayerModel(),
                    getLiquidTexture(tile.getLiquidType()),
                    bufferIn, packedLightIn, tile, partialTicks,0 ,0 ,0);
        }
        if (tile.getLiquidAmount() == 2) {
            addLayer(stack, new CookingPotLiquidSecondLayerModel(),
                    getLiquidTexture(tile.getLiquidType()),
                    bufferIn, packedLightIn, tile, partialTicks,0 ,0 ,0);
        }
        if (tile.getLiquidAmount() == 3) {
            addLayer(stack, new CookingPotLiquidThirdLayerModel(),
                    getLiquidTexture(tile.getLiquidType()),
                    bufferIn, packedLightIn, tile, partialTicks,0 ,0 ,0);
        }

    }

    public ResourceLocation getLiquidTexture(int liquidType) {

        if (liquidType == 1) {
            return new ResourceLocation("botsmod:textures/tileentities/cookingpotliquids/cp_water.png");
        } else if (liquidType == 2) {
            return new ResourceLocation("botsmod:textures/tileentities/cookingpotliquids/cp_milk.png");
        }else if (liquidType == 3) {
            return new ResourceLocation("botsmod:textures/tileentities/cookingpotliquids/cp_condensedmilk.png");
        }else if (liquidType == 4) {
            return new ResourceLocation("botsmod:textures/tileentities/cookingpotliquids/cp_caramel.png");
        }else if (liquidType == 5) {
            return new ResourceLocation("botsmod:textures/tileentities/cookingpotliquids/cp_powderedmilk.png");
        }  else {
            return new ResourceLocation("botsmod:textures/tileentities/cookingpotliquids/cp_milk.png");        }
    }



    public void addLayer(PoseStack stack, AnimatedGeoModel geoModel, ResourceLocation texture, MultiBufferSource bufferIn, int packedLightIn, CookingPotTileEntity tile, float partialTicks,float x,float y,float z) {
        PoseStack layerStack = stack;

        layerStack.pushPose();
        layerStack.translate(0, 0.01f, 0);
        layerStack.translate(0.5, 0, 0.5);

        GeoModel model = geoModel.getModel(geoModel.getModelLocation(tile));

        Color renderColor = getRenderColor(tile, partialTicks, layerStack, bufferIn, null, packedLightIn);

        RenderType renderType = getRenderType(tile, partialTicks, layerStack, bufferIn, null, packedLightIn, texture);

        RenderType renderType2 = RenderType.entityTranslucent(texture);

        render(model, tile, partialTicks, renderType2, layerStack, bufferIn, null, packedLightIn, OverlayTexture.NO_OVERLAY,
                (float) renderColor.getRed() / 255f, (float) renderColor.getGreen() / 255f,
                (float) renderColor.getBlue() / 255f, (float) renderColor.getAlpha() / 255);

        layerStack.popPose();
    }
}
