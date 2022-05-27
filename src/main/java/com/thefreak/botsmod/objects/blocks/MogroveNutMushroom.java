package com.thefreak.botsmod.objects.blocks;

import com.thefreak.botsmod.init.ItemInitNew;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


import java.util.Random;


public class MogroveNutMushroom extends BushBlock implements BonemealableBlock {
    public static IntegerProperty NUT = IntegerProperty.create("nut",0,1);
    public static VoxelShape FIRST_SHAPE = Block.box(2,0,2,14,15,14);
    public static VoxelShape SECOND_SHAPE = Block.box(2,0,2,14,19,14);
    public MogroveNutMushroom(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(NUT, Integer.valueOf(0)));
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        int i = state.getValue(NUT);
        boolean t = i == 1;
        if (!t && player.getItemInHand(handIn).getItem() == Items.BONE_MEAL) {
            return InteractionResult.PASS;
        } else if (i == 1) {
            popResource(worldIn, pos, new ItemStack(ItemInitNew.MOGROVE_NUT.get(), 1));
            worldIn.setBlock(pos, state.setValue(NUT, Integer.valueOf(0)), 2);
        }
            return super.use(state, worldIn, pos, player, handIn, hit);


    }

    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        int i = state.getValue(NUT);
        if (i < 1 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(5) == 0)) {
            worldIn.setBlock(pos, state.setValue(NUT, Integer.valueOf(i + 1)), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }

    }
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        switch(state.getValue(NUT)) {
            case 0:
            default:
                return FIRST_SHAPE;
            case 1:
                return SECOND_SHAPE;

        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NUT);
    }
    @Override
    public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return state.getValue(NUT) < 1;
    }

    @Override
    public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state) {
        int i = Math.min(1, state.getValue(NUT) + 1);
        worldIn.setBlock(pos, state.setValue(NUT, Integer.valueOf(i)), 2);
    }
}
