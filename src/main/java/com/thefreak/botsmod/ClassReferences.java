package com.thefreak.botsmod;

import com.mojang.math.Vector3f;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ClassReferences {
    public static Level getClientLevel() {
        return Minecraft.getInstance().level;
    }

    public static Minecraft getClientMC() {
        return Minecraft.getInstance();
    }

    public static MinecraftServer getIntegratedServer() {
        return Minecraft.getInstance().getSingleplayerServer();
    }


    public static String getVersion() {
        return Minecraft.getInstance().getLaunchedVersion();
    }

    public static boolean isClientLevel(Level level) {
        return level instanceof ClientLevel;
    }

    public static boolean isClientPacketListener(PacketListener listener) {
        return listener instanceof ClientGamePacketListener;
    }


    public static Player getPlayer() {
        return Minecraft.getInstance().player;
    }

    public static void tickLevel(Level level) {
        ((ClientLevel) level).tick(() -> true);
        ((ClientLevel) level).tickEntities();
    }



    private static Camera camera;

    public static void updateCamera() {
        camera = Minecraft.getInstance().getEntityRenderDispatcher().camera;
    }

    public static Vec3 getCameraPos() {
        return camera.getPosition();
    }

    public static ItemInHandRenderer getItemInHandRenderer() {
        return Minecraft.getInstance().getItemInHandRenderer();
    }


    public static Vector3f getCameraLook() {
        return camera.getLookVector();
    }

    public static boolean isCameraPresent() {
        return camera != null;
    }
}
