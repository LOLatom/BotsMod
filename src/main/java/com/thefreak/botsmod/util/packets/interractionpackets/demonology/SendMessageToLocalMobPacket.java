package com.thefreak.botsmod.util.packets.interractionpackets.demonology;

import com.thefreak.botsmod.API.IAmDivine;
import com.thefreak.botsmod.entities.misc.ShadowLightningBolt;
import com.thefreak.botsmod.entities.util.BotsMonster;
import com.thefreak.botsmod.init.ModEntityTypes;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.DivineKeyClick;
import com.thefreak.botsmod.util.packets.interractionpackets.serverpackets.SomeoneClickedDivineKeyPacket;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.List;
import java.util.function.Supplier;

public class SendMessageToLocalMobPacket {
    public final String msg;

    public SendMessageToLocalMobPacket(String msg) {
        this.msg = msg;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUtf(this.msg);
    }

    public static SendMessageToLocalMobPacket decode(FriendlyByteBuf buffer) {
        return new SendMessageToLocalMobPacket(buffer.readUtf());
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                List<BotsMonster> monstersList = ctx.get().getSender().getLevel().getEntitiesOfClass(BotsMonster.class, ctx.get().getSender().getBoundingBox().inflate(4));
                for (BotsMonster monster: monstersList) {
                    monster.worded(this.msg, ctx.get().getSender());
                }
            });}
        ctx.get().setPacketHandled(true);
    }
}
