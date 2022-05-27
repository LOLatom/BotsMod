package com.thefreak.botsmod.objects.blocks.Decorative;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class TarTorch extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty LIGHTED = BooleanProperty.create("lighted");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape SHAPE = Block.box(6.5D, 0.0D, 6.5D, 9.5D, 14.0D, 9.5D);


    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        return SHAPE;
    }

    public TarTorch(Properties p_i48440_1_) {
        super(p_i48440_1_);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIGHTED, false).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player playerEntity, InteractionHand hand, BlockHitResult traceResult) {
        if (playerEntity.getItemInHand(hand).getItem() instanceof FlintAndSteelItem) {
        world.setBlock(pos, state.setValue(LIGHTED, true), 1);
            return InteractionResult.SUCCESS;
        }
        if (playerEntity.getItemInHand(hand).getItem() instanceof ShovelItem && state.getValue(LIGHTED) == true) {
            world.setBlock(pos, state.setValue(LIGHTED, false), 1);
            return InteractionResult.SUCCESS;
        }

        return super.use(state, world, pos, playerEntity, hand, traceResult);

    }

    public FluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_204507_1_);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_196258_1_) {
        FluidState fluidstate = p_196258_1_.getLevel().getFluidState(p_196258_1_.getClickedPos());
        return super.getStateForPlacement(p_196258_1_).setValue(LIGHTED, false).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    @Override
    public boolean canSurvive(BlockState p_196260_1_, LevelReader p_196260_2_, BlockPos p_196260_3_) {
        return canSupportCenter(p_196260_2_, p_196260_3_.below(), Direction.UP);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(LIGHTED, WATERLOGGED));
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        if (state.getValue(WATERLOGGED) == true && state.getValue(LIGHTED) == true && random.nextInt(7) == 5) {
            world.setBlock(pos, state.setValue(LIGHTED, false), 1);
        }

        super.tick(state, world, pos, random);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level p_180655_2_, BlockPos p_180655_3_, Random random) {
        double d0 = (double) p_180655_3_.getX() + 0.5D;
        double d1 = (double) p_180655_3_.getY() + 0.7D;
        double d2 = (double) p_180655_3_.getZ() + 0.5D;
        if (state.getValue(LIGHTED) == true) {
            p_180655_2_.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            if (state.getValue(WATERLOGGED) == false) {
                p_180655_2_.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
