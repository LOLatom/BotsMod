package com.thefreak.botsmod.client.gui.dialogue;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.thefreak.botsmod.ClassReferences;
import net.minecraft.Util;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.awt.*;
import java.util.function.Consumer;

public class DialogueGUI extends Screen {

    public ResourceLocation DIALOGUE_ICON;
    public ResourceLocation DIALOGUE_ICONB;
    public ResourceLocation DIALOGUE_ICONC;
    public ResourceLocation DIALOGUE_BOX = new ResourceLocation("botsmod","textures/gui/dialogue/dialogue_box.png");
    public ResourceLocation DIALOGUE_ANWSER_BOX = new ResourceLocation("botsmod","textures/gui/dialogue/anwser_box.png");
    public ResourceLocation DIALOGUE_CLOSE_BUTTON = new ResourceLocation("botsmod","textures/gui/dialogue/close_dialogue_button.png");
    int ticks;
    int midx;
    int midy;
    String lineFirst;
    String lineSecond;
    String lineThird;
    String lineProcessFirst;
    String lineProcessSecond;
    String lineProcessThird;
    String firstAwnser;
    String secondAwnser;
    Consumer<DialogueGUI> firstAwnserAction;
    Consumer<DialogueGUI> secondAwnserAction;

    int lineapro;
    int linebpro;
    int linecpro;
    int textPassingTime;
    int textPassingTimeSecond;
    int textPassingTimeThird;
    boolean anwserfirst;
    boolean anwsersecond;
    boolean firstIconAnimated;
    boolean secondIconAnimated;
    int TEXTURE_SIZE_FIRST;
    int TEXTURE_SIZE_SECOND;
    int FIRST_AMOUNT_OF_FRAMES;
    int SECOND_AMOUNT_OF_FRAMES;
    int AMOUNT_OF_FRAMES;
    int frameProgress;
    int firstFrameProgress;
    int secondFrameProgress;
    int firstAnimSpeed;
    int secondAnimSpeed;

    Color color;
    Color colorb;
    Color colorc;
    public ImageButton icon;

    public DialogueGUI(ResourceLocation dialogueIcon, String firstLine, String secondLine, String thirdLine) {
        super(new TextComponent("bots.dialogue"));
        this.minecraft = ClassReferences.getClientMC();
        this.DIALOGUE_ICON = dialogueIcon;
        this.DIALOGUE_ICONB = dialogueIcon;
        this.DIALOGUE_ICONC = dialogueIcon;
        this.ticks = 0;
        this.midx = this.width /2;
        this.midy = (this.height /2) - 15;
        this.lineFirst = firstLine;
        this.lineSecond = secondLine;
        this.lineThird = thirdLine;
        this.lineProcessFirst = "";
        this.lineProcessSecond = "";
        this.lineProcessThird = "";
        this.lineapro = 0;
        this.linebpro = 0;
        this.linecpro = 0;
        this.textPassingTime = 1;
        this.textPassingTimeSecond = 1;
        this.textPassingTimeThird = 1;
        this.color = new Color(255, 255, 255);
        this.colorb = new Color(255, 255, 255);
        this.colorc = new Color(255, 255, 255);
        this.anwserfirst = false;
        this.anwsersecond = false;
        this.firstAwnser = "";
        this.secondAwnser = "";
        this.firstAwnserAction = (dialogueGUI) -> {};
        this.secondAwnserAction = (dialogueGUI) -> {};
        this.AMOUNT_OF_FRAMES = 1;
        this.FIRST_AMOUNT_OF_FRAMES = 1;
        this.SECOND_AMOUNT_OF_FRAMES = 1;
        this.firstFrameProgress = 0;
        this.secondFrameProgress = 0;
        this.frameProgress = 0;
        this.firstAnimSpeed = 1;
        this.secondAnimSpeed = 1;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);


        pPoseStack.pushPose();
        pPoseStack.translate((double)(this.width / 2) + 38, 84.0D, 0.0D);
        pPoseStack.scale(1, 1, 1);
        drawCenteredString(pPoseStack, this.font, this.lineProcessFirst, 0, -8, this.color.getRGB());
        pPoseStack.popPose();

        pPoseStack.pushPose();
        pPoseStack.translate((double)(this.width / 2) + 38, 99.0D, 0.0D);
        pPoseStack.scale(1, 1, 1);
        drawCenteredString(pPoseStack, this.font, this.lineProcessSecond, 0, -8, this.colorb.getRGB());
        pPoseStack.popPose();

        pPoseStack.pushPose();
        pPoseStack.translate((double)(this.width / 2) + 38, 114.0D, 0.0D);
        pPoseStack.scale(1, 1, 1);
        drawCenteredString(pPoseStack, this.font, this.lineProcessThird, 0, -8, this.colorc.getRGB());
        pPoseStack.popPose();

