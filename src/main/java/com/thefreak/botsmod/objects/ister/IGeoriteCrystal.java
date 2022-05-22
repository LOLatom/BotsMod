package com.thefreak.botsmod.objects.ister;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public interface IGeoriteCrystal {
    default int glowAlpha(ItemStack stack) { return 1; };

    default int glowR(ItemStack stack) { return 1; };

    default int glowG(ItemStack stack) { return 1; };

    default int glowB(ItemStack stack) { return 1; };
}
