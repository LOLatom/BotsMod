package com.thefreak.botsmod.objects.items.loreandclueitems;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.API.Animation.IHandlePoseable;
import com.thefreak.botsmod.API.IHaveSpecialTooltip;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.objects.items.bewlr.BanhirHeadBEWLR;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BanhirHead extends Item implements IHandlePoseable, IHaveSpecialTooltip {
    Minecraft mc = ClassReferences.getClientMC();

    public BanhirHead(Properties pProperties) {
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
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(Component.nullToEmpty("#2 is waiting").copy().withStyle().setStyle(Style.EMPTY.withFont(new ResourceLocation(BotsMod.MOD_ID, "underfont"))));
        pTooltipComponents.add(Component.nullToEmpty("#2 is a demon").copy().withStyle().setStyle(Style.EMPTY.withFont(new ResourceLocation(BotsMod.MOD_ID, "underfont"))));
        pTooltipComponents.add(Component.nullToEmpty("! dream for her").copy().withStyle().setStyle(Style.EMPTY.withFont(new ResourceLocation(BotsMod.MOD_ID, "underfont")).withColor(new Color(255, 98, 98).getRGB())));
        pTooltipComponents.add(Component.nullToEmpty("pure banishment for the demon").copy().withStyle().setStyle(Style.EMPTY.withFont(new ResourceLocation(BotsMod.MOD_ID, "underfont"))));
        pTooltipComponents.add(Component.nullToEmpty("NO DREAM").copy().withStyle().setStyle(Style.EMPTY.withFont(new ResourceLocation(BotsMod.MOD_ID, "underfont"))));
    }

    @Override
    public <T extends Player> BiConsumer<PoseStack, T> getItemPoser(InteractionHand side, ItemStack stack, LivingEntity livingEntity) {
        return (poseStack, t) -> {};
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {

                return new BanhirHeadBEWLR(mc.getBlockEntityRenderDispatcher(),mc.getEntityModels());
            }
        });
    }

    @Override
    public Color colorTop() {
        return new Color(64, 89, 66);
    }

    @Override
    public Color colorBottom() {
        return new Color(64, 89, 66);
    }

    @Override
    public Color backgroundColorTop() {
        return new Color(24, 26, 24, 237);
    }

    @Override
    public Color backgroundColorBottom() {
        return new Color(24, 26, 24, 237);
    }

    @Override
    public boolean hasCustomBackGroundColor() {
        return true;
    }

    @Override
    public boolean isRightArmAnimatedD(InteractionHand side, ItemStack stack) {
        return side == InteractionHand.MAIN_HAND ;
    }

    @Override
    public boolean isLeftArmAnimatedD(InteractionHand side, ItemStack stack) {
        return side == InteractionHand.OFF_HAND ;
    }


}
