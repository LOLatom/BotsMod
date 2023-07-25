package com.thefreak.botsmod.objects.containers;

import com.thefreak.botsmod.objects.items.loreandclueitems.coins.CoinItem;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;

public class CoinSpecContainer implements Container, StackedContentsCompatible {
    private final NonNullList<ItemStack> itemStacks = NonNullList.withSize(1, ItemStack.EMPTY);
    public int getContainerSize() {
        return 1;
    }

    public boolean isEmpty() {
        for(ItemStack itemstack : this.itemStacks) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }


    public ItemStack getItem(int pIndex) {
        return this.itemStacks.get(0);
    }


    public ItemStack removeItem(int pIndex, int pCount) {
        return ContainerHelper.takeItem(this.itemStacks, 0);
    }


    public ItemStack removeItemNoUpdate(int pIndex) {
        return ContainerHelper.takeItem(this.itemStacks, 0);
    }


    public void setItem(int pIndex, ItemStack pStack) {
            this.itemStacks.set(0, pStack);
    }

    public void setChanged() {
    }


    public boolean stillValid(Player pPlayer) {
        return true;
    }

    public void clearContent() {
        this.itemStacks.clear();
    }

    @Override
    public void fillStackedContents(StackedContents pHelper) {

    }


}
