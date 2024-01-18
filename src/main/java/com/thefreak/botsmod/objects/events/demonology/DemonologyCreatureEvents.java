package com.thefreak.botsmod.objects.events.demonology;


import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.entities.util.BotsMonster;
import com.thefreak.botsmod.util.BOTSEvent;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.demonology.SendMessageToLocalMobPacket;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DemonologyCreatureEvents {


    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void typeChatEvent(ClientChatEvent event) {
        List<BotsMonster> monstersList = ClassReferences.getPlayer().getLevel().getEntitiesOfClass(BotsMonster.class, ClassReferences.getPlayer().getBoundingBox().inflate(4));
        for (BotsMonster monster: monstersList) {
            monster.worded(event.getMessage(), ClassReferences.getPlayer());
        }
        BotsPacketHandler.INSTANCE.sendToServer(new SendMessageToLocalMobPacket(event.getMessage()));
    }

    @SubscribeEvent
    public static void renderedEntityEvent(RenderLivingEvent.Post event) {
        if (event.getEntity() instanceof BotsMonster botsMonster) {
            botsMonster.playerRendering();
        }

    }

}
