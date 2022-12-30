package com.thefreak.botsmod.util.packets.interractionpackets.serverpackets;

import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.client.gui.gkhguis.FingerScreen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenSpellGUI {
    public int spellGUIID;

    public OpenSpellGUI(int spellGUIID) {
        this.spellGUIID = spellGUIID;
    }

    public void encode(FriendlyByteBuf buffer) {
            buffer.writeInt(this.spellGUIID);
    }

    public static OpenSpellGUI decode(FriendlyByteBuf buffer) {
        int ID = buffer.readInt();
        return new OpenSpellGUI(ID);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> {
                    if (this.spellGUIID == 1) {

                    } else if (this.spellGUIID == 2) {

                    } else if (this.spellGUIID == 3) {

                    } else {

                    }
            });}
        ctx.get().setPacketHandled(true);
    }
}
