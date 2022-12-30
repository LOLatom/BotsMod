package com.thefreak.botsmod.objects.items.loreandclueitems;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.API.Animation.IHandlePoseable;
import com.thefreak.botsmod.objects.items.bewlr.GodKillerHandGEOBEWLR;
import com.thefreak.botsmod.spells.SpellIdentifier;
import com.thefreak.botsmod.spells.spellclass.AbstractSpell;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.serverpackets.OpenFingerGUI;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class GodKillerHand extends Item implements IAnimatable, ISyncable, IHandlePoseable {
    public static final int F1 = 1;
    public static final int F2 = 2;
    public static final int F3 = 3;
    public static final int F4 = 4;

    public static final int ARM = 6;

    public static final int BLADE_OUT = 5;

    public static final int C1 = 7;
    public static final int C2 = 8;
    public static final int C3 = 9;
    public static final int C4 = 10;

    public static final int CB = 11;

    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder();
    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public GodKillerHand(Properties pProperties) {
        super(pProperties);
        GeckoLibNetwork.registerSyncable(this);
    }



    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new GodKillerHandGEOBEWLR();
            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {

                return renderer;
            }
        });
    }
    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }
    private <P extends Item & IAnimatable> PlayState predicate2(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }
    private <P extends Item & IAnimatable> PlayState predicate3(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }
    private <P extends Item & IAnimatable> PlayState predicate4(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }
    private <P extends Item & IAnimatable> PlayState predicate5(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }
    private <P extends Item & IAnimatable> PlayState predicate6(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }

    @Nullable
    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        if (!stack.hasTag()) {
            nbt.putBoolean("firstfing",false);
            nbt.putBoolean("secondfing",false);
            nbt.putBoolean("thirdfing",false);
            nbt.putBoolean("fourthfing",false);
            nbt.putInt("spellid",0);
            nbt.putInt("mode",0);

            nbt.putBoolean("blade",false);
        } else {
            nbt.putBoolean("firstfing",nbt.getBoolean("firstfing"));
            nbt.putBoolean("secondfing",nbt.getBoolean("secondfing"));
            nbt.putBoolean("thirdfing",nbt.getBoolean("thirdfing"));
            nbt.putBoolean("fourthfing",nbt.getBoolean("fourthfing"));
            nbt.putInt("spellid",nbt.getInt("spellid"));
            nbt.putInt("mode",nbt.getInt("mode"));

            nbt.putBoolean("blade",nbt.getBoolean("blade"));
        }

        return nbt;

    }

    @Override
    public void readShareTag(ItemStack stack,  CompoundTag nbt) {
        super.readShareTag(stack,nbt);
        stack.setTag(nbt);
    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "finger1", 1, this::predicate));
        data.addAnimationController(new AnimationController(this, "finger2", 1, this::predicate2));
        data.addAnimationController(new AnimationController(this, "finger3", 1, this::predicate3));
        data.addAnimationController(new AnimationController(this, "finger4", 1, this::predicate4));

        data.addAnimationController(new AnimationController(this, "arm", 1, this::predicate6));

        data.addAnimationController(new AnimationController(this, "blade", 1, this::predicate5));



    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        CompoundTag nbt = stack.getOrCreateTag();
        boolean finger1 = nbt.getBoolean("firstfing");
        boolean finger2 = nbt.getBoolean("secondfing");
        boolean finger3 = nbt.getBoolean("thirdfing");
        boolean finger4 = nbt.getBoolean("fourthfing");
        boolean blade = nbt.getBoolean("blade");
        int spellID = nbt.getInt("spellid");
        System.out.println(finger1 + " " + finger2 + " " + finger3 + " " + finger4);
        //if (pPlayer instanceof IBotsModAnimatable animatable) {
        //    animatable.getSet().startAnimation("botsmod.test", animatable.getObject().animator());
        //}


        if (!pLevel.isClientSide) {
            final int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerLevel) pLevel);
            final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> pPlayer);
            if (pPlayer.isCrouching()) {
                BotsPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) pPlayer), new OpenFingerGUI());
            }

        }
        if (!pPlayer.isCrouching()) {
            SpellIdentifier SI = new SpellIdentifier(finger1, finger2, finger3, finger4, blade, spellID, stack, null);
            AbstractSpell spell = SI.getSpellFromID(spellID);
            spell.startExecuting(pPlayer, pLevel, pUsedHand,null,false);
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        CompoundTag nbt = pStack.getOrCreateTag();
        boolean blade = nbt.getBoolean("blade");
        if (blade) {
            pTarget.hurt(DamageSource.mobAttack(pAttacker), 5F);
            System.out.println("BLADEDAMAGE");
        }
        boolean finger1 = nbt.getBoolean("firstfing");
        boolean finger2 = nbt.getBoolean("secondfing");
        boolean finger3 = nbt.getBoolean("thirdfing");
        boolean finger4 = nbt.getBoolean("fourthfing");
        int spellID = nbt.getInt("spellid");
        SpellIdentifier SI = new SpellIdentifier(finger1, finger2, finger3, finger4, blade, spellID, pStack, null);
        AbstractSpell spell = SI.getOnAttackSpellFromID(spellID);
        spell.setTarget(pTarget);
        spell.startExecuting((Player) pAttacker, pAttacker.level, InteractionHand.MAIN_HAND,null,false);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        ItemStack stack = pContext.getItemInHand();
        CompoundTag nbt = stack.getOrCreateTag();
        boolean finger1 = nbt.getBoolean("firstfing");
        boolean finger2 = nbt.getBoolean("secondfing");
        boolean finger3 = nbt.getBoolean("thirdfing");
        boolean finger4 = nbt.getBoolean("fourthfing");
        boolean blade = nbt.getBoolean("blade");
        int spellID = nbt.getInt("spellid");
        System.out.println("CLICKCLICKCLICK");

        if (!pContext.getPlayer().isCrouching()) {
            SpellIdentifier SI = new SpellIdentifier(finger1, finger2, finger3, finger4, blade, spellID, stack, pContext);
            AbstractSpell spell = SI.getUseOnSpellFromID(spellID);
            spell.startExecuting(pContext.getPlayer(), pContext.getLevel(), pContext.getHand(),pContext,false);
        }

        return super.useOn(pContext);
    }


    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        CompoundTag nbt = pStack.getOrCreateTag();
        if (pStack.hasTag()) {
            boolean blade = nbt.getBoolean("blade");
            if (blade) {

            }
        } else {
            nbt.putBoolean("firstfing",false);
            nbt.putBoolean("secondfing",false);
            nbt.putBoolean("thirdfing",false);
            nbt.putBoolean("fourthfing",false);
            nbt.putInt("spellid",0);
            nbt.putInt("mode",0);

            nbt.putBoolean("blade",false);
        }


    }


    @Override
    public void onAnimationSync(int id, int state) {
        if (state == BLADE_OUT) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "blade");
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.god_killer_hand.blade", ILoopType.EDefaultLoopTypes.PLAY_ONCE)
                        .addAnimation("animation.god_killer_hand.bladeout", ILoopType.EDefaultLoopTypes.LOOP));
            }
        } else if (state == CB) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "blade");

                controller.clearAnimationCache();
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.god_killer_hand.hideblade", ILoopType.EDefaultLoopTypes.PLAY_ONCE));

        }
        if (state == F1) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "finger1");
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.god_killer_hand.firstfinger", ILoopType.EDefaultLoopTypes.PLAY_ONCE)
                        .addAnimation("animation.god_killer_hand.firstfingerup", ILoopType.EDefaultLoopTypes.LOOP));
            }
        } else if (state == C1) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "finger1");

                controller.clearAnimationCache();
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.god_killer_hand.closefirst", ILoopType.EDefaultLoopTypes.PLAY_ONCE));

        }
        if (state == F2) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "finger2");
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.god_killer_hand.secondfinger", ILoopType.EDefaultLoopTypes.PLAY_ONCE)
                        .addAnimation("animation.god_killer_hand.secondfingerup", ILoopType.EDefaultLoopTypes.LOOP));
            }
        } else if (state == C2) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "finger2");

                controller.clearAnimationCache();
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.god_killer_hand.closesecond", ILoopType.EDefaultLoopTypes.PLAY_ONCE));

        }
        if (state == F3) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "finger3");
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.god_killer_hand.thirdfinger", ILoopType.EDefaultLoopTypes.PLAY_ONCE)
                        .addAnimation("animation.god_killer_hand.thirdfingerup", ILoopType.EDefaultLoopTypes.LOOP));
            }
        } else if (state == C3) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "finger3");

                controller.clearAnimationCache();
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.god_killer_hand.closethird", ILoopType.EDefaultLoopTypes.PLAY_ONCE));

        }
        if (state == F4) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "finger4");
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.god_killer_hand.fourthfinger", ILoopType.EDefaultLoopTypes.PLAY_ONCE)
                        .addAnimation("animation.god_killer_hand.fourthfingerup", ILoopType.EDefaultLoopTypes.LOOP));
            }
        } else if (state == C4) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "finger4");

                controller.clearAnimationCache();
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.god_killer_hand.closefourth", ILoopType.EDefaultLoopTypes.PLAY_ONCE));

        }
        if (state == ARM) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "arm");
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.god_killer_hand.bladearm", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            }
        }
    }
    @Override
    public boolean isRightArmAnimatedD(InteractionHand side, ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        boolean firstfingonly = nbt.getBoolean("firstfing") && !nbt.getBoolean("secondfing") && !nbt.getBoolean("thirdfing")
                && !nbt.getBoolean("fourthfing") && !nbt.getBoolean("blade");
        return side == InteractionHand.MAIN_HAND && firstfingonly;
    }

    @Override
    public boolean isLeftArmAnimatedD(InteractionHand side, ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        boolean firstfingonly = nbt.getBoolean("firstfing") && !nbt.getBoolean("secondfing") && !nbt.getBoolean("thirdfing")
                && !nbt.getBoolean("fourthfing") && !nbt.getBoolean("blade");
        return side == InteractionHand.OFF_HAND && firstfingonly;
    }

    @Override
    public <T extends Player> BiConsumer<HumanoidModel, T> getLeftArmPoser(InteractionHand side, ItemStack stack, LivingEntity livingEntity) {
        CompoundTag nbt = stack.getOrCreateTag();
        boolean firstfingonly = nbt.getBoolean("firstfing") && !nbt.getBoolean("secondfing") && !nbt.getBoolean("thirdfing")
                && !nbt.getBoolean("fourthfing") && !nbt.getBoolean("blade");
        if (firstfingonly) {
        return side == InteractionHand.OFF_HAND ? ((bipedModel, t) -> {
            bipedModel.leftArm.xRot = -51.8F + bipedModel.head.xRot;
            bipedModel.leftArm.yRot = 0.3F + bipedModel.head.yRot;
        }) : ((bipedModel, t) -> {
            bipedModel.leftArm.xRot = bipedModel.leftArm.xRot;
            bipedModel.leftArm.yRot = bipedModel.leftArm.yRot;
        });
        } else return (bipedModel, t) ->{};
    }

    @Override
    public <T extends Player> BiConsumer<HumanoidModel, T> getRightArmPoser(InteractionHand side, ItemStack stack, LivingEntity livingEntity) {
        CompoundTag nbt = stack.getOrCreateTag();
        boolean firstfingonly = nbt.getBoolean("firstfing") && !nbt.getBoolean("secondfing") && !nbt.getBoolean("thirdfing")
                && !nbt.getBoolean("fourthfing") && !nbt.getBoolean("blade");
        if (firstfingonly) {
            return side == InteractionHand.MAIN_HAND ? ((bipedModel, t) -> {
                bipedModel.rightArm.xRot = -51.8F + bipedModel.head.xRot;
                bipedModel.rightArm.yRot = (-0.3F) + bipedModel.head.yRot;
            }) : ((bipedModel, t) -> {
                bipedModel.rightArm.xRot = bipedModel.rightArm.xRot;
                bipedModel.rightArm.yRot = bipedModel.rightArm.yRot;
            });
        } else return (bipedModel, t) ->{};
    }

    @Override
    public <T extends Player> BiConsumer<PoseStack, T> getItemPoser(InteractionHand side, ItemStack stack, LivingEntity livingEntity) {
        return (poseStack, t) -> {};
    }
}
