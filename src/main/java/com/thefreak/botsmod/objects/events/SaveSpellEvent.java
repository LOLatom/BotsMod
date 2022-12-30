package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.BotsMod;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SaveSpellEvent {

    public static void saveSpell(ItemEvent event) {

    }
}
