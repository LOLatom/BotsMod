package com.thefreak.botsmod.fluids.objects;

import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class CellFluid extends ForgeFlowingFluid {
    protected CellFluid(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSource(FluidState pState) {
        return false;
    }

    @Override
    public int getAmount(FluidState pState) {
        return 0;
    }


}
