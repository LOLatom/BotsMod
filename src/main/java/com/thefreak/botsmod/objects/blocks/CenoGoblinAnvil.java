package com.thefreak.botsmod.objects.blocks;

import com.thefreak.botsmod.API.TileEntity.ITileEntityBase;
import com.thefreak.botsmod.init.ModTileEntityTypes;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class CenoGoblinAnvil extends Block implements ITileEntityProvider {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
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

    private static final VoxelShape X_AXIS_AABB = VoxelShapes.or(BASEX, X_LEG1, X_LEG2, X_TOP2, X_TOP);
    private static final VoxelShape Z_AXIS_AABB = VoxelShapes.or(BASEZ, Z_LEG1, Z_LEG2, Z_TOP2, Z_TOP);

    public CenoGoblinAnvil(Properties p_i48440_1_) {
        super(p_i48440_1_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        Direction direction = p_220053_1_.getValue(FACING);
        return direction.getAxis() == Direction.Axis.X ? X_AXIS_AABB : Z_AXIS_AABB;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }


    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.CENO_GOBLIN_ANVIL_TILE_ENTITY.get().create();
    }

    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        return this.defaultBlockState().setValue(FACING, p_196258_1_.getHorizontalDirection().getClockWise());
    }

    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return p_185499_1_.setValue(FACING, p_185499_2_.rotate(p_185499_1_.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(FACING);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader blockReader) {
        return null;
    }




    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        ((ITileEntityBase) world.getBlockEntity(pos)).Activated(state, world, playerEntity);
        return super.use(state, world, pos, playerEntity, hand, blockRayTraceResult);
    }

    @Override
    public void tick(BlockState state, ServerWorld serverWorld, BlockPos pos, Random random) {
        super.tick(state, serverWorld, pos, random);

    }
}
