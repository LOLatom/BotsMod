package com.thefreak.botsmod.objects.items.loreandclueitems;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.API.Animation.IHandlePoseable;
import com.thefreak.botsmod.API.IHaveSpecialTooltip;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.objects.items.bewlr.BanhirHeadBEWLR;
import com.thefreak.botsmod.objects.items.bewlr.BanhirHeadGeoBEWLR;
import com.thefreak.botsmod.objects.items.bewlr.GodKillerHandGEOBEWLR;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.awt.*;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BanhirHead extends Item implements IHandlePoseable, IHaveSpecialTooltip, IAnimatable, ISyncable {

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public BanhirHead(Properties pProperties) {
        super(pProperties);
        GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public <T extends Player> BiConsumer<HumanoidModel, T> getLeftArmPoser(InteractionHand side, ItemStack stack, LivingEntity livingEntity) {
        return side == InteractionHand.OFF_HAND ? ((bipedModel, t) -> {
            bipedModel.leftArm.xRot = -1.534516779623857f + bipedModel.head.xRot;
            bipedModel.leftArm.yRot = 0.3F + bipedModel.head.yRot;
        }) : ((bipedModel, t) -> {
            bipedModel.leftArm.xRot = bipedModel.leftArm.xRot;
            bipedModel.leftArm.yRot = bipedModel.leftArm.yRot;
        });
    }

    @Override
    public <T extends Player> BiConsumer<HumanoidModel, T> getRightArmPoser(InteractionHand side, ItemStack stack, LivingEntity livingEntity) {
        return side == InteractionHand.MAIN_HAND ? ((bipedModel, t) -> {
            bipedModel.rightArm.xRot =  -1.534516779623857f + bipedModel.head.xRot;
            bipedModel.rightArm.yRot = (-0.3F) + bipedModel.head.yRot;
        }) : ((bipedModel, t) -> {
            bipedModel.rightArm.xRot = bipedModel.rightArm.xRot;
            bipedModel.rightArm.yRot = bipedModel.rightArm.yRot;
        });
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(Component.nullToEmpty("4").copy().withStyle().setStyle(Style.EMPTY.withFont(new ResourceLocation(BotsMod.MOD_ID, "rarityfont"))));
    }

    @Override
    public <T extends Player> BiConsumer<PoseStack, T> getItemPoser(InteractionHand side, ItemStack stack, LivingEntity livingEntity) {
        return (poseStack, t) -> {};
    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }


    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new BanhirHeadGeoBEWLR();
            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {

                return renderer;
            }
        });
    }

    @Override
    public Color colorTop() {
        return new Color(66, 54, 50);
    }

    @Override
    public Color colorBottom() {
        return new Color(66, 54, 50);
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


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 1, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void onAnimationSync(int id, int state) {

    }
}
