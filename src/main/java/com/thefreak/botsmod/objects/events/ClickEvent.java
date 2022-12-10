package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.objects.items.loreandclueitems.GodKillerHand;
import com.thefreak.botsmod.spells.SpellIdentifier;
import com.thefreak.botsmod.spells.spellclass.AbstractSpell;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.LeftClickPacket;
import com.thefreak.botsmod.util.packets.interractionpackets.OpenCloseGodKHPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClickEvent {

    @SubscribeEvent
    public static void leftClickEvent(InputEvent.ClickInputEvent event) {
        InteractionHand hand = event.getHand();
        Player player = ClassReferences.getPlayer();
        ItemStack handstack = player.getItemInHand(hand);
        CompoundTag nbt = handstack.getOrCreateTag();
        boolean finger1 = nbt.getBoolean("firstfing");
        boolean finger2 = nbt.getBoolean("secondfing");
        boolean finger3 = nbt.getBoolean("thirdfing");
        boolean finger4 = nbt.getBoolean("fourthfing");
        boolean blade = nbt.getBoolean("blade");
        int spellID = nbt.getInt("spellid");
        if (event.isAttack() && !player.isCrouching()) {
            if (handstack.getItem() instanceof GodKillerHand) {
                SpellIdentifier SI = new SpellIdentifier(finger1, finger2, finger3, finger4, blade, spellID, handstack);
                AbstractSpell spell = SI.getLeftCliclkSpellFromID(spellID);
                spell.startExecuting(player, ClassReferences.getClientLevel(), InteractionHand.MAIN_HAND);
            }
            BotsPacketHandler.INSTANCE.sendToServer(new LeftClickPacket());
        }


   }

}
