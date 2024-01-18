package com.thefreak.botsmod.objects.items.magic;

import com.thefreak.botsmod.API.IHaveSpecialTooltip;
import com.thefreak.botsmod.API.ItemSpecialRendering.IHaveIcon;
import com.thefreak.botsmod.spells.implementations.IAmSpellCard;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.util.TriConsumer;

import java.awt.*;

public class MythicalSpellCardItem extends SpellCardItem implements IHaveIcon, IHaveSpecialTooltip, IAmSpellCard {

    public static int remainingticksTime = 72000;
    public MythicalSpellCardItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hasIcon() {
        return true;
    }

    @Override
    public ResourceLocation iconTex(ItemStack pStack) {
        return new ResourceLocation("botsmod:textures/icons/mythical_attack_icon.png");
    }

    @Override
    public boolean hasAdvancedCustomToolTip(ItemStack stack) {
        return true;
    }

    @Override
    public ResourceLocation AdvancedCustomToolTipLocation(ItemStack stack) {
        return new ResourceLocation("botsmod:textures/gui/tooltip/spell_card.png");
    }


    @Override
    public boolean hasCustomBackGroundColor() {
        return true;
    }
    public Color backgroundColorTop() {
        return new Color(35, 30, 27, 211);
    }

    @Override
    public Color backgroundColorBottom() {
        return new Color(25, 16, 16, 212);
    }

    @Override
    public Color colorTop() {
        return new Color(52, 53, 37);
    }

    @Override
    public Color colorBottom() {
        return new Color(52, 53, 37, 169);
    }


    @Override
    public ResourceLocation spellCardTexture() {
        return new ResourceLocation("botsmod:textures/gui/spell/empty_mythical_spell_card.png");
    }

    @Override
    public TriConsumer<Level, Player, InteractionHand> rightClickAction() {
        return (level,player,hand) ->{

        };
    }

    @Override
    public void tickingOnUse(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {

    }

    @Override
    public void onRelease(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {

    }
}
