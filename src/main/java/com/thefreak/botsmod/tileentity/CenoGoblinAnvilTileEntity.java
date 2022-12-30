package com.thefreak.botsmod.tileentity;

import com.thefreak.botsmod.API.TileEntity.ITileEntityBase;
import com.thefreak.botsmod.init.ModTileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.Random;

public class CenoGoblinAnvilTileEntity extends BlockEntity implements ITileEntityBase {

    @Nonnull
    private ItemStack stack = ItemStack.EMPTY;

    public ItemStackHandler inventory = new ItemStackHandler(2){
        @Override
        protected void onContentsChanged(int slot) {

        }

        @Override
        protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
            return 1;
        }
    };

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        inventory.deserializeNBT(pTag.getCompound("inv"));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inv", inventory.serializeNBT());
        super.saveAdditional(pTag);
    }

    public CenoGoblinAnvilTileEntity(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }
    public CenoGoblinAnvilTileEntity(BlockPos pos, BlockState state) {
        this(ModTileEntityTypes.CENO_GOBLIN_ANVIL_TILE_ENTITY.get(), pos, state);
    }

    @Override
    public void Activated(BlockState state, Level world, Player playerEntity, BlockPos pos, InteractionHand hand) {
        if (!playerEntity.isCrouching()) {
            if (this.inventory.getStackInSlot(0) == ItemStack.EMPTY && !(playerEntity.getItemInHand(InteractionHand.MAIN_HAND).isEmpty())) {
                ItemStack stackcollected1 = playerEntity.getItemInHand(InteractionHand.MAIN_HAND).copy();
                playerEntity.getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
                this.inventory.setStackInSlot(0, stackcollected1);
            } else if (this.inventory.getStackInSlot(0) != ItemStack.EMPTY && this.inventory.getStackInSlot(1) == ItemStack.EMPTY && !(playerEntity.getItemInHand(InteractionHand.MAIN_HAND).isEmpty())) {
                ItemStack stackcollected2 = playerEntity.getItemInHand(InteractionHand.MAIN_HAND).copy();
                playerEntity.getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
                this.inventory.setStackInSlot(1, stackcollected2);
            }
        } else {
            if (playerEntity.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof AirItem) {
                if (this.inventory.getStackInSlot(1) != ItemStack.EMPTY && this.inventory.getStackInSlot(0) != ItemStack.EMPTY) {
                    doCraftResult(state, world, playerEntity, this.inventory.getStackInSlot(0), this.inventory.getStackInSlot(1));
                }
            } else {
                if (this.inventory.getStackInSlot(1) != ItemStack.EMPTY && (playerEntity.getItemInHand(InteractionHand.MAIN_HAND).isEmpty())) {
                    ItemStack stackcollected2 = this.inventory.getStackInSlot(1).copy();
                    this.inventory.setStackInSlot(1, ItemStack.EMPTY);
                    playerEntity.setItemInHand(InteractionHand.MAIN_HAND, stackcollected2);
                } else if (this.inventory.getStackInSlot(0) != ItemStack.EMPTY && (playerEntity.getItemInHand(InteractionHand.MAIN_HAND).isEmpty())) {
                    ItemStack stackcollected1 = this.inventory.getStackInSlot(0).copy();
                    this.inventory.setStackInSlot(0, ItemStack.EMPTY);
                    playerEntity.setItemInHand(InteractionHand.MAIN_HAND, stackcollected1);

                }
            }
        }



    }

    @Override
    public void Ticking(BlockState state, ServerLevel serverLevel, BlockPos pos, Random random) {

    }

    //This Method is called when hammering the anvil with two items inside it
    public void doCraftResult(BlockState state, Level world, Player playerEntity, ItemStack firstStack, ItemStack secondStack) {
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket(){
        CompoundTag nbtTag = new CompoundTag();
        saveAdditional(nbtTag);
        return ClientboundBlockEntityDataPacket.create(this, (idk) -> nbtTag);
    }

    @Override
    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket pkt){
        CompoundTag tag = pkt.getTag();
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

    //    @Override
    //    public void Ticking(BlockState state, ServerWorld serverWorld, BlockPos pos, Random random) {
    //
    //    }
    //
    //    @Override
    //    public void tick() {
    //
    //    }
}
