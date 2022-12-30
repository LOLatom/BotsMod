package com.thefreak.botsmod.objects.blocks.plants;

import com.thefreak.botsmod.init.blockinit.NoItemBlockInit;
import com.thefreak.botsmod.objectpackage.blocktypes.LogBlock;
import com.thefreak.botsmod.objectpackage.blocktypes.LogPlantBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class MedusaGroveFlower extends LogPlantBlock {
    public MedusaGroveFlower(Properties p_52591_, float x1, float y1, float z1, float x2, float y2, float z2) {
        super(p_52591_, x1, y1, z1, x2, y2, z2);
    }

    @Override
    protected void grow(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        super.grow(pState, pLevel, pPos, pRandom);
        boolean upsurvive = pState.getValue(FACING) == Direction.DOWN && pLevel.getBlockState(pPos.above()).getBlock() instanceof LogBlock;
        if (upsurvive && pRandom.nextInt(10) == 2) {
            pLevel.setBlockAndUpdate(pPos, NoItemBlockInit.LUXURE_MELON_PLANT.get().defaultBlockState());
        }
    }
}
