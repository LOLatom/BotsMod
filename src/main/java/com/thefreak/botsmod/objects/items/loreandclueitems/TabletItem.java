package com.thefreak.botsmod.objects.items.loreandclueitems;

import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.client.gui.tablet.TabletScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TabletItem extends Item {
    public ResourceLocation TEXTURE;
    public TabletItem(Properties pProperties, ResourceLocation texture) {
        super(pProperties);
        this.TEXTURE = texture;
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        if (pLevel.isClientSide) {
            ClassReferences.getClientMC().setScreen(new TabletScreen(TEXTURE));
        }
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
}
