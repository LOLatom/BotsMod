package com.thefreak.botsmod.objects.items.organs.tools.arms;

import com.thefreak.botsmod.API.IAmProstheticComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AdditionComponent extends Item implements IAmProstheticComponent {
    public AdditionComponent(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public String getRendererID(ItemStack stack, Player player) {
        return null;
    }

    public boolean hasModuleInput(ItemStack stack) {
        return false;
    }
}
