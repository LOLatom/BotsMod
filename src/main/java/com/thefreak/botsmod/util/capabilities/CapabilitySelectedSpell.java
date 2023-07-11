package com.thefreak.botsmod.util.capabilities;


import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class CapabilitySelectedSpell {

    public static Capability<ISelectedSpellCapability> SPELL_SELECT = CapabilityManager.get(new CapabilityToken<>(){});

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(ISelectedSpellCapability.class);
    }

}
