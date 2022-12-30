package com.thefreak.botsmod.util.packets.interractionpackets.serverpackets;

import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.client.gui.gkhguis.FingerScreen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenFingerGUI {

    public void encode(FriendlyByteBuf buffer) {

    }

    public static OpenFingerGUI decode(FriendlyByteBuf buffer) {
        return new OpenFingerGUI();
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> handleClientOFG.handlePacket(ctx)));}
        ctx.get().setPacketHandled(true);
    }

    public static class handleClientOFG {
        public static void handlePacket(Supplier<NetworkEvent.Context> ctx) {
            ClassReferences.getClientMC().setScreen(new FingerScreen());
        }
    }
}
