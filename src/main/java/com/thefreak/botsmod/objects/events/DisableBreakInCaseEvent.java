package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.API.IAmProstheticItem;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.util.capabilities.PlayerLimbsProvider;
import com.thefreak.botsmod.util.capabilities.PlayerProstheticArmProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DisableBreakInCaseEvent {

    @SubscribeEvent
    public static void onBlockBreaking(BlockEvent.BreakEvent event) {
        event.getPlayer().getCapability(PlayerLimbsProvider.PLAYER_LIMBS).ifPresent(playerLimbsCap -> {
            if (!playerLimbsCap.hasMainArm()) {
                event.getPlayer().getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM).ifPresent(playerProstheticArmCap -> {
                    if (playerProstheticArmCap.getProstheticArm().getItem() instanceof IAmProstheticItem prostheticItem) {
                        if (prostheticItem.canAttack(event.getPlayer())) {

                        } else {
                            event.setCanceled(true);
                        }
                    } else {
                        event.setCanceled(true);
                    }
                });
            }
        });
    }


}
