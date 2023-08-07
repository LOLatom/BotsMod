package com.thefreak.botsmod.mixins.server;

import com.thefreak.botsmod.API.IAmDivine;
import com.thefreak.botsmod.API.IDuelMode;
import com.thefreak.botsmod.API.IPlayerRenderables;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.serverpackets.SetDuelPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import javax.swing.text.html.parser.Entity;
import java.util.function.Supplier;

@Mixin(Player.class)
public class PlayerMixin implements IAmDivine, IPlayerRenderables, IDuelMode {
    @Unique
    public boolean isDivine;
    @Unique
    public boolean EAmulet;
    @Unique
    public boolean CAmulet;
    @Unique
    public boolean GAmulet;
    @Unique
    public boolean dueling;
    @Unique
    public int duelentityID;

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

    @Override
    public boolean isInDuel() {
        return ((PlayerMixin)(Object)this).dueling;
    }

    @Override
    public void setInDuel(boolean inDuel, int ID) {
        ((PlayerMixin)(Object)this).dueling = inDuel;
        ((PlayerMixin)(Object)this).duelentityID = ID;
    }

    @Override
    public void createDuel(LivingEntity entity, float dmg, Level level) {
        System.out.println("D!");
        if (!level.isClientSide) {
            BotsPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> ((ServerPlayer) (Object) this)), new SetDuelPacket(true, entity.getId()));
        }
    }

}
