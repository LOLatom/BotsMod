package com.thefreak.botsmod.mixins.server.prosthetic;

import com.mojang.datafixers.util.Pair;
import com.thefreak.botsmod.API.IAmProstheticItem;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.util.capabilities.PlayerLimbsProvider;
import com.thefreak.botsmod.util.capabilities.PlayerProstheticArmProvider;
import com.thefreak.botsmod.util.capabilities.SimpleProstheticContainer;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.serverpackets.ProstheticCheckPackage;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

@Mixin(InventoryMenu.class)
public abstract class InventoryMenuMixin extends AbstractContainerMenu {
    @Shadow public abstract int getGridWidth();
    private static final ResourceLocation BLOCK_ATLAS = new ResourceLocation("textures/atlas/blocks.png");
    private static final ResourceLocation EMPTY = new ResourceLocation(BotsMod.MOD_ID, "gui/prosthetic/no_item_icon_prosthetic");


    @Shadow public abstract int getGridHeight();

    private final SimpleProstheticContainer prostheticContainer = new SimpleProstheticContainer();

    protected InventoryMenuMixin(@Nullable MenuType<?> pMenuType, int pContainerId) {
        super(pMenuType, pContainerId);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void constructorMixin(Inventory p_39706_, boolean p_39707_, Player p_39708_, CallbackInfo ci) {
            this.addSlot(new Slot(this.prostheticContainer, 0, 154, 59) {

                @Override
                public ItemStack getItem() {

                    return p_39708_.getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM)
                            .isPresent() ? p_39708_.getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM)
                            .resolve().get().getProstheticArm() : ItemStack.EMPTY;
                }

                @Override
                public boolean mayPlace(ItemStack pStack) {
                   if ( p_39708_.getCapability(PlayerLimbsProvider.PLAYER_LIMBS).isPresent()) {
                       if ( p_39708_.getCapability(PlayerLimbsProvider.PLAYER_LIMBS).resolve().get().hasMainArm()) {
                           return false;
                       }
                   }
                    return pStack.getItem() instanceof IAmProstheticItem;
                }

                @Override
                public void set(ItemStack pStack) {
                    p_39708_.getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM).ifPresent(
                            playerProstheticArmCap -> {
                                playerProstheticArmCap.setProstheticArm(pStack == null ? ItemStack.EMPTY : pStack);
                                if (p_39708_ instanceof ServerPlayer serverPlayer) {
                                    BotsPacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new ProstheticCheckPackage(playerProstheticArmCap.getProstheticArm(), p_39708_.getId()));
                                }
                            }
                    );

                    this.setChanged();
                }


            /*@Nullable
            @Override
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(BLOCK_ATLAS, EMPTY);
            }*/
            });
    }

    @Inject(method = "removed", at = @At("TAIL"))
    public void prostheticRemoved(Player pPlayer, CallbackInfo ci) {

    }


    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
