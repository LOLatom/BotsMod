package com.thefreak.botsmod.client.gui.secrets;

import com.ibm.icu.impl.coll.UVector32;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.util.misc.SecretOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.Option;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.LockIconButton;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.narration.NarrationThunk;
import net.minecraft.client.gui.screens.*;
import net.minecraft.client.main.GameConfig;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.UUID;
import java.util.Vector;

public class SecretTitleScreen extends Screen {
    public static final ResourceLocation SECRET_BACKGROUND = new ResourceLocation(BotsMod.MOD_ID,"textures/gui/secrets_background.png");
    private static final ResourceLocation SECRET_LOOGI = new ResourceLocation(BotsMod.MOD_ID,"textures/gui/secret_luigi_button.png");
    private static final ResourceLocation SECRET_CAPPIN = new ResourceLocation(BotsMod.MOD_ID,"textures/gui/secret_cappin_button.png");
    private static final ResourceLocation SECRET_BIANCA = new ResourceLocation(BotsMod.MOD_ID,"textures/gui/secret_bianca_button.png");
    private static final ResourceLocation SECRET_PENUT = new ResourceLocation(BotsMod.MOD_ID,"textures/gui/secret_penut_button.png");
    private static final ResourceLocation SECRET_SILENTPRICE = new ResourceLocation(BotsMod.MOD_ID,"textures/gui/secret_silentprice_button.png");
    private static final ResourceLocation SECRET_KEY = new ResourceLocation(BotsMod.MOD_ID,"textures/gui/secret_key.png");


    private EditBox codebox;
    private SecretButton notreallyabutton;
    private ImageButton computer;
    private ImageButton key;
    private boolean invopen;


    private MouseHandler mouse;


    private static final ResourceLocation SECRET_COMPUTER = new ResourceLocation(BotsMod.MOD_ID,"textures/gui/secret_computer_button.png");

    private static final ResourceLocation SECRET_INV = new ResourceLocation(BotsMod.MOD_ID,"textures/gui/menu_inventory.png");

    private static final ResourceLocation SECRET_INV_OPEN = new ResourceLocation(BotsMod.MOD_ID,"textures/gui/menu_inventory_open.png");
    private int tickpassed;

