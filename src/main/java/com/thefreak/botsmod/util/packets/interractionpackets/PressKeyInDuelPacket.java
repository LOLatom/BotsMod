package com.thefreak.botsmod.util.packets.interractionpackets;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PressKeyInDuelPacket {
    private int key;

    public PressKeyInDuelPacket(int key) {
        this.key = key;
    }


    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(key);
    }

    public static PressKeyInDuelPacket decode(FriendlyByteBuf buffer) {
        return new PressKeyInDuelPacket(buffer.readInt());
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {


            });

        }
    }
}
