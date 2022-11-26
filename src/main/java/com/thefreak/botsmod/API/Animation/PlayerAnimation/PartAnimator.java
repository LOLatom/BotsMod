package com.thefreak.botsmod.API.Animation.PlayerAnimation;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class PartAnimator {
    Keyframe current;
    Vec3 lastPos;
    Vec3 lastRot;
    int frameTime;
    int lastTick;

    ModelPart part;
    Entity entityan;

    public PartAnimator(Keyframe current, Vec3 lastPos, Vec3 lastRot, ModelPart part) {
            this.current = current;
            this.lastPos = lastPos;
            this.lastRot = lastRot;
            this.part = part;
    }

    boolean ticking = false;
    public void tick(Entity entity) {
        if (entity != null) {
            this.entityan = entity;
            double progress = frameTime / (double) current.keyframeDuration;
            Vec3 pos = current.currentPos(entity, lastPos, progress);
            Vec3 rot = current.currentRot(entity, lastRot, progress);
            part.setPos((float) pos.x, (float) pos.y, (float) pos.z);
            part.setRotation((float) rot.x, (float) rot.y, (float) rot.z);
            if (ticking) {
                frameTime += entity.tickCount - lastTick;
                lastTick = entity.tickCount;
                if (frameTime > current.keyframeDuration) {
                    lastPos = current.currentPos(entity, lastPos, 1);
                    lastRot = current.currentRot(entity, lastRot, 1);
                    current = current.next(entity);
                    double currentStart = entity.tickCount;
                }
            }
        }
    }

    public void pause() {
        ticking = false;
    }

    public void play() {
        ticking = true;
        lastTick = entityan.tickCount;
    }

    public boolean isPaused() {
        return !ticking;
    }
}
