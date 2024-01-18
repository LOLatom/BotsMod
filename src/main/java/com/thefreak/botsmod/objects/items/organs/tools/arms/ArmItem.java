package com.thefreak.botsmod.objects.items.organs.tools.arms;

import com.thefreak.botsmod.API.IAmProstheticItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ArmItem extends Item implements IAmProstheticItem {
    public ArmItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public boolean renderItemStack(ItemStack inHandStack, Player player) {
        return true;
    }

    @Override
    public boolean canHandleItemStack(ItemStack inHandStack, Player player) {
        return true;
    }

    @Override
    public boolean canAttack(Player player) {
        return true;
    }

    @Override
    public boolean canUse(Player player, Level level, InteractionHand pInteractionHand) {
        return true;
    }

    @Override
    public boolean canUseOn(UseOnContext pContext) {
        return true;
    }

    @Override
    public String getRendererID() {
        return "armRender";
    }
}
