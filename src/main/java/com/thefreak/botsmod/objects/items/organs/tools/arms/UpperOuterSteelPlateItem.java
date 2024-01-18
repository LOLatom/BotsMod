package com.thefreak.botsmod.objects.items.organs.tools.arms;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class UpperOuterSteelPlateItem extends UpperOuterPlateComponent{
    public UpperOuterSteelPlateItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public String getRendererID(ItemStack stack, Player player) {
        return "upperOuterSteelPlate";
    }
}
