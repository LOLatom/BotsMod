package com.thefreak.botsmod.entities;

import com.thefreak.botsmod.init.ItemInitNew;
import com.thefreak.botsmod.init.ModEntityTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.FluidAttributes;

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

    public PinkPurifiedSaltItemEntity(EntityType<? extends ItemEntity> p_i50217_1_, Level world) {
        super(p_i50217_1_, world);
    }

    public PinkPurifiedSaltItemEntity(Level world, double posX, double posY, double posZ, ItemStack itemstack) {
        super(world, posX, posY, posZ, itemstack);
        this.setItem(itemstack);
        this.yRotO = this.random.nextFloat() * 360.0F;
        this.setDeltaMovement(this.random.nextDouble() * 0.2D - 0.1D, 0.2D, this.random.nextDouble() * 0.2D - 0.1D);
        this.lifespan = (itemstack.getItem() == null ? 6000 : itemstack.getEntityLifespan(world));
        this.pickupDelay = 80;
    }




    public void playerTouch(Player p_70100_1_) {
        if (!this.level.isClientSide) {
            if (this.pickupDelay > 0) return;
            ItemStack itemstack = this.getItem();
            Item item = itemstack.getItem();
            int i = itemstack.getCount();

            int hook = net.minecraftforge.event.ForgeEventFactory.onItemPickup(this, p_70100_1_);
            if (hook < 0) return;

            ItemStack copy = itemstack.copy();
            /*if (this.pickupDelay == 0 && (this.owner == null || lifespan - this.age <= 200 || this.owner.equals(p_70100_1_.getUUID())) && (hook == 1 || i <= 0 || p_70100_1_.inventory.add(itemstack))) {
                copy.setCount(copy.getCount() - getItem().getCount());
                // TODO: ?
                //net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerItemPickupEvent(p_70100_1_, this, copy);
                p_70100_1_.take(this, i);
                if (itemstack.isEmpty()) {
                    this.remove(RemovalReason.DISCARDED); // TODO: ?
                    itemstack.setCount(i);
                }

                p_70100_1_.awardStat(Stats.ITEM_PICKED_UP.get(item), i);
                p_70100_1_.onItemPickup(this);
            }
*/
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
        List<Arrow> inAABB = PinkPurifiedSaltItemEntity.this.level.getEntitiesOfClass(Arrow.class, PinkPurifiedSaltItemEntity.this.getBoundingBox().inflate(0.2D, 0.2D, 0.2D));
        for (Arrow arrowEntity : inAABB) {
            if (arrowEntity == null) {

            }else {

                this.spawnAtLocation(ItemInitNew.SALTED_ARROW.get());
                arrowEntity.remove(RemovalReason.CHANGED_DIMENSION);
                this.remove(RemovalReason.CHANGED_DIMENSION);
            }

        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        Entity entity = source.getDirectEntity();
        if (entity != null) {

            if (source.getDirectEntity() instanceof Arrow) {
                this.remove(RemovalReason.CHANGED_DIMENSION);
            }

        }

        return false;
    }




}




