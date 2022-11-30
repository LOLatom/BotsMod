package com.thefreak.botsmod.client.access;

import com.deltateam.deltalib.API.animation.keyframes.IAnimatableObject;

public interface IBotsModAnimatable<T, MODEL> {
	IAnimatableObject<T, MODEL> getObject();
}
