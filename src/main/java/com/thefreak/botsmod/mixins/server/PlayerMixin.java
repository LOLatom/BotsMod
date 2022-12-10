package com.thefreak.botsmod.mixins.server;

import com.thefreak.botsmod.API.IAmDivine;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Player.class)
public class PlayerMixin implements IAmDivine {
    public boolean isDivine;

    @Override
    public boolean isDivine() {
        return ((PlayerMixin)(Object)this).isDivine;
    }

    @Override
    public void setDivine(boolean divine) {
        ((PlayerMixin)(Object)this).isDivine = divine;
    }
}