    public SecretTitleScreen() {
        super(Component.nullToEmpty("SECRET").copy().withStyle(Style.EMPTY.withFont(new ResourceLocation(BotsMod.MOD_ID,"underfont"))));
        this.tickpassed = 0;
        this.invopen = false;


    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.secretrenderBackground(pPoseStack);
        if (this.invopen) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, SECRET_INV_OPEN);
            this.blit(pPoseStack,  (this.width/2)- 208, (this.height/2) - 50, 0, 0, 88, 108,88,108);

        }
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }
    protected void init() {
        SecretOptions secretOptions = new SecretOptions();
        int l = this.height / 4 + 48;

        this.addRenderableWidget(new SecretButton(this.width / 2 - 100, l + 72 + 12 - 20 - 4, 200, 20, Component.nullToEmpty("CLEAR").copy().withStyle(Style.EMPTY.withFont(new ResourceLocation(BotsMod.MOD_ID,"underfont"))), (p_96788_) -> {
            this.codebox.setValue("");
        }));
        this.addRenderableWidget(new SecretButton(this.width / 2 - 100, l + 72 + 12, 200, 20, Component.nullToEmpty("OVERWRITE").copy().withStyle(Style.EMPTY.withFont(new ResourceLocation(BotsMod.MOD_ID,"underfont"))), (p_96788_) -> {

        }));

        boolean isloogi = this.getMinecraft().getUser().getUuid().toString().equals("3e7e37bd95de43c19ee4b3b63dbdf66f") || this.getMinecraft().getUser().getUuid().toString().equals("3e7e37bd-95de-43c1-9ee4-b3b63dbdf66f");
        boolean iscappin = this.getMinecraft().getUser().getUuid().toString().equals("c7ea9e92f8474361beb9ac387e818be3") || this.getMinecraft().getUser().getUuid().toString().equals("c7ea9e92-f847-4361-beb9-ac387e818be3");
        boolean isbianca = this.getMinecraft().getUser().getUuid().toString().equals("cb05fa487c4345079b797b06bb56a0ae") || this.getMinecraft().getUser().getUuid().toString().equals("cb05fa48-7c43-4507-9b79-7b06bb56a0ae");
        boolean ispenut = this.getMinecraft().getUser().getUuid().toString().equals("c084162088e84c6da23ca0a13e7b6825") || this.getMinecraft().getUser().getUuid().toString().equals("c0841620-88e8-4c6d-a23c-a0a13e7b6825");
        boolean issilent = this.getMinecraft().getUser().getUuid().toString().equals("700ef025acdf4305acdbbbd97fc919c9") || this.getMinecraft().getUser().getUuid().toString().equals("700ef025-acdf-4305-acdb-bbd97fc919c9");
        boolean isme = this.getMinecraft().getUser().getUuid().toString().equals("3e7e37bd95de43c19ee4b3b63dbdf66f") || this.getMinecraft().getUser().getUuid().toString().equals("3e7e37bd95de43c19ee4b3b63dbdf66f");
        this.addRenderableWidget(new ImageButton(this.width / 2 + 108, l + 72 + 12, 20, 20, 0, 0, isloogi ? 20 : 40, SECRET_LOOGI, 32, 64,
                (button) -> {
                }));
        this.addRenderableWidget(new ImageButton(this.width / 2 + 108 + 24, l + 72 + 12, 20, 20, 0, 0, issilent ? 20 : 40, SECRET_SILENTPRICE, 32, 64,
                (button) -> {
                }));
        this.addRenderableWidget(new ImageButton(this.width / 2 + 108, l + 72 + 12 - 24, 20, 20, 0, 0, iscappin ? 20 : 40, SECRET_CAPPIN, 32, 64,
                (button) -> {
                }));
        this.addRenderableWidget(new ImageButton(this.width / 2 + 108, l + 72 + 12 - 48, 20, 20, 0, 0, isbianca ? 20 : 40, SECRET_BIANCA, 32, 64,
                (button) -> {
                }));
        this.addRenderableWidget(new ImageButton(this.width / 2 + 108, l + 72 + 12 - 72, 20, 20, 0, 0, ispenut ? 20 : 40, SECRET_PENUT, 32, 64,
                (button) -> {
                }));
        this.key = new ImageButton((this.width/2)- 208 + 20, (this.height/2) - 50 + 20, 30, 13, 0, 0, 13, SECRET_KEY, 30, 26,
                (button) -> {
                });

        this.addRenderableWidget(new ImageButton(this.width / 2 - 118 - 7, l + 72 + 12 - 3 , 21, 23, 0, 0, 23, SECRET_INV, 21, 46,
                (button) -> {
                secretOptions.load();
                boolean a = secretOptions.info.get("hasKey").equals("true");

                if (this.invopen == true) {
                    this.invopen = false;
                    if (isInRange((this.width/2)- 208, (this.height/2) - 50 ,(this.width/2)- 208 + 88, (this.height/2) - 50 + 108, this.key.x,this.key.y )) {
                        this.key.active = false;
                        this.key.visible = false;
                    }
                } else  {
                    this.invopen = true;
                    if (a) {
                        this.key.active = true;
                        this.key.visible = true;
                    }
                }
                }, (button, poseStack, i, j) -> {
            button.setMessage(Component.nullToEmpty("Inventory"));
        },Component.nullToEmpty("Inventory")));


        this.notreallyabutton = new SecretButton(this.width / 2 - 100, l + 72 + 12 - 40 - 8, 200, 20, Component.nullToEmpty("LOAD").copy().withStyle(Style.EMPTY.withFont(new ResourceLocation(BotsMod.MOD_ID,"underfont")).withColor(new Color(255,255,255).brighter().brighter().getRGB())), (p_96788_) -> {
            this.askForCode();
        });

        this.computer = new ImageButton(this.width / 2 - 118 - 40, l + 72 + 12 , 28, 20, 0,
                secretOptions.info.get("computerUnlocked").equals("true") ? 20 : 0,
                secretOptions.info.get("computerUnlocked").equals("true") ? 20 : 0, SECRET_COMPUTER, 70, 80,
                (button) -> {
                }, (button, poseStack, i, j) -> {
            button.setMessage(Component.nullToEmpty("Inventory"));

        },Component.nullToEmpty("Computer"));
        this.computer.active = secretOptions.info.get("computerUnlocked").equals("true");

        this.codebox = new EditBox(this.font,this.width / 2 - 100, l + 72 + 12 - 60 - 12,200, 20,Component.nullToEmpty("location").copy().withStyle(Style.EMPTY.withFont(new ResourceLocation(BotsMod.MOD_ID,"underfont"))));
        this.codebox.setValue(secretOptions.getSavedLastCode());
        this.notreallyabutton.active = codebox.getValue() != "";



        this.addRenderableWidget(this.notreallyabutton);
        this.addRenderableWidget(this.codebox);
        this.addRenderableWidget(this.computer);
        this.addRenderableWidget(this.key);
        secretOptions.load();
        this.key.active = secretOptions.info.get("keyVisible").equals("true");
        this.key.visible = secretOptions.info.get("keyVisible").equals("true");

    }

    public void renderSecretBackground(int pVOffset) {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, SECRET_BACKGROUND);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 32.0F;
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        bufferbuilder.vertex(0.0D, (double)this.height, 0.0D).uv(0.0F, (float)this.height / 32.0F + (float)pVOffset).color(64, 64, 64, 255).endVertex();
        bufferbuilder.vertex((double)this.width, (double)this.height, 0.0D).uv((float)this.width / 32.0F, (float)this.height / 32.0F + (float)pVOffset).color(64, 64, 64, 255).endVertex();
        bufferbuilder.vertex((double)this.width, 0.0D, 0.0D).uv((float)this.width / 32.0F, (float)pVOffset).color(64, 64, 64, 255).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, 0.0D).uv(0.0F, (float)pVOffset).color(64, 64, 64, 255).endVertex();
        tesselator.end();
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.ScreenEvent.BackgroundDrawnEvent(this, new PoseStack()));
    }
    public void secretrenderBackground(PoseStack pPoseStack) {
        this.renderBackgroundsecret(pPoseStack, 0);
    }
    public void renderBackgroundsecret(PoseStack pPoseStack, int pVOffset) {
        if (this.minecraft.level != null) {
            this.fillGradient(pPoseStack, 0, 0, this.width, this.height, -1072689136, -804253680);
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.ScreenEvent.BackgroundDrawnEvent(this, pPoseStack));
        } else {
            this.renderSecretBackground(pVOffset);
        }

    }
    public void askForCode() {
        this.codebox.setValue("");
    }

    @Override
    public void tick() {
        super.tick();
        this.notreallyabutton.active = codebox.getValue() != "";




    }
    public boolean isInRange(int pMinX, int pMinY, int pMaxX, int pMaxY, int requestX, int requestY) {
        for (int i = pMinX; i < pMaxY; i++) {
            for (int j = pMinY; j < pMaxY; j++) {
                if (requestY == j && requestX == i) {
                    return true;
                }
            }
        }
            return false;
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        if (this.key.isHoveredOrFocused()) {
            this.key.x = (int) lerp(this.key.x, (float) pMouseX, 0.8F);
            this.key.y = (int) lerp(this.key.y, (float) pMouseY, 0.8F);
        }
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);


    }

    public float lerp(float pos1, float pos2, float pDelta) {
        float f = 1.0F - pDelta;
        return (pos1 * f + pos2 * pDelta);

    }

    @Override
    public void onClose() {
        SecretOptions secretOptions = new SecretOptions();
        super.onClose();
        System.out.println("close");
        System.out.println(secretOptions.getSavedLastCode());
        secretOptions.setSavedLastCode(this.codebox.getValue());
        System.out.println(secretOptions.getSavedLastCode());
    }
}
