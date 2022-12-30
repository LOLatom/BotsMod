package com.thefreak.botsmod.mixins.client;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Gui.class)
public abstract class IngameGuiMixin extends GuiComponent {

}
