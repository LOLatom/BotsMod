package com.thefreak.botsmod.objects.items.loreandclueitems;

import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TremoringTabletItem extends Item {
    public TremoringTabletItem(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        if (!stack.hasTag()) {
            nbt.putInt("spellid",0);
        } else {
            nbt.putInt("spellid",nbt.getInt("spellid"));
        }
        return nbt;
    }

    @Override
    public void readShareTag(ItemStack stack,  CompoundTag nbt) {
        super.readShareTag(stack,nbt);
        stack.setTag(nbt);
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        for (int i = 0; i < ClassReferences.getPlayer().getInventory().getContainerSize(); i++) {
            if (ClassReferences.getPlayer().getInventory().getItem(i).getItem() instanceof GodKillerHand) {
                ItemStack stack = ClassReferences.getPlayer().getInventory().getItem(i);
                CompoundTag nbt = stack.getOrCreateTag();
                pPlayer.getItemInHand(pUsedHand).getOrCreateTag().putInt("spellid",nbt.getInt("spellid"));
                return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        CompoundTag nbt = pStack.getOrCreateTag();
        if (pStack.hasTag()) {
        } else {
            nbt.putInt("spellid",0);
        }


    }

}
