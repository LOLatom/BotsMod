package com.thefreak.botsmod.objects.items.loreandclueitems;

import com.thefreak.botsmod.API.IHaveSpecialTooltip;
import com.thefreak.botsmod.API.ItemSpecialRendering.IHaveIcon;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import com.thefreak.botsmod.objects.items.bewlr.GodKillerHandGEOBEWLR;
import com.thefreak.botsmod.spells.implementations.IAmSpellCard;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.StartAnimationPacket;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;
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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GodKillerHand extends Item implements IAnimatable, ISyncable, IHaveSpecialTooltip, IHaveIcon {

    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder();

    public static final int ANIM_NONE = -1;
    public static final int ANIM_OPEN = 0;

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public GodKillerHand(Properties pProperties) {
        super(pProperties);
        GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public boolean hasIcon() {
        return true;
    }

    @Override
    public ResourceLocation iconTex(ItemStack pStack) {
        return new ResourceLocation("botsmod:textures/icons/caster_icon.png");
    }

    @Override
    public Component getName(ItemStack pStack) {
        return (Component.nullToEmpty("GOD KILLER HAND").copy().withStyle(Style.EMPTY.withFont(new ResourceLocation(BotsMod.MOD_ID, "underfont"))));
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

    @Nullable
    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        if (!nbt.contains("spellID")) {
            nbt.putInt("spellID", 0);
            nbt.putInt("handMode", 0);
        } else {
            nbt.putInt("spellID", nbt.getInt("spellID"));
            nbt.putInt("handMode", nbt.getInt("handMode"));
        }
        return nbt;

    }

    @Override
    public Color colorBottom() {
        return new Color(79, 55, 34, 255);
    }

    @Override
    public Color colorTop() {
        return new Color(142, 98, 41, 255);
    }

    @Override
    public Color backgroundColorTop() {
        return new Color(44, 35, 30,226);
    }

    @Override
    public Color backgroundColorBottom() {
        return new Color(20, 18, 17, 226);
    }

    @Override
    public boolean hasCustomBackGroundColor() {
        return true;
    }


    @Override
    public void readShareTag(ItemStack stack,  CompoundTag nbt) {
        super.readShareTag(stack,nbt);
        stack.setTag(nbt);
    }




    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ArrayList<ItemStack> inv = new ArrayList<>();
        CompoundTag nbt = pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag();
        int mode = nbt.getInt("handMode");
        for (int i = 0; i < pPlayer.getInventory().getContainerSize() -1; i++) {
                if (pPlayer.getInventory().getItem(i).getItem() instanceof IAmSpellCard spellcard) {
                    if (mode == spellcard.modeRequirement()) inv.add(pPlayer.getInventory().getItem(i));
                }
        }
        int selection = nbt.getInt("spellID");
        // Execute the spell
        if (inv.size() >0) {
            ((IAmSpellCard) inv.get(selection).getItem()).rightClickAction().accept(pLevel, pPlayer, pUsedHand);
        }

        if (pLevel.isClientSide && inv.size() >0) {
            BotsPacketHandler.INSTANCE.sendToServer(new StartAnimationPacket(((IAmSpellCard) inv.get(selection).getItem()).animationForActivation()));
        }
        if (inv.size() >0) {
            if (((IAmSpellCard) inv.get(selection).getItem()).isTickAllowed()) pPlayer.startUsingItem(pUsedHand);
        }

        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        Player player = (Player) pLivingEntity;
        if (pLevel.isClientSide) {
            BotsPacketHandler.INSTANCE.sendToServer(new StartAnimationPacket(ANIM_NONE));
        }
        ArrayList<ItemStack> inv = new ArrayList<>();
        CompoundTag nbt = player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag();
        int mode = nbt.getInt("handMode");
        for (int i = 0; i < player.getInventory().getContainerSize() -1; i++) {
            if (player.getInventory().getItem(i).getItem() instanceof IAmSpellCard spellcard) {
                if (mode == spellcard.modeRequirement()) inv.add(player.getInventory().getItem(i));
            }
        }
        int selection = nbt.getInt("spellID");
        if (inv.size() >0) {
            ((IAmSpellCard) inv.get(selection).getItem()).onRelease(pStack, pLevel, pLivingEntity, pTimeCharged);
        }
        System.out.println("discharged" + player.getName());
        super.releaseUsing(pStack, pLevel, pLivingEntity, pTimeCharged);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {

        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        Player player = (Player) pLivingEntity;
        ArrayList<ItemStack> inv = new ArrayList<>();
        CompoundTag nbt = player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag();
        int mode = nbt.getInt("handMode");
        for (int i = 0; i < player.getInventory().getContainerSize() -1; i++) {
            if (player.getInventory().getItem(i).getItem() instanceof IAmSpellCard spellcard) {
                if (mode == spellcard.modeRequirement()) inv.add(player.getInventory().getItem(i));
            }
        }
        int selection = nbt.getInt("spellID");
        if (inv.size() >0) {
            ((IAmSpellCard) inv.get(selection).getItem()).tickingOnUse(pLevel,pLivingEntity,pStack,pRemainingUseDuration);
        }
        super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        return super.useOn(pContext);
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {

    }
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "openhand", 1, this::predicate));
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    @Nullable
    @Override
    public Entity createEntity(Level level, Entity location, ItemStack stack) {
        return new ItemStack(ItemInitNew.GOD_KILLER_HAND.get()).getEntityRepresentation();
    }

    @Override
    public void onAnimationSync(int id, int state) {
        AnimationController<?> controller = null;
        AnimationBuilder animationBuilder = new AnimationBuilder();
        if (state == ANIM_OPEN) {
            controller = GeckoLibUtil.getControllerForID(this.factory, id, "openhand");
            controller.markNeedsReload();
            controller.setAnimation(animationBuilder.addAnimation("animation.god_killer_hand.open", ILoopType.EDefaultLoopTypes.LOOP));
        } else {
            controller = GeckoLibUtil.getControllerForID(this.factory, id, "openhand");
            controller.markNeedsReload();
            controller.setAnimation(animationBuilder.addAnimation("animation.god_killer_hand.none", ILoopType.EDefaultLoopTypes.LOOP));

        }

    }

}
