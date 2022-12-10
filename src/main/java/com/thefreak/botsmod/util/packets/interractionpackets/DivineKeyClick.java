package com.thefreak.botsmod.util.packets.interractionpackets;

import com.thefreak.botsmod.API.IAmDivine;
import com.thefreak.botsmod.entities.misc.ShadowLightningBolt;
import com.thefreak.botsmod.init.ModEntityTypes;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.serverpackets.SomeoneClickedDivineKeyPacket;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class DivineKeyClick {

    public DivineKeyClick() {

    }

    public void encode(FriendlyByteBuf buffer) {
    }

    public static DivineKeyClick decode(FriendlyByteBuf buffer) {
        return new DivineKeyClick();
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                if (ctx.get().getSender() instanceof IAmDivine divine) {
                    divine.setDivine(!divine.isDivine());
                    ctx.get().getSender().getLevel().addParticle(ParticleTypes.CLOUD,ctx.get().getSender().getX(),ctx.get().getSender().getY() + 0.8F,ctx.get().getSender().getZ()
                    ,0,-0.1D,0);
                    ctx.get().getSender().getLevel().addParticle(ParticleTypes.CLOUD,ctx.get().getSender().getX(),ctx.get().getSender().getY() + 0.8F,ctx.get().getSender().getZ()
                            ,0,-0.1D,0);
                    ctx.get().getSender().getLevel().addParticle(ParticleTypes.CLOUD,ctx.get().getSender().getX(),ctx.get().getSender().getY() + 0.8F,ctx.get().getSender().getZ()
                            ,0,-0.1D,0);







                    int ID = ctx.get().getSender().getId();
                    boolean state = divine.isDivine();
                        ShadowLightningBolt lightningBolt = new ShadowLightningBolt(ModEntityTypes.SHADOW_LIGHTNING_BOLT.get(), ctx.get().getSender().getLevel());
                        lightningBolt.setPos(ctx.get().getSender().getX(),
                                ctx.get().getSender().getY(),ctx.get().getSender().getZ());
                        ctx.get().getSender().getLevel().addFreshEntity(lightningBolt);
                    BotsPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> ctx.get().getSender()),new SomeoneClickedDivineKeyPacket(ID,state));
                }
            });}
        ctx.get().setPacketHandled(true);
    }
}
