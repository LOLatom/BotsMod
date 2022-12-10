package com.thefreak.botsmod.util.packets;

import com.thefreak.botsmod.util.packets.interractionpackets.DivineKeyClick;
import com.thefreak.botsmod.util.packets.interractionpackets.LeftClickPacket;
import com.thefreak.botsmod.util.packets.interractionpackets.OpenCloseGodKHPacket;
import com.thefreak.botsmod.util.packets.interractionpackets.serverpackets.SomeoneClickedDivineKeyPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.logging.log4j.util.TriConsumer;

import java.awt.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BotsPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("botsmod", "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void init() {
        int ID = 0;
        
        //ServerBound

        //
        INSTANCE.messageBuilder(OpenCloseGodKHPacket.class,ID++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(OpenCloseGodKHPacket::encode)
                .decoder(OpenCloseGodKHPacket::decode)
                .consumer(OpenCloseGodKHPacket::handle)
                .add();

        INSTANCE.messageBuilder(LeftClickPacket.class,ID++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(LeftClickPacket::encode)
                .decoder(LeftClickPacket::decode)
                .consumer(LeftClickPacket::handle)
                .add();

        INSTANCE.messageBuilder(DivineKeyClick.class,ID++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(DivineKeyClick::encode)
                .decoder(DivineKeyClick::decode)
                .consumer(DivineKeyClick::handle)
                .add();


        //ClientBound

        INSTANCE.messageBuilder(SomeoneClickedDivineKeyPacket.class,ID++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(SomeoneClickedDivineKeyPacket::encode)
                .decoder(SomeoneClickedDivineKeyPacket::decode)
                .consumer(SomeoneClickedDivineKeyPacket::handle)
                .add();


    }


}
