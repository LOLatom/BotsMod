package com.thefreak.botsmod.mixins.client;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.gui.NewChatGui;
import net.minecraft.client.gui.SpectatorGui;
import net.minecraft.client.gui.chat.IChatListener;
import net.minecraft.client.gui.overlay.BossOverlayGui;
import net.minecraft.client.gui.overlay.DebugOverlayGui;
import net.minecraft.client.gui.overlay.PlayerTabOverlayGui;
import net.minecraft.client.gui.overlay.SubtitleOverlayGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.texture.PotionSpriteUploader;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Mixin(IngameGui.class)
public abstract class IngameGuiMixin extends AbstractGui {
    @Shadow
    @Final
    protected static ResourceLocation VIGNETTE_LOCATION = new ResourceLocation("textures/misc/vignette.png");
    @Shadow
    @Final
    protected static ResourceLocation WIDGETS_LOCATION = new ResourceLocation("textures/gui/widgets.png");
    @Shadow
    @Final
    protected static ResourceLocation PUMPKIN_BLUR_LOCATION = new ResourceLocation("textures/misc/pumpkinblur.png");
    @Shadow
    @Final
    private static ITextComponent DEMO_EXPIRED_TEXT = new TranslationTextComponent("demo.demoExpired");
    @Shadow
    @Final
    protected final Random random = new Random();
    @Shadow
    @Final
    protected Minecraft minecraft;
    @Shadow
    @Final
    protected ItemRenderer itemRenderer;
    @Shadow
    @Final
    protected NewChatGui chat;
    @Shadow
    protected int tickCount;
    @Nullable
    @Shadow
    protected ITextComponent overlayMessageString;
    @Shadow
    protected int overlayMessageTime;
    @Shadow
    protected boolean animateOverlayMessageColor;
    @Shadow
    public float vignetteBrightness = 1.0F;
    @Shadow
    protected int toolHighlightTimer;
    @Shadow
    protected ItemStack lastToolHighlight = ItemStack.EMPTY;
    @Shadow
    @Final
    protected DebugOverlayGui debugScreen;
    @Shadow
    @Final
    protected SubtitleOverlayGui subtitleOverlay;
    @Shadow
    @Final
    protected SpectatorGui spectatorGui;
    @Shadow
    @Final
    protected PlayerTabOverlayGui tabList;
    @Shadow
    @Final
    protected BossOverlayGui bossOverlay;
    @Shadow
    protected int titleTime;

    @Nullable
    @Shadow
    protected ITextComponent title;

    @Nullable
    @Shadow
    protected ITextComponent subtitle;
    @Shadow
    protected int titleFadeInTime;
    @Shadow
    protected int titleStayTime;
    @Shadow
    protected int titleFadeOutTime;
    @Shadow
    protected int lastHealth;
    @Shadow
    protected int displayHealth;
    @Shadow
    protected long lastHealthTime;
    @Shadow
    protected long healthBlinkTime;
    @Shadow
    protected int screenWidth;
    @Shadow
    protected int screenHeight;
    @Shadow
    @Final
    protected Map<ChatType, List<IChatListener>> chatListeners = Maps.newHashMap();

    @Inject(method = "renderEffects", cancellable = true, at = @At("HEAD"))
    protected void _renderEffects(MatrixStack p_238444_1_, CallbackInfo ci) {
        ci.cancel();
        Collection<EffectInstance> collection = this.minecraft.player.getActiveEffects();
        if (!collection.isEmpty()) {
            RenderSystem.enableBlend();
            int i = 0;
            int j = 0;
            PotionSpriteUploader potionspriteuploader = this.minecraft.getMobEffectTextures();
            List<Runnable> list = Lists.newArrayListWithExpectedSize(collection.size());
            this.minecraft.getTextureManager().bind(ContainerScreen.INVENTORY_LOCATION);

            for(EffectInstance effectinstance : Ordering.natural().reverse().sortedCopy(collection)) {
                Effect effect = effectinstance.getEffect();
                if (!effectinstance.shouldRenderHUD()) continue;
                // Rebind in case previous renderHUDEffect changed texture
                this.minecraft.getTextureManager().bind(ContainerScreen.INVENTORY_LOCATION);
                if (effectinstance.showIcon()) {
                    int k = this.screenWidth;
                    int l = 1;
                    if (this.minecraft.isDemo()) {
                        l += 15;
                    }

                    if (effect.isBeneficial()) {
                        ++i;
                        k = k - 25 * i;
                    } else {
                        ++j;
                        k = k - 25 * j;
                        l += 26;
                    }

                    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                    float f = 1.0F;
                    if (effectinstance.isAmbient()) {
                        this.blit(p_238444_1_, k, l, 165, 166, 24, 24);
                    } else {
                        this.blit(p_238444_1_, k, l, 141, 166, 24, 24);
                        if (effectinstance.getDuration() <= 200) {
                            int i1 = 10 - effectinstance.getDuration() / 20;
                            f = MathHelper.clamp((float)effectinstance.getDuration() / 10.0F / 5.0F * 0.5F, 0.0F, 0.5F) + MathHelper.cos((float)effectinstance.getDuration() * (float)Math.PI / 5.0F) * MathHelper.clamp((float)i1 / 10.0F * 0.25F, 0.0F, 0.25F);
                        }
                    }

                    TextureAtlasSprite textureatlassprite = potionspriteuploader.get(effect);
                    int j1 = k;
                    int k1 = l;
                    float f1 = f;
                    list.add(() -> {
                        this.minecraft.getTextureManager().bind(textureatlassprite.atlas().location());
                        RenderSystem.color4f(1.0F, 1.0F, 1.0F, f1);
                        blit(p_238444_1_, j1 + 3, k1 + 3, this.getBlitOffset(), 18, 18, textureatlassprite);
                    });
                    effectinstance.renderHUDEffect(this, p_238444_1_, k, l, this.getBlitOffset(), f);
                }
            }

            list.forEach(Runnable::run);
        }
    }
}
