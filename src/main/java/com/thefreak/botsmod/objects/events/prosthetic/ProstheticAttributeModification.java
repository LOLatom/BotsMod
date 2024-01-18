package com.thefreak.botsmod.objects.events.prosthetic;

import com.thefreak.botsmod.API.IAmProstheticComponent;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.objects.items.organs.tools.arms.MechanicalArm;
import com.thefreak.botsmod.util.capabilities.PlayerProstheticArmProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemStackHandler;

import java.io.File;
import java.security.DrbgParameters;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ProstheticAttributeModification {

    @SubscribeEvent
    public static void modifyAttributes(TickEvent.PlayerTickEvent event) {
        event.player.getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM).ifPresent((playerProstheticArmCap) ->{
                float reachNew = 5.0F;
                float damageNew = 2.0F;
                if ((playerProstheticArmCap.getProstheticArm().getItem() instanceof MechanicalArm)) {

                CompoundTag nbt = playerProstheticArmCap.getProstheticArm().getOrCreateTag();
                ItemStackHandler parts = new ItemStackHandler(6);
                parts.deserializeNBT(nbt.getCompound("parts"));
                for (int i = 0; i < parts.getSlots(); i++) {
                    if (parts.getStackInSlot(i).getItem() instanceof IAmProstheticComponent pc) {
                        reachNew += pc.getReachAddition();
                        damageNew += pc.getDamageAddition();
                    }
                }
                if (event.player.getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue() != reachNew ||
                        event.player.getAttribute(Attributes.ATTACK_DAMAGE).getValue() != damageNew) {

                    event.player.getAttribute(ForgeMod.REACH_DISTANCE.get()).setBaseValue(reachNew);
                    event.player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(damageNew);
                    }
                }

        });
    }


}
