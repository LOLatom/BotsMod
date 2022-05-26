package com.thefreak.botsmod.entities;

import com.thefreak.botsmod.init.ItemInitNew;
import com.thefreak.botsmod.init.ModEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fml.hooks.BasicEventHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PinkPurifiedSaltItemEntity extends ItemEntity {

    protected int pickupDelay = 0;
    protected UUID thrower;
    protected UUID owner;
    public int lifespan = 6000;
    private int age;

    public PinkPurifiedSaltItemEntity(EntityType<? extends ItemEntity> p_i50217_1_, World world) {
        super(p_i50217_1_, world);
    }

    public PinkPurifiedSaltItemEntity(World world, double posX, double posY, double posZ, ItemStack itemstack) {
        super(world, posX, posY, posZ, itemstack);
        this.setItem(itemstack);
        this.yRot = this.random.nextFloat() * 360.0F;
        this.setDeltaMovement(this.random.nextDouble() * 0.2D - 0.1D, 0.2D, this.random.nextDouble() * 0.2D - 0.1D);
        this.lifespan = (itemstack.getItem() == null ? 6000 : itemstack.getEntityLifespan(world));
        this.pickupDelay = 80;
    }




    public void playerTouch(PlayerEntity p_70100_1_) {
        if (!this.level.isClientSide) {
            if (this.pickupDelay > 0) return;
            ItemStack itemstack = this.getItem();
            Item item = itemstack.getItem();
            int i = itemstack.getCount();

            int hook = net.minecraftforge.event.ForgeEventFactory.onItemPickup(this, p_70100_1_);
            if (hook < 0) return;

            ItemStack copy = itemstack.copy();
            if (this.pickupDelay == 0 && (this.owner == null || lifespan - this.age <= 200 || this.owner.equals(p_70100_1_.getUUID())) && (hook == 1 || i <= 0 || p_70100_1_.inventory.add(itemstack))) {
                copy.setCount(copy.getCount() - getItem().getCount());
                net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerItemPickupEvent(p_70100_1_, this, copy);
                p_70100_1_.take(this, i);
                if (itemstack.isEmpty()) {
                    this.remove();
                    itemstack.setCount(i);
                }

                p_70100_1_.awardStat(Stats.ITEM_PICKED_UP.get(item), i);
                p_70100_1_.onItemPickup(this);
            }

        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.pickupDelay > 0 && this.pickupDelay != 32767) {
            --this.pickupDelay;
        }
        if (this.age != -32768) {
            ++this.age;
        }
        List<ArrowEntity> inAABB = PinkPurifiedSaltItemEntity.this.level.getEntitiesOfClass(ArrowEntity.class, PinkPurifiedSaltItemEntity.this.getBoundingBox().inflate(0.2D, 0.2D, 0.2D));
        for (ArrowEntity arrowEntity : inAABB) {
            if (arrowEntity == null) {

            }else {

                this.spawnAtLocation(ItemInitNew.SALTED_ARROW.get());
                arrowEntity.remove();
                this.remove();
            }

        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        Entity entity = source.getDirectEntity();
        if (entity != null) {

            if (source.getDirectEntity() instanceof ArrowEntity) {
                this.remove();
            }

        }

        return false;
    }




}




