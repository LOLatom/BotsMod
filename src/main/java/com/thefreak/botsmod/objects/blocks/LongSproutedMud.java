package com.thefreak.botsmod.objects.blocks;

import com.thefreak.botsmod.init.BlockInitNew;
import com.thefreak.botsmod.init.ItemInitNew;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;


public class LongSproutedMud extends Block implements BonemealableBlock {
    public static final BooleanProperty TRUFFLEHERE = BooleanProperty.create("truffle");
    public LongSproutedMud(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(TRUFFLEHERE, false));
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        ItemStack ItemHanded = player.getItemInHand(handIn);
        boolean nothingdown = worldIn.getBlockState(pos.below()).getBlock() instanceof AirBlock;
        if (ItemHanded.getItem() instanceof ShearsItem && state.getValue(TRUFFLEHERE) == true && worldIn.getBlockState(pos.below()).getBlock() instanceof AirBlock) {
            worldIn.setBlockAndUpdate(pos, state.setValue(TRUFFLEHERE, false));
            worldIn.setBlockAndUpdate(pos.below(), BlockInitNew.TRUFFLE_BLOCK.get().defaultBlockState());
            worldIn.setBlockAndUpdate(pos, BlockInitNew.WET_MUD_BLOCK2.get().defaultBlockState());
        } else if (ItemHanded.getItem() instanceof ShearsItem && state.getValue(TRUFFLEHERE) == true && !nothingdown) {
            popResource(worldIn, pos, new ItemStack(ItemInitNew.TRUFFLE.get()));
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(10) == 0)) {
            worldIn.setBlockAndUpdate(pos, state.setValue(TRUFFLEHERE, true));
        }
        super.tick(state, worldIn, pos, rand);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TRUFFLEHERE);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state) {

    }
}
