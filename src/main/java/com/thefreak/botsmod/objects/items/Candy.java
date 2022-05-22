package com.thefreak.botsmod.objects.items;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.BiConsumer;

import net.minecraft.item.Item.Properties;

public class Candy extends Item{

    public Candy(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        PlayerEntity playerEntity = entityLiving instanceof PlayerEntity ? worldIn.getNearestPlayer(entityLiving, 0.1D) : null;


        return super.finishUsingItem(stack, worldIn, entityLiving);
    }

}
