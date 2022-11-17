package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.client.gui.secrets.SecretTitleScreen;
import com.thefreak.botsmod.util.misc.SecretOptions;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.AccessibilityOptionsScreen;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.nio.file.FileSystem;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MainMenutScreenEvent {
    private static final ResourceLocation ACCESSIBILITY_TEXTURE = new ResourceLocation(BotsMod.MOD_ID,"textures/gui/secret_button.png");
    public boolean accesible = false;

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onMenuRender(ScreenEvent.InitScreenEvent event) {
        Screen screen = event.getScreen();


        Minecraft mc = ClassReferences.getClientMC();

        File f = new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "key14159268539793.txt");
        if (screen instanceof TitleScreen && f.exists()) {
            SecretOptions secretOptions = new SecretOptions();
            if (!(new File(secretOptions.getBotsPath().toString()).exists())) {
                secretOptions.createOptions();
            }
            int l = screen.height / 4 + 48;
            event.addListener(new ImageButton(screen.width / 2 + 108 + 20, l + 72 + 12, 20, 20, 0, 0, 20, ACCESSIBILITY_TEXTURE, 32, 64,
                    (button) -> {
                mc.setScreen(new SecretTitleScreen());
            }));

        }
    }


}
