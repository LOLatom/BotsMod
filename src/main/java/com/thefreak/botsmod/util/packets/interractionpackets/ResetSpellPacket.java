package com.thefreak.botsmod.util.packets.interractionpackets;

import com.thefreak.botsmod.objects.items.loreandclueitems.GodKillerHand;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ResetSpellPacket {
    public ResetSpellPacket() {

    }

    public void encode(FriendlyByteBuf buffer) {

    }

    public static ResetSpellPacket decode(FriendlyByteBuf buffer) {
        return new ResetSpellPacket();
    }


    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                Player player = ctx.get().getSender();
                System.out.println("RESET");

                for (int i = 0; i < player.getInventory().getContainerSize() - 1; i++) {
                    if (player.getInventory().getItem(i).getItem() instanceof GodKillerHand) {
                        ItemStack stack = player.getInventory().getItem(i);
                        CompoundTag nbt = stack.getOrCreateTag();
                        nbt.putInt("spellID", 0);
                    }
                }

            });

        }}
}
