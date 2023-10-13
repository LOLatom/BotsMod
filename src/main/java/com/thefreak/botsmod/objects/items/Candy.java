package com.thefreak.botsmod.objects.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Candy extends Item {

    public Candy(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        Player playerEntity = entityLiving instanceof Player ? worldIn.getNearestPlayer(entityLiving, 0.1D) : null;
        return super.finishUsingItem(stack, worldIn, entityLiving);
    }

}
