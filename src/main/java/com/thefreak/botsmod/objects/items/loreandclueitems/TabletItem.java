package com.thefreak.botsmod.objects.items.loreandclueitems;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.client.gui.tablet.TabletScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;

public class TabletItem extends Item {
    public ResourceLocation TEXTURE;
    public TabletItem(Properties pProperties, ResourceLocation texture) {
        super(pProperties);
        this.TEXTURE = texture;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        if (pLevel.isClientSide) {
            Minecraft minecraft = ClassReferences.getClientMC();
            minecraft.setScreen(new TabletScreen(TEXTURE));
        }
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());    }
}
