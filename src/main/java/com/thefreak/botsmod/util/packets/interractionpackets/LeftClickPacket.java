package com.thefreak.botsmod.util.packets.interractionpackets;

import com.thefreak.botsmod.objects.items.loreandclueitems.GodKillerHand;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class LeftClickPacket {

    public LeftClickPacket() {
    }

    public void encode(FriendlyByteBuf buffer) {
    }

    public static LeftClickPacket decode(FriendlyByteBuf buffer) {
        return new LeftClickPacket();
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ItemStack stack = ctx.get().getSender().getMainHandItem();
            CompoundTag nbt = stack.getOrCreateTag();

            ctx.get().enqueueWork(() -> {
            gkhInteractions(ctx);
            });}
        ctx.get().setPacketHandled(true);
    }

    public void gkhInteractions(Supplier<NetworkEvent.Context> ctx) {

    }
}
