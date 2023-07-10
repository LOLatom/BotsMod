package com.thefreak.botsmod.util.packets.interractionpackets.serverpackets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateBlocksAt {
    public BlockPos pos;
    public UpdateBlocksAt(BlockPos pos) {
        this.pos = pos;
    }
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(this.pos);
    }

    public static UpdateBlocksAt decode(FriendlyByteBuf buffer) {
        BlockPos pos = buffer.readBlockPos();
        return new UpdateBlocksAt(pos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> updateBlockOnClientPacket.handlePacket(this.pos,ctx)));}
        ctx.get().setPacketHandled(true);
    }

    public static class updateBlockOnClientPacket {
        public static void handlePacket(BlockPos pos, Supplier<NetworkEvent.Context> ctx) {
            
        }
    }
}
