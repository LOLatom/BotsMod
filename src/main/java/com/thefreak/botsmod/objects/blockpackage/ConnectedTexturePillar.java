package com.thefreak.botsmod.objects.blockpackage;




import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class ConnectedTexturePillar extends RotatedPillarBlock {
    public static final BooleanProperty C_UP = BooleanProperty.create("c_up");
    public static final BooleanProperty C_DOWN = BooleanProperty.create("c_down");

    public ConnectedTexturePillar(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(C_UP, false).setValue(C_DOWN, false));
    }



    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos pos, BlockPos facingPos) {

        boolean vertical = stateIn.getValue(AXIS) == Direction.Axis.Y;
        boolean horizontalx = stateIn.getValue(AXIS) == Direction.Axis.X;
        boolean horizontalz = stateIn.getValue(AXIS) == Direction.Axis.Z;


        BlockPos up = pos.above();
        BlockPos down = pos.below();
        BlockPos north = pos.north();
        BlockPos south = pos.south();
        BlockPos east = pos.east();
        BlockPos west = pos.west();

        //                              Y-AXIS                                      //
        if (vertical && isConnectedAt(up,worldIn) && !isConnectedAt(down, worldIn)) {
            System.out.println(pos.toString()+ ": BOTTOM part detected");
            worldIn.setBlock(pos, stateIn.setValue(C_UP, false).setValue(C_DOWN, true), 2);
        } else if (vertical && !isConnectedAt(up, worldIn) && isConnectedAt(down, worldIn)) {
            System.out.println(pos.toString() + ": TOP part detected");
            worldIn.setBlock(pos, stateIn.setValue(C_UP, true).setValue(C_DOWN, false), 2);
        } else if (vertical && !isConnectedAt(up, worldIn) && !isConnectedAt(down, worldIn)) {
            System.out.println(pos.toString() + ": MIDDLE part detected");
            worldIn.setBlock(pos, stateIn.setValue(C_UP, false).setValue(C_DOWN, false), 2);
        } else if (vertical && isConnectedAt(up, worldIn) && isConnectedAt(down, worldIn)) {
            System.out.println(pos.toString() + ": MIDDLE part detected");
            worldIn.setBlock(pos, stateIn.setValue(C_UP, false).setValue(C_DOWN, false), 2);
        }


        //                              Z-AXIS                                      //
        if (horizontalz && isConnectedAt(north,worldIn) && !isConnectedAt(south, worldIn)) {
            System.out.println(pos.toString()+ ": BOTTOM part detected");
            worldIn.setBlock(pos, stateIn.setValue(C_UP, false).setValue(C_DOWN, true), 2);
        } else if (horizontalz && !isConnectedAt(north, worldIn) && isConnectedAt(south, worldIn)) {
            System.out.println(pos.toString() + ": TOP part detected");
            worldIn.setBlock(pos, stateIn.setValue(C_UP, true).setValue(C_DOWN, false), 2);
        } else if (horizontalz && !isConnectedAt(north, worldIn) && !isConnectedAt(south, worldIn)) {
            System.out.println(pos.toString() + ": MIDDLE part detected");
            worldIn.setBlock(pos, stateIn.setValue(C_UP, false).setValue(C_DOWN, false), 2);
        } else if (horizontalz && isConnectedAt(north, worldIn) && isConnectedAt(south, worldIn)) {
            System.out.println(pos.toString() + ": MIDDLE part detected");
            worldIn.setBlock(pos, stateIn.setValue(C_UP, false).setValue(C_DOWN, false), 2);
        }

        //                              X-AXIS                                      //
        if (horizontalx && isConnectedAt(east,worldIn) && !isConnectedAt(west, worldIn)) {
            System.out.println(pos.toString()+ ": BOTTOM part detected");
            worldIn.setBlock(pos, stateIn.setValue(C_UP, false).setValue(C_DOWN, true), 2);
        } else if (horizontalx && !isConnectedAt(east, worldIn) && isConnectedAt(west, worldIn)) {
            System.out.println(pos.toString() + ": TOP part detected");
            worldIn.setBlock(pos, stateIn.setValue(C_UP, true).setValue(C_DOWN, false), 2);
        } else if (horizontalx && !isConnectedAt(east, worldIn) && !isConnectedAt(west, worldIn)) {
            System.out.println(pos.toString() + ": MIDDLE part detected");
            worldIn.setBlock(pos, stateIn.setValue(C_UP, false).setValue(C_DOWN, false), 2);
        } else if (horizontalx && isConnectedAt(east, worldIn) && isConnectedAt(west, worldIn)) {
            System.out.println(pos.toString() + ": MIDDLE part detected");
            worldIn.setBlock(pos, stateIn.setValue(C_UP, false).setValue(C_DOWN, false), 2);
        }
        return super.updateShape(stateIn, facing, facingState, worldIn, pos, facingPos);
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(C_DOWN).add(C_UP).add(AXIS);
    }

    public static boolean isConnectedAt(BlockPos pos, LevelAccessor worldIn) {
        boolean COnnect = worldIn.getBlockState(pos).getBlock() instanceof ConnectedTexturePillar;
        return COnnect;
    }


}
