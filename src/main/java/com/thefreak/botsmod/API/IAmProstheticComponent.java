package com.thefreak.botsmod.API;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IAmProstheticComponent {
    String getRendererID(ItemStack stack, Player player);

    /**
     *TODO:Add reach addition
     **/
    default float getReachAddition() {
        return .0F;
    }

    default float getDamageAddition() {
        return .0F;
    }

    default float getAdditionalArmor() {
        return .0F;
    }

    default void onHitEntity(LivingEntity targetEntity, Player playerEntity) {
    };
}
