package com.thefreak.botsmod.objects.containers.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.objects.containers.SpecialisedCraftingMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.CraftingMenu;

public class SpecialisedCraftingScreen extends AbstractContainerScreen<SpecialisedCraftingMenu> {
    public SpecialisedCraftingScreen(SpecialisedCraftingMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {

    }
}
