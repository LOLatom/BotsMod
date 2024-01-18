package com.thefreak.botsmod.util.botsevent;

import com.google.common.eventbus.DeadEvent;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.objects.events.JoinWorldEvent;
import com.thefreak.botsmod.util.capabilities.PlayerLimbsCap;
import com.thefreak.botsmod.util.capabilities.PlayerLimbsProvider;
import com.thefreak.botsmod.util.capabilities.PlayerProstheticArmCap;
import com.thefreak.botsmod.util.capabilities.PlayerProstheticArmProvider;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.serverpackets.ArmCheckPackage;
import com.thefreak.botsmod.util.packets.interractionpackets.serverpackets.ProstheticCheckPackage;
import net.minecraft.network.Connection;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RegisterCapEvent {


    @SubscribeEvent
    public static void onAttachCapPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player player) {
            if (!player.getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM).isPresent()) {
                event.addCapability(new ResourceLocation(BotsMod.MOD_ID, "prosthetic"), new PlayerProstheticArmProvider());
            }
            if (!player.getCapability(PlayerLimbsProvider.PLAYER_LIMBS).isPresent()) {
                event.addCapability(new ResourceLocation(BotsMod.MOD_ID, "limbs"), new PlayerLimbsProvider());
            }

        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
           event.getOriginal().getCapability(PlayerLimbsProvider.PLAYER_LIMBS).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerLimbsProvider.PLAYER_LIMBS).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });

        }
    }

    @SubscribeEvent
    public static void updateOnTracking(PlayerEvent.StartTracking event) {
        if (event.getTarget() instanceof Player) {
            System.out.println("IsPlayer");
            event.getPlayer().getCapability(PlayerLimbsProvider.PLAYER_LIMBS).ifPresent(playerLimbsCap -> {
                BotsPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getTarget()),
                        new ArmCheckPackage(playerLimbsCap.hasMainArm(), event.getPlayer().getId()));
            });
            event.getPlayer().getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM).ifPresent(playerProstheticArmCap -> {
                BotsPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getTarget()),
                        new ProstheticCheckPackage(playerProstheticArmCap.getProstheticArm(), event.getPlayer().getId()));
            });
        }

    }

    @SubscribeEvent
    public static void onRegisterCap(RegisterCapabilitiesEvent event) {
        event.register(PlayerProstheticArmCap.class);
        event.register(PlayerLimbsCap.class);
    }


    @SubscribeEvent
    public static void onDeathCaps(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            player.getCapability(PlayerLimbsProvider.PLAYER_LIMBS).ifPresent(playerLimbsCap -> {
                playerLimbsCap.restoreMainArm();
            });
        }
    }

    @SubscribeEvent
    public static void onJoinCaps(EntityJoinWorldEvent event) {
        if (!event.getWorld().isClientSide) {
            if (event.getEntity() instanceof Player player) {
               player.getCapability(PlayerLimbsProvider.PLAYER_LIMBS).ifPresent(playerLimbsCap -> {
                    BotsPacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new ArmCheckPackage(playerLimbsCap.hasMainArm(), player.getId()));
                });
                player.getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM).ifPresent(playerProstheticArmCap -> {
                    BotsPacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new ProstheticCheckPackage(playerProstheticArmCap.getProstheticArm(), player.getId()));
                });
            }
        }
    }
}
