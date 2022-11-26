package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.fluids.FluidInit;
import net.minecraft.core.BlockPos;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class InFluidEvent {

    @SubscribeEvent
    public static void inFluid(TickEvent.PlayerTickEvent event) {
        BlockPos pos = new BlockPos(event.player.getEyePosition());
        if (event.player.getLevel().getBlockState(pos).getBlock() == FluidInit.CELL_FLUID.get()) {
            event.player.setHealth(event.player.getHealth() < event.player.getMaxHealth() ? event.player.getHealth() + 1 : event.player.getHealth());
            event.player.setAirSupply(event.player.getMaxAirSupply());

        }

    }
}
