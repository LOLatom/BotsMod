package com.thefreak.botsmod.mixins.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.API.IAmDivine;
import com.thefreak.botsmod.client.Rendering.RenderTargets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityRendererDispatcherMixin {
	@Inject(method = "render", at = @At("HEAD"))
	public <E extends Entity> void preRender(E pEntity, double pX, double pY, double pZ, float pRotationYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, CallbackInfo ci) {
		boolean isme = pEntity.getUUID().toString().equals("6fe814fa9a6a436d80996c390116a67e") || pEntity.getUUID().toString().equals("6fe814fa-9a6a-436d-8099-6c390116a67e");
		if (pEntity instanceof IAmDivine divine) {
			if (divine.isDivine() && isme) {
				Minecraft.getInstance().getMainRenderTarget().unbindWrite();
				RenderTargets.FreakPlayer.bindWrite(true);
			}
		}
	}
	
	@Inject(method = "render", at = @At("TAIL"))
	public <E extends Entity> void postRender(E pEntity, double pX, double pY, double pZ, float pRotationYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, CallbackInfo ci) {
		boolean isme = pEntity.getUUID().toString().equals("6fe814fa9a6a436d80996c390116a67e") || pEntity.getUUID().toString().equals("6fe814fa-9a6a-436d-8099-6c390116a67e");
		if (pEntity instanceof IAmDivine divine) {
			if (divine.isDivine() && isme) {
				RenderTargets.FreakPlayer.unbindWrite();
				RenderTargets.FreakPlayerDepth.copyDepthFrom(RenderTargets.FreakPlayer);
				RenderTargets.FreakPlayerDepth.unbindWrite();
				Minecraft.getInstance().getMainRenderTarget().bindWrite(true);
			}
		}
	}
}
