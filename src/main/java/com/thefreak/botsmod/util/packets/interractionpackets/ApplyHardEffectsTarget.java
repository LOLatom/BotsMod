package com.thefreak.botsmod.util.packets.interractionpackets;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ApplyHardEffectsTarget {
    //player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 40,1));


    public ApplyHardEffectsTarget(Player targetPlayer) {

    }

    public void encode(FriendlyByteBuf buffer) {

    }

    public static LeftClickPacket decode(FriendlyByteBuf buffer) {
        return new LeftClickPacket();
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {


            ctx.get().enqueueWork(() -> {

            });}
        ctx.get().setPacketHandled(true);
    }

}
