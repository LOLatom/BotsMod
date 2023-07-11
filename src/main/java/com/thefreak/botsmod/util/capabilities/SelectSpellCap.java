package com.thefreak.botsmod.util.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class SelectSpellCap implements ISelectedSpellCapability, INBTSerializable<CompoundTag> {
    private int SPELL_ID;

    private final int spellNull = -1;

    @Override
    public boolean hasSpell() {
        return SPELL_ID != spellNull;
    }

    @Override
    public int getSpell() {
        return this.SPELL_ID;
    }

    @Override
    public void setSpell(int id) {
        this.SPELL_ID = id;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("spellID", this.SPELL_ID);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        if (nbt.contains("spellID")) {
            this.SPELL_ID = nbt.getInt("spellID");
        } else {
            this.SPELL_ID = spellNull;
        }
    }
}
