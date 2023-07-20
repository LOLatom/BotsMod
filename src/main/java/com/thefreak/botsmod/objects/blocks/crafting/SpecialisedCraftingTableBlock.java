package com.thefreak.botsmod.objects.blocks.crafting;


import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SpecialisedCraftingTableBlock extends Block {

    private static final Component CONTAINER_TITLE = new TextComponent("Specialised Crafting Table");
    public SpecialisedCraftingTableBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            if (pPlayer instanceof ServerPlayer serverPlayer) {
                serverPlayer.openMenu(new SimpleMenuProvider((p_52229_, p_52230_, p_52231_) -> {
                    return new CraftingMenu(p_52229_, p_52230_, ContainerLevelAccess.create(pLevel, pPos));
                }, CONTAINER_TITLE));
                serverPlayer.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            }
            return InteractionResult.CONSUME;
        }
    }
}
