package com.thefreak.botsmod.objects.items.organs.tools.arms;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class LowerOuterSteelPlateItem extends LowerOuterPlateComponent{
    public LowerOuterSteelPlateItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public String getRendererID(ItemStack stack, Player player) {
        return "lowerOuterSteelPlate";
    }
}
