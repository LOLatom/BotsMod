package com.thefreak.botsmod.tileentity;

import com.thefreak.botsmod.init.ModTileEntityTypes;
import com.thefreak.botsmod.objects.blocks.HeatBlockMechanics.HeatMaths;
import com.thefreak.botsmod.tileentity.TileBases.HeatBlockEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Random;

public class HeatAccumulatorTileEntity extends HeatBlockEntityBase implements BlockEntityTicker {


    private HeatMaths heatMaths = new HeatMaths();

    public HeatAccumulatorTileEntity(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    public HeatAccumulatorTileEntity(BlockPos pos, BlockState state) {
        this(ModTileEntityTypes.HEAT_ACCUMULATOR_TILE_ENTITY.get(), pos, state);
    }

    public HeatMaths getHeatMaths() {
        return heatMaths;
    }

    public void setHeatMaths(HeatMaths heatMaths) {
        this.heatMaths = heatMaths;
    }


    public Double HeatChange() {
        return getHeatV() - 32D;
    }




    @Override
    public void Activated(BlockState state, Level level, Player playerEntity, BlockPos pos, InteractionHand hand) {
        if (level.isClientSide()) {
            System.out.println(getHeatV());
        }
    }

    @Override
    public void Ticking(BlockState state, ServerLevel serverLevel, BlockPos pos, Random random) {

    }


    public static void tickHeat(Level pLevel, BlockPos pPos, BlockState pState, BlockEntity pBlockEntity) {
        boolean WaterAround = pLevel.getBlockState(pPos.east()).getBlock() == Blocks.WATER &&
                pLevel.getBlockState(pPos.west()).getBlock() == Blocks.WATER &&
                pLevel.getBlockState(pPos.north()).getBlock() == Blocks.WATER &&
                pLevel.getBlockState(pPos.south()).getBlock() == Blocks.WATER &&
                pLevel.getBlockState(pPos.north().east()).getBlock() == Blocks.WATER &&
                pLevel.getBlockState(pPos.north().west()).getBlock() == Blocks.WATER &&
                pLevel.getBlockState(pPos.south().east()).getBlock() == Blocks.WATER &&
                pLevel.getBlockState(pPos.south().west()).getBlock() == Blocks.WATER;

        HeatAccumulatorTileEntity heatAccumulatorTileEntity = (HeatAccumulatorTileEntity) pLevel.getBlockEntity(pPos);
        if (heatAccumulatorTileEntity.getTickpassed() < 100 && heatAccumulatorTileEntity.getHeatMaths().getHeat(pLevel,pPos.below(),20,false) != heatAccumulatorTileEntity.getHeatV()) {
            heatAccumulatorTileEntity.setTickpassed(heatAccumulatorTileEntity.getTickpassed() + 1);
        } else {
            heatAccumulatorTileEntity.setTickpassed(0);
            if (pLevel.getBlockEntity(pPos.below()) instanceof HeatCapacitorTileEntity) {
                HeatCapacitorTileEntity heatCapacitorTileEntity = (HeatCapacitorTileEntity) pLevel.getBlockEntity(pPos.below());
                heatAccumulatorTileEntity.setHeatV(heatCapacitorTileEntity.getCapacityHeatV() * (20/100));
            } else {
                heatAccumulatorTileEntity.setHeatV(heatAccumulatorTileEntity.getHeatMaths().getHeat(pLevel, pPos.below(), 20, false));
            }
            }
        Random random = new Random();
        BlockPos pos1 = pPos.east();
        BlockPos pos2 = pPos.west();
        BlockPos pos3 = pPos.south();
        BlockPos pos4 = pPos.north();

        if (heatAccumulatorTileEntity.getHeatV() - 15D > 60D && WaterAround && random.nextInt(30) == 16) {
            pLevel.addParticle(ParticleTypes.BUBBLE.getType(),pos1.getX(), pos1.getY(), pos1.getZ(),0,0.1D,0);
            pLevel.addParticle(ParticleTypes.BUBBLE.getType(),pos2.getX(), pos2.getY(), pos2.getZ(),0,0.1D,0);
            pLevel.addParticle(ParticleTypes.BUBBLE.getType(),pos3.getX(), pos3.getY(), pos3.getZ(),0,0.1D,0);
            pLevel.addParticle(ParticleTypes.BUBBLE.getType(),pos4.getX(), pos4.getY(), pos4.getZ(),0,0.1D,0);
        }

        if (heatAccumulatorTileEntity.getHeatV() - 15D > 60D && !WaterAround) {
            AABB heatbox = new AABB(pPos);
            List<LivingEntity> nearestLivingEntity = pLevel.getEntitiesOfClass(LivingEntity.class, heatbox.inflate(1D));
        for (LivingEntity livingEntity : nearestLivingEntity) {
            livingEntity.hurt(DamageSource.ON_FIRE, 3);
            if (heatAccumulatorTileEntity.getHeatV() - 15D >1200) {
                livingEntity.setRemainingFireTicks(2);
            }
        }
            if ((heatAccumulatorTileEntity.getHeatV() - 15) * 0.20D > 60D) {
                AABB heatbox2 = new AABB(pPos);
                List<LivingEntity> nearestLivingEntity2 = pLevel.getEntitiesOfClass(LivingEntity.class, heatbox2.inflate(2D));
                for (LivingEntity livingEntity2 : nearestLivingEntity2) {
                    livingEntity2.hurt(DamageSource.HOT_FLOOR, 1);
                }
            }
        }
    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState, BlockEntity pBlockEntity) {

    }
}
