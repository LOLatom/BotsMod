package com.thefreak.botsmod.spells.spellclass.spelltype;

import com.thefreak.botsmod.API.ISFreezable;
import com.thefreak.botsmod.spells.spellclass.AbstractSpell;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;


public class FreezeSpell extends AbstractSpell {

    public FreezeSpell(ItemStack usedItemStack, boolean f1state, boolean f2state, boolean f3state, boolean f4state, boolean blade) {
        super(usedItemStack, f1state, f2state, f3state, f4state, blade);
    }

    @Override
    public boolean shouldExecute(Player player, Level level, InteractionHand hand) {
        return firstFinger&&secondFinger&&thirdFinger&&fourthFinger&&!blade;
    }

    @Override
    public boolean shouldContinueExecuting(Player player, Level level, InteractionHand hand) {
        return false;
    }

    @Override
    public void Execute(Player player, Level level, InteractionHand hand) {
        List<Entity> entities = level.getEntitiesOfClass(Entity.class, player.getBoundingBox().inflate(3D));
        for (Entity entity : entities) {
            if (entity instanceof ISFreezable && !(entity instanceof Player)) {
                ISFreezable freezable = (ISFreezable) entity;
                entity.resetFallDistance();
                freezable.setSFreezed(true);
                System.out.println("Freezed  :  " + entity);
            }
        }
        super.Execute(player, level, hand);
    }
}
