package com.thefreak.botsmod.mixins.client;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.thefreak.botsmod.API.EffectRender.IEffectSpecialRenderings;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.MobEffectTextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Mixin(EffectRenderingInventoryScreen.class)
public abstract class DisplayEffectsScreenMixin<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {

    public DisplayEffectsScreenMixin(T pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }


    @Inject(method = "renderEffects", cancellable = true, at = @At("HEAD"))
    private void renderEffects(PoseStack p_194015_, int p_194016_, int p_194017_, CallbackInfo ci) {
        ci.cancel();
        int i = this.leftPos + this.imageWidth + 2;
        int j = this.width - i;
        Collection<MobEffectInstance> collection = this.minecraft.player.getActiveEffects();
        if (!collection.isEmpty() && j >= 32) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            boolean flag = j >= 120;
            var event = net.minecraftforge.client.ForgeHooksClient.onScreenPotionSize(this);
            if (event != net.minecraftforge.eventbus.api.Event.Result.DEFAULT)
                flag = event == net.minecraftforge.eventbus.api.Event.Result.DENY; // true means classic mode
            int k = 33;
            if (collection.size() > 5) {
                k = 132 / (collection.size() - 1);
            }


            Iterable<MobEffectInstance> iterable = collection.stream().filter(net.minecraftforge.client.ForgeHooksClient::shouldRenderEffect).sorted().collect(java.util.stream.Collectors.toList());
            this.renderBackgrounds(p_194015_, i, k, iterable, flag);
            this.renderIcons(p_194015_, i, k, iterable, flag);
            if (flag) {
                this.renderLabels(p_194015_, i, k, iterable);
            } else if (p_194016_ >= i && p_194016_ <= i + 33) {
                int l = this.topPos;
                MobEffectInstance mobeffectinstance = null;

                for (MobEffectInstance mobeffectinstance1 : iterable) {
                    if (p_194017_ >= l && p_194017_ <= l + k) {
                        mobeffectinstance = mobeffectinstance1;
                    }

                    l += k;
                }

                if (mobeffectinstance != null) {
                    List<Component> list = List.of(this.getEffectName(mobeffectinstance), new TextComponent(MobEffectUtil.formatDuration(mobeffectinstance, 1.0F)));
                    this.renderTooltip(p_194015_, list, Optional.empty(), p_194016_, p_194017_);
                }
            }
            for (MobEffectInstance effectInstance : iterable) {
                MobEffect effect = effectInstance.getEffect();
                if (effect instanceof IEffectSpecialRenderings) {
                    renderAdditionals(p_194015_, i, j, effectInstance, effect);
                }
            }
        }



    }



    private void renderAdditionals(PoseStack matrixStack, int i, int j, MobEffectInstance effectInstances, MobEffect focusedEffect) {
        int TopPos = this.topPos;
        IEffectSpecialRenderings effectSpecialRenderings = (IEffectSpecialRenderings) focusedEffect;
        effectSpecialRenderings.addAdditionalToRender(matrixStack,i,j,effectInstances,focusedEffect).accept(matrixStack,effectInstances,this.minecraft);
    }


    private void renderBackgrounds(PoseStack p_194003_, int p_194004_, int p_194005_, Iterable<MobEffectInstance> p_194006_, boolean p_194007_) {
        int i = this.topPos;

        for(MobEffectInstance mobeffectinstance : p_194006_) {
            MobEffect mobeffect = mobeffectinstance.getEffect();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            if (mobeffect instanceof IEffectSpecialRenderings) {
                IEffectSpecialRenderings effectSpecialRenderings = (IEffectSpecialRenderings) mobeffect;
                if (effectSpecialRenderings.hasCustomBackground(mobeffectinstance)) {
                    RenderSystem.setShaderTexture(0, effectSpecialRenderings.hasCustomIconBackgroundLocation(mobeffectinstance));
                        this.blit(p_194003_, p_194004_, i + effectSpecialRenderings.hasCustomBackgroundHeight(mobeffectinstance), 0, 0, 120, 32);
                    } else {
                    RenderSystem.setShaderTexture(0, INVENTORY_LOCATION);
                    this.blit(p_194003_, p_194004_, i + effectSpecialRenderings.hasCustomBackgroundHeight(mobeffectinstance), 0, 166, 120, 32);


                }
            } else if (p_194007_) {
                RenderSystem.setShaderTexture(0, INVENTORY_LOCATION);
                this.blit(p_194003_, p_194004_, i, 0, 166, 120, 32);
            } else {
                RenderSystem.setShaderTexture(0, INVENTORY_LOCATION);
                this.blit(p_194003_, p_194004_, i, 0, 198, 32, 32);
            }

            i += p_194005_;
        }

    }

    private void renderIcons(PoseStack p_194009_, int p_194010_, int p_194011_, Iterable<MobEffectInstance> p_194012_, boolean p_194013_) {
        MobEffectTextureManager mobeffecttexturemanager = this.minecraft.getMobEffectTextures();
        int i = this.topPos;

        for(MobEffectInstance mobeffectinstance : p_194012_) {
            MobEffect mobeffect = mobeffectinstance.getEffect();
            TextureAtlasSprite textureatlassprite = mobeffecttexturemanager.get(mobeffect);
            RenderSystem.setShaderTexture(0, textureatlassprite.atlas().location());
            blit(p_194009_, p_194010_ + (p_194013_ ? 6 : 7), i + 7, this.getBlitOffset(), 18, 18, textureatlassprite);
            i += p_194011_;
        }

    }

    private void renderLabels(PoseStack pPoseStack, int pRenderX, int pYOffset, Iterable<MobEffectInstance> pEffects) {
        int i = this.topPos;

        for(MobEffectInstance mobeffectinstance : pEffects) {
            MobEffect mobeffect = mobeffectinstance.getEffect();
            net.minecraftforge.client.EffectRenderer renderer = net.minecraftforge.client.RenderProperties.getEffectRenderer(mobeffectinstance);
            if (!renderer.shouldRenderInvText(mobeffectinstance)) {
                i += pYOffset;
                continue;
            }
            Component component = this.getEffectName(mobeffectinstance);
            this.font.drawShadow(pPoseStack, component, (float)(pRenderX + 10 + 18), (float)(i + 6), 16777215);
            String s = MobEffectUtil.formatDuration(mobeffectinstance, 1.0F);
            this.font.drawShadow(pPoseStack, s, (float)(pRenderX + 10 + 18), (float)(i + 6 + 10), 8355711);
            i += pYOffset;
        }

    }

    private Component getEffectName(MobEffectInstance p_194001_) {
        MutableComponent mutablecomponent = p_194001_.getEffect().getDisplayName().copy();
        if (p_194001_.getAmplifier() >= 1 && p_194001_.getAmplifier() <= 9) {
            mutablecomponent.append(" ").append(new TranslatableComponent("enchantment.level." + (p_194001_.getAmplifier() + 1)));
        }

        return mutablecomponent;
    }

}


