package com.thefreak.botsmod.objects.events.capabilities;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.util.capabilities.CapabilitySelectedSpell;
import com.thefreak.botsmod.util.capabilities.ISelectedSpellCapability;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RegisterCapabilities {

    @SubscribeEvent
    public void registerCaps(RegisterCapabilitiesEvent event) {
        CapabilitySelectedSpell.register(event);
    }


}
