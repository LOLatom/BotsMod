package com.thefreak.botsmod.API.Animation.PlayerAnimation;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class BasicKeyframe extends Keyframe{

    private Vec3 targetPos;
    private Vec3 targetRot;
    private Vec3 currentPos;
    private Vec3 currentRot;

    public BasicKeyframe(Keyframe prev, Keyframe next, Vec3 targetPos, Vec3 targetRot, double duration) {
        super(prev, next, duration);
        this.targetPos = targetPos;
        this.targetRot = targetRot;
    }

    public Vec3 getTargetRot() {
        return targetRot;
    }

    public Vec3 getTargetPos() {
        return targetPos;
    }

    public Vec3 getCurrentRot() {
        return currentRot;
    }

    @Override
    public Vec3 currentPos(Entity entity, Vec3 lastpos, double progress) {
        return new Vec3(
                lastpos.x * (1 - progress) + targetPos.x * progress,
                lastpos.y * (1 - progress) + targetPos.y * progress,
                lastpos.z * (1 - progress) + targetPos.z * progress
        );
    }

    @Override
    public Vec3 currentRot(Entity entity, Vec3 lastrot, double progress) {
        return new Vec3(
                lastrot.x * (1 - progress) + targetRot.x * progress,
                lastrot.y * (1 - progress) + targetRot.y * progress,
                lastrot.z * (1 - progress) + targetRot.z * progress
        );
    }
}
