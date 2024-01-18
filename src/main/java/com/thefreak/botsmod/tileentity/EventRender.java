package com.thefreak.botsmod.tileentity;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.client.Rendering.blockentity.ArmFactoryRenderer;
import com.thefreak.botsmod.init.ModTileEntityTypes;
import com.thefreak.botsmod.tileentity.render.CookingPotRender;
import com.thefreak.botsmod.tileentity.render.PostMortalAltarRender;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EventRender {

   @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
    event.registerBlockEntityRenderer(ModTileEntityTypes.POST_MORTAL_ALTAR_TILE_ENTITY.get(), PostMortalAltarRender::new);
    event.registerBlockEntityRenderer(ModTileEntityTypes.COOKING_POT_TILE_ENTITY.get(), CookingPotRender::new);
    event.registerBlockEntityRenderer(ModTileEntityTypes.ARM_FACTORY_TILE_ENTITY.get(), ArmFactoryRenderer::new);


   }




}
