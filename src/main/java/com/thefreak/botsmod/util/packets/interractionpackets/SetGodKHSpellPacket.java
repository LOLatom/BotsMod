package com.thefreak.botsmod.util.packets.interractionpackets;

import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import com.thefreak.botsmod.objects.items.loreandclueitems.GodKillerHand;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.function.Supplier;

public class SetGodKHSpellPacket {
    public final int ID;


    public SetGodKHSpellPacket( int ID) {
        this.ID = ID;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.ID);
    }

    public static SetGodKHSpellPacket decode(FriendlyByteBuf buffer) {
        int a = buffer.readInt();
        return new SetGodKHSpellPacket(a);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {

            ctx.get().enqueueWork(() ->
                    {
                        ItemStack stack =
                                ctx.get().getSender().getMainHandItem();
                        CompoundTag nbt = stack.getOrCreateTag();
                        nbt.putInt("spellid", this.ID);
                    }
            );

        }
        ctx.get().setPacketHandled(true);
    }
}
