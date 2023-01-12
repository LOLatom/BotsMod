package com.thefreak.botsmod.util.misc;

import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.client.gui.dialogue.DialogueGUI;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;

import java.awt.*;

public class DialoguesHelper {
    Player player;
    InteractionHand hand;
    public DialoguesHelper(Player pPlayer, InteractionHand pHand) {
        this.player = pPlayer;
        this.hand = pHand;
    }

    public void krasiaFirstDialogue() {
        DialogueGUI dialogueFirst = new DialogueGUI(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_icon.png"),
                "","(sigh) . . .", "Well this was close...");
        dialogueFirst.setSecondIcon(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_sigh_icon.png"),true,20,1);
        dialogueFirst.setThirdIcon(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_pity_icon.png"));
        dialogueFirst.setTextPassingTime(1,3,1);
        dialogueFirst.setFirstAwnser(true,"[[ . . . ]]", (dialogueGUI) -> {
            dialogueGUI.onClose();
            krasiaSecondDialogue();
        });
        ClassReferences.getClientMC().setScreen(dialogueFirst);
    }

    public void krasiaSecondDialogue() {
        DialogueGUI dialogue = new DialogueGUI(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_pity_icon.png"),
                "Oh....","OH! sorry my poor child i havn't seen you,","You may call me Krasia");
        dialogue.setSecondIcon(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_sweat_icon.png"),true,10,2);
        dialogue.setTextPassingTime(3,1,2);
        dialogue.setThirdIcon(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_sigh_icon.png"),true,20,2);
        dialogue.setSecondLineColor(new Color(182, 182, 182));
        dialogue.setFirstAwnser(true,"I'm confused...", (dialogueGUI) -> {
            dialogueGUI.onClose();
            krasiaDialogue_1();
        });
        dialogue.setSecondAwnser(true,"Hello there...", (dialogueGUI) -> {
            dialogueGUI.onClose();
            krasiaDialogue_2();
        });
        ClassReferences.getClientMC().setScreen(dialogue);
    }

    public void krasiaDialogue_1() {
        DialogueGUI dialogue = new DialogueGUI(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_pity_icon.png"),
                "I'm very sorry, i'm not usually like this","But thanks for the help my child","");
        dialogue.setSecondIcon(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_smile_icon.png"));
        dialogue.setFirstAwnser(true,"What Are you ?", (dialogueGUI) -> {
            dialogueGUI.onClose();
            krasiaDialogue_3();
        });
        dialogue.setSecondAwnser(true,"Where you trapped in that crystal ?",(dialogueGUI) -> {
            dialogueGUI.onClose();
        });
        ClassReferences.getClientMC().setScreen(dialogue);
    }
    public void krasiaDialogue_2() {
        DialogueGUI dialogue = new DialogueGUI(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_pity_icon.png"),
                "Hey...","I'm the goddess of Time and Beauty","I'm glad to see you child");
        dialogue.setSecondIcon(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_sigh_icon.png"),true,20,2);
        dialogue.setThirdIcon(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_smile_icon.png"));
        dialogue.setTextPassingTime(3,1,1);
        dialogue.setFirstAwnser(true,"Why were you trapped in here ?", (dialogueGUI) -> {
            dialogueGUI.onClose();
            krasiaDialogue_4();
        });
        dialogue.setSecondAwnser(true,"Where you trapped in that crystal ?",(dialogueGUI) -> {
            dialogueGUI.onClose();
        });
        ClassReferences.getClientMC().setScreen(dialogue);
    }

    public void krasiaDialogue_3() {
        DialogueGUI dialogue = new DialogueGUI(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_pity_icon.png"),
                "","I'm the goddess of Time and Beauty","I'm glad to see you child");
        dialogue.setSecondIcon(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_sigh_icon.png"),true,20,2);
        dialogue.setThirdIcon(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_pity_icon.png"));
        dialogue.setTextPassingTime(3,1,1);
        dialogue.setFirstAwnser(true,"Why were you trapped in here ?", (dialogueGUI) -> {
            dialogueGUI.onClose();
            krasiaDialogue_4();
        });
        dialogue.setSecondAwnser(true,"Where you trapped in that crystal ?",(dialogueGUI) -> {
            dialogueGUI.onClose();
        });
        ClassReferences.getClientMC().setScreen(dialogue);
    }
    public void krasiaDialogue_4() {
        DialogueGUI dialogue = new DialogueGUI(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_pity_icon.png"),
                "It's... A long story","But if you want to hear it, we will have all the time","For that my child...");
        dialogue.setThirdLineColor(new Color(157, 157, 157));
        dialogue.setTextPassingTime(1,1,1);
        dialogue.setFirstAwnser(true,"[[ Let Her Continue ]]", (dialogueGUI) -> {
            dialogueGUI.onClose();
            krasiaInterupt_1();
        });
        dialogue.setSecondAwnser(true,"[[ Interrupt ]]",(dialogueGUI) -> {
            dialogueGUI.onClose();
            krasiaContinue_1();
        });
        ClassReferences.getClientMC().setScreen(dialogue);
    }

    public void krasiaInterupt_1() {
        DialogueGUI dialogue = new DialogueGUI(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_pity_icon.png"),
                ". . .",". . .Child please don't interrupt me alright ?","Anyway ! I'll continue child");
        dialogue.setSecondIcon(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_angry_icon.png"),true,20,2);
        dialogue.setThirdIcon(new ResourceLocation("botsmod", "textures/gui/dialogue/krasia_smile_icon.png"));
        dialogue.setTextPassingTime(1,2,1);
        ClassReferences.getClientMC().setScreen(dialogue);
    }
    public void krasiaContinue_1() {

    }

}
