package com.thefreak.botsmod.spells.spellclass.spelltype;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.spells.spellclass.AbstractSpell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class LeftClickFireSpell extends AbstractSpell {
    public LeftClickFireSpell(ItemStack usedItemStack, boolean f1state, boolean f2state, boolean f3state, boolean f4state, boolean blade, @Nullable UseOnContext context) {
        super(usedItemStack, f1state, f2state, f3state, f4state, blade, context);
    }
    @Override
    public boolean shouldExecute(Player player, Level level, InteractionHand hand) {
        return firstFinger&&secondFinger&&thirdFinger&&fourthFinger&&!blade;
    }

    @Override
    public boolean shouldContinueExecuting(Player player, Level level, InteractionHand hand,@Nullable UseOnContext context) {
        return false;
    }

    @Override
    public void Execute(Player player, Level level, InteractionHand hand, @Nullable UseOnContext context) {
        if (player.getItemInHand(InteractionHand.OFF_HAND).getItem() == Items.COAL) {
            double d1 = player.getX();
            double d2 = player.getY(0.5D);
            double d3 = player.getZ();
            double d4 = Math.sqrt(Math.sqrt(2)) * 0.5D;
            player.getItemInHand(InteractionHand.OFF_HAND).setCount(player.getItemInHand(InteractionHand.OFF_HAND).getCount() - 1);
            SmallFireball smallfireball = new SmallFireball(EntityType.SMALL_FIREBALL,level);
            smallfireball.setPos(player.getX(), player.getY(0.5D) + 0.5D, player.getZ());
            smallfireball.setXRot(player.xRotO);
            smallfireball.setYRot(player.yHeadRotO);
            smallfireball.shootFromRotation(smallfireball,player.xRotO,player.yHeadRotO, 0.0F, 1F, 0.0F);
            level.addFreshEntity(smallfireball);
        }
        if (player.getItemInHand(InteractionHand.OFF_HAND).getItem() == Items.TNT) {
            double d1 = player.getX();
            double d2 = player.getY(0.5D);
            double d3 = player.getZ();
            double d4 = Math.sqrt(Math.sqrt(2)) * 0.5D;
            player.getItemInHand(InteractionHand.OFF_HAND).setCount(player.getItemInHand(InteractionHand.OFF_HAND).getCount() - 1);
            PrimedTnt primedTnt = new PrimedTnt(EntityType.TNT,level);
            primedTnt.setPos(player.getX(), player.getY(0.5D) + 0.5D, player.getZ());
            primedTnt.setXRot(player.xRotO);
            primedTnt.setYRot(player.yHeadRotO);
            shootFromRotation(primedTnt,player.xRotO,player.yHeadRotO, 0.0F, 0.4F, 0.0F);
            level.addFreshEntity(primedTnt);
        }
        if (player.getItemInHand(InteractionHand.OFF_HAND).getItem() == Items.COAL_BLOCK) {
            double d1 = player.getX();
            double d2 = player.getY(0.5D);
            double d3 = player.getZ();
            double d4 = Math.sqrt(Math.sqrt(2)) * 0.5D;
            player.getItemInHand(InteractionHand.OFF_HAND).setCount(player.getItemInHand(InteractionHand.OFF_HAND).getCount() - 1);
            LargeFireball largeFireball = new LargeFireball(EntityType.FIREBALL,level);
            largeFireball.setPos(player.getX(), player.getY(0.5D) + 0.5D, player.getZ());
            largeFireball.setXRot(player.xRotO);
            largeFireball.setYRot(player.yHeadRotO);
            largeFireball.shootFromRotation(largeFireball,player.xRotO,player.yHeadRotO, 0.0F, 0.5F, 0.0F);
            level.addFreshEntity(largeFireball);
        }
        super.Execute(player, level, hand, context);
    }

    @Override
    public ResourceLocation getIconLocation() {
        return new ResourceLocation(BotsMod.MOD_ID,"textures/gui/spell/fire_spell.png");
    }
    public void shoot(Entity entity, double pX, double pY, double pZ, float pVelocity, float pInaccuracy, Random random) {
        Vec3 vec3 = (new Vec3(pX, pY, pZ)).normalize().add(random.nextGaussian() * (double)0.0075F * (double)pInaccuracy, random.nextGaussian() * (double)0.0075F * (double)pInaccuracy, random.nextGaussian() * (double)0.0075F * (double)pInaccuracy).scale((double)pVelocity);
        entity.setDeltaMovement(vec3);
        double d0 = vec3.horizontalDistance();
        entity.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
        entity.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
        entity.yRotO = entity.getYRot();
        entity.xRotO = entity.getXRot();
    }

    public void shootFromRotation(Entity entity, float pX, float pY, float pZ, float pVelocity, float pInaccuracy) {
        float f = -Mth.sin(pY * ((float)Math.PI / 180F)) * Mth.cos(pX * ((float)Math.PI / 180F));
        float f1 = -Mth.sin((pX + pZ) * ((float)Math.PI / 180F));
        float f2 = Mth.cos(pY * ((float)Math.PI / 180F)) * Mth.cos(pX * ((float)Math.PI / 180F));
        this.shoot(entity,(double)f, (double)f1, (double)f2, pVelocity, pInaccuracy, new Random());
        Vec3 vec3 = entity.getDeltaMovement();
        entity.setDeltaMovement(entity.getDeltaMovement().add(vec3.x, entity.isOnGround() ? 0.0D : vec3.y, vec3.z));
    }

    @Override
    public boolean activeFinger1() {
        return true;
    }

    @Override
    public boolean activeFinger2() {
        return true;
    }

    @Override
    public boolean activeFinger3() {
        return true;
    }

    @Override
    public boolean activeFinger4() {
        return true;
    }

    @Override
    public boolean activeBlade() {
        return false;
    }

    @Override
    public int modeActive() {
        return 0;
    }
}
