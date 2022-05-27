package com.thefreak.botsmod.objects.blocks;

import com.thefreak.botsmod.init.BlockInitNew;
import com.thefreak.botsmod.init.ItemInitNew;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;


import java.util.Random;

public class SporianSpikyLongusTop extends GrowingPlantHeadBlock implements BonemealableBlock {
    int a;
    public static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);
    public static final BooleanProperty FRUIT = BooleanProperty.create("fruit");

    public SporianSpikyLongusTop(BlockBehaviour.Properties properties) {
        super(properties, Direction.UP, SHAPE, false, 0.1D);
        this.registerDefaultState(this.stateDefinition.any().setValue(FRUIT, false));
    }

    @Override
    public boolean isLadder(BlockState state, LevelReader world, BlockPos pos, LivingEntity entity) { return true; }

    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        EntityType Entity = entityIn.getType();

        if (Entity == EntityType.PLAYER) {
            entityIn.hurt(DamageSource.CACTUS, 1F);

        }
        super.entityInside(state, worldIn, pos, entityIn);
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) { super.createBlockStateDefinition(builder.add(FRUIT)); }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        boolean a = state.getValue(FRUIT);
        if (a == false && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(5) == 0)) {
            worldIn.setBlock(pos, state.setValue(FRUIT, true), 2);
        }
        super.tick(state, worldIn, pos, rand);
    }
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (state.getValue(FRUIT) == true) {
            worldIn.setBlock(pos, state.setValue(FRUIT, false), 2);
            popResource(worldIn, pos, new ItemStack(ItemInitNew.SPORIAN_SPIKY_LONGUS_FRUIT.get(), 1));

        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }
    @Override
    protected int getBlocksToGrowWhenBonemealed(Random rand) { return 0; }

    @Override
    protected boolean canGrowInto(BlockState state) { return NetherVines.isValidGrowthState(state); }

    @Override
    protected Block getBodyBlock() { return BlockInitNew.SPORIAN_SPIKY_LONGUS_PLANT.get(); }



    @Override
    public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state) {

        worldIn.setBlock(pos, state.setValue(FRUIT, true), 2);
    }
}
