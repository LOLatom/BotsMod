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

public class NextSpellPacket {

    public NextSpellPacket() {

    }

    public void encode(FriendlyByteBuf buffer) {

    }

    public static NextSpellPacket decode(FriendlyByteBuf buffer) {
        return new NextSpellPacket();
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                Player player = ctx.get().getSender();
                System.out.println("NEXT");
                ArrayList<ItemStack> invContainer = new ArrayList<>();
                ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
                CompoundTag nbt = stack.getOrCreateTag();
                int mode = nbt.getInt("handMode");
                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    if (player.getInventory().getItem(i).getItem() instanceof IAmSpellCard spellCard) {
                        if (spellCard.modeRequirement() == mode) invContainer.add(player.getInventory().getItem(i));
                    }
                }

                if (invContainer.size() - 1 > nbt.getInt("spellID")) {
                    nbt.putInt("spellID", nbt.getInt("spellID") + 1);
                }

            });

        }
    }
}
