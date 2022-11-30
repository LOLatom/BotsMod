package com.thefreak.botsmod.mixins.client;

import com.deltateam.deltalib.API.animation.keyframes.IAnimatableObject;
import com.deltateam.deltalib.API.animation.keyframes.animator.ModelAnimator;
import com.deltateam.deltalib.API.animation.keyframes.animator.PartAnimator;
import com.thefreak.botsmod.client.access.IBotsModAnimatable;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractClientPlayer.class)
public class AbstractClientPlayerMixin implements IBotsModAnimatable<AbstractClientPlayer, HumanoidModel<AbstractClientPlayer>> {
	IAnimatableObject<AbstractClientPlayer, HumanoidModel<AbstractClientPlayer>> object = new IAnimatableObject<AbstractClientPlayer, HumanoidModel<AbstractClientPlayer>>() {
		@Override
		public AbstractClientPlayer getObject() {
			return (AbstractClientPlayer) (Object) AbstractClientPlayerMixin.this;
		}
		
		@Override
		public ModelAnimator<AbstractClientPlayer, HumanoidModel<AbstractClientPlayer>> animator() {
			return animator;
		}
		
		boolean isSetup = false;
		
		@Override
		public void setup(HumanoidModel<AbstractClientPlayer> model) {
			isSetup = true;
			// if you want to animate another part, add it here
			animator.addPartAnimator(model.rightArm, new PartAnimator(null, Vec3.ZERO, Vec3.ZERO, model.rightArm));
		}
		
		@Override
		public boolean isSetup() {
			return isSetup;
		}
	};
	
	ModelAnimator<AbstractClientPlayer, HumanoidModel<AbstractClientPlayer>> animator = new ModelAnimator<>(object.getObject());
	
	@Override
	public IAnimatableObject<AbstractClientPlayer, HumanoidModel<AbstractClientPlayer>> getObject() {
		return object;
	}
}
