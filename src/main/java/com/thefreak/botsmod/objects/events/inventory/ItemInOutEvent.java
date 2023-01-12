package com.thefreak.botsmod.objects.events.inventory;

import com.thefreak.botsmod.API.IPlayerRenderables;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import com.thefreak.botsmod.objects.items.ItemType.AmuletItem;
import com.thefreak.botsmod.util.BOTSEvent;
import com.thefreak.botsmod.util.botsevent.SlotChanged;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ItemInOutEvent {

    @SubscribeEvent
    public static void ItemInOut(SlotChanged event) {

            if (event.getContainer() instanceof Inventory) {
                if (event.getPreItemStack().getItem() instanceof AmuletItem && event.getPostItemStack().isEmpty()) {
                    boolean stillhasSimilarAmulet = false;
                    for (int i = 0; i < event.getContainer().getContainerSize(); i++) {
                        if (event.getContainer().getItem(i).getItem() == event.getPreItemStack().getItem() && i != event.getSlot()) {
                            stillhasSimilarAmulet = true;
                        }
                    }
                    if (!stillhasSimilarAmulet) {
                        //Do stuff: Send Packet To Update amulet count
                    }

                }
                if (event.getPreItemStack().isEmpty() && event.getPostItemStack().getItem() instanceof AmuletItem) {
                    //Do stuff: Send Packet To Update amulet count
                }
            }


    }


    public static void ItemsRefreshOnLoad(PlayerEvent.StartTracking event) {
        if (event.getTarget() instanceof Player player) {
            if (player instanceof IPlayerRenderables playerRenderables) {
                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    if (player.getInventory().getItem(i).getItem() == ItemInitNew.GROUND_BESTIAL_AMULET.get()) {
                        playerRenderables.setGamulet(true);
                    } else if (player.getInventory().getItem(i).getItem() == ItemInitNew.CAVE_BESTIAL_AMULET.get()) {
                        playerRenderables.setCamulet(true);
                    } else if (player.getInventory().getItem(i).getItem() == ItemInitNew.ENDER_BESTIAL_AMULET.get()) {
                        playerRenderables.setEamulet(true);
                    }
                }
            }
        }
    }


}
