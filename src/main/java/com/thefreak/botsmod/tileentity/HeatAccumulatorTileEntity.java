package com.thefreak.botsmod.tileentity;

import com.thefreak.botsmod.init.ModTileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class HeatAccumulatorTileEntity extends BlockEntity {
    public HeatAccumulatorTileEntity(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    public HeatAccumulatorTileEntity(BlockPos pos, BlockState state) {
        this(ModTileEntityTypes.HEAT_ACCUMULATOR_TILE_ENTITY.get(), pos, state);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
    }
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket(){
        CompoundTag nbtTag = new CompoundTag();
        saveAdditional(nbtTag);
        return ClientboundBlockEntityDataPacket.create(this, (UwU) -> nbtTag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        load(pkt.getTag());
        super.onDataPacket(net, pkt);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbtTag = new CompoundTag();
        saveAdditional(nbtTag);
        return nbtTag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.load(tag);
    }
}
