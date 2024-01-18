package com.thefreak.botsmod.init;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.objects.containers.screens.SpecialisedCraftingScreen;
import com.thefreak.botsmod.objects.screens.ArmFactoryScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ModMenuScreens {

    @SubscribeEvent
    public void registerMenuScreens(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModContainerTypes.SPECIALISED_CRAFTING_MENU.get(), SpecialisedCraftingScreen::new);
            MenuScreens.register(ModContainerTypes.ARM_FACTORY_MENU.get(), ArmFactoryScreen::new);

        });
    }

}
