package com.thefreak.botsmod.objects.blocks.plants;

import com.thefreak.botsmod.init.blockinit.NoItemBlockInit;
import com.thefreak.botsmod.objectpackage.blocktypes.LogPlantBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.Random;

public class LuxureMelon extends LogPlantBlock {

    public static final IntegerProperty GROWTH = IntegerProperty.create("age", 0, 1);;

    public LuxureMelon(Properties p_52591_, float x1, float y1, float z1, float x2, float y2, float z2) {
        super(p_52591_, x1, y1, z1, x2, y2, z2);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.DOWN).setValue(GROWTH,0));
    }

    @Override
    protected void grow(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        super.grow(pState, pLevel, pPos, pRandom);
        if (pState.getValue(GROWTH) == 0 && pRandom.nextInt(10) == 2) {
            pLevel.setBlockAndUpdate(pPos,pState.setValue(GROWTH,1));
        }
    }

    @Override
    protected boolean sideAttaching(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return false;
    }

    @Override
    protected boolean topAttaching(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(GROWTH));
    }
}
