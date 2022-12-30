package com.thefreak.botsmod.spells.spellclass.spelltype;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.spells.spellclass.AbstractSpell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class BlankSpell extends AbstractSpell {


    public BlankSpell(ItemStack usedItemStack, boolean f1state, boolean f2state, boolean f3state, boolean f4state, boolean blade, @Nullable UseOnContext context) {
        super(usedItemStack, f1state, f2state, f3state, f4state, blade, context);
    }

    @Override
    public boolean shouldExecute(Player player, Level level, InteractionHand hand) {
        return false;
    }

    @Override
    public boolean shouldContinueExecuting(Player player, Level level, InteractionHand hand,@Nullable UseOnContext context) {
        return false;
    }

    @Override
    public ResourceLocation getIconLocation() {
        return new ResourceLocation(BotsMod.MOD_ID,"textures/gui/spell/blank_spell.png");
    }

    @Override
    public boolean activeFinger1() {
        return false;
    }

    @Override
    public boolean activeFinger2() {
        return false;
    }

    @Override
    public boolean activeFinger3() {
        return false;
    }

    @Override
    public boolean activeFinger4() {
        return false;
    }

    @Override
    public boolean activeBlade() {
        return false;
    }

    @Override
    public int modeActive() {
        return 0;
    }


}
