package com.thefreak.botsmod.mixins.server;

import com.thefreak.botsmod.API.IAmDivine;
import com.thefreak.botsmod.API.IPlayerRenderables;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Player.class)
public class PlayerMixin implements IAmDivine, IPlayerRenderables {
    public boolean isDivine;
    public boolean EAmulet;
    public boolean CAmulet;
    public boolean GAmulet;

    @Override
    public boolean isDivine() {
        return ((PlayerMixin)(Object)this).isDivine;
    }

    @Override
    public void setDivine(boolean divine) {
        ((PlayerMixin)(Object)this).isDivine = divine;
    }

    @Override
    public boolean enderAmulet() {
        return ((PlayerMixin)(Object)this).EAmulet;
    }

    @Override
    public boolean caveAmulet() {
        return ((PlayerMixin)(Object)this).CAmulet;
    }

    @Override
    public boolean groundAmulet() {
        return ((PlayerMixin)(Object)this).GAmulet;
    }

    @Override
    public void setEamulet(boolean enderamulet) {
        ((PlayerMixin)(Object)this).EAmulet = enderamulet;
    }

    @Override
    public void setCamulet(boolean caveamulet) {
        ((PlayerMixin)(Object)this).CAmulet = caveamulet;
    }

    @Override
    public void setGamulet(boolean groundamulet) {
        ((PlayerMixin)(Object)this).GAmulet = groundamulet;
    }
}
