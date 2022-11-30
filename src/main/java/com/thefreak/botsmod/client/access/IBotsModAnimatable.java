package com.thefreak.botsmod.client.access;

import com.deltateam.deltalib.API.animation.keyframes.IAnimatableObject;

public interface IBotsModAnimatable<T, MODEL> extends IAnimationHolder {
	IAnimatableObject<T, MODEL> getObject();
}