        pPoseStack.pushPose();
        if (this.anwserfirst) {
            pPoseStack.translate((double)(this.width / 2), 159.0D, 0.0D);
            pPoseStack.scale(1, 1, 1);
            drawCenteredString(pPoseStack, this.font, this.firstAwnser, 0, -8, this.color.getRGB());
        }
        pPoseStack.popPose();
        pPoseStack.pushPose();
        if (this.anwsersecond) {
            pPoseStack.translate((double)(this.width / 2), 214.0D, 0.0D);
            pPoseStack.scale(1, 1, 1);
            drawCenteredString(pPoseStack, this.font, this.secondAwnser, 0, -8, this.color.getRGB());
        }
        pPoseStack.popPose();

        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    protected void init() {
        super.init();
        this.midx = this.width /2;
        this.midy = (this.height /2) - 15;
        this.addRenderableOnly(new ImageButton(midx - 108, midy - 50, 294,64,0,0,0,DIALOGUE_BOX,294,64,(button) -> {
        }));
        this.icon = new ImageButton(midx - 108 - 100, midy - 50, 96 ,96,0,96 * this.frameProgress,0 ,DIALOGUE_ICON,96,96 * this.AMOUNT_OF_FRAMES,(button) -> {
        });
        this.addRenderableOnly(this.icon);
        this.addRenderableWidget(new ImageButton(midx + 140 + 14, midy - 70, 32,18,0,0,18,DIALOGUE_CLOSE_BUTTON,32,36,(button) -> {
            this.onClose();
        }));

        if (this.anwserfirst) {
            this.addRenderableWidget(new ImageButton(midx - 108, midy - 50 + 67, 214,50,0,0,50,DIALOGUE_ANWSER_BOX,214,100,(button) -> {
            this.firstAwnserAction.accept(this);
            }));
        }
        if (this.anwsersecond) {
            this.addRenderableWidget(new ImageButton(midx - 108, midy - 50 + 67 + 54, 214,50,0,0,50,DIALOGUE_ANWSER_BOX,214,100,(button) -> {
            this.secondAwnserAction.accept(this);
            }));
        }

    }

    public float lerp(float pos1, float pos2, float pDelta) {
        float f = 1.0F - pDelta;
        return (pos1 * f + pos2 * pDelta);

    }

    public void setTextPassingTime(int ticks) {
        this.textPassingTime = ticks;
    }
    public void setTextPassingTime(int ticks, int secondlineticks, int thirdlineticks) {
        this.textPassingTime = ticks;
        this.textPassingTimeSecond = secondlineticks;
        this.textPassingTimeThird = thirdlineticks;
    }

    public void setFirstLineColor(Color color) {
        this.color = color;
    }
    public void setSecondLineColor(Color color) {
            this.colorb = color;
    }
    public void setThirdLineColor(Color color) {
        this.colorc = color;
    }

    public void setSecondIcon(ResourceLocation icon) {
        this.DIALOGUE_ICONB = icon;
    }
    public void setSecondIcon(ResourceLocation icon, boolean isAnimated, int framesAmount, int frameSpeed) {
        this.DIALOGUE_ICONB = icon;
        this.firstFrameProgress = 0;
        this.firstIconAnimated = isAnimated;
        this.FIRST_AMOUNT_OF_FRAMES = framesAmount;
        this.firstAnimSpeed = frameSpeed;
    }
    public void setThirdIcon(ResourceLocation icon) {
        this.DIALOGUE_ICONC = icon;
    }
    public void setThirdIcon(ResourceLocation icon, boolean isAnimated, int framesAmount, int frameSpeed) {
        this.DIALOGUE_ICONC = icon;
        this.secondFrameProgress = 0;
        this.secondIconAnimated = isAnimated;
        this.SECOND_AMOUNT_OF_FRAMES = framesAmount;
        this.secondAnimSpeed = frameSpeed;
    }

    public void setFirstAwnser(boolean exist, String awnser, Consumer<DialogueGUI> action) {
        this.anwserfirst = exist;
        this.firstAwnser = awnser;
        this.firstAwnserAction = action;

    }
    public void setSecondAwnser(boolean exist, String awnser, Consumer<DialogueGUI> action) {
        this.anwsersecond = exist;
        this.secondAwnser = awnser;
        this.secondAwnserAction = action;
    }


    @Override
    public void tick() {
        super.tick();
        ticks++;
        if (ticks % this.textPassingTime == 0) {
            if (!this.lineProcessFirst.equals(this.lineFirst)) {
                this.lineProcessFirst = this.lineProcessFirst + this.lineFirst.charAt(this.lineapro);
                this.lineapro++;
            } else if (!this.lineProcessSecond.equals(this.lineSecond)) {
                this.textPassingTime = this.textPassingTimeSecond;
                this.DIALOGUE_ICON = this.DIALOGUE_ICONB;
                if (this.firstIconAnimated) {
                    this.AMOUNT_OF_FRAMES = this.FIRST_AMOUNT_OF_FRAMES;

                }
                this.clearWidgets();
                this.init();
                if (this.firstIconAnimated && ticks % this.firstAnimSpeed == 0) {
                    if (this.frameProgress < this.AMOUNT_OF_FRAMES - 1) {
                        this.frameProgress++;
                    }
                }
                this.lineProcessSecond = this.lineProcessSecond + this.lineSecond.charAt(this.linebpro);
                this.linebpro++;
            } else if (!this.lineProcessThird.equals(this.lineThird)) {
                if (this.firstIconAnimated && this.frameProgress >= 1) {
                    this.frameProgress = 0;
                    this.AMOUNT_OF_FRAMES = 1;
                    this.firstIconAnimated = false;
                }
                this.textPassingTime = this.textPassingTimeThird;
                this.DIALOGUE_ICON = this.DIALOGUE_ICONC;
                if (this.secondIconAnimated) {
                    this.AMOUNT_OF_FRAMES = this.SECOND_AMOUNT_OF_FRAMES;
                }
                this.clearWidgets();
                this.init();
                if (this.secondIconAnimated && ticks % this.secondAnimSpeed == 0) {
                    if (this.frameProgress < this.AMOUNT_OF_FRAMES - 1) {
                        this.frameProgress++;
                    }
                }
                System.out.println(this.frameProgress);
                this.lineProcessThird = this.lineProcessThird + this.lineThird.charAt(this.linecpro);
                this.linecpro++;
            }
        }

    }
}
