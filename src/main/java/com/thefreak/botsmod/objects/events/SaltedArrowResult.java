package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.PinkPurifiedSaltItemEntity;
import com.thefreak.botsmod.entities.Projectile.SaltedArrow;
import com.thefreak.botsmod.init.ItemInitNew;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SaltedArrowResult {

    @SubscribeEvent
    public static void OnProjectileHit(ProjectileImpactEvent event) {

    }




}
