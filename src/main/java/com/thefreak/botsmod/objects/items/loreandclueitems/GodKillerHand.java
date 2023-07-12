package com.thefreak.botsmod.objects.items.loreandclueitems;

import com.thefreak.botsmod.API.IHaveSpecialTooltip;
import com.thefreak.botsmod.API.ItemSpecialRendering.IHaveIcon;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.objects.items.bewlr.GodKillerHandGEOBEWLR;
import com.thefreak.botsmod.util.capabilities.ItemCapGKH;
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
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class GodKillerHand extends Item implements IAnimatable, ISyncable, IHaveSpecialTooltip, IHaveIcon {

    public static AnimationBuilder IDLE_ANIM = new AnimationBuilder();
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
    public void registerControllers(AnimationData data) {
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }






    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {

        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
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
    public void onAnimationSync(int id, int state) {
    }

}
