package com.thefreak.botsmod.API.Animation.PlayerAnimation;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public abstract class Keyframe {
    @Nullable
    public Keyframe prevframe;
    @Nullable
    public Keyframe nextframe;

    public double keyframeDuration;


    /**
     * @param prev Used to define the previous Keyframe
     * @param next Used to define the next Keyframe
     * @param duration duration of the Keyframe animation
     */
    public Keyframe(Keyframe prev, Keyframe next, double duration) {
        this.prevframe = prev;
        this.nextframe = next;
        this.keyframeDuration = duration;
    }

    /**
     * When no previous Keyframe is seen we should use this constructor
     * @param next Used to define the next Keyframe
     * @param duration duration of the Keyframe animation
     */
    public Keyframe(Keyframe next, double duration) {
        this.nextframe = next;
        this.keyframeDuration = duration;
    }

    /**
     * When no next Keyframe is seen we should use this constructor
     * @param prev Used to define the next Keyframe
     * @param duration duration of the Keyframe animation
     */
    public Keyframe(double duration,Keyframe prev) {
        this.prevframe = prev;
        this.keyframeDuration = duration;
    }

    public abstract Vec3 currentPos(Entity entity, Vec3 lastpos, double progress);

    public abstract Vec3 currentRot(Entity entity,Vec3 lastrot, double progress);

    public Keyframe next(Entity entity) {
        return nextframe;
    }

    public Keyframe prev(Entity entity) {
        return prevframe;
    }


}
