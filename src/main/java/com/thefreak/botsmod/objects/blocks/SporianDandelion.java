package com.thefreak.botsmod.objects.blocks;

import com.thefreak.botsmod.init.BlockInitNew;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


import java.util.Random;


public class SporianDandelion extends BushBlock {
    public static final BooleanProperty POLLEN = BooleanProperty.create("pollen");
    public static VoxelShape SHAPE = Block.box(1,0,1,15,12,15);
    public SporianDandelion(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(POLLEN, true));
    }

    Random rand = new Random();

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(POLLEN));
    }

    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        boolean a = state.getValue(POLLEN) == false;
        if (!a) {
            double x = pos.getX() + 0.5D;
            double y = pos.getY() + 0.5D;
            double z = pos.getZ() + 0.5D;
            worldIn.addParticle(ParticleTypes.CLOUD, x, y, z, 0D, 0.3D, 0);
            worldIn.setBlock(pos, state.setValue(POLLEN, false), 2);
            worldIn.playSound((Player) null, pos, SoundEvents.WOOL_FALL, SoundSource.BLOCKS, 1F, 0.25F);

            if (rand.nextInt(10) == 0) {
                worldIn.destroyBlock(pos,false);
            }
        }
        super.entityInside(state, worldIn, pos, entityIn);
    }

    @Override
    public void onProjectileHit(Level worldIn, BlockState state, BlockHitResult hit, Projectile projectile) {
        boolean a = state.getValue(POLLEN) == false;
        if (!a) {
            double x = projectile.getX() + 0.5D;
            double y = projectile.getY() + 0.5D;
            double z = projectile.getZ() + 0.5D;
            worldIn.addParticle(ParticleTypes.CLOUD, x, y, z, 0D, 0.3D, 0);
            worldIn.setBlock(projectile.blockPosition(), state.setValue(POLLEN, false), 2);
            worldIn.playSound((Player) null, projectile.blockPosition(), SoundEvents.WOOL_FALL, SoundSource.BLOCKS, 1F, 0.25F);
            if (rand.nextInt(10) == 0) {
            worldIn.destroyBlock(projectile.blockPosition(),false);
            }

        }
        super.onProjectileHit(worldIn, state, hit, projectile);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.PODZOL) || state.is(Blocks.FARMLAND) || state.is(BlockInitNew.SPORIAN_MOSS_GRASS.get());
    }
}
