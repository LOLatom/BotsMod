package com.thefreak.botsmod.objects.items.loreandclueitems;

import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.init.iteminit.FoodItemInit;
import com.thefreak.botsmod.objects.items.bewlr.BanhirHeadBEWLR;
import com.thefreak.botsmod.objects.items.bewlr.GodKillerHandBEWLR;
import com.thefreak.botsmod.objects.items.bewlr.GodKillerHandGEOBEWLR;
import com.thefreak.botsmod.objects.items.bewlr.models.GodKillerHandModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
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

import java.awt.*;
import java.util.function.Consumer;

public class GodKillerHand extends Item implements IAnimatable, ISyncable {
    private static final int F1 = 1;
    private static final int F2 = 2;
    private static final int F3 = 3;
    private static final int F4 = 4;

    private static final int ARM = 6;

    private static final int BLADE_OUT = 5;
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

            nbt.putBoolean("blade",false);
        } else {
            nbt.putBoolean("firstfing",nbt.getBoolean("firstfing"));
            nbt.putBoolean("secondfing",nbt.getBoolean("secondfing"));
            nbt.putBoolean("thirdfing",nbt.getBoolean("thirdfing"));
            nbt.putBoolean("fourthfing",nbt.getBoolean("fourthfing"));

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

        if (!pLevel.isClientSide) {
            // Gets the item that the player is holding, should be a JackInTheBoxItem

            final int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerLevel) pLevel);
            // Tell all nearby clients to trigger this JackInTheBoxItem
            final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> pPlayer);
            GeckoLibNetwork.syncAnimation(target, this, id, BLADE_OUT);
            GeckoLibNetwork.syncAnimation(target, this, id, F1);
            GeckoLibNetwork.syncAnimation(target, this, id, F2);
            GeckoLibNetwork.syncAnimation(target, this, id, F3);
            GeckoLibNetwork.syncAnimation(target, this, id, F4);

            GeckoLibNetwork.syncAnimation(target, this, id, ARM);

        }
        return InteractionResultHolder.pass(stack);
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
        Player player = pContext.getPlayer();
        Level level = pContext.getLevel();
        BlockPos clickpos = pContext.getClickedPos();

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

        } else {
            nbt.putBoolean("firstfing",false);
            nbt.putBoolean("secondfing",false);
            nbt.putBoolean("thirdfing",false);
            nbt.putBoolean("fourthfing",false);

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
        }
        if (state == F1) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "finger1");
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.god_killer_hand.firstfinger", ILoopType.EDefaultLoopTypes.PLAY_ONCE)
                        .addAnimation("animation.god_killer_hand.firstfingerup", ILoopType.EDefaultLoopTypes.LOOP));
            }
        }
        if (state == F2) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "finger2");
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.god_killer_hand.secondfinger", ILoopType.EDefaultLoopTypes.PLAY_ONCE)
                        .addAnimation("animation.god_killer_hand.secondfingerup", ILoopType.EDefaultLoopTypes.LOOP));
            }
        }
        if (state == F3) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "finger3");
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.god_killer_hand.thirdfinger", ILoopType.EDefaultLoopTypes.PLAY_ONCE)
                        .addAnimation("animation.god_killer_hand.thirdfingerup", ILoopType.EDefaultLoopTypes.LOOP));
            }
        }
        if (state == F4) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "finger4");
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.god_killer_hand.fourthfinger", ILoopType.EDefaultLoopTypes.PLAY_ONCE)
                        .addAnimation("animation.god_killer_hand.fourthfingerup", ILoopType.EDefaultLoopTypes.LOOP));
            }
        }
        if (state == ARM) {
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "arm");
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.god_killer_hand.bladearm", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            }
        }
    }
}
