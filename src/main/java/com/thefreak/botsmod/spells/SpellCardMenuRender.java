package com.thefreak.botsmod.spells;

import com.thefreak.botsmod.BotsMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class SpellCardMenuRender {

        @SubscribeEvent
        public static void onTickRenderSpellCard(TickEvent.ClientTickEvent event) {

        }

}
