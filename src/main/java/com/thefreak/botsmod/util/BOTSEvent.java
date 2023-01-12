package com.thefreak.botsmod.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.util.botsevent.SlotChanged;
import com.thefreak.botsmod.util.botsevent.TooltipDisplayEvent;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class BOTSEvent {

    public static void renderToolTip(ItemStack stack, PoseStack poseStack, int Width, int Heigh, int preX, int preY, int postX, int postY) {
        TooltipDisplayEvent event = new TooltipDisplayEvent(stack,poseStack,Width,Heigh,preX,preY,postX,postY);

        MinecraftForge.EVENT_BUS.post(event);

    }

    public class BOTSPlayerEvent {
        public class RenderEvent {



        }
        public class SlotEvents {
            public static void setSlotChanged(Container container, ItemStack preItemStack, ItemStack postItemStack, int slot) {
                SlotChanged event = new SlotChanged(container,preItemStack,postItemStack, slot);
                MinecraftForge.EVENT_BUS.post(event);
            }




        }






    }

}
