package com.thefreak.botsmod.objects.items.organs.tools;

import com.thefreak.botsmod.init.iteminit.FoodItemInit;
import com.thefreak.botsmod.util.capabilities.PlayerLimbsProvider;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.serverpackets.ArmCheckPackage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;

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
        if (count == 4850) {
            Level pLevel = player.getLevel();
            pLevel.playLocalSound(player.getX(),player.getY(),player.getZ(), SoundEvents.COPPER_STEP, SoundSource.MASTER,1, 1,false);
        }
        super.onUsingTick(stack, player, count);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        if (pTimeCharged < 4850  && pLivingEntity instanceof Player player) {
            player.getCapability(PlayerLimbsProvider.PLAYER_LIMBS).ifPresent(playerLimbsCap -> {
                playerLimbsCap.cutMainArm();
                if (!pLevel.isClientSide) {
                    BotsPacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new ArmCheckPackage(playerLimbsCap.hasMainArm(), player.getId()));
                }
                pLevel.addFreshEntity(new ItemEntity(pLevel,player.getX(),player.getY(),player.getZ(), FoodItemInit.FLESH_CHUNK.get().getDefaultInstance()));
                pLevel.addFreshEntity(new ItemEntity(pLevel,player.getX(),player.getY(),player.getZ(), FoodItemInit.FLESH_CHUNK.get().getDefaultInstance()));
                pLevel.playLocalSound(player.getX(),player.getY(),player.getZ(), SoundEvents.WART_BLOCK_BREAK, SoundSource.PLAYERS,1, 1,true);
                player.hurt(DamageSource.playerAttack(player),15F);
            });
        }
        System.out.println(pTimeCharged);
        super.releaseUsing(pStack, pLevel, pLivingEntity, pTimeCharged);
    }
}
