package com.thefreak.botsmod.spells.implementations;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.List;
import java.util.function.BiConsumer;

public interface IAmSpellCard {

    ResourceLocation spellCardTexture();

    TriConsumer<Level, Player, InteractionHand> rightClickAction();

    default Component spellName() {
        return new TextComponent("Empty");
    }

    default int modeRequirement() {return 0;}

    default int animationForActivation() {return -1;}

    default boolean isTickAllowed() {return false;}

    void tickingOnUse(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration);

    void onRelease(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged);
}
