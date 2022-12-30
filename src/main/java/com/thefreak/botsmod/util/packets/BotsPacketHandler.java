package com.thefreak.botsmod.util.packets;

import com.thefreak.botsmod.util.packets.interractionpackets.DivineKeyClick;
import com.thefreak.botsmod.util.packets.interractionpackets.LeftClickPacket;
import com.thefreak.botsmod.util.packets.interractionpackets.OpenCloseGodKHPacket;
import com.thefreak.botsmod.util.packets.interractionpackets.SetGodKHSpellPacket;
import com.thefreak.botsmod.util.packets.interractionpackets.serverpackets.OpenFingerGUI;
import com.thefreak.botsmod.util.packets.interractionpackets.serverpackets.OpenSpellGUI;
import com.thefreak.botsmod.util.packets.interractionpackets.serverpackets.SomeoneClickedDivineKeyPacket;
import com.thefreak.botsmod.util.packets.interractionpackets.serverpackets.UpdateBlocksAt;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

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

        INSTANCE.messageBuilder(SetGodKHSpellPacket.class,ID++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(SetGodKHSpellPacket::encode)
                .decoder(SetGodKHSpellPacket::decode)
                .consumer(SetGodKHSpellPacket::handle)
                .add();


        //ClientBound

        INSTANCE.messageBuilder(SomeoneClickedDivineKeyPacket.class,ID++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(SomeoneClickedDivineKeyPacket::encode)
                .decoder(SomeoneClickedDivineKeyPacket::decode)
                .consumer(SomeoneClickedDivineKeyPacket::handle)
                .add();

        INSTANCE.messageBuilder(OpenFingerGUI.class,ID++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(OpenFingerGUI::encode)
                .decoder(OpenFingerGUI::decode)
                .consumer(OpenFingerGUI::handle)
                .add();
        INSTANCE.messageBuilder(UpdateBlocksAt.class,ID++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(UpdateBlocksAt::encode)
                .decoder(UpdateBlocksAt::decode)
                .consumer(UpdateBlocksAt::handle)
                .add();


    }


}
