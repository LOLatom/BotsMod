package com.thefreak.botsmod.API.TileEntity;



import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public interface ITileEntityBase {

    void Activated (BlockState state, Level level, Player playerEntity, BlockPos pos, InteractionHand hand);

    void Ticking (BlockState state, ServerLevel serverLevel, BlockPos pos, Random random);

}
