package com.thefreak.botsmod.util.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class PlayerLimbsCap {
    private boolean mainArm = true;

    public boolean hasMainArm() {
        return mainArm;
    }

    public void cutMainArm() {
        mainArm = false;
    }

    public void restoreMainArm() {
        mainArm = true;
    }

    public void copyFrom(PlayerLimbsCap source) {
        mainArm = source.mainArm;
    }


    public void saveNBTData(CompoundTag nbt) {
        nbt.putBoolean("mainArm", mainArm);
    }

    public void loadNBTData(CompoundTag nbt){
        mainArm = nbt.getBoolean("mainArm");
    }
}
