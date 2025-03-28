package com.thefreak.botsmod.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.thefreak.botsmod.entities.misc.ShadowLightningBolt;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import java.util.Random;

public class ShadowLightningBoltRenderer extends EntityRenderer<ShadowLightningBolt> {


    public ShadowLightningBoltRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }

    public void render(ShadowLightningBolt pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        float[] afloat = new float[8];
        float[] afloat1 = new float[8];
        float f = 0.0F;
        float f1 = 0.0F;
        Random random = new Random(pEntity.seed);

        for(int i = 7; i >= 0; --i) {
            afloat[i] = f;
            afloat1[i] = f1;
            f += (float)(random.nextInt(11) - 5);
            f1 += (float)(random.nextInt(11) - 5);
        }

        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.lightning());

        Matrix4f matrix4f = pMatrixStack.last().pose();

        for(int j = 0; j < 4; ++j) {
            Random random1 = new Random(pEntity.seed);

            for(int k = 0; k < 3; ++k) {
                int l = 7;
                int i1 = 0;
                if (k > 0) {
                    l = 7 - k;
                }

                if (k > 0) {
                    i1 = l - 2;
                }

                float f2 = afloat[l] - f;
                float f3 = afloat1[l] - f1;

                for(int j1 = l; j1 >= i1; --j1) {
                    float f4 = f2;
                    float f5 = f3;
                    if (k == 0) {
                        f2 += (float)(random1.nextInt(11) - 5);
                        f3 += (float)(random1.nextInt(11) - 5);
                    } else {
                        f2 += (float)(random1.nextInt(31) - 15);
                        f3 += (float)(random1.nextInt(31) - 15);
                    }

                    float f6 = 0.5F;
                    float f7 = 0.45F;
                    float f8 = 0.45F;
                    float f9 = 0.5F;
                    float f10 = 0.1F + (float)j * 0.2F;
                    if (k == 0) {
                        f10 *= (float)j1 * 0.1F + 1.0F;
                    }

                    float f11 = 0.1F + (float)j * 0.2F;
                    if (k == 0) {
                        f11 *= ((float)j1 - 1.0F) * 0.1F + 1.0F;
                    }

                    quad(matrix4f, vertexconsumer, f2, f3, j1, f4, f5, 0.45F, 0.45F, 0.5F, f10, f11, false, false, true, false);
                    quad(matrix4f, vertexconsumer, f2, f3, j1, f4, f5, 0.45F, 0.45F, 0.5F, f10, f11, true, false, true, true);
                    quad(matrix4f, vertexconsumer, f2, f3, j1, f4, f5, 0.45F, 0.45F, 0.5F, f10, f11, true, true, false, true);
                    quad(matrix4f, vertexconsumer, f2, f3, j1, f4, f5, 0.45F, 0.45F, 0.5F, f10, f11, false, true, false, false);
                }
            }
        }

    }

    private static void quad(Matrix4f p_115273_, VertexConsumer p_115274_, float p_115275_, float p_115276_, int p_115277_, float p_115278_, float p_115279_, float p_115280_, float p_115281_, float p_115282_, float p_115283_, float p_115284_, boolean p_115285_, boolean p_115286_, boolean p_115287_, boolean p_115288_) {
        p_115274_.vertex(p_115273_, p_115275_ + (p_115285_ ? p_115284_ : -p_115284_), (float)(p_115277_ * 16), p_115276_ + (p_115286_ ? p_115284_ : -p_115284_)).color(0, 0, 0, 0.5F).endVertex();
        p_115274_.vertex(p_115273_, p_115278_ + (p_115285_ ? p_115283_ : -p_115283_), (float)((p_115277_ + 1) * 16), p_115279_ + (p_115286_ ? p_115283_ : -p_115283_)).color(0, 0, 0, 0.5F).endVertex();
        p_115274_.vertex(p_115273_, p_115278_ + (p_115287_ ? p_115283_ : -p_115283_), (float)((p_115277_ + 1) * 16), p_115279_ + (p_115288_ ? p_115283_ : -p_115283_)).color(0, 0, 0, 0.5F).endVertex();
        p_115274_.vertex(p_115273_, p_115275_ + (p_115287_ ? p_115284_ : -p_115284_), (float)(p_115277_ * 16), p_115276_ + (p_115288_ ? p_115284_ : -p_115284_)).color(0, 0, 0, 0.5F).endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(ShadowLightningBolt pEntity) {
        return null;
    }
}
