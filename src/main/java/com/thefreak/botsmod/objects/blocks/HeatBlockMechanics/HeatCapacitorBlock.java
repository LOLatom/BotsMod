package com.thefreak.botsmod.objects.blocks.HeatBlockMechanics;

import com.thefreak.botsmod.API.TileEntity.ITileEntityBase;
import com.thefreak.botsmod.init.ModTileEntityTypes;
import com.thefreak.botsmod.tileentity.HeatAccumulatorTileEntity;
import com.thefreak.botsmod.tileentity.HeatCapacitorTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;


public class HeatCapacitorBlock extends BaseEntityBlock{
    public HeatCapacitorBlock(Properties p_49795_) {
        super(p_49795_);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
       return ModTileEntityTypes.HEAT_CAPACITOR_TILE_ENTITY.get().create(pPos, pState);
    }


    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player playerEntity, InteractionHand hand, BlockHitResult blockRayTraceResult) {
        ((ITileEntityBase) world.getBlockEntity(pos)).Activated(state, world, playerEntity, pos, hand);
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pBlockEntityType == ModTileEntityTypes.HEAT_CAPACITOR_TILE_ENTITY.get() ? HeatCapacitorTileEntity::tickHeat : null;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> GameEventListener getListener(Level pLevel, T pBlockEntity) {
        return super.getListener(pLevel, pBlockEntity);
    }

}
