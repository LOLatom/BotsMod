package com.thefreak.botsmod.util.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemCapGKH implements ICapabilitySerializable<CompoundTag> {


    public ItemCapGKH(ItemStack stack, CompoundTag nbt) {
        if (nbt != null && nbt.contains("spellCap")) {
            selectSpellCap.deserializeNBT(nbt.getCompound("spellCap"));
        }
    }

    private final SelectSpellCap selectSpellCap = new SelectSpellCap();

    private final LazyOptional<ISelectedSpellCapability> lazySelectedSpell = LazyOptional.of(() -> selectSpellCap);
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilitySelectedSpell.SPELL_SELECT) {
            return this.lazySelectedSpell.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return selectSpellCap.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        selectSpellCap.deserializeNBT(nbt);
    }
}
