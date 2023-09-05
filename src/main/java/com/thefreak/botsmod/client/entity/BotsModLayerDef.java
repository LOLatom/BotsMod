package com.thefreak.botsmod.client.entity;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.client.entity.model.LightSwordAttackModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BotsModLayerDef {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BotsModLayers.LIGHT_SWORD_ATTACK, LightSwordAttackModel::createBodyLayer);

    }

}
