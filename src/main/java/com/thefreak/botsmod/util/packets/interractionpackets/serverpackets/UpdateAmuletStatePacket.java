package com.thefreak.botsmod.util.packets.interractionpackets.serverpackets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateAmuletStatePacket {
    public int ID;
    public UpdateAmuletStatePacket(int id) {
        this.ID = id;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.ID);
    }

    public static UpdateAmuletStatePacket decode(FriendlyByteBuf buffer) {
        int IDs = buffer.readInt();
        return new UpdateAmuletStatePacket(IDs);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> UpdateAmuletStatePacket.updateAmuletClientPacket.handlePacket(this.ID,ctx)));

        }
        ctx.get().setPacketHandled(true);
    }

    public static class updateAmuletClientPacket {
        public static void handlePacket(int ID, Supplier<NetworkEvent.Context> ctx) {

        }
    }
}
