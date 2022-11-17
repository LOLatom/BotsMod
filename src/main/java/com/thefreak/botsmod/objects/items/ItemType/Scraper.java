package com.thefreak.botsmod.objects.items.ItemType;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.thefreak.botsmod.API.Animation.IHandlePoseable;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiConsumer;

public class Scraper extends Item implements IHandlePoseable {
    public Scraper(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public <T extends Player> BiConsumer<HumanoidModel, T> getLeftArmPoser(InteractionHand side, ItemStack stack, LivingEntity livingEntity) {
        return side == InteractionHand.OFF_HAND ? ((bipedModel, t) -> {
            bipedModel.leftArm.xRot = -51.8F + bipedModel.head.xRot;
            bipedModel.leftArm.yRot = 0.3F + bipedModel.head.yRot;
        }) : ((bipedModel, t) -> {
            bipedModel.leftArm.xRot = bipedModel.leftArm.xRot;
            bipedModel.leftArm.yRot = bipedModel.leftArm.yRot;
        });
    }

    @Override
    public <T extends Player> BiConsumer<HumanoidModel, T> getRightArmPoser(InteractionHand side, ItemStack stack, LivingEntity livingEntity) {
        return side == InteractionHand.MAIN_HAND ? ((bipedModel, t) -> {
            bipedModel.rightArm.xRot =  -51.8F + bipedModel.head.xRot;
            bipedModel.rightArm.yRot = (-0.3F) + bipedModel.head.yRot;
        }) : ((bipedModel, t) -> {
            bipedModel.rightArm.xRot = bipedModel.rightArm.xRot;
            bipedModel.rightArm.yRot = bipedModel.rightArm.yRot;
        });
    }

    @Override
    public boolean isRightArmAnimatedD(InteractionHand side, ItemStack stack) {
        return side == InteractionHand.MAIN_HAND ;
    }

    @Override
    public boolean isLeftArmAnimatedD(InteractionHand side, ItemStack stack) {
        return side == InteractionHand.OFF_HAND ;
    }

    @Override
    public boolean isItemAnimatedD(InteractionHand side, ItemStack stack, LivingEntity livingEntity) {
        return true;
    }

    @Override
    public <T extends Player> BiConsumer<PoseStack, T> getItemPoser(InteractionHand side, ItemStack stack, LivingEntity livingEntity) {
        return side == InteractionHand.MAIN_HAND ? ((matrixStack, t) -> {
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(-45.0F));
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
            matrixStack.translate((double)((float)(-1) / 16.0F), 0.3D, 0);
        }) :((matrixStack, t) -> {
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(-45.0F));
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
            matrixStack.translate((double)((float)(1) / 16.0F), 0.3D, 0);
        });
    }
}
