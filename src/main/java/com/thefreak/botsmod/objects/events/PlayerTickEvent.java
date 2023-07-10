package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.BotsMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerTickEvent {
    @SubscribeEvent
    public static void spellTicking(TickEvent.PlayerTickEvent event) {

    }
}
