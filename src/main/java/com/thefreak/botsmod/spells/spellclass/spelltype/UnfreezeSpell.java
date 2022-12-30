package com.thefreak.botsmod.spells.spellclass.spelltype;

import com.thefreak.botsmod.API.ISFreezable;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.spells.spellclass.AbstractSpell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UnfreezeSpell extends AbstractSpell {

    public UnfreezeSpell(ItemStack usedItemStack, boolean f1state, boolean f2state, boolean f3state, boolean f4state, boolean blade, @Nullable UseOnContext context) {
        super(usedItemStack, f1state, f2state, f3state, f4state, blade, context);
    }

    @Override
    public boolean shouldExecute(Player player, Level level, InteractionHand hand) {
        return firstFinger&&secondFinger&&thirdFinger&&fourthFinger&&!blade;
    }

    @Override
    public boolean shouldContinueExecuting(Player player, Level level, InteractionHand hand,@Nullable UseOnContext context) {
        return false;
    }

    @Override
    public void Execute(Player player, Level level, InteractionHand hand,@Nullable UseOnContext context) {
        List<Entity> entities = level.getEntitiesOfClass(Entity.class, player.getBoundingBox().inflate(3D));
        for (Entity entity : entities) {
            if (entity instanceof ISFreezable && !(entity instanceof Player)) {
                ISFreezable freezable = (ISFreezable) entity;
                freezable.setSFreezed(false);
                System.out.println("UnFreezed  :  " + entity);
            }
        }
        super.Execute(player, level, hand,context);
    }

    @Override
    public ResourceLocation getIconLocation() {
        return new ResourceLocation(BotsMod.MOD_ID,"textures/gui/spell/freeze_spell.png");
    }

    @Override
    public boolean activeFinger1() {
        return true;
    }

    @Override
    public boolean activeFinger2() {
        return true;
    }

    @Override
    public boolean activeFinger3() {
        return true;
    }

    @Override
    public boolean activeFinger4() {
        return true;
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
