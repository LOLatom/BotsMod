package com.thefreak.botsmod.API.Animation;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiConsumer;

public interface IHandlePoseable {
    public <T extends AbstractClientPlayer> BiConsumer<HumanoidModel, T> getLeftArmPoser(InteractionHand side, ItemStack stack, LivingEntity livingEntity); // side is the side with the item, stack is the item stack

    public <T extends AbstractClientPlayer> BiConsumer<HumanoidModel, T> getRightArmPoser(InteractionHand side, ItemStack stack, LivingEntity livingEntity);

    public <T extends AbstractClientPlayer> BiConsumer<PoseStack, T> getItemPoser(InteractionHand side, ItemStack stack, LivingEntity livingEntity);

    default boolean isItemAnimatedD(InteractionHand side , ItemStack stack, LivingEntity livingEntity) { return false; };

    default boolean isRightArmAnimatedD(InteractionHand side , ItemStack stack) { return false; };

    default boolean isLeftArmAnimatedD(InteractionHand side , ItemStack stack) { return false; };
}
