package com.thefreak.botsmod.objects.items.organs.tools.arms;

import com.thefreak.botsmod.API.IAmProstheticComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class IronLongBladeModuleItem extends ModuleComponent{
    public IronLongBladeModuleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public String getRendererID(ItemStack stack, Player player) {
        return "longBladeModule";
    }

    @Override
    public float getDamageAddition() {
        return 2F;
    }
}
