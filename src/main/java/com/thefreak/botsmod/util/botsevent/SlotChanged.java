package com.thefreak.botsmod.util.botsevent;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.Event;

@OnlyIn(Dist.CLIENT)
public class SlotChanged extends Event {
    private final Container container;
    private final ItemStack preItemStack;
    private final ItemStack postItemStack;
    private final int slot;

    public SlotChanged(Container container, ItemStack preItemStack, ItemStack postItemStack, int slot) {
        this.container = container;
        this.preItemStack = preItemStack;
        this.postItemStack = postItemStack;
        this.slot = slot;
    }

    public Container getContainer() {
        return container;
    }

    public ItemStack getPreItemStack() {
        return preItemStack;
    }

    public ItemStack getPostItemStack() {
        return postItemStack;
    }

    public int getSlot() {
        return slot;
    }
}
