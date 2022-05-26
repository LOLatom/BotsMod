package com.thefreak.botsmod.objects.blocks;

import com.thefreak.botsmod.init.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.level.block.entity.BlockEntityType;


public class PostMortalAltar extends Block {

    private static final VoxelShape POST_TEST = Block.box(15.0D, 0.0D, 15.0D, 1.0D, 16.0D, 1.0D);

    public PostMortalAltar(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return POST_TEST;
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public BlockEntityType createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.POST_MORTAL_ALTAR_TILE_ENTITY.get().create();
    }
}
