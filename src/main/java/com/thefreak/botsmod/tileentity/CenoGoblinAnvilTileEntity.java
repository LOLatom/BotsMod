package com.thefreak.botsmod.tileentity;

import com.thefreak.botsmod.API.TileEntity.ITileEntityBase;
import com.thefreak.botsmod.init.ModTileEntityTypes;
import com.thefreak.botsmod.objects.items.ItemType.HammerItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class CenoGoblinAnvilTileEntity extends TileEntity implements ITileEntityBase, ITickableTileEntity {

    @Nonnull
    private ItemStack stack = Items.AIR.getDefaultInstance().getStack();

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
    public void load(BlockState p_230337_1_, CompoundNBT nbt) {
        super.load(p_230337_1_, nbt);
        inventory.deserializeNBT(nbt.getCompound("inv"));
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        nbt.put("inv", inventory.serializeNBT());
        return super.save(nbt);
    }

    public CenoGoblinAnvilTileEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public CenoGoblinAnvilTileEntity() {
        this(ModTileEntityTypes.CENO_GOBLIN_ANVIL_TILE_ENTITY.get());
    }


    @Override
    public void Activated(BlockState state, World world, PlayerEntity playerEntity) {
        if (!playerEntity.isCrouching()) {
            if (this.inventory.getStackInSlot(0) == ItemStack.EMPTY && !(playerEntity.getItemInHand(Hand.MAIN_HAND).isEmpty())) {
                ItemStack stackcollected1 = playerEntity.getItemInHand(Hand.MAIN_HAND).copy();
                playerEntity.getItemInHand(Hand.MAIN_HAND).shrink(1);
                this.inventory.setStackInSlot(0, stackcollected1);
            } else if (this.inventory.getStackInSlot(0) != ItemStack.EMPTY && this.inventory.getStackInSlot(1) == ItemStack.EMPTY && !(playerEntity.getItemInHand(Hand.MAIN_HAND).isEmpty())) {
                ItemStack stackcollected2 = playerEntity.getItemInHand(Hand.MAIN_HAND).copy();
                playerEntity.getItemInHand(Hand.MAIN_HAND).shrink(1);
                this.inventory.setStackInSlot(1, stackcollected2);
            }
        } else {
            if (playerEntity.getItemInHand(Hand.MAIN_HAND).getItem() instanceof HammerItem) {
                if (this.inventory.getStackInSlot(1) != ItemStack.EMPTY && this.inventory.getStackInSlot(0) != ItemStack.EMPTY) {
                    doCraftResult(state, world, playerEntity, this.inventory.getStackInSlot(0), this.inventory.getStackInSlot(1));
                }
            } else {
                if (this.inventory.getStackInSlot(1) != ItemStack.EMPTY && (playerEntity.getItemInHand(Hand.MAIN_HAND).isEmpty())) {
                    ItemStack stackcollected2 = this.inventory.getStackInSlot(1).copy();
                    this.inventory.setStackInSlot(1, ItemStack.EMPTY);
                    playerEntity.setItemInHand(Hand.MAIN_HAND, stackcollected2);
                } else if (this.inventory.getStackInSlot(0) != ItemStack.EMPTY && (playerEntity.getItemInHand(Hand.MAIN_HAND).isEmpty())) {
                    ItemStack stackcollected1 = this.inventory.getStackInSlot(0).copy();
                    this.inventory.setStackInSlot(0, ItemStack.EMPTY);
                    playerEntity.setItemInHand(Hand.MAIN_HAND, stackcollected1);

                }
            }
        }



    }

    //This Method is called when hammering the anvil with two items inside it
    public void doCraftResult(BlockState state, World world, PlayerEntity playerEntity, ItemStack firstStack, ItemStack secondStack) {
        }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket(){
        CompoundNBT nbtTag = new CompoundNBT();
        save(nbtTag);
        return new SUpdateTileEntityPacket(getBlockPos(), -1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt){
        BlockState state = level.getBlockState(getBlockPos());
        load(state, pkt.getTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbtTag = new CompoundNBT();
        save(nbtTag);
        return nbtTag;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        this.load(state, tag);
    }

    @Override
    public void Ticking(BlockState state, ServerWorld serverWorld, BlockPos pos, Random random) {

    }

    @Override
    public void tick() {

    }
}
