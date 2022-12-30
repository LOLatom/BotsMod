package com.thefreak.botsmod.client.gui.gkhguis.spellguis;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import com.thefreak.botsmod.spells.SpellIdentifier;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.SetGodKHSpellPacket;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class SpellSelectionGUI extends Screen {
    public int s1;
    public int s2;
    public int s3;
    public int s4;

    private static final ResourceLocation INDICATOR = new ResourceLocation(BotsMod.MOD_ID,"textures/gui/spell/spell_indicator.png");

    public SpellSelectionGUI(int firstspell, int secondspell, int thirdspell, int fourthspell) {
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
            BotsPacketHandler.INSTANCE.sendToServer(new SetGodKHSpellPacket(s1));
            this.removed();
        }));
        this.addRenderableWidget(new ImageButton(this.width / 2 - 49, this.height/2 + 2- 24, 48,48,0,0,48,nopSI.getIconFromID(s2),48,96,(button) -> {
            BotsPacketHandler.INSTANCE.sendToServer(new SetGodKHSpellPacket(s2));
            this.removed();
        }));
        this.addRenderableWidget(new ImageButton(this.width / 2 , this.height/2 + 2 - 24, 48,48,0,0,48,nopSI.getIconFromID(s3),48,96,(button) -> {
            BotsPacketHandler.INSTANCE.sendToServer(new SetGodKHSpellPacket(s3));
            this.removed();
        }));
        this.addRenderableWidget(new ImageButton(this.width / 2 + 48, this.height/2 + 2- 24, 48,48,0,0,48,nopSI.getIconFromID(s4),48,96,(button) -> {
            BotsPacketHandler.INSTANCE.sendToServer(new SetGodKHSpellPacket(s4));
            this.removed();
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
