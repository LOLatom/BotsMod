package com.thefreak.botsmod.objects.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TestItem extends Item {
    public TestItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {

    return true;
    }


}
