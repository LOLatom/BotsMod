package com.thefreak.botsmod.objects.items.Capabilities;

import com.thefreak.botsmod.objects.items.GeoriteCrystal;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityProvideGeoriteCrystal implements ICapabilitySerializable<INBT> {
    @Nullable
    private static final Direction DIRECTION_NULL = null;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if(GeoriteCrystalCapabilities.CAPABILITY_SOUL == cap) {
            return  (LazyOptional<T>)LazyOptional.of(()-> georiteSoul);
        }
        return LazyOptional.empty();
    }



    @Override
    public INBT serializeNBT() {
        return GeoriteCrystalCapabilities.CAPABILITY_SOUL.writeNBT(georiteSoul, DIRECTION_NULL);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        GeoriteCrystalCapabilities.CAPABILITY_SOUL.readNBT(georiteSoul, DIRECTION_NULL, nbt);
    }

    private GeoriteSoul georiteSoul = new GeoriteSoul();
}
