package com.thefreak.botsmod.util.packets.interractionpackets.serverpackets;

import com.thefreak.botsmod.API.IAmDivine;
import com.thefreak.botsmod.API.IDuelMode;
import com.thefreak.botsmod.ClassReferences;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SetDuelPacket {
    private boolean duel;
    private int entityID;
    public SetDuelPacket(boolean duel, int entityID) {
        this.duel = duel;
        this.entityID = entityID;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBoolean(duel);
        buffer.writeInt(entityID);
    }

    public static SetDuelPacket decode(FriendlyByteBuf buffer) {
        return new SetDuelPacket(buffer.readBoolean(),buffer.readInt());
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> {
                if (ClassReferences.getPlayer() instanceof IDuelMode duelMode) {
                    duelMode.setInDuel(this.duel, this.entityID);
                    System.out.println("DUEL!");
                }
            });}
        ctx.get().setPacketHandled(true);
    }
}
