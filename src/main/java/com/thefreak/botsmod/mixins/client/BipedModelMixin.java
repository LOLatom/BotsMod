package com.thefreak.botsmod.mixins.client;

import com.deltateam.deltalib.API.animation.keyframes.animator.AnimationSet;
import com.deltateam.deltalib.API.animation.keyframes.animator.ModelAnimator;
import com.deltateam.deltalib.API.animation.keyframes.animator.builders.AnimationBuilder;
import com.deltateam.deltalib.API.animation.keyframes.animator.builders.AnimationSetBuilder;
import com.deltateam.deltalib.API.animation.keyframes.animator.builders.KeyframeSequenceBuilder;
import com.thefreak.botsmod.API.Animation.IHandlePoseable;
import com.thefreak.botsmod.client.access.IBotsModAnimatable;
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
public abstract class BipedModelMixin<T extends LivingEntity> extends AgeableListModel<T> implements ArmedModel, HeadedModel {
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
				.addAnimation("botsmod.test", new AnimationBuilder()
						.animatePart(rightArm,
								new KeyframeSequenceBuilder()
										.addPosition(0, new Vec3(0, 0, 0))
										.addPosition(80, new Vec3(0, 0, 0))
										.addRotation(0, new Vec3(0, 0, 0), true)
										.addRotation(20, new Vec3(0, 90, 0), true)
										.addRotation(40, new Vec3(0, 90, 90), true)
										.addRotation(60, new Vec3(0, 0, 90), true)
										.addRotation(80, new Vec3(0, 0, 0), true)
										.build()
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

//	private static final Keyframe first;
	
	static {
//		first = new BasicKeyframe(null, null, new Vec3(0, 0, 0), new Vec3(0, 0, 0), 20);
//		Keyframe last = first;
//		last = last.nextframe = new BasicKeyframe(null, null, new Vec3(0, 0, 0), new Vec3(0, Math.toRadians(90), 0), 20);
//		last = last.nextframe = new BasicKeyframe(null, null, new Vec3(0, 0, 0), new Vec3(0, Math.toRadians(90), Math.toRadians(90)), 20);
//		last = last.nextframe = new BasicKeyframe(null, null, new Vec3(0, 0, 0), new Vec3(0, 0, Math.toRadians(90)), 20);
//		last.nextframe = first;
//		first.prevframe = last;
	}
	
	@Inject(method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V", at = @At("TAIL"))
	private void setupNewAnimations(T entity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, CallbackInfo ci) {
		if (entity instanceof IBotsModAnimatable animatableObject) {
			ModelAnimator animator = animatableObject.getObject().animator();
			if (!animatableObject.getObject().isSetup()) {
				if (pLimbSwing != 0)
					animatableObject.getObject().setup(this);
				else return;
				animations.startAnimation("botsmod.test", animatableObject.getObject().animator());
			}
//			if (!animatableObject.getObject().isSetup()) {
//				if (pLimbSwing != 0) {
//					animatableObject.getObject().setup(this);
//					animator.animatePart(20, EasingTypes.LINEAR, EasingDirection.NEITHER, rightArm, first);
//				} else {
//					return;
//				}
//			}
			// TODO: figure out how to get partial ticks to work right
			animator.tick(entity, (pAgeInTicks % 1 /* idk how modulo works on decimals, but */));
		}
	}
}
