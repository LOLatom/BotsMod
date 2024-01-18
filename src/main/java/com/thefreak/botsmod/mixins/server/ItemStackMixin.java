package com.thefreak.botsmod.mixins.server;

import com.thefreak.botsmod.API.IAmProstheticItem;
import com.thefreak.botsmod.util.capabilities.PlayerLimbsProvider;
import com.thefreak.botsmod.util.capabilities.PlayerProstheticArmProvider;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void bots_useMixin(Level pLevel, Player pPlayer, InteractionHand pUsedHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        pPlayer.getCapability(PlayerLimbsProvider.PLAYER_LIMBS).ifPresent(playerLimbsCap -> {
            if (pUsedHand == InteractionHand.MAIN_HAND && !playerLimbsCap.hasMainArm()) {
                pPlayer.getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM).ifPresent(playerProstheticArmCap -> {
                    if (playerProstheticArmCap.getProstheticArm().getItem() instanceof IAmProstheticItem prostheticItem) {
                        if (prostheticItem.canUse(pPlayer, pLevel, pUsedHand)) {

                        }
                    } else {
                        cir.setReturnValue(InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand)));
                    }
                });
            }
        });
    }

    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    public void bots_useOnMixin(UseOnContext pContext, CallbackInfoReturnable<InteractionResult> cir) {
            Player player = pContext.getPlayer();
        player.getCapability(PlayerLimbsProvider.PLAYER_LIMBS).ifPresent(playerLimbsCap -> {
            if (pContext.getHand() == InteractionHand.MAIN_HAND && !playerLimbsCap.hasMainArm()) {
                player.getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM).ifPresent(playerProstheticArmCap -> {
                    if (playerProstheticArmCap.getProstheticArm().getItem() instanceof IAmProstheticItem prostheticItem) {
                        if (prostheticItem.canUseOn(pContext)) {

                        } else {
                            cir.setReturnValue(InteractionResult.FAIL);
                        }
                    } else {
                        cir.setReturnValue(InteractionResult.FAIL);
                    }
                });

            }
        });
    }

}
