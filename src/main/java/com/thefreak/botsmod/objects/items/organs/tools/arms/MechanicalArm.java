package com.thefreak.botsmod.objects.items.organs.tools.arms;

import com.google.common.base.Preconditions;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MechanicalArm extends ArmItem{
    public MechanicalArm(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        CompoundTag nbt = pStack.getOrCreateTag();
        ItemStackHandler parts = new ItemStackHandler(6);
        parts.deserializeNBT(nbt.getCompound("parts"));
        for (int i = 0; i < parts.getSlots(); i++) {
            if (!parts.getStackInSlot(i).isEmpty()) {
                pTooltipComponents.add(new TextComponent(parts.getStackInSlot(i).getItem().toString()));
            }
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundTag nbt) {
        super.readShareTag(stack, nbt);
        stack.setTag(nbt);
    }
    private static NonNullList<ItemStack> copyFromInv(Container inv) {
        NonNullList<ItemStack> ret = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ret.set(i, inv.getItem(i));
        }
        return ret;
    }

    private static void copyToInv(NonNullList<ItemStack> src, Container dest) {
        Preconditions.checkArgument(src.size() == dest.getContainerSize());
        for (int i = 0; i < src.size(); i++) {
            dest.setItem(i, src.get(i));
        }
    }

    @Nullable
    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        if (nbt.getCompound("parts").isEmpty()) {
            ItemStackHandler parts = new ItemStackHandler(6);
            nbt.put("parts", parts.serializeNBT());
        } else {
            ItemStackHandler parts = new ItemStackHandler(6);
            parts.deserializeNBT(nbt.getCompound("parts"));
            nbt.put("parts",parts.serializeNBT());
        }
        return nbt;
    }

    @Override
    public String getRendererID() {
        return "mechanicalArm";
    }
}
