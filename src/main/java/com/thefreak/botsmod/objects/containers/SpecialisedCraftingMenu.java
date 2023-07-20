package com.thefreak.botsmod.objects.containers;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;

public class SpecialisedCraftingMenu extends AbstractContainerMenu {

    private static final int INV_SLOT_START = 10;
    private static final int INV_SLOT_END = 37;
    private final ContainerLevelAccess access;
    protected SpecialisedCraftingMenu(@Nullable MenuType<?> pMenuType, int pContainerId, ContainerLevelAccess access) {
        super(pMenuType, pContainerId);
        this.access = access;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
