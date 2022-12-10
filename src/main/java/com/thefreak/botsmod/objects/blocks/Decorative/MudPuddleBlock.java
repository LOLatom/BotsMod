package com.thefreak.botsmod.objects.blocks.Decorative;

import com.thefreak.botsmod.init.BlockInitNew;
import com.thefreak.botsmod.init.blockinit.NoItemBlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MudPuddleBlock extends Block implements SimpleWaterloggedBlock {

    public static final IntegerProperty STATE = IntegerProperty.create("state",0,6);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape SHAPE0 = Block.box(0, 0, 0, 16, 2, 16);
    protected static final VoxelShape SHAPE1 = Block.box(0, 0, 0, 16, 4, 16);
    protected static final VoxelShape SHAPE2 = Block.box(0, 0, 0, 16, 6, 16);
    protected static final VoxelShape SHAPE3 = Block.box(0, 0, 0, 16, 8, 16);
    protected static final VoxelShape SHAPE4 = Block.box(0, 0, 0, 16, 10, 16);
    protected static final VoxelShape SHAPE5 = Block.box(0, 0, 0, 16, 12, 16);
    protected static final VoxelShape SHAPE6 = Block.box(0, 0, 0, 16, 14, 16);

    public MudPuddleBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(STATE, 0).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    public FluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_204507_1_);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        switch (p_220053_1_.getValue(STATE)) {
            case 1:
                return SHAPE1;
            case 2:
                return SHAPE2;
            case 3:
                return SHAPE3;
            case 4:
                return SHAPE4;
            case 5:
                return SHAPE5;
            case 6:
                return SHAPE6;
            default:
                return SHAPE0;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_196258_1_) {
        FluidState fluidstate = p_196258_1_.getLevel().getFluidState(p_196258_1_.getClickedPos());
        BlockState bstate = p_196258_1_.getLevel().getBlockState(p_196258_1_.getClickedPos());
        if (bstate.getBlock() == BlockInitNew.MUD_PUDDLE.get() && bstate.getValue(STATE) < 6 && bstate.getValue(STATE) >= 0) {
            return super.getStateForPlacement(p_196258_1_).setValue(STATE, bstate.getValue(STATE) + 1).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
        } else if (bstate.getBlock() == BlockInitNew.MUD_PUDDLE.get() && bstate.getValue(STATE) == 6) {
            return NoItemBlockInit.MUD_PUDDLE_FULL_BLOCK.get().defaultBlockState();
        }
        return super.getStateForPlacement(p_196258_1_).setValue(STATE, 0).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        if (pState.getBlock() == BlockInitNew.MUD_PUDDLE.get() && pState.getValue(STATE) < 6 && pState.getValue(STATE) >= 0) {
            return true;
        } else if (pState.getBlock() == BlockInitNew.MUD_PUDDLE.get() && pState.getValue(STATE) == 6) {
            return true;
        }
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(STATE, WATERLOGGED));
    }

}
