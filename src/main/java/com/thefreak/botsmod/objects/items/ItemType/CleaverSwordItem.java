package com.thefreak.botsmod.objects.items.ItemType;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.thefreak.botsmod.API.Animation.IHandlePoseable;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraft.world.item.SwordItem;

import java.util.function.BiConsumer;

public class CleaverSwordItem extends SwordItem implements IHandlePoseable {
        boolean isused;

    public CleaverSwordItem(IItemTier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
    }



    @Override
    public <T extends AbstractClientPlayerEntity> BiConsumer<BipedModel, T> getLeftArmPoser(Hand side, ItemStack stack, LivingEntity livingEntity) {

        PlayerEntity playerEntity = (PlayerEntity) livingEntity;
        if (this.isused == false) {
            return side == Hand.OFF_HAND ? ((bipedModel, t) -> {
                bipedModel.leftArm.xRot = -45;
                bipedModel.leftArm.yRot = 0.3F + bipedModel.head.yRot;
            }) : ((bipedModel, t) -> {
                bipedModel.leftArm.xRot = bipedModel.leftArm.xRot;
                bipedModel.leftArm.yRot = bipedModel.leftArm.yRot;
            });
    } else {
            return side == Hand.OFF_HAND ? ((bipedModel, t) -> {
                bipedModel.leftArm.xRot = -110;
            }) : ((bipedModel, t) -> {
                bipedModel.leftArm.xRot = bipedModel.leftArm.xRot;
                bipedModel.leftArm.yRot = bipedModel.leftArm.yRot;
            });
        }
    }





    @Override
    public <T extends AbstractClientPlayerEntity> BiConsumer<BipedModel, T> getRightArmPoser(Hand side, ItemStack stack, LivingEntity livingEntity) {

        PlayerEntity playerEntity = (PlayerEntity) livingEntity;
        if (this.isused == false) {

            return side == Hand.MAIN_HAND ? ((bipedModel, t) -> {
                bipedModel.rightArm.xRot = -45;
                bipedModel.rightArm.yRot = (-0.3F) + bipedModel.head.yRot;
            }) : ((bipedModel, t) -> {
                bipedModel.rightArm.xRot = bipedModel.rightArm.xRot;
                bipedModel.rightArm.yRot = bipedModel.rightArm.yRot;
            });
    } else {
            return side == Hand.MAIN_HAND ? ((bipedModel, t) -> {
                bipedModel.rightArm.xRot = -110;
            }) : ((bipedModel, t) -> {
                bipedModel.rightArm.xRot = bipedModel.rightArm.xRot;
                bipedModel.rightArm.yRot = bipedModel.rightArm.yRot;
            });
        }
    }





    @Override
    public <T extends AbstractClientPlayerEntity> BiConsumer<MatrixStack, T> getItemPoser(Hand side, ItemStack stack, LivingEntity livingEntity) {
        return side == Hand.MAIN_HAND ? ((matrixStack, t) -> {
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
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        ItemStack itemstack = p_77659_2_.getItemInHand(p_77659_3_);
        boolean flag = !p_77659_2_.getProjectile(itemstack).isEmpty();

        if (!p_77659_2_.abilities.instabuild && !flag) {
            return ActionResult.fail(itemstack);
        } else {
            p_77659_2_.startUsingItem(p_77659_3_);
            return ActionResult.consume(itemstack);
        }
    }


    @Override
    public boolean isRightArmAnimatedD(Hand side, ItemStack stack) {
        return side == Hand.MAIN_HAND ;
    }

    @Override
    public boolean isLeftArmAnimatedD(Hand side, ItemStack stack) {
        return side == Hand.OFF_HAND ;
    }

    @Override
    public boolean isItemAnimatedD(Hand side, ItemStack stack, LivingEntity livingEntity) {

        PlayerEntity playerEntity = (PlayerEntity) livingEntity;
        return this.isused;
    }
}
