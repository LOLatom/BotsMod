package com.thefreak.botsmod.mixins.client;

import com.thefreak.botsmod.API.IDuelMode;
import com.thefreak.botsmod.ClassReferences;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {

    @Inject(method = "onMove", at = @At("HEAD"), cancellable = true)
    private void onMoveInDuel(long pWindowPointer, double pXpos, double pYpos, CallbackInfo ci) {
        if (ClassReferences.getPlayer() instanceof IDuelMode duelMode) {
            if (duelMode.isInDuel()) {
                ci.cancel();
            }
        }
    }


}
