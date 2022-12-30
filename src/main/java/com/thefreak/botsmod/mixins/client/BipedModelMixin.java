package com.thefreak.botsmod.mixins.client;

import com.deltateam.deltalib.API.animation.keyframes.animator.AnimationSet;
import com.deltateam.deltalib.API.animation.keyframes.animator.ModelAnimator;
import com.deltateam.deltalib.API.animation.keyframes.animator.builders.AnimationBuilder;
import com.deltateam.deltalib.API.animation.keyframes.animator.builders.AnimationSetBuilder;
import com.deltateam.deltalib.API.animation.keyframes.animator.builders.KeyframeSequenceBuilder;
import com.deltateam.deltalib.util.animation.KeyframeGroup;
import com.thefreak.botsmod.API.Animation.IHandlePoseable;
import com.thefreak.botsmod.client.access.IAnimationHolder;
import com.thefreak.botsmod.client.access.IBotsModAnimatable;
import com.thefreak.botsmod.objects.animations.SpellUpCastingKeyFrame;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@Mixin(HumanoidModel.class)
public abstract class BipedModelMixin<T extends LivingEntity> extends AgeableListModel<T> implements ArmedModel, HeadedModel, IAnimationHolder {
	@Shadow
	public ModelPart head;
	@Shadow
	public ModelPart hat;
	@Shadow
	public ModelPart body;
	@Shadow
	public ModelPart rightArm;
	@Shadow
	public ModelPart leftArm;
	@Shadow
	public ModelPart rightLeg;
	@Shadow
	public ModelPart leftLeg;
	@Shadow
	public HumanoidModel.ArmPose leftArmPose = HumanoidModel.ArmPose.EMPTY;
	@Shadow
	public HumanoidModel.ArmPose rightArmPose = HumanoidModel.ArmPose.EMPTY;
	@Shadow
	public boolean crouching;
	@Shadow
	public float swimAmount;
	
	AnimationSet animations;
	
	@Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/client/model/geom/ModelPart;)V")
	public void postInit0(ModelPart pRoot, CallbackInfo ci) {
		animations = buildAnimations();
	}
	
	@Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/client/model/geom/ModelPart;Ljava/util/function/Function;)V")
	public void postInit1(ModelPart pRoot, Function pRenderType, CallbackInfo ci) {
		animations = buildAnimations();
	}
	
	protected AnimationSet buildAnimations() {
		return new AnimationSetBuilder()
				.addAnimation("botsmod.spellcastingup", new AnimationBuilder()
						.animatePart(rightArm,
								new KeyframeGroup(null,new SpellUpCastingKeyFrame(null,null,120))
						).build()
				).build();
	}
	
	@Inject(method = "poseLeftArm", at = @At("TAIL"))
	private void leftArmpos(T p_241655_1_, CallbackInfo ci) {
		
		LivingEntity livingEntity = (LivingEntity) p_241655_1_;
		if (livingEntity instanceof Player) {
			Player playerEntity = (Player) p_241655_1_;
			InteractionHand main = InteractionHand.OFF_HAND;
			InteractionHand off = InteractionHand.MAIN_HAND;
			if (playerEntity.getUsedItemHand() == InteractionHand.MAIN_HAND) {
				main = InteractionHand.MAIN_HAND;
				off = InteractionHand.OFF_HAND;
			}
			if (playerEntity.getUsedItemHand() == InteractionHand.OFF_HAND) {
				main = InteractionHand.OFF_HAND;
				off = InteractionHand.MAIN_HAND;
			}
			ItemStack stack = playerEntity.getItemInHand(main);
			ItemStack stack1 = playerEntity.getItemInHand(off);
			if (stack1.getItem() instanceof IHandlePoseable) {
				
				
				((IHandlePoseable) stack1.getItem()).getLeftArmPoser(off, stack1, livingEntity).accept((HumanoidModel) (Object) this, (Player) playerEntity);
				
			} else if (stack.getItem() instanceof IHandlePoseable) {
				
				((IHandlePoseable) stack.getItem()).getLeftArmPoser(main, stack, livingEntity).accept((HumanoidModel) (Object) this, (Player) playerEntity);
				
			}
		}
	}
	
	
	@Inject(method = "poseRightArm", at = @At("TAIL"))
	private void rightArmpos(T p_241655_1_, CallbackInfo ci) {
		
		
		LivingEntity livingEntity = (LivingEntity) p_241655_1_;
		if (livingEntity instanceof Player) {
			Player playerEntity = (Player) p_241655_1_;
			InteractionHand main = InteractionHand.OFF_HAND;
			InteractionHand off = InteractionHand.MAIN_HAND;
			if (playerEntity.getUsedItemHand() == InteractionHand.MAIN_HAND) {
				main = InteractionHand.MAIN_HAND;
				off = InteractionHand.OFF_HAND;
			}
			if (playerEntity.getUsedItemHand() == InteractionHand.OFF_HAND) {
				main = InteractionHand.OFF_HAND;
				off = InteractionHand.MAIN_HAND;
			}
			ItemStack stack = playerEntity.getItemInHand(main);
			ItemStack stack1 = playerEntity.getItemInHand(off);
			if (stack1.getItem() instanceof IHandlePoseable) {
				
				
				((IHandlePoseable) stack1.getItem()).getRightArmPoser(off, stack1, livingEntity).accept((HumanoidModel) (Object) this, (Player) playerEntity);
				
			} else if (stack.getItem() instanceof IHandlePoseable) {
				
				
				((IHandlePoseable) stack.getItem()).getRightArmPoser(main, stack, livingEntity).accept((HumanoidModel) (Object) this, (Player) playerEntity);
				
			}
		}
	}
	
	@Inject(method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V", at = @At("TAIL"))
	private void setupNewAnimations(T entity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, CallbackInfo ci) {
		if (entity instanceof IBotsModAnimatable animatableObject) {
			ModelAnimator animator = animatableObject.getObject().animator();
			if (!animatableObject.getObject().isSetup(this))
				animatableObject.getObject().setup(this);
			// TODO: figure out how to get partial ticks to work right
			animator.tick(entity, (pAgeInTicks % 1 /* idk how modulo works on decimals, but */));
		}
	}
	
	@Override
	public AnimationSet getSet() {
		return animations;
	}
}
