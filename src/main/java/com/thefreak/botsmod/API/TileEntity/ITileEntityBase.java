package com.thefreak.botsmod.API.TileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public interface ITileEntityBase {

    void Activated (BlockState state, World world, PlayerEntity playerEntity);

    void Ticking (BlockState state, ServerWorld serverWorld, BlockPos pos, Random random);

}
