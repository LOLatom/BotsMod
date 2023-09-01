package com.thefreak.botsmod.objects.blocks.crafting;


import com.thefreak.botsmod.objects.containers.SpecialisedCraftingMenu;
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
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SpecialisedCraftingTableBlock extends Block {
    private static final VoxelShape BASE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 4.0D, 15.0D);
    private static final VoxelShape MIDDLE = Block.box(5.0D, 4.0D, 5.0D, 11.0D, 9.0D, 11.0D);
    private static final VoxelShape TOP = Block.box(3.0D, 9.0D, 3.0D, 13.0D, 11.0D, 13.0D);
    private static final VoxelShape CLOTH = Block.box(0.0D, 11.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    private static final VoxelShape SHAPE = Shapes.or(BASE,MIDDLE,TOP,CLOTH);

    private static final Component CONTAINER_TITLE = new TextComponent("");
    public SpecialisedCraftingTableBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
            pPlayer.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        return new SimpleMenuProvider((id, inventory, p_52231_) -> {
            return new SpecialisedCraftingMenu(id, ContainerLevelAccess.create(pLevel, pPos), inventory);
        }, CONTAINER_TITLE);
    }
}
