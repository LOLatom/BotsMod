package com.thefreak.botsmod.objects.blocks.crafting;

import com.thefreak.botsmod.init.ModTileEntityTypes;
import com.thefreak.botsmod.objects.containers.SpecialisedCraftingMenu;
import com.thefreak.botsmod.objects.screens.menu.ArmFactoryMenu;
import com.thefreak.botsmod.tileentity.ArmFactoryTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.filters.VanillaPacketFilter;
import org.jetbrains.annotations.Nullable;

public class ArmFactoryBlock extends BaseEntityBlock {
    public ArmFactoryBlock(Properties p_49795_) {
        super(p_49795_);
    }
    private static final Component CONTAINER_TITLE = new TextComponent("");
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return ModTileEntityTypes.ARM_FACTORY_TILE_ENTITY.get().create(pPos, pState);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity tile = pLevel.getBlockEntity(pPos);
            if (tile instanceof ArmFactoryTileEntity te) {
                te.sendUpdates();
                NetworkHooks.openGui((ServerPlayer) pPlayer, te,pPos);

            }
            return InteractionResult.CONSUME;
        }
    }


}
