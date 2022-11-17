package com.thefreak.botsmod.objects.blocks.plants;

import com.thefreak.botsmod.objectpackage.blocktypes.LogPlantBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class LogMushroom extends LogPlantBlock {
    public LogMushroom(Properties p_52591_, float x1, float y1, float z1, float x2, float y2, float z2) {
        super(p_52591_, x1, y1, z1, x2, y2, z2);
    }

    @Override
    protected boolean topAttaching(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return false;
    }

    @Override
    protected boolean bottomAttaching(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return false;
    }
}
