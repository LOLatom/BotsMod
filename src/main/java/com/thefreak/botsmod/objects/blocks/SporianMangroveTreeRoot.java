package com.thefreak.botsmod.objects.blocks;

import com.thefreak.botsmod.init.BlockInitNew;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SporianMangroveTreeRoot extends GrowingPlantBodyBlock {
    public static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);

    public SporianMangroveTreeRoot(Properties properties) {
        super(properties, Direction.UP, SHAPE, false);
    }
    public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(BlockInitNew.SPORIAN_MANGROVE_TREE_ROOT_TOP.get());
    }



    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) BlockInitNew.SPORIAN_MANGROVE_TREE_ROOT_TOP.get();
    }
}
