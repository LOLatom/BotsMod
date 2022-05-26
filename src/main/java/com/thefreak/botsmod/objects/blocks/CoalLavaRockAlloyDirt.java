package com.thefreak.botsmod.objects.blocks;

import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class CoalLavaRockAlloyDirt extends Block {
    public static final BooleanProperty ROCKY = BooleanProperty.create("rocky");
    public CoalLavaRockAlloyDirt(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ROCKY, false));

    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(ROCKY));
    }


    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        BlockPos pos1 = currentPos;
        BlockPos pos2 = currentPos.above();
        boolean rocky = stateIn.getValue(ROCKY);
        if (worldIn.getBlockState(pos2).getBlock() instanceof CoalLavaRockAlloy && rocky == false) {
            worldIn.setBlock(pos1, stateIn.setValue(ROCKY, true), 2);

        }
        if (worldIn.getBlockState(pos2).getBlock() instanceof AirBlock && rocky == true) {
            worldIn.setBlock(pos1, stateIn.setValue(ROCKY, false), 2);

        }
        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);

    }
}
