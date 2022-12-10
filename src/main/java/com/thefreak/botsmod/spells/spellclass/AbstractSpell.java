package com.thefreak.botsmod.spells.spellclass;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;


public class AbstractSpell {

    public final ItemStack spellItemStack;
    public final boolean firstFinger;
    public final boolean secondFinger;
    public final boolean thirdFinger;
    public final boolean fourthFinger;
    public final boolean blade;


    public AbstractSpell(ItemStack usedItemStack, boolean f1state, boolean f2state, boolean f3state, boolean f4state, boolean blade) {
        this.spellItemStack = usedItemStack;
        this.firstFinger = f1state;
        this.secondFinger = f2state;
        this.thirdFinger = f3state;
        this.fourthFinger = f4state;
        this.blade = blade;

    }


    public boolean shouldExecute(Player player, Level level, InteractionHand hand) {
        return false;
    }

    public boolean shouldContinueExecuting(Player player, Level level, InteractionHand hand) {
        return false;
    }


    public void startExecuting(Player player, Level level, InteractionHand hand) {
        if (!shouldExecute(player, level, hand)) {
            return;
        }
        Execute(player, level, hand);
    }

    public void Execute(Player player, Level level, InteractionHand hand) {

        if (shouldContinueExecuting(player, level, hand)) {
            Execute(player, level, hand);
        } else {
            finishExecuting(player, level, hand);
        }
    }

    public void finishExecuting(Player player, Level level, InteractionHand hand) {

    }



}
