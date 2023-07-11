package com.thefreak.botsmod.util.capabilities;

import net.minecraftforge.common.util.LazyOptional;

public interface ISelectedSpellCapability {

    boolean hasSpell();

    int getSpell();

    void setSpell(int id);

}
