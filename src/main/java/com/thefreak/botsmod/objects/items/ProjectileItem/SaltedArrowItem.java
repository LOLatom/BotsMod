package com.thefreak.botsmod.objects.items.ProjectileItem;

import com.thefreak.botsmod.entities.Projectile.SaltedArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SaltedArrowItem extends ArrowItem {
    public SaltedArrowItem(Properties p_i48531_1_) {
        super(p_i48531_1_);
    }

    @Override
    public AbstractArrow createArrow(Level p_200887_1_, ItemStack p_200887_2_, LivingEntity p_200887_3_) {
        return new SaltedArrow(p_200887_1_, p_200887_3_);
    }
}
