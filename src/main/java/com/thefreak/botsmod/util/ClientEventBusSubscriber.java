package com.thefreak.botsmod.util;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.client.entity.render.*;

import com.thefreak.botsmod.init.BlockInitNew;


import com.thefreak.botsmod.init.ModEntityTypes;
import com.thefreak.botsmod.init.ModTileEntityTypes;

import com.thefreak.botsmod.tileentity.render.PostMortalAltarRender;
import net.minecraft.client.Minecraft;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    private static final EntityRenderDispatcher renderManager = Minecraft.getInstance().getEntityRenderDispatcher();

    @SubscribeEvent
    public static void clientSetup (FMLClientSetupEvent event) {
                                         //      MOBS       //
        EntityRenderers.register(ModEntityTypes.GIANT_TARDIGRADE.get(), GiantTardigradeRender::new);
        EntityRenderers.register(ModEntityTypes.WANDERING_SPECTER.get(), WanderingSpecterRender::new);
        EntityRenderers.register(ModEntityTypes.LADYBUG.get(), LadybugRender::new);
        EntityRenderers.register(ModEntityTypes.TIPPY_LIZARD.get(), TippyLizardRender::new);
        EntityRenderers.register(ModEntityTypes.PUFF_WORM.get(), PuffWormRender::new);
        EntityRenderers.register(ModEntityTypes.DRAINED.get(), DrainedRender::new);
        EntityRenderers.register(ModEntityTypes.DRAINED_CHIEF.get(), DrainedChiefRender::new);
        


        //      SPECIAL       //
        EntityRenderers.register(ModEntityTypes.BANSHEE_SCREAM.get(), BansheeScreamRenderer::new);
        EntityRenderers.register(ModEntityTypes.SALTED_ARROW.get(), SaltedArrowRenderer::new);

        //      ITEMS       //

        EntityRenderers.register(ModEntityTypes.PINK_PURIFIED_SALT_ITEM_ENTITY.get(), (renderManager) -> new ItemEntityRenderer(renderManager));

        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.POST_MORTAL_ALTAR.get(), RenderType.cutout());
    }


	
}
