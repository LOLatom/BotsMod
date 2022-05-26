package com.thefreak.botsmod.objects.blocks;



import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AncientUrnCooked extends FallingBlock {
    public static VoxelShape BASE = Block.box(5,0,5,11,12,11);
    public AncientUrnCooked(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return BASE;
    }

    @Override
    public void onLand(Level level, BlockPos pos, BlockState fallingState, BlockState hitState, FallingBlockEntity fallingBlock) {
        super.onLand(level, pos, fallingState, hitState, fallingBlock);
        FallingBlockEntity Entity = fallingBlock;
            if (Entity.time > 10){


                level.destroyBlock(pos, false);
            }
    }



}
