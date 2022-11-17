package com.thefreak.botsmod.objects.items.bewlr;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.objects.ister.IGeoriteCrystal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.data.EmptyModelData;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

import java.util.Random;

public class BanhirHeadBEWLR extends BlockEntityWithoutLevelRenderer {
    Minecraft mc = ClassReferences.getClientMC();
    private static final ResourceLocation DRAGON_EXPLODING_LOCATION = new ResourceLocation("textures/entity/enderdragon/dragon_exploding.png");

    public BanhirHeadBEWLR(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
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
                "item/banhir_head_model"));
        VertexConsumer vertexBuilder = buffer
                .getBuffer(RenderType.entityTranslucent(InventoryMenu.BLOCK_ATLAS));

        for (int i = 0; i < model.getQuads(null, null, random, EmptyModelData.INSTANCE).size(); i++) {
            vertexBuilder.putBulkData(entry, model.getQuads(null, null, random, EmptyModelData.INSTANCE).get(i), 1, 1, 1, 1, combinedLight, combinedOverlay, true);
        }
        PoseStack poseStack2 = matrixStack;

        matrixStack.pushPose();
        matrixStack.translate(0.25F, 0.5F, 0.5F);
        PoseStack.Pose entry2 = matrixStack.last();
        matrixStack.popPose();
        entry2 = matrixStack.last();
        //NORMAL
        BakedModel model2 = Minecraft.getInstance().getModelManager().getModel(new ResourceLocation(BotsMod.MOD_ID,
                "item/banhir_head_model_glow"));
        VertexConsumer vertexBuilder2 = buffer
                .getBuffer(RenderType.eyes(InventoryMenu.BLOCK_ATLAS));

        for (int i = 0; i < model2.getQuads(null, null, random, EmptyModelData.INSTANCE).size(); i++) {
            vertexBuilder2.putBulkData(entry2, model2.getQuads(null, null, random, EmptyModelData.INSTANCE).get(i), 50, 50, 50, 1, combinedLight, combinedOverlay, true);
        }
    }
}
