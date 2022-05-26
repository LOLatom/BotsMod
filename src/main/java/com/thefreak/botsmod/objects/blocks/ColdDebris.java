package com.thefreak.botsmod.objects.blocks;


import net.minecraft.core.BlockPos;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;


public class ColdDebris extends Block {

    public ColdDebris(Properties properties) {
        super(properties);
    }

    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,100,2));
        super.playerWillDestroy(worldIn, pos, state, player);
    }

}
