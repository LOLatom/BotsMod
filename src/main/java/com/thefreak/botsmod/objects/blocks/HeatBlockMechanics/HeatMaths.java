package com.thefreak.botsmod.objects.blocks.HeatBlockMechanics;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.SoulFireBlock;
import net.minecraft.world.level.material.LavaFluid;

public class HeatMaths {


    public Double getHeat(Level level, BlockPos pos, float accuracy, boolean isolate) {
        Block blocktarget = level.getBlockState(pos).getBlock();
        if (blocktarget instanceof FireBlock) {
            Float CoveredAccuracy =  1200 * (accuracy / 100);
            return CoveredAccuracy.doubleValue();
        } else if(blocktarget instanceof SoulFireBlock) {
            Float CoveredAccuracy =  9500 * (accuracy / 100);
            return CoveredAccuracy.doubleValue();
        } else {
            Float CoveredAccuracy =  22 * (accuracy / 100);
            return CoveredAccuracy.doubleValue();
        }
    }
}
