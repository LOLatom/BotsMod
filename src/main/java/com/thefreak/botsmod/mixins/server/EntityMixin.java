package com.thefreak.botsmod.mixins.server;

import com.thefreak.botsmod.API.ISFreezable;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Entity.class)
public abstract class EntityMixin implements ISFreezable {

    @Unique
    boolean isSfreezed;


    @Override
    public boolean isSFreezed() {
        return ((EntityMixin)(Object)this).isSfreezed;
    }

    @Override
    public void setSFreezed(boolean freezed) {
        ((EntityMixin)(Object)this).isSfreezed = freezed;

    }
}
