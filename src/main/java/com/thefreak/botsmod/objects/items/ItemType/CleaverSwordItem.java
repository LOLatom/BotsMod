package com.thefreak.botsmod.objects.items.ItemType;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.thefreak.botsmod.API.Animation.IHandlePoseable;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

import java.util.function.BiConsumer;


public class CleaverSwordItem extends SwordItem implements IHandlePoseable {
        boolean isused;

    public CleaverSwordItem(Tier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
    }



    @Override
    public <T extends Player> BiConsumer<HumanoidModel, T> getLeftArmPoser(InteractionHand side, ItemStack stack, LivingEntity livingEntity) {

        Player playerEntity = (Player) livingEntity;
        if (this.isused == false) {
            return side == InteractionHand.OFF_HAND ? ((bipedModel, t) -> {
                bipedModel.leftArm.xRot = -45;
                bipedModel.leftArm.yRot = 0.3F + bipedModel.head.yRot;
            }) : ((bipedModel, t) -> {
                bipedModel.leftArm.xRot = bipedModel.leftArm.xRot;
                bipedModel.leftArm.yRot = bipedModel.leftArm.yRot;
            });
    } else {
            return side == InteractionHand.OFF_HAND ? ((bipedModel, t) -> {
                bipedModel.leftArm.xRot = -110;
            }) : ((bipedModel, t) -> {
                bipedModel.leftArm.xRot = bipedModel.leftArm.xRot;
                bipedModel.leftArm.yRot = bipedModel.leftArm.yRot;
            });
        }
    }





    @Override
    public <T extends Player> BiConsumer<HumanoidModel, T> getRightArmPoser(InteractionHand side, ItemStack stack, LivingEntity livingEntity) {

        Player playerEntity = (Player) livingEntity;
        if (this.isused == false) {

            return side == InteractionHand.MAIN_HAND ? ((bipedModel, t) -> {
                bipedModel.rightArm.xRot = -45;
                bipedModel.rightArm.yRot = (-0.3F) + bipedModel.head.yRot;
            }) : ((bipedModel, t) -> {
                bipedModel.rightArm.xRot = bipedModel.rightArm.xRot;
                bipedModel.rightArm.yRot = bipedModel.rightArm.yRot;
            });
    } else {
            return side == InteractionHand.MAIN_HAND ? ((bipedModel, t) -> {
                bipedModel.rightArm.xRot = -110;
            }) : ((bipedModel, t) -> {
                bipedModel.rightArm.xRot = bipedModel.rightArm.xRot;
                bipedModel.rightArm.yRot = bipedModel.rightArm.yRot;
            });
        }
    }





    @Override
    public <T extends Player> BiConsumer<PoseStack, T> getItemPoser(InteractionHand side, ItemStack stack, LivingEntity livingEntity) {
        return side == InteractionHand.MAIN_HAND ? ((matrixStack, t) -> {
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(-45.0F));
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            matrixStack.translate((double)((float)(-1) / 16.0F), 0.5F, -0.625D);
        }) :((matrixStack, t) -> {
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(-45.0F));
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            matrixStack.translate((double)((float)(1) / 16.0F), 0.5D, -0.625D);
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

        Player playerEntity = (Player) livingEntity;
        return this.isused;
    }
}
