package com.thefreak.botsmod.API.Animation;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

import java.util.function.BiConsumer;

public interface IHandlePoseable {
    public <T extends AbstractClientPlayerEntity> BiConsumer<BipedModel, T> getLeftArmPoser(Hand side, ItemStack stack, LivingEntity livingEntity); // side is the side with the item, stack is the item stack

    public <T extends AbstractClientPlayerEntity> BiConsumer<BipedModel, T> getRightArmPoser(Hand side, ItemStack stack, LivingEntity livingEntity);

    public <T extends AbstractClientPlayerEntity> BiConsumer<MatrixStack, T> getItemPoser(Hand side, ItemStack stack, LivingEntity livingEntity);

    default boolean isItemAnimatedD(Hand side , ItemStack stack, LivingEntity livingEntity) { return false; };

    default boolean isRightArmAnimatedD(Hand side , ItemStack stack) { return false; };

    default boolean isLeftArmAnimatedD(Hand side , ItemStack stack) { return false; };
}
