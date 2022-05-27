package com.thefreak.botsmod.objects.blocks;


import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.BeaconBeamBlock;

public class SulfurGlassBlock extends AbstractGlassBlock implements BeaconBeamBlock {
    public SulfurGlassBlock(Properties p_i49999_1_) {
        super(p_i49999_1_);
    }

    @Override
    public DyeColor getColor() {
        return null;
    }

}
