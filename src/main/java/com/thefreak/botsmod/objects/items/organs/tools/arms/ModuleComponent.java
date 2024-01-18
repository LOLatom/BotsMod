package com.thefreak.botsmod.objects.items.organs.tools.arms;

import com.thefreak.botsmod.API.IAmProstheticComponent;
import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModuleComponent extends Item implements IAmProstheticComponent {
    public ModuleComponent(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public String getRendererID(ItemStack stack, Player player) {
        return null;
    }

    public Item getRequiredStackToBePlaced() {
        return ItemInitNew.MODULE_ATTACHMENT_COMPONENT.get();
    }
}
