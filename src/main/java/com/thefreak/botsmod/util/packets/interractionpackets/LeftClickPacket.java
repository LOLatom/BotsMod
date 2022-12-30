package com.thefreak.botsmod.util.packets.interractionpackets;

import com.thefreak.botsmod.objects.items.loreandclueitems.GodKillerHand;
import com.thefreak.botsmod.spells.SpellIdentifier;
import com.thefreak.botsmod.spells.spellclass.AbstractSpell;
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
        ItemStack stack = ctx.get().getSender().getMainHandItem();
        CompoundTag nbt = stack.getOrCreateTag();
        boolean finger1 = nbt.getBoolean("firstfing");
        boolean finger2 = nbt.getBoolean("secondfing");
        boolean finger3 = nbt.getBoolean("thirdfing");
        boolean finger4 = nbt.getBoolean("fourthfing");
        boolean blade = nbt.getBoolean("blade");
        int spellID = nbt.getInt("spellid");

        if (stack.getItem() instanceof GodKillerHand) {
            if (ctx.get().getSender().isCrouching()) {

            } else {
                SpellIdentifier SI = new SpellIdentifier(finger1, finger2, finger3, finger4, blade, spellID, stack,null);
                AbstractSpell spell = SI.getLeftCliclkSpellFromID(spellID);
                spell.startExecuting(ctx.get().getSender(), ctx.get().getSender().level, InteractionHand.MAIN_HAND,null,false);
            }
        }
    }
}
