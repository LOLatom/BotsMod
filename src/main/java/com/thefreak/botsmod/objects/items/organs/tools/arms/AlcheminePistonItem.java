package com.thefreak.botsmod.objects.items.organs.tools.arms;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class AlcheminePistonItem extends PistonComponent{
    public AlcheminePistonItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public String getRendererID(ItemStack stack, Player player) {
        return "regularPiston";
    }

    @Override
    public float getReachAddition() {
        return 2;
    }
}
