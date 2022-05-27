package com.thefreak.botsmod.objects.blocks;

import com.thefreak.botsmod.init.BlockInitNew;
import com.thefreak.botsmod.init.ItemInitNew;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;


public class SporianMangroveTreeLog extends RotatedPillarBlock {
    public SporianMangroveTreeLog(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        ItemStack HeldItemIn = player.getItemInHand(handIn);
        if (HeldItemIn.getItem() instanceof AxeItem) {
        worldIn.setBlockAndUpdate(pos, BlockInitNew.STRIPPED_SPORIAN_MANGROVE_TREE_LOG.get().defaultBlockState());
        worldIn.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1F, 1F);
        popResource(worldIn,pos, new ItemStack(ItemInitNew.SPORIAN_MANGROVE_TREE_BARK.get(), 1));
        }

        return super.use(state, worldIn, pos, player, handIn, hit);
    }
}
