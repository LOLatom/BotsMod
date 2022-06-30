package com.thefreak.botsmod.tileentity;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.init.ModTileEntityTypes;
import com.thefreak.botsmod.tileentity.render.PostMortalAltarRender;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.example.client.renderer.tile.BotariumTileRenderer;
import software.bernie.geckolib3.GeckoLib;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EventRender {

   @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
    event.registerBlockEntityRenderer(ModTileEntityTypes.POST_MORTAL_ALTAR_TILE_ENTITY.get(), PostMortalAltarRender::new);
    }

}
