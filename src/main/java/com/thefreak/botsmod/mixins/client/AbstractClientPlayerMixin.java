package com.thefreak.botsmod.mixins.client;

import com.deltateam.deltalib.API.animation.keyframes.IAnimatableObject;
import com.deltateam.deltalib.API.animation.keyframes.animator.AnimationSet;
import com.deltateam.deltalib.API.animation.keyframes.animator.ModelAnimator;
import com.deltateam.deltalib.API.animation.keyframes.animator.PartAnimator;
import com.thefreak.botsmod.client.access.IAnimationHolder;
import com.thefreak.botsmod.client.access.IBotsModAnimatable;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractClientPlayer.class)
public class AbstractClientPlayerMixin implements IBotsModAnimatable<AbstractClientPlayer, HumanoidModel<AbstractClientPlayer>> {
	AnimationSet animations;
	
	IAnimatableObject<AbstractClientPlayer, HumanoidModel<AbstractClientPlayer>> object = new IAnimatableObject<AbstractClientPlayer, HumanoidModel<AbstractClientPlayer>>() {
		@Override
		public AbstractClientPlayer getObject() {
			return (AbstractClientPlayer) (Object) AbstractClientPlayerMixin.this;
		}
		
		@Override
		public ModelAnimator<AbstractClientPlayer, HumanoidModel<AbstractClientPlayer>> animator() {
			return animator;
		}
		
		HumanoidModel<AbstractClientPlayer> currentModel = null;
		
		@Override
		public void setup(HumanoidModel<AbstractClientPlayer> model) {
			currentModel = model;
			animations = ((IAnimationHolder) model).getSet();
			// if you want to animate another part, add it here
			if (!animator.hasPart(model.rightArm))
				animator.addPartAnimator(model.rightArm, new PartAnimator(null, Vec3.ZERO, Vec3.ZERO, model.rightArm));
		}
		
		@Override
		public boolean isSetup(HumanoidModel<AbstractClientPlayer> model) {
			return currentModel == model;
		}
	};
	
	ModelAnimator<AbstractClientPlayer, HumanoidModel<AbstractClientPlayer>> animator = new ModelAnimator<>(object.getObject());
	
	@Override
	public IAnimatableObject<AbstractClientPlayer, HumanoidModel<AbstractClientPlayer>> getObject() {
		return object;
	}
	
	@Override
	public AnimationSet getSet() {
		return animations;
	}
}
