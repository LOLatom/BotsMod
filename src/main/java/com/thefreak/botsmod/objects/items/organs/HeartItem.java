package com.thefreak.botsmod.objects.items.organs;

import com.thefreak.botsmod.API.IHaveSpecialTooltip;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.init.iteminit.FoodItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

public class HeartItem extends Item implements IHaveSpecialTooltip {


    public HeartItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<net.minecraft.network.chat.Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        CompoundTag nbt = pStack.getOrCreateTag();
        if(pStack.hasTag()) {
            String Health = "";
            if (nbt.getInt("health") % 2 == 0) {
                String a = "";
                String b = "";
                int a1 = nbt.getInt("health") / 2;
                int b1 = (24 - a1 * 2) / 2;
                for (int i = 0; i < a1; i++) {
                    a = a + "A";
                }
                for (int i = 0; i < b1; i++) {
                    b = b + "C";
                }
                Health = a + b;
            }
            if (nbt.getInt("health") % 2 == 1) {
                String a = "";
                String b = "";
                String c = "B";
                int a1 = nbt.getInt("health") / 2;
                int b1 = (24 - a1 * 2) / 2 - 1;
                for (int i = 0; i < a1; i++) {
                    a = a + "A";
                }
                for (int i = 0; i < b1; i++) {
                    b = b + "C";
                }
                Health = a + c + b;
            }
            pTooltipComponents.add(net.minecraft.network.chat.Component.nullToEmpty(Health).copy().withStyle().setStyle(Style.EMPTY.withFont(new ResourceLocation(BotsMod.MOD_ID, "logofont"))));
        }
        pTooltipComponents.add(net.minecraft.network.chat.Component.nullToEmpty("DDEF").copy().withStyle().setStyle(Style.EMPTY.withFont(new ResourceLocation(BotsMod.MOD_ID, "logofont"))));
        if (pStack.hasTag()) {
            if (nbt.getBoolean("hasSoul") == true) {

            pTooltipComponents.add(net.minecraft.network.chat.Component.nullToEmpty("G").copy().withStyle().setStyle(Style.EMPTY.withFont(new ResourceLocation(BotsMod.MOD_ID, "logofont"))));
        }
        }
    }

    @Override
    public boolean hasCustomBackGroundColor() {
        return true;
    }

    @Override
    public Color backgroundColorTop() {
        return new Color(68, 87, 66, 237);
    }

    @Override
    public Color backgroundColorBottom() {
        return new Color(55, 63, 48, 237);
    }

    @Override
    public Color colorTop() {
        return new Color(81, 52, 58);
    }

    @Override
    public Color colorBottom() {
        return new Color(44, 28, 31);
    }



    @Nullable
    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        if (!stack.hasTag()) {
            nbt.putInt("tick",0);
            nbt.putInt("health",24);
            nbt.putBoolean("hasSoul",true);
        } else {
            nbt.putInt("tick", nbt.getInt("tick"));
            nbt.putInt("health",nbt.getInt("health"));
            nbt.putBoolean("hasSoul",nbt.getBoolean("hasSoul"));
        }

            return nbt;

    }

    @Override
    public void readShareTag(ItemStack stack,  CompoundTag nbt) {
        super.readShareTag(stack,nbt);
        stack.setTag(nbt);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        CompoundTag nbt = pStack.getOrCreateTag();
        if (pStack.hasTag()) {
            nbt.putInt("tick", nbt.getInt("tick") + 1);
            if (nbt.getInt("tick") == 1200 && nbt.getInt("health") > 0) {
                nbt.putInt("health", nbt.getInt("health") - 1);
                nbt.putInt("tick", 0);
            }
            if (nbt.getInt("health") == 0) {
                Player player = (Player) pEntity;
                player.addItem(FoodItemInit.PERISHED_HEART.get().getDefaultInstance());
                pStack.setCount(0);
            }
        } else {
            nbt.putInt("tick",0);
            nbt.putInt("health",24);
            nbt.putBoolean("hasSoul",true);
        }


    }
}
