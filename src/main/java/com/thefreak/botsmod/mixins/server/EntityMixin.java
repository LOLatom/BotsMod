package com.thefreak.botsmod.mixins.server;

import com.thefreak.botsmod.API.ISFreezable;
import net.minecraft.CrashReportCategory;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
