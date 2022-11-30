package com.thefreak.botsmod.mixins.client;

import com.thefreak.botsmod.client.access.IAnimationHolder;
import com.thefreak.botsmod.client.access.IBotsModAnimatable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	@Shadow
	protected abstract int increaseAirSupply(int pCurrentAir);
	
	@Inject(at = @At("TAIL"), method = "swing(Lnet/minecraft/world/InteractionHand;Z)V")
	public void postSwing(InteractionHand pHand, boolean pUpdateSelf, CallbackInfo ci) {
		if (((Object) this) instanceof IBotsModAnimatable animatable) {
			animatable.getSet().startAnimation("botsmod.test", animatable.getObject().animator());
		}
	}
}
