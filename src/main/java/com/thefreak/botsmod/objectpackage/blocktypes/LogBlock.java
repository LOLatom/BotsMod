package com.thefreak.botsmod.objectpackage.blocktypes;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.MaterialColor;

import java.util.Random;


public class LogBlock extends RotatedPillarBlock {
    private boolean hasFlowers;
    public LogBlock(Properties p_55926_) {
        super(p_55926_);
        properties.randomTicks();
        properties.color(MaterialColor.COLOR_BROWN);
        properties.sound(SoundType.WOOD);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        super.tick(pState, pLevel, pPos, pRandom);

    }



    public void tickGrowFlower() {

    }

    public boolean hasFlowerBlock() {
        return this.hasFlowers;
    }



}
