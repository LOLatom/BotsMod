package com.thefreak.botsmod.client.entity.render;

import com.thefreak.botsmod.entities.Projectile.SaltedArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SaltedArrowRenderer extends ArrowRenderer<SaltedArrow> {
    public static final ResourceLocation SALTED_ARROW_TEXTURE = new ResourceLocation("botsmod:textures/entities/projectiles/salted_arrow_entity.png");

    public SaltedArrowRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getTextureLocation(SaltedArrow p_110775_1_) {
        return SALTED_ARROW_TEXTURE;
    }

}
