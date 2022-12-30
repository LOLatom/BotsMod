package com.thefreak.botsmod.tileentity;

import com.thefreak.botsmod.init.ModTileEntityTypes;
import com.thefreak.botsmod.tileentity.TileBases.HeatBlockEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class HeatCapacitorTileEntity extends HeatBlockEntityBase implements BlockEntityTicker {


    private float capacity;

    public HeatCapacitorTileEntity(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    public HeatCapacitorTileEntity(BlockPos pos, BlockState state) {
        this(ModTileEntityTypes.HEAT_CAPACITOR_TILE_ENTITY.get(), pos, state);
    }

    @Override
    public void Activated(BlockState state, Level level, Player playerEntity, BlockPos pos, InteractionHand hand) {


        if (hand == InteractionHand.MAIN_HAND) {
            if (playerEntity.isCrouching()) {
                if (getCapacity() > 0) {
                    setCapacity(getCapacity() - 0.5F);

                }
            } else if (!playerEntity.isCrouching()) {
                if (getCapacity() < 100) {
                    setCapacity(getCapacity() + 0.5F);
                } else {

                }
            }

            System.out.println(getCapacity());
            System.out.println(getCapacityHeatV());
        }

        }


    @Override
    public void Ticking(BlockState state, ServerLevel serverLevel, BlockPos pos, Random random) {

    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        setCapacity(pTag.getFloat("capacity"));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putFloat("capacity", getCapacity());
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



    public void setCapacity(float capacity) {
        this.capacity = capacity;
        setChanged();
    }

    public float getCapacity() {
        return capacity;
    }

    public double getCapacityHeatV() {
        return (getHeatV() * (getCapacity() / 100));
    }

    public static void tickHeat(Level pLevel, BlockPos pPos, BlockState pState, BlockEntity pBlockEntity) {
        if (pLevel.getBlockEntity(pPos.below()) instanceof HeatBlockEntityBase) {
            HeatBlockEntityBase heatBlockEntityBase = (HeatBlockEntityBase) pLevel.getBlockEntity(pPos.below());
            HeatCapacitorTileEntity heatCapacitorTileEntity = (HeatCapacitorTileEntity) pLevel.getBlockEntity(pPos);
            heatCapacitorTileEntity.setHeatV(heatBlockEntityBase.getHeatV());
            Random random = new Random();
            if (heatCapacitorTileEntity.getCapacity() >= 50 && random.nextInt(20) == 6) {

            }
        }
    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState, BlockEntity pBlockEntity) {

    }
}
