package com.thefreak.botsmod.entities.misc;

import com.thefreak.botsmod.entities.WickedOne;
import com.thefreak.botsmod.particles.ModParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.minecraftforge.common.util.LazyOptional;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;
import java.util.Optional;

public class LightSwordConstruct extends Projectile {

    private int ticksSpent;
    private static final EntityDataAccessor<Integer> TICKS_BEFORE_FALLING = SynchedEntityData.defineId(WickedOne.class, EntityDataSerializers.INT);
    private Optional<LivingEntity> target;
    public LightSwordConstruct(EntityType<LightSwordConstruct> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setTicksBeforeFalling(0);
        this.target = Optional.empty();
        this.ticksSpent = 0;
    }

    public LightSwordConstruct(EntityType<LightSwordConstruct> pEntityType, Level pLevel, int ticksBeforeFalling, LivingEntity target) {
        super(pEntityType, pLevel);
        setTicksBeforeFalling(ticksBeforeFalling);
        this.target = Optional.of(target);
        this.ticksSpent = 0;
    }

    @Override
    protected void defineSynchedData() {
        entityData.define(TICKS_BEFORE_FALLING, 0);
    }

    @Override
    public void tick() {
        super.tick();
        this.move(MoverType.SELF,this.getDeltaMovement());
        if (this.ticksSpent == getTicksBeforeFalling()) {
            this.level.playLocalSound(this.getX(),this.getY(),this.getZ(), SoundEvents.EVOKER_CAST_SPELL, SoundSource.AMBIENT, 2, 1.1F, true);
            shoot(0,-5,0,2,0);
        }
        if (this.ticksSpent > getTicksBeforeFalling() + 3 && this.getDeltaMovement().y == 0) {
            for(int i = 0; i < 360; i++) {
                if (i % 20 == 0) {
                    this.level.addParticle(ModParticleType.GOLDEN_SPECTER_PARTICLE.get(), this.getX() + 0.2d, this.getY() + 0.1, this.getZ() + 0.2d,
                            Math.cos(i) * 0.1d, 0.02d, Math.sin(i) * 0.1d);
                }
            }
            this.remove(RemovalReason.DISCARDED);
        }
        this.ticksSpent++;
        List<Player> entities = this.level.getEntitiesOfClass(Player.class, new AABB(this.blockPosition().offset(0,1,0)).inflate(0.2,0.2,0.2));
        if (!entities.isEmpty()) {
            entities.forEach((entity) -> {
                entity.hurt(DamageSource.MAGIC,5);
            });
            this.remove(RemovalReason.DISCARDED);
        }
        if (this.ticksSpent > getTicksBeforeFalling() && this.getDeltaMovement().y != 0) {
                    this.level.addParticle(ModParticleType.GOLDEN_SPECTER_PARTICLE.get(), this.getX(), this.getY() + 0.1, this.getZ(),
                            0, 0.02d, 0);
        }
    }



    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        this.level.playLocalSound(this.getX(),this.getY(),this.getZ(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.AMBIENT, 2, 1.1F, true);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    public LivingEntity getTarget() {
        if (target.isEmpty()) {
            return null;
        }
        return target.get();
    }

    public void setTicksBeforeFalling(int ticksBeforeFalling) {
        this.entityData.set(TICKS_BEFORE_FALLING, ticksBeforeFalling);
    }

    public int getTicksBeforeFalling() {
        return this.entityData.get(TICKS_BEFORE_FALLING);
    }
}
