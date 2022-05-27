package com.thefreak.botsmod.objects.ister;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.thefreak.botsmod.BotsMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.data.EmptyModelData;

import java.util.Random;

public class GeoriteCrystalISTER extends BlockEntityWithoutLevelRenderer {

    public GeoriteCrystalISTER(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        super.renderByItem(stack, transformType, matrixStack, buffer, combinedLight, combinedOverlay);
        matrixStack.pushPose();
        matrixStack.translate(0.25F, 0.5F, 0.5F);
        PoseStack.Pose entry = matrixStack.last();
        matrixStack.popPose();
        Random random = new Random();
        entry = matrixStack.last();
        //NORMAL
        BakedModel model = Minecraft.getInstance().getModelManager().getModel(new ResourceLocation(BotsMod.MOD_ID,
                "item/georite_crystal_model_part"));
        VertexConsumer vertexBuilder = buffer
                .getBuffer(RenderType.entityTranslucent(InventoryMenu.BLOCK_ATLAS));

        for (int i = 0; i < model.getQuads(null, null, random, EmptyModelData.INSTANCE).size(); i++) {
            vertexBuilder.putBulkData(entry, model.getQuads(null, null, random, EmptyModelData.INSTANCE).get(i), 50, 50, 50, 1, combinedLight, combinedOverlay, true);
        }
        //    ---------------------------------------------------------------------------------------------------------
        //NORMAL
        BakedModel model3 = Minecraft.getInstance().getModelManager().getModel(new ResourceLocation(BotsMod.MOD_ID,
                "item/georite_crystal_model_glow"));
        VertexConsumer vertexBuilder3 = buffer
                .getBuffer(RenderType.entityTranslucent(InventoryMenu.BLOCK_ATLAS));

        for (int i = 0; i < model.getQuads(null, null, random, EmptyModelData.INSTANCE).size(); i++) {
            vertexBuilder3.putBulkData(entry, model3.getQuads(null, null, random, EmptyModelData.INSTANCE).get(i), 1, 1, 1, 1, combinedLight, combinedOverlay, true);
        }
        //    ---------------------------------------------------------------------------------------------------------
        //GLOW
        Item item = stack.getItem();
        IGeoriteCrystal georiteCrystal = (IGeoriteCrystal) item;
        int GeoriteGlowAlpha = georiteCrystal.glowAlpha(stack);
        int GeoriteGlowR = georiteCrystal.glowR(stack);
        int GeoriteGlowG = georiteCrystal.glowG(stack);
        int GeoriteGlowB = georiteCrystal.glowB(stack);
        BakedModel model2 = Minecraft.getInstance().getModelManager().getModel(new ResourceLocation(BotsMod.MOD_ID,
                "item/georite_crystal_model_glow"));

        VertexConsumer vertexBuilder2 = buffer
                .getBuffer(RenderType.eyes(InventoryMenu.BLOCK_ATLAS));

        for (int i = 0; i < model.getQuads(null, null, random, EmptyModelData.INSTANCE).size() + 1; i++) {
            vertexBuilder2.putBulkData(entry, model2.getQuads(null, null, random, EmptyModelData.INSTANCE).get(i), GeoriteGlowR, GeoriteGlowG, GeoriteGlowB, GeoriteGlowAlpha, combinedLight, combinedOverlay, true);
        }
    }


}
