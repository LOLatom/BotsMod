package com.thefreak.botsmod.objects.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BronzeAlloyCrystal extends DirectionalBlock {
    protected static final VoxelShape SHAPE_VERTICAL = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 22.0D, 12.0D);
    protected static final VoxelShape SHAPE1 = Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 16.0D);
    protected static final VoxelShape SHAPE2 = Block.box(0.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D);

    public BronzeAlloyCrystal(Properties properties) {
        super(properties);
    }
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        switch(state.getValue(FACING).getAxis()) {
            case X:
            default:
                return SHAPE2;
            case Z:
                return SHAPE1;
            case Y:
                return SHAPE_VERTICAL;
        }
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getClickedFace();
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction.getOpposite()));
        return blockstate.getBlock() == this && blockstate.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()) : this.defaultBlockState().setValue(FACING, direction);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

}
