package com.thefreak.botsmod.util.packets.interractionpackets.serverpackets;

import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.util.capabilities.PlayerLimbsProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ArmCheckPackage {

    private boolean armState;

    private int id;

    public ArmCheckPackage(boolean armState, int id) {
        this.armState = armState;
        this.id = id;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBoolean(this.armState).writeInt(this.id);
    }

    public static ArmCheckPackage decode(FriendlyByteBuf buffer) {
        return new ArmCheckPackage(buffer.readBoolean(), buffer.readInt());
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> {
                ClassReferences.getClientLevel().getEntity(id).getCapability(PlayerLimbsProvider.PLAYER_LIMBS).ifPresent(playerLimbsCap -> {
                    if (!armState) {
                        playerLimbsCap.cutMainArm();
                    }
                });
            });}
        ctx.get().setPacketHandled(true);
    }
}
