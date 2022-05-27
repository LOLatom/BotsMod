package com.thefreak.botsmod.objects.blockpackage;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ConnectedWoodBeam extends RotatedPillarBlock {
    public static final BooleanProperty C_UP = BooleanProperty.create("c_up");
    public static final BooleanProperty C_DOWN = BooleanProperty.create("c_down");
    public static final BooleanProperty ATTACH = BooleanProperty.create("attach");
    protected static final VoxelShape SHAPE_Y = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    protected static final VoxelShape SHAPE_X = Block.box(0.0D, 5.0D, 4.0D, 16.0D, 15.0D, 12.0D);
    protected static final VoxelShape SHAPE_Z = Block.box(4.0D, 5.0D, 0.0D, 12.0D, 15.0D, 16.0D);

    public ConnectedWoodBeam(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(C_UP, false).setValue(C_DOWN, false).setValue(ATTACH, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        switch (state.getValue(AXIS)) {
            case X:
            default:
                return SHAPE_X;

            case Y:
                return SHAPE_Y;

            case Z:
                return SHAPE_Z;
        }
    }



    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos pos, BlockPos facingPos) {

        boolean vertical = stateIn.getValue(AXIS) == Direction.Axis.Y;
        boolean horizontalx = stateIn.getValue(AXIS) == Direction.Axis.X;
        boolean horizontalz = stateIn.getValue(AXIS) == Direction.Axis.Z;
        boolean cup = stateIn.getValue(C_UP);
        boolean cdown = stateIn.getValue(C_DOWN);

        BlockPos up = pos.above();
        BlockPos down = pos.below();
        BlockPos north = pos.north();
        BlockPos south = pos.south();
        BlockPos east = pos.east();
        BlockPos west = pos.west();

        updateIndirectNeighbourShapes(stateIn,worldIn,pos,2,1);

        //                  ATTACHEMENT           Y-AXIS                            //
        if (vertical && isConnectedAt(north,worldIn)
                || isConnectedAt(south,worldIn)
                || isConnectedAt(east,worldIn)
                || isConnectedAt(west,worldIn)) {

            worldIn.setBlock(pos, stateIn.setValue(ATTACH, true),2);


        }
        if (vertical && !isConnectedAt(north,worldIn)
                ) {
            if (!isConnectedAt(south,worldIn)) {
                if (!isConnectedAt(east,worldIn)) {
                    if (!isConnectedAt(west,worldIn)) {

                        worldIn.setBlock(pos, stateIn.setValue(ATTACH, false), 2);
                    }
                }
            }
        }



        return super.updateShape(stateIn, facing, facingState, worldIn, pos, facingPos);
    }



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(C_DOWN).add(C_UP).add(ATTACH).add(AXIS);
    }

    public static boolean isConnectedAt(BlockPos pos, LevelAccessor worldIn) {
        boolean COnnect = worldIn.getBlockState(pos).getBlock() instanceof ConnectedWoodBeam;
        return COnnect;
    }
    public static boolean isAttachedAt(BlockPos pos, LevelAccessor worldIn) {
        boolean COnnect = worldIn.getBlockState(pos).getBlock() instanceof ConnectedWoodBeam;
        boolean ConnectedAir = worldIn.getBlockState(pos).getBlock() instanceof AirBlock;
        boolean ConeCt = !COnnect && !ConnectedAir;
        return ConeCt;
    }


}
