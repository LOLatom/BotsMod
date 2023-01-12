package com.thefreak.botsmod.mixins.client;

import com.thefreak.botsmod.util.BOTSEvent;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Slot.class)
public class SlotMixinForEvent {

    @Final
    @Shadow
    public Container container;

    @Final
    @Shadow
    private int slot;


    @Inject(method = "set", at = @At("HEAD"))
    public void setChangedEventMixin(ItemStack pStack,CallbackInfo ci) {
        ItemStack preItemStack = container.getItem(slot);
        BOTSEvent.BOTSPlayerEvent.SlotEvents.setSlotChanged(container, preItemStack, pStack, slot);
    }
}
