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

public class OpenCloseGodKHPacket {

    public final int ID;


    public OpenCloseGodKHPacket( int ID) {

    this.ID = ID;
    }

    public void encode(FriendlyByteBuf buffer) {

        buffer.writeInt(this.ID);
    }

    public static OpenCloseGodKHPacket decode(FriendlyByteBuf buffer) {

        int a = buffer.readInt();
        return new OpenCloseGodKHPacket(a);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {

            ctx.get().enqueueWork(() ->
                    {

                        ItemStack stack =
                                ctx.get().getSender().getMainHandItem();
                        CompoundTag nbt = stack.getOrCreateTag();
                        final int id = GeckoLibUtil.guaranteeIDForStack(stack, ctx.get().getSender().getLevel());
                        final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> ctx.get().getSender());
                        //final PacketDistributor.PacketTarget target = PacketDistributor.PLAYER.with(() -> ctx.get().getSender());

                            if (this.ID == 1) {

                                nbt.putBoolean("firstfing", !nbt.getBoolean("firstfing"));
                                GeckoLibNetwork.syncAnimation(target, (ISyncable) ItemInitNew.GOD_KILLER_HAND.get(), id, !nbt.getBoolean("firstfing") ?
                                        GodKillerHand.C1 : GodKillerHand.F1);
                                nbt.putInt("spellid", 0);

                            } else if (this.ID == 2) {

                                nbt.putBoolean("secondfing", !nbt.getBoolean("secondfing"));
                                GeckoLibNetwork.syncAnimation(target, (ISyncable) ItemInitNew.GOD_KILLER_HAND.get(), id, !nbt.getBoolean("secondfing") ?
                                        GodKillerHand.C2 : GodKillerHand.F2);
                                nbt.putInt("spellid", 0);

                            } else if (this.ID == 3) {

                                nbt.putBoolean("thirdfing", !nbt.getBoolean("thirdfing"));
                                GeckoLibNetwork.syncAnimation(target, (ISyncable) ItemInitNew.GOD_KILLER_HAND.get(), id, !nbt.getBoolean("thirdfing") ?
                                        GodKillerHand.C3 : GodKillerHand.F3);
                                nbt.putInt("spellid", 0);

                            } else if (this.ID == 4) {

                                nbt.putBoolean("fourthfing", !nbt.getBoolean("fourthfing"));
                                GeckoLibNetwork.syncAnimation(target, (ISyncable) ItemInitNew.GOD_KILLER_HAND.get(), id, !nbt.getBoolean("fourthfing") ?
                                        GodKillerHand.C4 : GodKillerHand.F4);
                                nbt.putInt("spellid", 0);

                            } else if (this.ID == 5) {

                                nbt.putBoolean("blade", !nbt.getBoolean("blade"));
                                GeckoLibNetwork.syncAnimation(target, (ISyncable) ItemInitNew.GOD_KILLER_HAND.get(), id, !nbt.getBoolean("blade") ?
                                        GodKillerHand.CB : GodKillerHand.BLADE_OUT);
                                GeckoLibNetwork.syncAnimation(target, (ISyncable) ItemInitNew.GOD_KILLER_HAND.get(), id,
                                        GodKillerHand.ARM);

                                nbt.putInt("spellid", 0);

                            }


                    }
            );

        }
        ctx.get().setPacketHandled(true);
    }
}
