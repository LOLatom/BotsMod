package com.thefreak.botsmod.entities.util;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.builder.AnimationBuilder;


import java.util.*;

public class BotsMonster extends PathfinderMob {
    private static final byte PLAYING_MUSIC_ID = 67;
    private static final byte STOPPING_MUSIC_ID = 68;

    private boolean recordingTicks;

    private int ticksRecorded;

    private ArrayList<UUID> playerWatchingIDs;

    private int ticksRecordedSinceFirstRendered;

    private static final EntityDataAccessor<Integer> DATA_ACTION_FLAGS = SynchedEntityData.defineId(BotsMonster.class, EntityDataSerializers.INT);

    protected HashMap<Integer,AnimationBuilder> animations = new HashMap<>();


    public BotsMonster(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
        this.xpReward = 10;
        this.recordingTicks = false;
        this.ticksRecorded = 0;
        this.playerWatchingIDs = new ArrayList<>();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ACTION_FLAGS, 0);
    }

    /**
     * A method that execute whenever the player tell a message in chat
     * @param msg the message that has been sent
     * @param player the player who sent the message
     */
    public void worded(String msg, Player player) {
    }

    @Override
    public void tick() {
        super.tick();
        if (isRecordingTicks()) {
            this.ticksRecorded += 1;
            recordedTick(getTicksRecorded());
        }

        if (!level.isClientSide && getAliveMusic() != null) {
            if (isAllowedToPlayMusic()) {
                this.level.broadcastEntityEvent(this, PLAYING_MUSIC_ID);
            }else {
                this.level.broadcastEntityEvent(this, STOPPING_MUSIC_ID);
            }
        }
    }

    public void onPlayerScreenTick(Player player) {};

    public void setActionFlags(int actionFlags) {
        this.entityData.set(DATA_ACTION_FLAGS, actionFlags);
    }

    public int getActionFlags() {
        return this.entityData.get(DATA_ACTION_FLAGS);
    }


    public void recordedTick(int ticks) {
    }

    public int getTicksRecorded() {
        return this.ticksRecorded;
    }

    public boolean isRecordingTicks() {
        return this.recordingTicks;
    }

    public void setRecordingTicks(int ticksRecorded) {
        this.ticksRecorded = ticksRecorded;
    }
    public void startRecordingTicks() {
        this.recordingTicks = true;
    }

    public void stopRecordingTicks() {
        this.recordingTicks = false;
        this.ticksRecorded = 0;
    }

    public void playerRendering() {
    }
    @Override
    public boolean doHurtTarget(Entity pEntity) {
        return doHurtTarget(pEntity, 1F,1F);
    }

    public boolean doHurtTarget(Entity pEntity, float damageMult, float knockbackMult) {
        return doHurtTarget(pEntity,damageMult,knockbackMult,false,false);
    }

    public boolean doHurtTarget(Entity pEntity, float damageMult, float knockbackMult, boolean passThroughShield, boolean ignoreArmor) {
        float f = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE) * damageMult;
        float f1 = (float)this.getAttributeValue(Attributes.ATTACK_KNOCKBACK) * knockbackMult;
        if (pEntity instanceof LivingEntity) {
            f += EnchantmentHelper.getDamageBonus(this.getMainHandItem(), ((LivingEntity)pEntity).getMobType());
            f1 += (float)EnchantmentHelper.getKnockbackBonus(this);
        }

        int i = EnchantmentHelper.getFireAspect(this);
        if (i > 0) {
            pEntity.setSecondsOnFire(i * 4);
        }

        boolean flag = pEntity.hurt(DamageSource.mobAttack(this), f);
        if (flag) {
            if (f1 > 0.0F && pEntity instanceof LivingEntity) {
                ((LivingEntity)pEntity).knockback((double)(f1 * 0.5F), (double) Mth.sin(this.getYRot() * ((float)Math.PI / 180F)), (double)(-Mth.cos(this.getYRot() * ((float)Math.PI / 180F))));
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.6D, 1.0D, 0.6D));
            }

            if (pEntity instanceof Player && !passThroughShield) {
                Player player = (Player)pEntity;
                this.maybeDisableShield(player, player.isUsingItem() ? player.getUseItem() : ItemStack.EMPTY);            }

            this.doEnchantDamageEffects(this, pEntity);
            this.setLastHurtMob(pEntity);
        }

        return flag;
    }

    /**
     * TODO:Remove shield from hand if Aetheral Slasher Enchantement
     * @param p_21425_
     * @param itemStack
     */
    private void maybeDisableShield(Player p_21425_, ItemStack itemStack) {
        if (!itemStack.isEmpty() && itemStack.is(Items.SHIELD)) {
            float f = 0.25F + (float)EnchantmentHelper.getBlockEfficiency(this) * 0.05F;
            if (this.random.nextFloat() < f) {
                p_21425_.getCooldowns().addCooldown(Items.SHIELD, 100);
                this.level.broadcastEntityEvent(p_21425_, (byte)30);
            }
        }

    }

    public SoundEvent getAliveMusic() {
        return null;
    }

    public boolean isAllowedToPlayMusic() {
        return false;
    }
}
