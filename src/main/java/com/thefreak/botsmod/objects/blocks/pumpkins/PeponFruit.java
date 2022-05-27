package com.thefreak.botsmod.objects.blocks.pumpkins;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PeponFruit extends FallingBlock {

    public PeponFruit(Properties properties) {
        super(properties);

    }


    @Override
    public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return true;
    }

}
