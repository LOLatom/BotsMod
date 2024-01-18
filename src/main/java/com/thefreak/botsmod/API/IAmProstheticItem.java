package com.thefreak.botsmod.API;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;


public interface IAmProstheticItem {

    boolean renderItemStack(ItemStack inHandStack, Player player);

    boolean canHandleItemStack(ItemStack inHandStack, Player player);

    boolean canAttack(Player player);

    boolean canUse(Player player, Level level, InteractionHand pInteractionHand);

    boolean canUseOn(UseOnContext pContext);

    String getRendererID();
}
