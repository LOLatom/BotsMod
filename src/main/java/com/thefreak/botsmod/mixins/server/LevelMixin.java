package com.thefreak.botsmod.mixins.server;

import com.thefreak.botsmod.API.ISFreezable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(Level.class)
public class LevelMixin {

    @Inject(method = "guardEntityTick", at = @At("HEAD"), cancellable = true)
    public void botsmod$entityTickMixin(Consumer<?> ignored, Entity entity, CallbackInfo ci) {
        if (entity instanceof ISFreezable isFreezable) {
            if (isFreezable.isSFreezed()) {
                ci.cancel();
            }
        }


    }
}
