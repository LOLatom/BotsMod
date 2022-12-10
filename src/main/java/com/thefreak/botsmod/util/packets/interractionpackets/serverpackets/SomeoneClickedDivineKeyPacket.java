package com.thefreak.botsmod.util.packets.interractionpackets.serverpackets;

import com.thefreak.botsmod.API.IAmDivine;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.util.packets.interractionpackets.DivineKeyClick;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SomeoneClickedDivineKeyPacket {
    public int ID;
    public boolean divineState;

    public SomeoneClickedDivineKeyPacket(int ID, boolean divine) {
        this.ID = ID;
        this.divineState = divine;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.ID);
        buffer.writeBoolean(divineState);
    }

    public static SomeoneClickedDivineKeyPacket decode(FriendlyByteBuf buffer) {
        return new SomeoneClickedDivineKeyPacket(buffer.readInt(),buffer.readBoolean());
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> {
                if (ClassReferences.getClientLevel().getEntity(this.ID) instanceof IAmDivine divine) {
                    divine.setDivine(this.divineState);
                }
            });}
        ctx.get().setPacketHandled(true);
    }
}
