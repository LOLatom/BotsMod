package com.thefreak.botsmod.util;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.client.entity.render.*;
import com.thefreak.botsmod.client.entity.render.demons.sadface.SadFaceRenderer;
import com.thefreak.botsmod.client.entity.render.demons.sadface.SadManRenderer;
import com.thefreak.botsmod.init.BlockInitNew;
import com.thefreak.botsmod.init.ModEntityTypes;
import com.thefreak.botsmod.objects.keys.KeyInitiation;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup (FMLClientSetupEvent event) {
        KeyInitiation.init();
                                         //      MOBS       //
        EntityRenderers.register(ModEntityTypes.WANDERING_SPECTER.get(), WanderingSpecterRender::new);
        EntityRenderers.register(ModEntityTypes.LADYBUG.get(), LadybugRender::new);
        EntityRenderers.register(ModEntityTypes.TIPPY_LIZARD.get(), TippyLizardRender::new);
        EntityRenderers.register(ModEntityTypes.PUFF_WORM.get(), PuffWormRender::new);
        EntityRenderers.register(ModEntityTypes.DRAINED.get(), DrainedRender::new);
        EntityRenderers.register(ModEntityTypes.DRAINED_CHIEF.get(), DrainedChiefRender::new);
        EntityRenderers.register(ModEntityTypes.WICKED_ONE.get(), WickedOneRender::new);
        EntityRenderers.register(ModEntityTypes.KRASIA.get(), KrasiaRender::new);
        EntityRenderers.register(ModEntityTypes.CAVE_ANGELIA_GUPPIE.get(), CaveAngeliaGuppieRender::new);
        EntityRenderers.register(ModEntityTypes.GATE_KEEPER.get(), GateKeeperRender::new);
        EntityRenderers.register(ModEntityTypes.SAD_MAN.get(), SadManRenderer::new);
        EntityRenderers.register(ModEntityTypes.SAD_FACE.get(), SadFaceRenderer::new);



        //      SPECIAL       //
        EntityRenderers.register(ModEntityTypes.SHADOW_LIGHTNING_BOLT.get(), ShadowLightningBoltRenderer::new);
        EntityRenderers.register(ModEntityTypes.LIGHT_SWORD_CONSTRUCT.get(), LightSwordConstructRenderer::new);
        //      ITEMS       //


        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.POST_MORTAL_ALTAR.get(), RenderType.cutout());

    }


	
}
