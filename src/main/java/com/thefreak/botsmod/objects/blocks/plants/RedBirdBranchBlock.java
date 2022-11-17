package com.thefreak.botsmod.objects.blocks.plants;

import com.thefreak.botsmod.init.iteminit.FoodItemInit;
import com.thefreak.botsmod.objectpackage.blocktypes.GrowingBranchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class RedBirdBranchBlock extends GrowingBranchBlock {
    public static final IntegerProperty GROWTH = IntegerProperty.create("grow", 0, 2);
    public static final BooleanProperty FERTILE = BooleanProperty.create("fertile");
    public RedBirdBranchBlock(Properties p_52591_, float x1, float y1, float z1, float x2, float y2, float z2) {
        super(p_52591_, x1, y1, z1, x2, y2, z2);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.DOWN).setValue(GROWTH,0).setValue(FERTILE, false));
    }
    @Override
    protected void grow(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        super.grow(pState, pLevel, pPos, pRandom);
        if (pState.getValue(GROWTH) < 2 && pRandom.nextInt(10) == 2 && pState.getValue(FERTILE) == false) {
            pLevel.setBlockAndUpdate(pPos,pState.setValue(GROWTH,pState.getValue(GROWTH) + 1));
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack inHand = pPlayer.getItemInHand(pHand);
        if (inHand.getItem() == Items.COAL && pState.getValue(FERTILE) == false) {
            inHand.setCount(inHand.getCount() - 1);
            pLevel.setBlockAndUpdate(pPos,pState.setValue(FERTILE,true));
            return InteractionResult.SUCCESS;
        }else if (inHand == ItemStack.EMPTY && pState.getValue(GROWTH) == 2) {
            pLevel.setBlockAndUpdate(pPos,pState.setValue(GROWTH,1));
            pPlayer.setItemInHand(pHand,FoodItemInit.RED_BIRD_BERRIES.get().getDefaultInstance());
            return InteractionResult.SUCCESS;
        }else if (inHand.getItem() == FoodItemInit.RED_BIRD_BERRIES.get() && pState.getValue(GROWTH) == 2) {
            pLevel.setBlockAndUpdate(pPos,pState.setValue(GROWTH,1));
            pPlayer.getItemInHand(pHand).setCount(pPlayer.getItemInHand(pHand).getCount() + 1);
            return InteractionResult.SUCCESS;
        }else if (inHand.getItem() instanceof ShearsItem && pState.getValue(GROWTH) == 1) {
            pLevel.setBlockAndUpdate(pPos,pState.setValue(GROWTH,0));
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(GROWTH).add(FERTILE));
    }
}
