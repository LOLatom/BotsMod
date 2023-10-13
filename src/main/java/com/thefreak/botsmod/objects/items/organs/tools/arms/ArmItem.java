package com.thefreak.botsmod.objects.items.organs.tools.arms;

import com.thefreak.botsmod.API.IAmProstheticItem;
import net.minecraft.world.item.Item;

public class ArmItem extends Item implements IAmProstheticItem {
    public ArmItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public String getRendererID() {
        return "armRender";
    }
}
