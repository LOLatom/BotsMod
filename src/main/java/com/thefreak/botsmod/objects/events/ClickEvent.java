package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.BotsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.fml.common.Mod;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClickEvent {

    public static void rightClickEvent(InputEvent.ClickInputEvent event) {
        InteractionHand hand = event.getHand();


   }

}
