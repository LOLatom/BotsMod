package com.thefreak.botsmod.objects.blocks.pumpkins;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TinyPeponFruitGrowth extends FallingBlock {
    protected static final VoxelShape SHAPE = Block.box(10.5D, 0.0D, 10.5D, 5.5D, 6, 5.5D);

    public TinyPeponFruitGrowth(Properties properties) {
        super(properties);

    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return true;
    }

}
