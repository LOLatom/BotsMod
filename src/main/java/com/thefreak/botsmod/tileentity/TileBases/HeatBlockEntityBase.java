package com.thefreak.botsmod.tileentity.TileBases;

import com.thefreak.botsmod.API.TileEntity.ITileEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public abstract class HeatBlockEntityBase extends BlockEntity implements ITileEntityBase {


    private Double heat = 22.5D;

    private int tickpassed = 0;


    public HeatBlockEntityBase(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);
    }

    public void setTickpassed(int tickpassed) {
        this.tickpassed = tickpassed;
    }

    public int getTickpassed() {
        return tickpassed;
    }

    public Double getHeatV() {
        return heat;

    }

    public void setHeatV(Double heat) {
        this.heat = heat;
        setChanged();
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        heat = pTag.getDouble("Heat");
        tickpassed = pTag.getInt("TickPassed");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putDouble("Heat", heat);
        pTag.putInt("TickPassed", tickpassed);
        super.saveAdditional(pTag);
    }
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket(){
        CompoundTag nbtTag = new CompoundTag();
        saveAdditional(nbtTag);
        return ClientboundBlockEntityDataPacket.create(this);
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
