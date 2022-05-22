package com.thefreak.botsmod.objects.items.Capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class GeoriteCrystalCapabilities {
    @CapabilityInject(GeoriteSoul.class)
    public static Capability<GeoriteSoul> CAPABILITY_SOUL = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(
                GeoriteSoul.class,
                new GeoriteSoul.GeoriteSoulNBTStore(),
                GeoriteSoul::defaultInstance
        );
    }
}
