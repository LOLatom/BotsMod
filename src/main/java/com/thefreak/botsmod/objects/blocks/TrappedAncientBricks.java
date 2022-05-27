package com.thefreak.botsmod.objects.blocks;

import com.thefreak.botsmod.init.BlockInitNew;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;


import java.util.Random;



public class TrappedAncientBricks extends Block {
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public TrappedAncientBricks(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        BlockPos up = pos.above();
        boolean i = state.getValue(POWERED);
        if (worldIn.getBlockState(up).getBlock() instanceof AncientUrnCooked) {
            worldIn.setBlock(pos, state.setValue(POWERED, true), 2);
        } else {
            worldIn.setBlock(pos, state.setValue(POWERED, false), 2);
        }
        super.tick(state, worldIn, pos, rand);
    }

    protected void updateNeighborsInFront(Level worldIn, BlockPos pos, BlockState state) {
        worldIn.neighborChanged(pos, this, pos);
    }

    @Override
    public void updateIndirectNeighbourShapes(BlockState state, LevelAccessor worldIn, BlockPos pos, int flags, int recursionLeft) {
        super.updateIndirectNeighbourShapes(state, worldIn, pos, flags, recursionLeft);
    }

    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(POWERED) ? 15 : 0;
    }

    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(POWERED) ? 15 : 0;
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(POWERED));
    }



    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        BlockPos up = currentPos.above();
        if (worldIn.getBlockState(up).getBlock() instanceof AncientUrnCooked) {
            worldIn.setBlock(currentPos, stateIn.setValue(POWERED, true), 2);
        } else {
            worldIn.setBlock(currentPos, stateIn.setValue(POWERED, false), 2);
        }

        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
}
