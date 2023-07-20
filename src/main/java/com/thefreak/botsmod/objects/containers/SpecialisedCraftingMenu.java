package com.thefreak.botsmod.objects.containers;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;

public class SpecialisedCraftingMenu extends AbstractContainerMenu {

    private static final int INV_SLOT_START = 10;
    private static final int INV_SLOT_END = 37;
    protected SpecialisedCraftingMenu(@Nullable MenuType<?> pMenuType, int pContainerId) {
        super(pMenuType, pContainerId);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
