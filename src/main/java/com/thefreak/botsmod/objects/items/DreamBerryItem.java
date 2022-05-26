package com.thefreak.botsmod.objects.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DreamBerryItem extends Item {
    public DreamBerryItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        entityLiving.startSleeping(entityLiving.blockPosition());
        entityLiving.isSleeping();
        return super.finishUsingItem(stack, worldIn, entityLiving);
    }
}
