package com.thefreak.botsmod.entities.demons;

import com.mojang.math.Vector3d;
import com.thefreak.botsmod.entities.WanderingSpecterEntity;
import com.thefreak.botsmod.entities.util.BotsMonster;
import com.thefreak.botsmod.init.sounds.SoundRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Vector;

public class SadFace extends BotsMonster {

    private static final EntityDataAccessor<Boolean> DATA_HIDDEN = SynchedEntityData.defineId(SadFace.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> DATA_MODE = SynchedEntityData.defineId(SadFace.class, EntityDataSerializers.INT);

    private ArrayList<UUID> playerWhoAngeredHistory;

    private int angerTimer;

    public SadFace(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
        this.playerWhoAngeredHistory = new ArrayList<>();
        this.angerTimer = 0;

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 500D)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.2F)
                .add(Attributes.ATTACK_DAMAGE, 0.1D)
                .add(Attributes.FOLLOW_RANGE, 5D)
                .add(Attributes.ARMOR, 0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new TeleportAwayFromPlayerGoal(this));
        this.goalSelector.addGoal(1, new DetermineNextMoveGoal(this));
        this.goalSelector.addGoal(1, new GetCloserWhenTooFarAwayGoal(this));
        super.registerGoals();
    }

    @Override
    public void onPlayerScreenTick(Player player) {
        System.out.println("IS HERE");
        super.onPlayerScreenTick(player);
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_HIDDEN, false);
        this.entityData.define(DATA_MODE, 0);
    }

    @Override
    protected void doPush(Entity pEntity) {
    }

    boolean isLookingAtMe(Player pPlayer) {
            Vec3 vec3 = pPlayer.getViewVector(1.0F).normalize();
            Vec3 vec31 = new Vec3(this.getX() - pPlayer.getX(), this.getEyeY() - pPlayer.getEyeY(), this.getZ() - pPlayer.getZ());
            double d0 = vec31.length();
            vec31 = vec31.normalize();
            double d1 = vec3.dot(vec31);
            return d1 > 1.0D - 0.025D / d0 ? pPlayer.hasLineOfSight(this) : false;
    }

    @Override
    public void tick() {
            if (this.getTarget() == null && this.level.getNearestPlayer(this, 30D) != null) {
                this.setTarget(this.level.getNearestPlayer(this, 30D));
            }
            if (this.getTarget() != null) {
                if (this.getMode() == 0 && this.isLookingAtMe((Player) this.getTarget()) && this.getTicksRecorded() == 0) {
                    this.setMode(1);
                    this.startRecordingTicks();
                    this.level.playLocalSound(this.getX(),this.getY(),this.getZ(),SoundRegistry.HATE_WISHPERS_SOUND.get(),SoundSource.PLAYERS,1,1,true);
                    if (!this.playerWhoAngeredHistory.contains(((Player) this.getTarget()).getUUID())) {
                        this.playerWhoAngeredHistory.add(((Player) this.getTarget()).getUUID());
                    }
                }
            }
        super.tick();
    }

    @Override
    public void recordedTick(int ticks) {
        super.recordedTick(ticks);
        if (this.getTarget() != null) {
            ClientboundStopSoundPacket clientboundstopsoundpacket = new ClientboundStopSoundPacket((ResourceLocation) null,SoundSource.PLAYERS);

            if (this.getMode() == 1 && !this.isLookingAtMe((Player) this.getTarget()) && ticks >= 160) {
                this.setMode(0);
                this.stopRecordingTicks();
                if (!this.level.isClientSide()) {
                    ((ServerPlayer) this.getTarget()).connection.send(clientboundstopsoundpacket);
                }

            }else if (this.getMode() == 1 && this.isLookingAtMe((Player) this.getTarget())) {
                this.setRecordingTicks(0);

            }
            if (this.tickCount % 360 == 0) {
                this.level.playLocalSound(this.getX(),this.getY(),this.getZ(),SoundRegistry.HATE_WISHPERS_SOUND.get(),SoundSource.PLAYERS,1,1,true);
            }
        }
    }

    public boolean getHidden() {
        return this.entityData.get(DATA_HIDDEN);
    }

    public int getMode() {
        return this.entityData.get(DATA_MODE);
    }

    @Override
    public void playerRendering() {
        System.out.println("SHOWN");
        super.playerRendering();
    }

    public void setMode(int mode) {
        this.entityData.set(DATA_MODE, mode);
    }

    public void setHidden(boolean hidden) {
        this.entityData.set(DATA_HIDDEN, hidden);
    }

    @Override
    public boolean isPersistenceRequired() {
        return true;
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        boolean isCommand = pSource == DamageSource.OUT_OF_WORLD;
        System.out.println(isCommand);
        if (isCommand) {
            this.remove(RemovalReason.KILLED);
        };
        return false;
    }

    public static class DetermineNextMoveGoal extends Goal {
        private SadFace entity;

        public DetermineNextMoveGoal(SadFace entity) {
            this.entity = entity;
        }
        @Override
        public boolean canUse() {
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return false;
        }

        @Override
        public void start() {
            super.start();
            //this.entity.setActionFlags(this.entity.random.nextInt(3) + 1);
        }

    }

    public static class TeleportAwayFromPlayerGoal extends Goal {

        private SadFace entity;

        public TeleportAwayFromPlayerGoal(SadFace sadFace) {
            this.entity = sadFace;
        }

        @Override
        public boolean canUse() {
            if (this.entity.getTarget() == null) {
                return false;
            } else if (((Player) this.entity.getTarget()).isCreative()) {
                return false;
            }
            boolean randUsage = this.entity.random.nextInt(4) == 1;
            boolean can = this.entity.distanceTo(this.entity.getTarget()) <= 20;
            return randUsage && this.entity.getMode() == 0 && can;
        }

        @Override
        public void start() {
            super.start();
            double distance = 45 + this.entity.random.nextDouble() * 10;
            double angle = this.entity.random.nextDouble() * (2 * Math.PI);

            double x = this.entity.getTarget().getX() + distance * Math.cos(angle);
            double z = this.entity.getTarget().getZ() + distance * Math.sin(angle);

            BlockPos teleportPosition = new BlockPos(x, this.entity.level.getHeight(Heightmap.Types.WORLD_SURFACE, (int) x, (int) z), z);
            this.entity.setPos(teleportPosition.getX(), teleportPosition.getY(),teleportPosition.getZ());


        }

        @Override
        public void stop() {
            super.stop();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse();
        }
    }

    public static class GetCloserWhenTooFarAwayGoal extends Goal {
        private SadFace entity;
        public GetCloserWhenTooFarAwayGoal(SadFace entity) {
            this.entity = entity;
        }
        @Override
        public boolean canUse() {
            if (this.entity.getTarget() == null) {
                return false;
            }
            boolean a = this.entity.distanceTo(this.entity.getTarget()) >= 100 && (this.entity.distanceTo(this.entity.getTarget()) <= 135);
            return a;
        }

        @Override
        public void start() {
            super.start();
            double distance = 45 + this.entity.random.nextDouble() * 10;
            double angle = this.entity.random.nextDouble() * (2 * Math.PI);

            double x = this.entity.getTarget().getX() + distance * Math.cos(angle);
            double z = this.entity.getTarget().getZ() + distance * Math.sin(angle);

            BlockPos teleportPosition = new BlockPos(x, this.entity.level.getHeight(Heightmap.Types.WORLD_SURFACE, (int) x, (int) z), z);
            this.entity.setPos(teleportPosition.getX(), teleportPosition.getY(),teleportPosition.getZ());
        }


    }
}
