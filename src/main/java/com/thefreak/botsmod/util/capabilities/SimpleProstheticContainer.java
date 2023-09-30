package com.thefreak.botsmod.util.capabilities;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class SimpleProstheticContainer extends SimpleContainer {

    public SimpleProstheticContainer() {
        super(1);
    }

    @Override
    public void startOpen(Player pPlayer) {
        pPlayer.getCapability(PlayerProstheticArmProvider.PLAYER_PROSTHETIC_ARM)
                .ifPresent(playerProstheticArmCap -> {
                    this.setItem(0,playerProstheticArmCap.getProstheticArm());
                });
        super.startOpen(pPlayer);
    }


}
