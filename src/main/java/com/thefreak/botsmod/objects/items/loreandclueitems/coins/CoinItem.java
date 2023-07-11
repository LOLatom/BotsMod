package com.thefreak.botsmod.objects.items.loreandclueitems.coins;

import com.thefreak.botsmod.API.IHaveSpecialTooltip;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CoinItem extends Item implements IHaveSpecialTooltip {
    public CoinItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isCoin(ItemStack stack) {
        return true;
    }
}
