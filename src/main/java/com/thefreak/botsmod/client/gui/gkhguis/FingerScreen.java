package com.thefreak.botsmod.client.gui.gkhguis;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.OpenCloseGodKHPacket;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FingerScreen extends Screen {
    private static final ResourceLocation TARGET_BUTTON = new ResourceLocation(BotsMod.MOD_ID,"textures/gui/targetbutton.png");
    public InteractionHand hand;
    public ItemStack stack;
    public Level level;
    public Player player;

    public FingerScreen(Level level, Player player, ItemStack stack, InteractionHand hand) {
        super(new TextComponent("Finger"));
        this.hand = hand;
        this.level = level;
        this.stack = stack;
        this.player = player;
    }

    @Override
    protected void init() {
        int l = this.height / 4 + 48;
        this.addRenderableWidget(new ImageButton(this.width / 2, this.height/2, 10,10,0,0,10,TARGET_BUTTON,10,20,(button) -> {
            BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(this.stack,3));
        }));
        this.addRenderableWidget(new ImageButton(this.width / 2 + 11, this.height/2, 10,10,0,0,10,TARGET_BUTTON,10,20,(button) -> {
            BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(this.stack,4));
        }));
        this.addRenderableWidget(new ImageButton(this.width / 2 - 11, this.height/2, 10,10,0,0,10,TARGET_BUTTON,10,20,(button) -> {
            BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(this.stack,2));
        }));
        this.addRenderableWidget(new ImageButton(this.width / 2 - 22, this.height/2, 10,10,0,0,10,TARGET_BUTTON,10,20,(button) -> {
            BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(this.stack,1));
        }));
        this.addRenderableWidget(new ImageButton(this.width / 2 + 22, this.height/2, 10,10,0,0,10,TARGET_BUTTON,10,20,(button) -> {
            BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(this.stack,5));
        }));
        System.out.println("WORKING ACTUALLY HERE");


    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    public void rendernewBackground(PoseStack pPoseStack, int pVOffset) {
        if (this.minecraft.level != null) {
            this.fillGradient(pPoseStack, 0, 0, this.width, this.height, -1072689136, -804253680);
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.ScreenEvent.BackgroundDrawnEvent(this, pPoseStack));
        } else {
            this.renderDirtBackground(pVOffset);
        }

    }
}
