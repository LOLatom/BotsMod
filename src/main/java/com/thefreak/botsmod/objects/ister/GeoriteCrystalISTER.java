package com.thefreak.botsmod.objects.ister;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.thefreak.botsmod.BotsMod;
import net.minecraft.client.GameSettings;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemModelGenerator;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.model.data.EmptyModelData;

import java.util.Random;

public class GeoriteCrystalISTER extends ItemStackTileEntityRenderer {

    @Override
    public void renderByItem(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        super.renderByItem(stack, transformType, matrixStack, buffer, combinedLight, combinedOverlay);
        matrixStack.pushPose();
        matrixStack.translate(0.25F, 0.5F, 0.5F);
        MatrixStack.Entry entry = matrixStack.last();
        matrixStack.popPose();
        Random random = new Random();
        entry = matrixStack.last();
        //NORMAL
        IBakedModel model = Minecraft.getInstance().getModelManager().getModel(new ResourceLocation(BotsMod.MOD_ID,
                "item/georite_crystal_model_part"));
        IVertexBuilder vertexBuilder = buffer
                .getBuffer(RenderType.entityTranslucent(PlayerContainer.BLOCK_ATLAS));

        for (int i = 0; i < model.getQuads(null, null, random, EmptyModelData.INSTANCE).size(); i++) {
            vertexBuilder.addVertexData(entry, model.getQuads(null, null, random, EmptyModelData.INSTANCE).get(i), 50, 50, 50, 1, combinedLight, combinedOverlay, true);
        }
        //    ---------------------------------------------------------------------------------------------------------
        //NORMAL
        IBakedModel model3 = Minecraft.getInstance().getModelManager().getModel(new ResourceLocation(BotsMod.MOD_ID,
                "item/georite_crystal_model_glow"));
        IVertexBuilder vertexBuilder3 = buffer
                .getBuffer(RenderType.entityTranslucent(PlayerContainer.BLOCK_ATLAS));

        for (int i = 0; i < model.getQuads(null, null, random, EmptyModelData.INSTANCE).size(); i++) {
            vertexBuilder3.addVertexData(entry, model3.getQuads(null, null, random, EmptyModelData.INSTANCE).get(i), 1, 1, 1, 1, combinedLight, combinedOverlay, true);
        }
        //    ---------------------------------------------------------------------------------------------------------
        //GLOW
        Item item = stack.getItem();
        IGeoriteCrystal georiteCrystal = (IGeoriteCrystal) item;
        int GeoriteGlowAlpha = georiteCrystal.glowAlpha(stack);
        int GeoriteGlowR = georiteCrystal.glowR(stack);
        int GeoriteGlowG = georiteCrystal.glowG(stack);
        int GeoriteGlowB = georiteCrystal.glowB(stack);
        IBakedModel model2 = Minecraft.getInstance().getModelManager().getModel(new ResourceLocation(BotsMod.MOD_ID,
                "item/georite_crystal_model_glow"));

        IVertexBuilder vertexBuilder2 = buffer
                .getBuffer(RenderType.eyes(PlayerContainer.BLOCK_ATLAS));

        for (int i = 0; i < model.getQuads(null, null, random, EmptyModelData.INSTANCE).size() + 1; i++) {
            vertexBuilder2.addVertexData(entry, model2.getQuads(null, null, random, EmptyModelData.INSTANCE).get(i), GeoriteGlowR, GeoriteGlowG, GeoriteGlowB, GeoriteGlowAlpha, combinedLight, combinedOverlay, true);
        }
    }


}
