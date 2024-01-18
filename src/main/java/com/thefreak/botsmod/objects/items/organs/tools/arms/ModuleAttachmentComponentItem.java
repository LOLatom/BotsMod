package com.thefreak.botsmod.objects.items.organs.tools.arms;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ModuleAttachmentComponentItem extends AdditionComponent {
    public ModuleAttachmentComponentItem(Properties pProperties) {
        super(pProperties);
    }

    public String getRendererID(ItemStack stack, Player player) {
        return "moduleAttachmentComponent";
    }

    @Override
    public boolean hasModuleInput(ItemStack stack) {
        return true;
    }
}
