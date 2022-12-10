package com.thefreak.botsmod.spells.spellclass.spelltype;

import com.thefreak.botsmod.spells.spellclass.AbstractSpell;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlankSpell extends AbstractSpell {
    public BlankSpell(ItemStack usedItemStack, boolean f1state, boolean f2state, boolean f3state, boolean f4state, boolean blade) {
        super(usedItemStack, f1state, f2state, f3state, f4state, blade);
    }

    @Override
    public boolean shouldExecute(Player player, Level level, InteractionHand hand) {
        return false;
    }

    @Override
    public boolean shouldContinueExecuting(Player player, Level level, InteractionHand hand) {
        return false;
    }
}
