package com.thefreak.botsmod.util.packets;

import com.thefreak.botsmod.util.packets.interractionpackets.*;
import com.thefreak.botsmod.util.packets.interractionpackets.serverpackets.*;
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

        INSTANCE.messageBuilder(NextSpellPacket.class,ID++,NetworkDirection.PLAY_TO_SERVER)
                .encoder(NextSpellPacket::encode)
                .decoder(NextSpellPacket::decode)
                .consumer(NextSpellPacket::handle)
                .add();

        INSTANCE.messageBuilder(PreviousSpellPacket.class,ID++,NetworkDirection.PLAY_TO_SERVER)
                .encoder(PreviousSpellPacket::encode)
                .decoder(PreviousSpellPacket::decode)
                .consumer(PreviousSpellPacket::handle)
                .add();

        INSTANCE.messageBuilder(ResetSpellPacket.class,ID++,NetworkDirection.PLAY_TO_SERVER)
                .encoder(ResetSpellPacket::encode)
                .decoder(ResetSpellPacket::decode)
                .consumer(ResetSpellPacket::handle)
                .add();


        //ClientBound

        INSTANCE.messageBuilder(SomeoneClickedDivineKeyPacket.class,ID++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(SomeoneClickedDivineKeyPacket::encode)
                .decoder(SomeoneClickedDivineKeyPacket::decode)
                .consumer(SomeoneClickedDivineKeyPacket::handle)
                .add();
        INSTANCE.messageBuilder(UpdateBlocksAt.class,ID++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(UpdateBlocksAt::encode)
                .decoder(UpdateBlocksAt::decode)
                .consumer(UpdateBlocksAt::handle)
                .add();
        INSTANCE.messageBuilder(OpenDialogueGUI.class,ID++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(OpenDialogueGUI::encode)
                .decoder(OpenDialogueGUI::decode)
                .consumer(OpenDialogueGUI::handle)
                .add();


    }


}
