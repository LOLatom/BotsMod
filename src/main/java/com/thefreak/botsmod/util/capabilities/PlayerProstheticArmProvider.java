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

public class PlayerProstheticArmProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerProstheticArmCap> PLAYER_PROSTHETIC_ARM = CapabilityManager.get(new CapabilityToken<PlayerProstheticArmCap>() {
    });
    private PlayerProstheticArmCap prostheticArm = null;

    private final LazyOptional<PlayerProstheticArmCap> optional = LazyOptional.of(this::createProstheticArmCap);

    private PlayerProstheticArmCap createProstheticArmCap() {
        if (this.prostheticArm == null) {
            this.prostheticArm = new PlayerProstheticArmCap();
        }
        return this.prostheticArm;
    }
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_PROSTHETIC_ARM) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createProstheticArmCap().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createProstheticArmCap().loadNBTData(nbt);
    }
}
