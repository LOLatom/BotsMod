package com.thefreak.botsmod.util.packets.interractionpackets;

import com.thefreak.botsmod.spells.implementations.IAmSpellCard;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.function.Supplier;

public class PreviousSpellPacket {

    public PreviousSpellPacket() {

    }


    public void encode(FriendlyByteBuf buffer) {

    }

    public static PreviousSpellPacket decode(FriendlyByteBuf buffer) {
        return new PreviousSpellPacket();
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                Player player = ctx.get().getSender();
                System.out.println("PREVIOUS");
                ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
                CompoundTag nbt = stack.getOrCreateTag();
                if (nbt.getInt("spellID") != 0 && nbt.getInt("spellID") > 0) {
                    nbt.putInt("spellID", nbt.getInt("spellID") - 1);


                }
            });

        }
    }
}
