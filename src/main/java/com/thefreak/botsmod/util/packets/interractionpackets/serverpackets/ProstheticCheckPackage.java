package com.thefreak.botsmod.util.packets.interractionpackets.serverpackets;

import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.util.capabilities.PlayerLimbsProvider;
import com.thefreak.botsmod.util.capabilities.PlayerProstheticArmProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ProstheticCheckPackage {

    private ItemStack prostheticStack;

    private int id;

    public ProstheticCheckPackage(ItemStack prostheticStack, int id) {
        this.prostheticStack = prostheticStack;
        this.id = id;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeItem(this.prostheticStack).writeInt(this.id);
    }

    public static ProstheticCheckPackage decode(FriendlyByteBuf buffer) {
        return new ProstheticCheckPackage(buffer.readItem(), buffer.readInt());
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> {
                ClassReferences.getClientLevel().getEntity(id).getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM).ifPresent(prostheticPlayer -> {
                        if (prostheticPlayer.getProstheticArm() != prostheticStack) {
                            prostheticPlayer.setProstheticArm(prostheticStack);
                        }
                });
            });}
        ctx.get().setPacketHandled(true);
    }
}
