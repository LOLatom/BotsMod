package com.thefreak.botsmod.spells.spellclass.spelltype;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.spells.spellclass.AbstractSpell;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.serverpackets.UpdateBlocksAt;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class FireUseOnSpell extends AbstractSpell {

    public BlockPos savedBlockPos;
    public int tickPassed;
    public boolean isActive;
    public Level levelp;

    public FireUseOnSpell(ItemStack usedItemStack, boolean f1state, boolean f2state, boolean f3state, boolean f4state, boolean blade, @Nullable UseOnContext context) {
        super(usedItemStack, f1state, f2state, f3state, f4state, blade, context);
        this.tickPassed = 0;
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
            boolean a = level.getBlockState(context.getClickedPos().above()) == Blocks.WATER.defaultBlockState();
            boolean b = level.getBlockState(context.getClickedPos().above(2)) == Blocks.WATER.defaultBlockState();
            boolean c = level.getBlockState(context.getClickedPos().above(3)) == Blocks.WATER.defaultBlockState();
            boolean d = level.getBlockState(context.getClickedPos()).getMaterial() == Material.STONE;
            boolean d1 = level.getBlockState(context.getClickedPos().east()).getMaterial() == Material.STONE;
            boolean d2 = level.getBlockState(context.getClickedPos().west()).getMaterial() == Material.STONE;
            boolean d3 = level.getBlockState(context.getClickedPos().north()).getMaterial() == Material.STONE;
            boolean d4 = level.getBlockState(context.getClickedPos().south()).getMaterial() == Material.STONE;
            Random random = new Random();

        if (a&&b&&c&&d) {
            level.setBlocksDirty(context.getClickedPos(),level.getBlockState(context.getClickedPos()),Blocks.MAGMA_BLOCK.defaultBlockState());
                level.setBlockAndUpdate(context.getClickedPos(),Blocks.MAGMA_BLOCK.defaultBlockState());
                BubbleColumnBlock.updateColumn(level,context.getClickedPos().above(),level.getBlockState(context.getClickedPos()));
                System.out.println("Changed");
                level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().getX() + random.nextFloat(1),context.getClickedPos().getY()+ random.nextFloat(1),context.getClickedPos().getZ()+ random.nextFloat(1),0,0.1D,0);
                level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().getX()+ random.nextFloat(1),context.getClickedPos().getY()+0.8,context.getClickedPos().getZ()+ random.nextFloat(1),0,0.1D,0);
                level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().getX()+ random.nextFloat(1),context.getClickedPos().getY()+0.8,context.getClickedPos().getZ()+ random.nextFloat(1),0,0.1D,0);
                level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().getX()+ random.nextFloat(1),context.getClickedPos().getY()+0.8,context.getClickedPos().getZ()+ random.nextFloat(1),0,0.1D,0);
                level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().getX(),context.getClickedPos().getY()+0.9,context.getClickedPos().getZ(),0,0.1D,0);
                level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().getX(),context.getClickedPos().getY()+1,context.getClickedPos().getZ(),0,0.1D,0);
                level.playSound(player,context.getClickedPos(), SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS,1F,0.3F);
            }
        if (a&&b&&c&&d1) {
            level.setBlocksDirty(context.getClickedPos().east(),level.getBlockState(context.getClickedPos().east()),Blocks.MAGMA_BLOCK.defaultBlockState());
            level.setBlockAndUpdate(context.getClickedPos().east(),Blocks.MAGMA_BLOCK.defaultBlockState());
            BubbleColumnBlock.updateColumn(level,context.getClickedPos().above().east(),level.getBlockState(context.getClickedPos().east()));

            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().east().getX() + random.nextFloat(1),context.getClickedPos().east().getY()+ random.nextFloat(1),context.getClickedPos().east().getZ()+ random.nextFloat(1),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().east().getX()+ random.nextFloat(1),context.getClickedPos().east().getY()+0.8,context.getClickedPos().east().getZ()+ random.nextFloat(1),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().east().getX()+ random.nextFloat(1),context.getClickedPos().east().getY()+0.8,context.getClickedPos().east().getZ()+ random.nextFloat(1),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().east().getX()+ random.nextFloat(1),context.getClickedPos().east().getY()+0.8,context.getClickedPos().east().getZ()+ random.nextFloat(1),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().east().getX(),context.getClickedPos().east().getY()+0.9,context.getClickedPos().east().getZ(),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().east().getX(),context.getClickedPos().east().getY()+1,context.getClickedPos().east().getZ(),0,0.1D,0);
            level.playSound(player,context.getClickedPos().east(), SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS,1F,0.3F);
        }
        if (a&&b&&c&&d2) {
            level.setBlocksDirty(context.getClickedPos().west(),level.getBlockState(context.getClickedPos().west()),Blocks.MAGMA_BLOCK.defaultBlockState());
            level.setBlockAndUpdate(context.getClickedPos().west(),Blocks.MAGMA_BLOCK.defaultBlockState());
            BubbleColumnBlock.updateColumn(level,context.getClickedPos().west().above(),level.getBlockState(context.getClickedPos().west()));

            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().west().getX() + random.nextFloat(1),context.getClickedPos().getY()+ random.nextFloat(1),context.getClickedPos().west().getZ()+ random.nextFloat(1),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().west().getX()+ random.nextFloat(1),context.getClickedPos().west().getY()+0.8,context.getClickedPos().west().getZ()+ random.nextFloat(1),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().west().getX()+ random.nextFloat(1),context.getClickedPos().west().getY()+0.8,context.getClickedPos().west().getZ()+ random.nextFloat(1),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().west().getX()+ random.nextFloat(1),context.getClickedPos().west().getY()+0.8,context.getClickedPos().west().getZ()+ random.nextFloat(1),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().west().getX(),context.getClickedPos().west().getY()+0.9,context.getClickedPos().west().getZ(),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().west().getX(),context.getClickedPos().west().getY()+1,context.getClickedPos().west().getZ(),0,0.1D,0);
            level.playSound(player,context.getClickedPos().west(), SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS,1F,0.3F);
        }
        if (a&&b&&c&&d3) {
            level.setBlocksDirty(context.getClickedPos().north(),level.getBlockState(context.getClickedPos().north()),Blocks.MAGMA_BLOCK.defaultBlockState());
            level.setBlockAndUpdate(context.getClickedPos().north(),Blocks.MAGMA_BLOCK.defaultBlockState());
            BubbleColumnBlock.updateColumn(level,context.getClickedPos().north().above(),level.getBlockState(context.getClickedPos().north()));

            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().north().getX() + random.nextFloat(1),context.getClickedPos().north().getY()+ random.nextFloat(1),context.getClickedPos().north().getZ()+ random.nextFloat(1),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().north().getX()+ random.nextFloat(1),context.getClickedPos().north().getY()+0.8,context.getClickedPos().north().getZ()+ random.nextFloat(1),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().north().getX()+ random.nextFloat(1),context.getClickedPos().north().getY()+0.8,context.getClickedPos().north().getZ()+ random.nextFloat(1),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().north().getX()+ random.nextFloat(1),context.getClickedPos().north().getY()+0.8,context.getClickedPos().north().getZ()+ random.nextFloat(1),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().north().getX(),context.getClickedPos().north().getY()+0.9,context.getClickedPos().north().getZ(),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().north().getX(),context.getClickedPos().north().getY()+1,context.getClickedPos().north().getZ(),0,0.1D,0);
            level.playSound(player,context.getClickedPos().north(), SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS,1F,0.3F);
        }
        if (a&&b&&c&&d4) {
            level.setBlocksDirty(context.getClickedPos().south(),level.getBlockState(context.getClickedPos().south()),Blocks.MAGMA_BLOCK.defaultBlockState());
            level.setBlockAndUpdate(context.getClickedPos().south(),Blocks.MAGMA_BLOCK.defaultBlockState());
            BubbleColumnBlock.updateColumn(level,context.getClickedPos().south().above(),level.getBlockState(context.getClickedPos().south()));

            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().south().getX() + random.nextFloat(1),context.getClickedPos().south().getY()+ random.nextFloat(1),context.getClickedPos().south().getZ()+ random.nextFloat(1),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().south().getX()+ random.nextFloat(1),context.getClickedPos().south().getY()+0.8,context.getClickedPos().south().getZ()+ random.nextFloat(1),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().south().getX()+ random.nextFloat(1),context.getClickedPos().south().getY()+0.8,context.getClickedPos().south().getZ()+ random.nextFloat(1),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().south().getX()+ random.nextFloat(1),context.getClickedPos().south().getY()+0.8,context.getClickedPos().south().getZ()+ random.nextFloat(1),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().south().getX(),context.getClickedPos().south().getY()+0.9,context.getClickedPos().south().getZ(),0,0.1D,0);
            level.addParticle(ParticleTypes.LARGE_SMOKE,context.getClickedPos().south().getX(),context.getClickedPos().south().getY()+1,context.getClickedPos().south().getZ(),0,0.1D,0);
            level.playSound(player,context.getClickedPos().south(), SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS,1F,0.3F);
        }
        if (level.getBlockState(context.getClickedPos()).getBlock() instanceof TntBlock tntBlock) {
            level.setBlocksDirty(context.getClickedPos(),level.getBlockState(context.getClickedPos()),Blocks.AIR.defaultBlockState());
            level.setBlockAndUpdate(context.getClickedPos(),Blocks.AIR.defaultBlockState());
            tntBlock.onCaughtFire(level.getBlockState(context.getClickedPos()),level,context.getClickedPos(), Direction.UP,player);
        }
            System.out.println(context.getClickedPos());
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


}
