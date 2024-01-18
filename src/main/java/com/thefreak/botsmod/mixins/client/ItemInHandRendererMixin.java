package com.thefreak.botsmod.mixins.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.API.IAmProstheticItem;
import com.thefreak.botsmod.util.capabilities.PlayerLimbsProvider;
import com.thefreak.botsmod.util.capabilities.PlayerProstheticArmProvider;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {



    @ModifyVariable(method = "renderArmWithItem", at = @At("HEAD"))
    private ItemStack bots_dontRenderIfArmIsCutOff(ItemStack pStack, AbstractClientPlayer pPlayer, float pPartialTicks, float pPitch, InteractionHand pHand) {
        if (pPlayer.getCapability(PlayerLimbsProvider.PLAYER_LIMBS).isPresent()) {
            if (!pPlayer.getCapability(PlayerLimbsProvider.PLAYER_LIMBS).resolve().get().hasMainArm() && pHand == InteractionHand.MAIN_HAND) {
                if (pPlayer.getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM).isPresent()) {
                    if (pPlayer.getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM).resolve().get().getProstheticArm().getItem() instanceof IAmProstheticItem prostheticItem) {
                        if (prostheticItem.renderItemStack(pStack,pPlayer)) {
                            return pStack;
                        }
                    }
                }
                return ItemStack.EMPTY;
            }
        }
        return pStack;
    }
}
