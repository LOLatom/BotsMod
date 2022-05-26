package com.thefreak.botsmod.objects.blocks;

import com.thefreak.botsmod.API.TileEntity.ITileEntityBase;
import com.thefreak.botsmod.init.ModTileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CenoGoblinAnvil extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final VoxelShape BASEX = Block.box(2.0D, 0.0D, 4.0D, 14.0D, 4.0D, 12.0D);
    private static final VoxelShape X_LEG1 = Block.box(5.0D, 3.0D, 6.0D, 11.0D, 6.0D, 10.0D);
    private static final VoxelShape X_LEG2 = Block.box(4.0D, 6.0D, 5.0D, 12.0D, 10.0D, 11.0D);
    private static final VoxelShape X_TOP = Block.box(0.0D, 10.0D, 4.0D, 16.0D, 14.0D, 12.0D);
    private static final VoxelShape X_TOP2 = Block.box(3.0D, 8.0D, 4.0D, 13.0D, 10.0D, 12.0D);


    private static final VoxelShape BASEZ = Block.box(4.0D, 0.0D, 2.0D, 12.0D, 4.0D, 14.0D);
    private static final VoxelShape Z_LEG1 = Block.box(6.0D, 3.0D, 5.0D, 10.0D, 6.0D, 11.0D);
    private static final VoxelShape Z_LEG2 = Block.box(5.0D, 6.0D, 4.0D, 11.0D, 10.0D, 12.0D);
    private static final VoxelShape Z_TOP = Block.box(4.0D, 10.0D, 0.0D, 12.0D, 14.0D, 16.0D);
    private static final VoxelShape Z_TOP2 = Block.box(4.0D, 8.0D, 3.0D, 12.0D, 10.0D, 13.0D);

    private static final VoxelShape X_AXIS_AABB = Shapes.or(BASEX, X_LEG1, X_LEG2, X_TOP2, X_TOP);
    private static final VoxelShape Z_AXIS_AABB = Shapes.or(BASEZ, Z_LEG1, Z_LEG2, Z_TOP2, Z_TOP);

    public CenoGoblinAnvil(Properties p_i48440_1_) {
        super(p_i48440_1_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        Direction direction = p_220053_1_.getValue(FACING);
        return direction.getAxis() == Direction.Axis.X ? X_AXIS_AABB : Z_AXIS_AABB;
    }
    
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return ModTileEntityTypes.CENO_GOBLIN_ANVIL_TILE_ENTITY.get().create(pPos, pState);
    }
    
    public BlockState getStateForPlacement(BlockPlaceContext p_196258_1_) {
        return this.defaultBlockState().setValue(FACING, p_196258_1_.getHorizontalDirection().getClockWise());
    }

    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return p_185499_1_.setValue(FACING, p_185499_2_.rotate(p_185499_1_.getValue(FACING)));
    }
    
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(FACING);
    }
    



    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player playerEntity, InteractionHand hand, BlockHitResult blockRayTraceResult) {
        ((ITileEntityBase) world.getBlockEntity(pos)).Activated(state, world, playerEntity);
        return super.use(state, world, pos, playerEntity, hand, blockRayTraceResult);
    }

    @Override
    public void tick(BlockState state, ServerLevel serverWorld, BlockPos pos, Random random) {
        super.tick(state, serverWorld, pos, random);

    }
}
