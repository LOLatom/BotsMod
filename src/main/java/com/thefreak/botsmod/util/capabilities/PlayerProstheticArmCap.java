package com.thefreak.botsmod.util.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;

public class PlayerProstheticArmCap {
    private ItemStack prostheticArm = ItemStack.EMPTY;


    public ItemStack getProstheticArm() {
        return prostheticArm;
    }

    public void emptyPlayerProstheticArm() {
        prostheticArm = ItemStack.EMPTY;
    }

    public void copyFrom(PlayerProstheticArmCap source) {
        prostheticArm = source.prostheticArm;
    }

    public void setProstheticArm(ItemStack stack) {
        prostheticArm = stack;
    }

    public void saveNBTData(CompoundTag nbt) {
        CompoundTag itemTag = new CompoundTag();
        if (prostheticArm == null) {
            ItemStack.EMPTY.save(itemTag);
        } else {
            prostheticArm.save(itemTag);
        }
        nbt.put("prosthetic", itemTag);
    }

    public void loadNBTData(CompoundTag nbt){
        prostheticArm = ItemStack.of(nbt.getCompound("prosthetic"));
    }
}
