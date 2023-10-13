package com.thefreak.botsmod.objects.items.organs.tools;

import com.thefreak.botsmod.util.capabilities.PlayerLimbsProvider;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class Cleaver extends Item {
    public Cleaver(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pUsedHand == InteractionHand.OFF_HAND && pPlayer.getItemInHand(InteractionHand.MAIN_HAND) == ItemStack.EMPTY) {
            pPlayer.startUsingItem(pUsedHand);
            return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 5000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        super.onUsingTick(stack, player, count);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        if (pTimeCharged < 4850  && pLivingEntity instanceof Player player) {
            player.getCapability(PlayerLimbsProvider.PLAYER_LIMBS).ifPresent(playerLimbsCap -> {
                playerLimbsCap.cutMainArm();
                pLevel.playLocalSound(player.getX(),player.getY(),player.getZ(), SoundEvents.WART_BLOCK_BREAK, SoundSource.PLAYERS,1, 1,true);
            });
        }
        System.out.println(pTimeCharged);
        super.releaseUsing(pStack, pLevel, pLivingEntity, pTimeCharged);
    }
}
