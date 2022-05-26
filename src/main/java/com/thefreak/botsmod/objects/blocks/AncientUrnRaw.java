package com.thefreak.botsmod.objects.blocks;

import com.thefreak.botsmod.init.BlockInitNew;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


import java.util.Random;


public class AncientUrnRaw extends Block {
    public static VoxelShape BASE = Block.box(5,0,5,11,12,11);

    public AncientUrnRaw(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return BASE;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        BlockPos down = pos.below();
        BlockPos downplus = pos.below(2);
        if (worldIn.getBlockState(down).getBlock() instanceof FireBlock || worldIn.getBlockState(downplus).getBlock() instanceof FireBlock) {
        worldIn.setBlockAndUpdate(pos, BlockInitNew.ANCIENT_POTTERY_URN.get().defaultBlockState());
        }
        super.tick(state, worldIn, pos, rand);
    }
}
