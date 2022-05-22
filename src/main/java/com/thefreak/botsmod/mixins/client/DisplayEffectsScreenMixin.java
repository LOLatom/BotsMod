package com.thefreak.botsmod.mixins.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.thefreak.botsmod.API.EffectRender.IEffectSpecialRenderings;
import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.texture.PotionSpriteUploader;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

@Mixin(DisplayEffectsScreen.class)
public abstract class DisplayEffectsScreenMixin<T extends Container> extends ContainerScreen<T> {
    @Shadow
    protected boolean doRenderEffects;

    public DisplayEffectsScreenMixin(T p_i51091_1_, PlayerInventory p_i51091_2_, ITextComponent p_i51091_3_) {
        super(p_i51091_1_, p_i51091_2_, p_i51091_3_);
    }
    @Inject(method = "renderEffects", cancellable = true, at = @At("HEAD"))
    private void renderEffects(MatrixStack p_238811_1_, CallbackInfo ci) {
        ci.cancel();
        int i = this.leftPos - 124;
        Collection<EffectInstance> collection = this.minecraft.player.getActiveEffects();
        if (!collection.isEmpty()) {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            int j = 33;
            if (collection.size() > 5) {
                j = 132 / (collection.size() - 1);
            }

            Iterable<EffectInstance> iterable = collection.stream().filter(effectInstance -> effectInstance.shouldRender()).sorted().collect(java.util.stream.Collectors.toList());
            this.renderBackgrounds(p_238811_1_, i, j, iterable);
            this.renderIcons(p_238811_1_, i, j, iterable);
            this.renderLabels(p_238811_1_, i, j, iterable);
            for(EffectInstance effectInstance : iterable) {
                Effect effect = effectInstance.getEffect();
                if(effect instanceof IEffectSpecialRenderings) {
                    renderAdditionals(p_238811_1_,i,j,effectInstance, effect);
                }
            }

        }
    }

    private void renderAdditionals(MatrixStack matrixStack, int i, int j, EffectInstance effectInstances, Effect focusedEffect) {
        int TopPos = this.topPos;
        IEffectSpecialRenderings effectSpecialRenderings = (IEffectSpecialRenderings) focusedEffect;
        effectSpecialRenderings.addAdditionalToRender(matrixStack,i,j,effectInstances,focusedEffect).accept(matrixStack,effectInstances,this.minecraft);
    }


    private void renderBackgrounds(MatrixStack p_238810_1_, int p_238810_2_, int p_238810_3_, Iterable<EffectInstance> p_238810_4_) {
        int i = this.topPos;

        for(EffectInstance effectinstance : p_238810_4_) {
            Effect effect = effectinstance.getEffect();
            if(effect instanceof IEffectSpecialRenderings) {
                IEffectSpecialRenderings effectSpecialRenderings = (IEffectSpecialRenderings) effect;
                if(effectSpecialRenderings.hasCustomBackground(effectinstance)) {
                   //SPECIAL
                    this.minecraft.getTextureManager().bind(effectSpecialRenderings.hasCustomBackgroundLocation(effectinstance));
                    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                    this.blit(p_238810_1_, p_238810_2_, i + effectSpecialRenderings.customBackgroundHeightAdded(effectinstance), 0, 0, 140, effectSpecialRenderings.hasCustomBackgroundHeight(effectinstance));
                } else {
                    this.minecraft.getTextureManager().bind(INVENTORY_LOCATION);
                    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                    this.blit(p_238810_1_, p_238810_2_, i + effectSpecialRenderings.customBackgroundHeightAdded(effectinstance), 0, 166, 140, effectSpecialRenderings.hasCustomBackgroundHeight(effectinstance));
                }

            } else {
                this.minecraft.getTextureManager().bind(INVENTORY_LOCATION);
                RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.blit(p_238810_1_, p_238810_2_, i, 0, 166, 140, 32);
            }
            i += p_238810_3_;
        }

    }

    private void renderIcons(MatrixStack p_238812_1_, int p_238812_2_, int p_238812_3_, Iterable<EffectInstance> p_238812_4_) {
        PotionSpriteUploader potionspriteuploader = this.minecraft.getMobEffectTextures();
        int i = this.topPos;

        for(EffectInstance effectinstance : p_238812_4_) {
            Effect effect = effectinstance.getEffect();
            TextureAtlasSprite textureatlassprite = potionspriteuploader.get(effect);
            if (effect instanceof IEffectSpecialRenderings) {
                IEffectSpecialRenderings effectSpecialRenderings = (IEffectSpecialRenderings) effect;
                if(effectSpecialRenderings.showEffectIcon(effectinstance) == false) {
                }else {
                    this.minecraft.getTextureManager().bind(textureatlassprite.atlas().location());
                }
            } else {
                this.minecraft.getTextureManager().bind(textureatlassprite.atlas().location());
            }
            blit(p_238812_1_, p_238812_2_ + 6, i + 7, this.getBlitOffset(), 18, 18, textureatlassprite);
            i += p_238812_3_;
        }

    }

    private void renderLabels(MatrixStack p_238813_1_, int p_238813_2_, int p_238813_3_, Iterable<EffectInstance> p_238813_4_) {
        int i = this.topPos;

        for(EffectInstance effectinstance : p_238813_4_) {
            Effect effect = effectinstance.getEffect();
            if (!effectinstance.shouldRenderInvText()) {
                i += p_238813_3_;
                continue;
            }
            String s = I18n.get(effectinstance.getEffect().getDescriptionId());
            if (effectinstance.getAmplifier() >= 1 && effectinstance.getAmplifier() <= 9) {
                s = s + ' ' + I18n.get("enchantment.level." + (effectinstance.getAmplifier() + 1));
            }
            if (effect instanceof IEffectSpecialRenderings) {
                IEffectSpecialRenderings effectSpecialRenderings = (IEffectSpecialRenderings) effect;
                if (effectSpecialRenderings.showEffectLabelName(effectinstance) == true) {
                    this.font.drawShadow(p_238813_1_, s, (float) (p_238813_2_ + 10 + 18), (float) (i + 6), 16777215);
                }
                String s1 = EffectUtils.formatDuration(effectinstance, 1.0F);
                if (effectSpecialRenderings.showEffectLabelTime(effectinstance) == true) {
                    this.font.drawShadow(p_238813_1_, s1, (float) (p_238813_2_ + 10 + 18), (float) (i + 6 + 10), 8355711);
                }
            } else {
                this.font.drawShadow(p_238813_1_, s, (float) (p_238813_2_ + 10 + 18), (float) (i + 6), 16777215);
                String s1 = EffectUtils.formatDuration(effectinstance, 1.0F);
                this.font.drawShadow(p_238813_1_, s1, (float) (p_238813_2_ + 10 + 18), (float) (i + 6 + 10), 8355711);
            }

            i += p_238813_3_;
        }

    }

}


