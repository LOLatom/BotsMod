package com.thefreak.botsmod.client.gui.gkhguis.spellguis;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import com.thefreak.botsmod.objects.items.loreandclueitems.GodKillerHand;
import com.thefreak.botsmod.spells.SpellIdentifier;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.OpenCloseGodKHPacket;
import com.thefreak.botsmod.util.packets.interractionpackets.SetGodKHSpellPacket;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class SavedSpellSelectionGUI extends Screen {
    public int s1;
    public int s2;
    public int s3;
    public int s4;

    private static final ResourceLocation INDICATOR = new ResourceLocation(BotsMod.MOD_ID,"textures/gui/spell/spell_indicator.png");

    public SavedSpellSelectionGUI(int firstspell, int secondspell, int thirdspell, int fourthspell) {
        super(TextComponent.EMPTY);
        this.s1 = firstspell;
        this.s2 = secondspell;
        this.s3 = thirdspell;
        this.s4 = fourthspell;
    }

    @Override
    protected void init() {

        SpellIdentifier nopSI = new SpellIdentifier(false,false,false,false,false,0, ItemInitNew.GOD_KILLER_HAND.get().getDefaultInstance(), null);
        this.addRenderableWidget(new ImageButton(this.width / 2 - 48 - 49, this.height/2 + 2- 24, 48,48,0,0,48,nopSI.getIconFromID(s1),48,96,(button) -> {
            if (ClassReferences.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof GodKillerHand godKillerHand) {
                CompoundTag nbt = ClassReferences.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag();
                boolean finger1 = nbt.getBoolean("firstfing");
                boolean finger2 = nbt.getBoolean("secondfing");
                boolean finger3 = nbt.getBoolean("thirdfing");
                boolean finger4 = nbt.getBoolean("fourthfing");
                boolean blade = nbt.getBoolean("blade");
                int mode = nbt.getInt("mode");
                int spellID = nbt.getInt("spellid");
                if (finger1 != nopSI.getSpellFromID(s1).activeFinger1()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(1));
                }
                if (finger2 != nopSI.getSpellFromID(s1).activeFinger2()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(2));
                }
                if (finger3 != nopSI.getSpellFromID(s1).activeFinger3()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(3));
                }
                if (finger4 != nopSI.getSpellFromID(s1).activeFinger4()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(4));
                }
                if (blade != nopSI.getSpellFromID(s1).activeBlade()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(5));
                }
                BotsPacketHandler.INSTANCE.sendToServer(new SetGodKHSpellPacket(s1));
            }
            this.removed();
        }));
        this.addRenderableWidget(new ImageButton(this.width / 2 - 49, this.height/2 + 2- 24, 48,48,0,0,48,nopSI.getIconFromID(s2),48,96,(button) -> {
            if (ClassReferences.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof GodKillerHand godKillerHand) {
                CompoundTag nbt = ClassReferences.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag();
                boolean finger1 = nbt.getBoolean("firstfing");
                boolean finger2 = nbt.getBoolean("secondfing");
                boolean finger3 = nbt.getBoolean("thirdfing");
                boolean finger4 = nbt.getBoolean("fourthfing");
                boolean blade = nbt.getBoolean("blade");
                int mode = nbt.getInt("mode");
                int spellID = nbt.getInt("spellid");
                if (finger1 != nopSI.getSpellFromID(s2).activeFinger1()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(1));
                }
                if (finger2 != nopSI.getSpellFromID(s2).activeFinger2()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(2));
                }
                if (finger3 != nopSI.getSpellFromID(s2).activeFinger3()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(3));
                }
                if (finger4 != nopSI.getSpellFromID(s2).activeFinger4()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(4));
                }
                if (blade != nopSI.getSpellFromID(s2).activeBlade()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(5));
                }
                BotsPacketHandler.INSTANCE.sendToServer(new SetGodKHSpellPacket(s2));
            }            this.removed();
        }));
        this.addRenderableWidget(new ImageButton(this.width / 2 , this.height/2 + 2 - 24, 48,48,0,0,48,nopSI.getIconFromID(s3),48,96,(button) -> {
            if (ClassReferences.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof GodKillerHand godKillerHand) {
                CompoundTag nbt = ClassReferences.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag();
                boolean finger1 = nbt.getBoolean("firstfing");
                boolean finger2 = nbt.getBoolean("secondfing");
                boolean finger3 = nbt.getBoolean("thirdfing");
                boolean finger4 = nbt.getBoolean("fourthfing");
                boolean blade = nbt.getBoolean("blade");
                int mode = nbt.getInt("mode");
                int spellID = nbt.getInt("spellid");
                if (finger1 != nopSI.getSpellFromID(s3).activeFinger1()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(1));
                }
                if (finger2 != nopSI.getSpellFromID(s3).activeFinger2()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(2));
                }
                if (finger3 != nopSI.getSpellFromID(s3).activeFinger3()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(3));
                }
                if (finger4 != nopSI.getSpellFromID(s3).activeFinger4()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(4));
                }
                if (blade != nopSI.getSpellFromID(s3).activeBlade()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(5));
                }
                BotsPacketHandler.INSTANCE.sendToServer(new SetGodKHSpellPacket(s3));
            }            this.removed();
        }));
        this.addRenderableWidget(new ImageButton(this.width / 2 + 48, this.height/2 + 2- 24, 48,48,0,0,48,nopSI.getIconFromID(s4),48,96,(button) -> {
            if (ClassReferences.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof GodKillerHand godKillerHand) {
                CompoundTag nbt = ClassReferences.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag();
                boolean finger1 = nbt.getBoolean("firstfing");
                boolean finger2 = nbt.getBoolean("secondfing");
                boolean finger3 = nbt.getBoolean("thirdfing");
                boolean finger4 = nbt.getBoolean("fourthfing");
                boolean blade = nbt.getBoolean("blade");
                int mode = nbt.getInt("mode");
                int spellID = nbt.getInt("spellid");
                if (finger1 != nopSI.getSpellFromID(s4).activeFinger1()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(1));
                }
                if (finger2 != nopSI.getSpellFromID(s4).activeFinger2()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(2));
                }
                if (finger3 != nopSI.getSpellFromID(s4).activeFinger3()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(3));
                }
                if (finger4 != nopSI.getSpellFromID(s4).activeFinger4()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(4));
                }
                if (blade != nopSI.getSpellFromID(s4).activeBlade()) {
                    BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(5));
                }
                BotsPacketHandler.INSTANCE.sendToServer(new SetGodKHSpellPacket(s4));
            }            this.removed();
        }));
        Player player = ClassReferences.getPlayer();
        ItemStack handstack = player.getItemInHand(InteractionHand.MAIN_HAND);
        CompoundTag nbt = handstack.getOrCreateTag();
        int spellID = nbt.getInt("spellid");
        if (spellID != 0) {
            if (spellID == s1) {
                this.addRenderableOnly(new ImageButton(this.width / 2 - 48 - 48 + 18, this.height/2 + 2- 31, 12,10,0,0,0,INDICATOR,12,10,(button) -> {
                }));
            } else if (spellID == s2) {
                this.addRenderableOnly(new ImageButton(this.width / 2 - 48 + 18, this.height/2 + 2- 31, 12,10,0,0,0,INDICATOR,12,10,(button) -> {
                }));
            } else if (spellID == s3) {
                this.addRenderableOnly(new ImageButton(this.width / 2 + 18, this.height/2 + 2- 31, 12,10,0,0,0,INDICATOR,12,10,(button) -> {
                }));
            } else if (spellID == s4) {
                this.addRenderableOnly(new ImageButton(this.width / 2 + 48 + 18, this.height/2 + 2- 31, 12,10,0,0,0,INDICATOR,12,10,(button) -> {
                }));
            }
        }
        super.init();
    }

}
