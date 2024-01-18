package com.thefreak.botsmod.spells;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.objects.items.loreandclueitems.GodKillerHand;
import com.thefreak.botsmod.objects.keys.KeyInitiation;
import com.thefreak.botsmod.spells.implementations.IAmSpellCard;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.NextSpellPacket;
import com.thefreak.botsmod.util.packets.interractionpackets.PreviousSpellPacket;
import com.thefreak.botsmod.util.packets.interractionpackets.ResetSpellPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class SpellCardMenuRender {

        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void onTickRenderSpellCard(TickEvent.RenderTickEvent event) {
                Minecraft mc = ClassReferences.getClientMC();
                LocalPlayer player = mc.player;
                if (mc.player == null) {
                        return;
                }

                if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof GodKillerHand) {
                        CompoundTag nbt = player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag();
                        int selection = nbt.getInt("spellID");
                        int mode = nbt.getInt("handMode");
                        ArrayList<ItemStack> invContainer = new ArrayList<>();
                        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                                if (player.getInventory().getItem(i).getItem() instanceof IAmSpellCard spellCard) {
                                        if (spellCard.modeRequirement() == mode) invContainer.add(player.getInventory().getItem(i));
                                }
                        }
                        if (selection > invContainer.size() - 1) {
                                BotsPacketHandler.INSTANCE.sendToServer(new ResetSpellPacket());
                        }
                        if (!invContainer.isEmpty() && !(mc.screen instanceof CreativeModeInventoryScreen) &&
                                !(mc.screen instanceof PauseScreen) && !(mc.screen instanceof InventoryScreen) &&
                        !(mc.screen instanceof ChatScreen)) {
                                PoseStack poseStack = new PoseStack();
                                int width = mc.getWindow().getGuiScaledWidth();
                                int height = mc.getWindow().getGuiScaledHeight();


                                ResourceLocation texture = ((IAmSpellCard)invContainer.get(selection).getItem()).spellCardTexture();

                                RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
                                RenderSystem.setShaderTexture(0, texture);

                                poseStack.pushPose();

                                RenderSystem.enableBlend();
                                ClassReferences.getClientMC().getTextureManager().getTexture(texture).bind();
                                poseStack.translate(0,0,101);
                                int texWidth = GlStateManager._getTexLevelParameter(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_WIDTH);
                                int texHeight = GlStateManager._getTexLevelParameter(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_HEIGHT);
                                GuiComponent.blit(poseStack,width/3/3,(height/2) + (height/4),0,0,48,64, texWidth, texHeight);
                                RenderSystem.disableBlend();
                                poseStack.popPose();

                                if (invContainer.size() - 1 > selection) {
                                        ResourceLocation texture2 = ((IAmSpellCard)invContainer.get(selection + 1).getItem()).spellCardTexture();
                                        PoseStack poseStack2 = new PoseStack();

                                        RenderSystem.setShaderColor(0.5F, 0.5F, 0.5F, 0.9F);
                                        RenderSystem.setShaderTexture(0, texture2);

                                        poseStack2.pushPose();

                                        RenderSystem.enableBlend();
                                        ClassReferences.getClientMC().getTextureManager().getTexture(texture2).bind();
                                        poseStack2.translate(0,0,101);
                                        GuiComponent.blit(poseStack2,width/3/3 + 48,(height/2) + (height/4) - 5,0,0,48,64, texWidth, texHeight);
                                        RenderSystem.disableBlend();
                                        poseStack2.popPose();
                                }
                                if (selection != 0) {
                                        ResourceLocation texture3 = ((IAmSpellCard)invContainer.get(selection - 1).getItem()).spellCardTexture();
                                        PoseStack poseStack3 = new PoseStack();

                                        RenderSystem.setShaderColor(0.5F, 0.5F, 0.5F, 0.9F);
                                        RenderSystem.setShaderTexture(0, texture3);

                                        poseStack3.pushPose();

                                        RenderSystem.enableBlend();
                                        ClassReferences.getClientMC().getTextureManager().getTexture(texture3).bind();
                                        poseStack3.translate(0,0,101);
                                        GuiComponent.blit(poseStack3,width/3/3 - 48,(height/2) + (height/4) - 5,0,0,48,64, texWidth, texHeight);
                                        RenderSystem.disableBlend();
                                        poseStack3.popPose();
                                }

                        }
                }


        }


        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void pressKey(InputEvent.KeyInputEvent event) {
                if (KeyInitiation.previousKey.consumeClick()) {
                        Minecraft mc = ClassReferences.getClientMC();
                        LocalPlayer player = mc.player;
                        CompoundTag nbt = player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag();
                        int selection = nbt.getInt("spellID");
                        int mode = nbt.getInt("handMode");
                        if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof GodKillerHand) {
                                ArrayList<ItemStack> invContainer = new ArrayList<>();
                                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                                        if (player.getInventory().getItem(i).getItem() instanceof IAmSpellCard spellCard) {
                                                if (spellCard.modeRequirement() == mode) invContainer.add(player.getInventory().getItem(i));
                                        }
                                }
                                if (selection > 0 && selection != 0) {
                                        IAmSpellCard spellCard = ((IAmSpellCard) invContainer.get(selection - 1).getItem());
                                        player.displayClientMessage(spellCard.spellName(),true);
                                        BotsPacketHandler.INSTANCE.sendToServer(new PreviousSpellPacket());
                                        return;
                                }
                        }

                }
                if (KeyInitiation.nextKey.consumeClick()) {
                        Minecraft mc = ClassReferences.getClientMC();
                        LocalPlayer player = mc.player;
                        CompoundTag nbt = player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag();
                        int selection = nbt.getInt("spellID");
                        int mode = nbt.getInt("handMode");
                        if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof GodKillerHand) {
                                ArrayList<ItemStack> invContainer = new ArrayList<>();
                                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                                        if (player.getInventory().getItem(i).getItem() instanceof IAmSpellCard spellCard) {
                                                if (spellCard.modeRequirement() == mode) invContainer.add(player.getInventory().getItem(i));
                                        }
                                }
                                if (selection < invContainer.size() -1) {
                                        IAmSpellCard spellCard = ((IAmSpellCard) invContainer.get(selection + 1).getItem());
                                        player.displayClientMessage(spellCard.spellName(),true);
                                        BotsPacketHandler.INSTANCE.sendToServer(new NextSpellPacket());
                                        return;
                                }
                        }
                }


        }

        /*@SubscribeEvent
        public static void onMenuRender(ScreenEvent.InitScreenEvent event) {
                if (event.getScreen() instanceof InventoryScreen ||event.getScreen() instanceof CreativeModeInventoryScreen) {
                        BotsPacketHandler.INSTANCE.sendToServer(new ResetSpellPacket());
                        return;
                }
        }*/
}


