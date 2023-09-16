package com.thefreak.botsmod.objects.animations;

import com.deltateam.deltalib.API.animation.keyframes.keyframes.Keyframe;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;


public class SpellUpCastingKeyFrame extends Keyframe {
    public SpellUpCastingKeyFrame(Keyframe prev, Keyframe next, double duration) {
        super(prev, next, duration);
    }

    @Override
    public Vec3 currentPos(Entity entity, Vec3 lastpos, Vec3 defaultpos, double v) {
        return new Vec3(Math.toRadians(-85) + (Math.sin(Minecraft.getInstance().player.tickCount)* 0.15), (Math.cos(Minecraft.getInstance().player.tickCount) * 0.15), lastpos.z);
    }
}
