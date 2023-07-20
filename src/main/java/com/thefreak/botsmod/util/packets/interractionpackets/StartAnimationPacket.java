package com.thefreak.botsmod.util.packets.interractionpackets;

import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import com.thefreak.botsmod.spells.implementations.IAmSpellCard;
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

public class StartAnimationPacket {

    private int ANIM_ID;
    public StartAnimationPacket(int animationID) {
        this.ANIM_ID = animationID;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.ANIM_ID);
    }

    public static StartAnimationPacket decode(FriendlyByteBuf buffer) {
        return new StartAnimationPacket(buffer.readInt());
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                ItemStack stack =
                        ctx.get().getSender().getMainHandItem();
                CompoundTag nbt = stack.getOrCreateTag();
                final int id = GeckoLibUtil.guaranteeIDForStack(stack, ctx.get().getSender().getLevel());
                final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> ctx.get().getSender());
                GeckoLibNetwork.syncAnimation(target, (ISyncable) ItemInitNew.GOD_KILLER_HAND.get(), id, this.ANIM_ID);
            });
        }}

}
