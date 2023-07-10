package com.thefreak.botsmod.util.packets.interractionpackets.serverpackets;

import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.client.gui.dialogue.DialogueGUI;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenDialogueGUI {
    public int dialogue;

    public OpenDialogueGUI(int dialogueID) {
        this.dialogue = dialogueID;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.dialogue);
    }

    public static OpenDialogueGUI decode(FriendlyByteBuf buffer) {
        int d = buffer.readInt();
        return new OpenDialogueGUI(d);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {

            ctx.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> handleClientOFG.handlePacket(this.dialogue,ctx)));}
        ctx.get().setPacketHandled(true);
    }

    public static class handleClientOFG {


        public static void handlePacket(int ID,Supplier<NetworkEvent.Context> ctx) {
            ResourceLocation texture = new ResourceLocation("botsmod:","textures/gui/dialogue/krasia_icon.png");

            ClassReferences.getClientMC().setScreen(new DialogueGUI(texture,"","",""));
        }
    }
}
