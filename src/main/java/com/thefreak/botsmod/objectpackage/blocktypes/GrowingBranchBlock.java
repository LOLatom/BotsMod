package com.thefreak.botsmod.objectpackage.blocktypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class GrowingBranchBlock extends DirectionalBlock {

    private VoxelShape Y_AXIS_AABB = Block.box(0, 0, 0, 1, 1, 1);
    private VoxelShape Z_AXIS_AABB = Block.box(0, 0, 0, 1, 1, 1);
    private VoxelShape X_AXIS_AABB = Block.box(0, 0, 0, 1, 1, 1);

    private VoxelShape Y_AXIS_AABB_MIRROR = Block.box(0, 0, 0, 1, 1, 1);
    private VoxelShape Z_AXIS_AABB_MIRROR = Block.box(0, 0, 0, 1, 1, 1);
    private VoxelShape X_AXIS_AABB_MIRROR = Block.box(0, 0, 0, 1, 1, 1);


    public GrowingBranchBlock(Properties p_52591_, float x1, float y1, float z1 , float x2, float y2, float z2) {
        super(p_52591_);
        setxyzAxisAabb(x1,y1,z1,x2,y2,z2);
    }



    public VoxelShape getShape(BlockState p_154346_, BlockGetter p_154347_, BlockPos p_154348_, CollisionContext p_154349_) {
        switch(p_154346_.getValue(FACING)) {
            case EAST:
            default:
                return X_AXIS_AABB;
            case WEST:
                return X_AXIS_AABB_MIRROR;
            case NORTH:
                return Z_AXIS_AABB_MIRROR;
            case SOUTH:
                return Z_AXIS_AABB;
            case UP:
                return Y_AXIS_AABB;
            case DOWN:
                return Y_AXIS_AABB_MIRROR;
        }
    }

    protected void setxyzAxisAabb(float x1, float y1, float z1, float x2, float y2, float z2) {
        //Face 1
        this.Y_AXIS_AABB = Block.box(x1, y1, z1, x2, y2, z2);
        this.Z_AXIS_AABB = Block.box(x1, z1, y1, x2, z2, y2);
        this.X_AXIS_AABB = Block.box(y1, x1, z1, y2, x2, z2);

        //Face 2
        this.Y_AXIS_AABB_MIRROR = Block.box(16 -x2,16 - y2,16 - z2,16 - x1, 16, 16 - z1);
        this.Z_AXIS_AABB_MIRROR = Block.box(16 -x2,16 - z2,16 - y2, 16 - x1,16 - z1, 16);
        this.X_AXIS_AABB_MIRROR = Block.box( 16 -y2, 16 - x2,16 - z2, 16, 16 - x1, 16 - z1);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction direction = pContext.getClickedFace();
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos().relative(direction.getOpposite()));
        return blockstate.is(this) && blockstate.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()) : this.defaultBlockState().setValue(FACING, direction);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        super.tick(pState, pLevel, pPos, pRandom);
        grow(pState,pLevel,pPos,pRandom);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        return !canSurvive(pState,pLevel,pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }

    protected void grow(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {

    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        boolean upsurvive = pState.getValue(FACING) == Direction.DOWN;
        boolean downsurvive = pState.getValue(FACING) == Direction.UP;
        boolean rightsurvive = pState.getValue(FACING) == Direction.WEST;
        boolean leftsurvive = pState.getValue(FACING) == Direction.EAST;
        boolean frontsurvive = pState.getValue(FACING) == Direction.NORTH;
        boolean backsurvive = pState.getValue(FACING) == Direction.SOUTH;
        if (sideAttaching(pState,pLevel,pPos) == true && (leftsurvive || rightsurvive || frontsurvive || backsurvive)) {
            return true;
        } else if (topAttaching(pState,pLevel,pPos) == true && downsurvive) {
            return true;
        } else if (bottomAttaching(pState,pLevel,pPos) == true && upsurvive) {
            return true;
        } else return false;
    }

    //Set true if the block can attach to the sides of a log
    protected boolean sideAttaching(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return true;
    }

    //Set true if the block can attach on top of a log
    protected boolean topAttaching(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return true;
    }

    //Set true if the block can attach at the bottom of a log
    protected boolean bottomAttaching(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return true;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(FACING));
    }
}
