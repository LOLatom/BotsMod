package com.thefreak.botsmod.util.botsevent;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;
import org.openjdk.nashorn.internal.objects.annotations.Getter;

public class TooltipDisplayEvent extends Event {


    private final ItemStack stack;

    private final PoseStack poseStack;

    private final int Width;

    private final int Height;

    private final int preX;

    private final int preY;

    private final int postX;

    private final int postY;



    public TooltipDisplayEvent(ItemStack stack, PoseStack poseStack, int Width, int Heigh, int preX , int preY, int postX , int postY) {
        this.Height = Heigh;
        this.Width = Width;
        this.postX = postX;
        this.postY = postY;
        this.preX = preX;
        this.preY = preY;
        this.poseStack = poseStack;
        this.stack = stack;

    }

    public int getHeight() {
        return Height;
    }

    public int getPostX() {
        return postX;
    }

    public int getPostY() {
        return postY;
    }

    public int getPreX() {
        return preX;
    }

    public int getPreY() {
        return preY;
    }

    public int getWidth() {
        return Width;
    }

    public ItemStack getStack() {
        return stack;
    }

    public PoseStack getPoseStack() {
        return poseStack;
    }
}
