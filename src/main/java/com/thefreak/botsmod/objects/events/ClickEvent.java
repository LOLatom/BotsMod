package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.client.gui.gkhguis.spellguis.SpellSelectionGUI;
import com.thefreak.botsmod.init.ItemInit;
import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import com.thefreak.botsmod.objects.items.loreandclueitems.GodKillerHand;
import com.thefreak.botsmod.spells.SpellIdentifier;
import com.thefreak.botsmod.spells.spellclass.AbstractSpell;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.LeftClickPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
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
        int mode = nbt.getInt("mode");
        int spellID = nbt.getInt("spellid");
        if (event.isAttack() && !player.isCrouching()) {
            if (handstack.getItem() instanceof GodKillerHand) {
                SpellIdentifier SI = new SpellIdentifier(finger1, finger2, finger3, finger4, blade, spellID, handstack, null);
                AbstractSpell spell = SI.getLeftCliclkSpellFromID(spellID);
                spell.startExecuting(player, ClassReferences.getClientLevel(), InteractionHand.MAIN_HAND,null,false);
            }
            BotsPacketHandler.INSTANCE.sendToServer(new LeftClickPacket());
        } else if (event.isAttack() && player.isCrouching()){
            if (handstack.getItem() instanceof GodKillerHand) {
                OpenMenu(finger1,finger2,finger3,finger4,blade,mode);
            }
        }
   }

   public static void OpenMenu(boolean f1, boolean f2, boolean f3, boolean f4, boolean b, int m) {
        if (f1&&f2&&f3&&f4&&!b&& m == 0) {
            ClassReferences.getClientMC().setScreen(new SpellSelectionGUI(0,0,2,1));
        } else if (f1&&f2&&f3&&f4&&b&& m == 0) {
           Player player = ClassReferences.getPlayer();
           Inventory inv = player.getInventory();
           int spell1 = 0;
           int spell2 = 0;
           int spell3 = 0;
           int spell4 = 0;
           for (int i = 0; i < inv.getContainerSize(); i++) {
               if (inv.getItem(i).getItem() == ItemInitNew.ENDER_BESTIAL_AMULET.get()) {
                   spell1 = 3;
               }
               if (inv.getItem(i).getItem() == ItemInitNew.CAVE_BESTIAL_AMULET.get()) {
                   spell2 = 4;
               }
               if (inv.getItem(i).getItem() == ItemInitNew.GROUND_BESTIAL_AMULET.get()) {
                   spell3 = 5;
               }
           }
           ClassReferences.getClientMC().setScreen(new SpellSelectionGUI(spell1,spell2,spell3,spell4));
       } else {
            ClassReferences.getClientMC().setScreen(new SpellSelectionGUI(0,0,0,0));

        }
   }

}
