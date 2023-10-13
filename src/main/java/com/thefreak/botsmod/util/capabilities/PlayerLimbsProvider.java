package com.thefreak.botsmod.util.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerLimbsProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerLimbsCap> PLAYER_LIMBS = CapabilityManager.get(new CapabilityToken<PlayerLimbsCap>() {
    });
    private PlayerLimbsCap playerLimbsCap = null;

    private final LazyOptional<PlayerLimbsCap> optional = LazyOptional.of(this::createLimbsCap);

    private PlayerLimbsCap createLimbsCap() {
        if (this.playerLimbsCap == null) {
            this.playerLimbsCap = new PlayerLimbsCap();
        }
        return this.playerLimbsCap;
    }
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_LIMBS) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createLimbsCap().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createLimbsCap().loadNBTData(nbt);
    }
}
