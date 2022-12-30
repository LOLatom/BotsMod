package com.thefreak.botsmod.spells.spellclass;

import com.thefreak.botsmod.BotsMod;
import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.ticks.TickAccess;
import org.jetbrains.annotations.Nullable;


public abstract class AbstractSpell {

    public final ItemStack spellItemStack;
    public final boolean firstFinger;
    public final boolean secondFinger;
    public final boolean thirdFinger;
    public final boolean fourthFinger;
    public final boolean blade;
    public ResourceLocation iconLocation;
    public UseOnContext context;
    public int ticks;
    public LivingEntity spellTarget;


    public AbstractSpell(ItemStack usedItemStack, boolean f1state, boolean f2state, boolean f3state, boolean f4state, boolean blade, @Nullable UseOnContext context) {
        this.spellItemStack = usedItemStack;
        this.firstFinger = f1state;
        this.secondFinger = f2state;
        this.thirdFinger = f3state;
        this.fourthFinger = f4state;
        this.blade = blade;
        this.context = context;
        this.spellTarget = null;

    }


    public boolean shouldExecute(Player player, Level level, InteractionHand hand) {
        return false;
    }

    public boolean shouldContinueExecuting(Player player, Level level, InteractionHand hand,@Nullable UseOnContext context) {
        return false;
    }


    public void startExecuting(Player player, Level level, InteractionHand hand, @Nullable UseOnContext context, boolean isHolding) {
        if (!shouldExecute(player, level, hand)) {
            return;
        }
        Execute(player, level, hand,context);
    }

    public void Execute(Player player, Level level, InteractionHand hand,@Nullable UseOnContext context) {

        if (shouldContinueExecuting(player, level, hand,context)) {
            Execute(player, level, hand,context);
        } else {
            finishExecuting(player, level, hand,context);
        }
    }

    public void finishExecuting(Player player, Level level, InteractionHand hand,@Nullable UseOnContext context) {

    }

    public abstract ResourceLocation getIconLocation();

    public void setIconLocation(ResourceLocation iconLocation) {
        this.iconLocation = iconLocation;
    }

    public abstract boolean activeFinger1();
    public abstract boolean activeFinger2();
    public abstract boolean activeFinger3();
    public abstract boolean activeFinger4();
    public abstract boolean activeBlade();
    public abstract int modeActive();

    public void setTarget(LivingEntity target) {
        this.spellTarget = target;
    }
    public LivingEntity getTarget() {
        return this.spellTarget;
    }

    public void tick() {

    }
}
