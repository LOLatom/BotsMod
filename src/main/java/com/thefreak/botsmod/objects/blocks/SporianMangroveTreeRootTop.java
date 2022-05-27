package com.thefreak.botsmod.objects.blocks;

import com.thefreak.botsmod.init.BlockInitNew;

import net.minecraft.core.Direction;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;


public class SporianMangroveTreeRootTop extends GrowingPlantHeadBlock {
    int a;
    public static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);
    public SporianMangroveTreeRootTop(Properties properties) {
        super(properties, Direction.UP, SHAPE, false, 0.001D);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(Random rand) { return 0; }

    @Override
    protected boolean canGrowInto(BlockState state) { return NetherVines.isValidGrowthState(state); }

    @Override
    protected Block getBodyBlock() { return BlockInitNew.SPORIAN_MANGROVE_TREE_ROOT.get(); }

}
