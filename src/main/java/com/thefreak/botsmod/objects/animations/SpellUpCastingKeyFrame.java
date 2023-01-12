package com.thefreak.botsmod.objects.animations;

import com.deltateam.deltalib.API.animation.keyframes.keyframes.Keyframe;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;


public class SpellUpCastingKeyFrame extends Keyframe {
    public SpellUpCastingKeyFrame(Keyframe prev, Keyframe next, double duration) {
        super(prev, next, duration);
    }

    @Override
    public Vec3 currentPos(Entity entity, Vec3 lastpos, Vec3 defaultpos, double v) {
        return new Vec3(lastpos.x + Math.sin((v) * 4*(Math.PI)), lastpos.y, lastpos.z + Math.sin((v) * 4*(Math.PI)) * 2);
    }
}
