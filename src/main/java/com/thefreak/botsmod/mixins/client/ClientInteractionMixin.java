package com.thefreak.botsmod.mixins.client;

import com.thefreak.botsmod.API.IAmProstheticItem;
import com.thefreak.botsmod.util.capabilities.PlayerLimbsProvider;
import com.thefreak.botsmod.util.capabilities.PlayerProstheticArmProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Minecraft.class)
public class ClientInteractionMixin {


    @Shadow @Nullable public LocalPlayer player;

    @Inject(at = @At("HEAD"),method = "startAttack", cancellable = true)
    private void attackMixinStart(CallbackInfoReturnable<Boolean> cir) {
        this.player.getCapability(PlayerLimbsProvider.PLAYER_LIMBS).ifPresent(playerLimbsCap -> {
            if (!playerLimbsCap.hasMainArm()) {
                this.player.getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM).ifPresent(playerProstheticArmCap -> {
                    if (playerProstheticArmCap.getProstheticArm().getItem() instanceof IAmProstheticItem prostheticItem) {
                        if (!prostheticItem.canAttack(this.player)) {
                            cir.setReturnValue(false);
                        }
                    } else {
                        cir.setReturnValue(false);
                    }
                });

            }
        });
    }
}
