package com.thefreak.botsmod.mixins.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.client.access.IBotsModAnimatable;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class EntityRendererMixin<T extends LivingEntity> {
	@Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V")
	public void postRender(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, CallbackInfo ci) {
		// safety
		if (pEntity instanceof IBotsModAnimatable animatableObject) {
			animatableObject.getObject().animator().resetAnimation();
		}
	}
}
