package com.thefreak.botsmod.spells.spellclass.spelltype;

import com.thefreak.botsmod.API.ISFreezable;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.spells.spellclass.AbstractSpell;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FireSpell extends AbstractSpell {


    public FireSpell(ItemStack usedItemStack, boolean f1state, boolean f2state, boolean f3state, boolean f4state, boolean blade, @Nullable UseOnContext context) {
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
    public void Execute(Player player, Level level, InteractionHand hand,@Nullable UseOnContext context) {
        List<Entity> entities = level.getEntitiesOfClass(Entity.class, player.getBoundingBox().inflate(3D));
        for (Entity entity : entities) {
            entity.setSecondsOnFire(10);
            if (entity instanceof Creeper creeper) {
                creeper.ignite();
                level.addParticle(ParticleTypes.SMOKE,creeper.blockPosition().getX() + 0.1,creeper.blockPosition().getY()+0.8,creeper.blockPosition().getZ()+ 0.2,0,0.1D,0);
                level.addParticle(ParticleTypes.SMOKE,creeper.blockPosition().getX()+ 0.2,creeper.blockPosition().getY()+0.8,creeper.blockPosition().getZ()+ 0.1,0,0.1D,0);
                level.addParticle(ParticleTypes.SMOKE,creeper.blockPosition().getX()+ 0.1,creeper.blockPosition().getY()+0.8,creeper.blockPosition().getZ()+ 0.1,0,0.1D,0);
                level.addParticle(ParticleTypes.SMOKE,creeper.blockPosition().getX()+ 0.2,creeper.blockPosition().getY()+0.8,creeper.blockPosition().getZ()+ 0.2,0,0.1D,0);
                level.addParticle(ParticleTypes.SMOKE,creeper.blockPosition().getX(),creeper.blockPosition().getY()+0.9,creeper.blockPosition().getZ(),0,0.1D,0);
                level.addParticle(ParticleTypes.SMOKE,creeper.blockPosition().getX(),creeper.blockPosition().getY()+1,creeper.blockPosition().getZ(),0,0.1D,0);

            }
        }
        super.Execute(player, level, hand,context);
    }

    @Override
    public ResourceLocation getIconLocation() {
        return new ResourceLocation(BotsMod.MOD_ID,"textures/gui/spell/fire_spell.png");
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

    @Override
    public void tick() {
        System.out.println("ticking");
        super.tick();
    }
}
