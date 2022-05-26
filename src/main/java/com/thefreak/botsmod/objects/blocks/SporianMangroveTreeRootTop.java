package com.thefreak.botsmod.objects.blocks;

import com.thefreak.botsmod.init.BlockInitNew;
import com.thefreak.botsmod.init.ItemInitNew;
import net.minecraft.block.*;
import net.minecraft.core.Direction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;


public class SporianMangroveTreeRootTop extends AbstractTopPlantBlock {
    int a;
    public static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);
    public SporianMangroveTreeRootTop(Properties properties) {
        super(properties, Direction.UP, SHAPE, false, 0.001D);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(Random rand) { return 0; }

    @Override
    protected boolean canGrowInto(BlockState state) { return PlantBlockHelper.isValidGrowthState(state); }

    @Override
    protected Block getBodyBlock() { return BlockInitNew.SPORIAN_MANGROVE_TREE_ROOT.get(); }

}
