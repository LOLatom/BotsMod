package com.thefreak.botsmod.objects.animations;

import com.deltateam.deltalib.API.animation.keyframes.EasingTypes;
import com.deltateam.deltalib.API.animation.keyframes.keyframes.Keyframe;
import com.deltateam.deltalib.accessors.MinecraftAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;


public class SpellUpCastingKeyFrame extends Keyframe {
    public SpellUpCastingKeyFrame(Keyframe prev, Keyframe next, double duration) {
        super(prev, next, duration);
    }

    @Override
    public Vec3 currentPos(Entity entity, Vec3 lastpos, Vec3 defaultpos, double v) {
        float ageTicks = Minecraft.getInstance().player.tickCount + ((MinecraftAccessor) Minecraft.getInstance()).getTimer().partialTick;
        return new Vec3(Math.toRadians(-85) + (Math.sin(ageTicks * 0.3)* 0.15), (Math.cos(ageTicks * 0.3) * 0.15), lastpos.z);
    }
}
