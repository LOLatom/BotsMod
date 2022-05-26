package com.thefreak.botsmod.util;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.client.entity.render.*;
import com.thefreak.botsmod.entities.Projectile.SaltedArrow;
import com.thefreak.botsmod.entities.WanderingSpecterEntity;
import com.thefreak.botsmod.init.BlockInitNew;
import com.thefreak.botsmod.init.ModContainerTypes;

import com.thefreak.botsmod.init.ModEntityTypes;
import com.thefreak.botsmod.init.ModTileEntityTypes;
import com.thefreak.botsmod.tileentity.render.PostMortalAltarGlowRender;
import com.thefreak.botsmod.tileentity.render.PostMortalAltarRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.example.registry.BlockRegistry;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    private static final EntityRendererManager renderManager = Minecraft.getInstance().getEntityRenderDispatcher();

    @SubscribeEvent
    public static void clientSetup (FMLClientSetupEvent event) {
                                         //      MOBS       //
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GIANT_TARDIGRADE.get(), GiantTardigradeRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.WANDERING_SPECTER.get(), WanderingSpecterRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.LADYBUG.get(), LadybugRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.TIPPY_LIZARD.get(), TippyLizardRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PUFF_WORM.get(), PuffWormRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DRAINED.get(), DrainedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DRAINED_CHIEF.get(), DrainedChiefRender::new);



        //      SPECIAL       //
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BANSHEE_SCREAM.get(), BansheeScreamRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SALTED_ARROW.get(), SaltedArrowRenderer::new);

        //      ITEMS       //

        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PINK_PURIFIED_SALT_ITEM_ENTITY.get(), (renderManager) -> new ItemRenderer(renderManager, Minecraft.getInstance().getItemRenderer()));

        //  TILE-ENTITIES  //
        ClientRegistry.bindTileEntityRenderer((BlockEntityType)ModTileEntityTypes.POST_MORTAL_ALTAR_TILE_ENTITY.get(), PostMortalAltarRender::new);
        RenderTypeLookup.setRenderLayer(BlockInitNew.POST_MORTAL_ALTAR.get(), RenderType.cutout());
    }


	
}
