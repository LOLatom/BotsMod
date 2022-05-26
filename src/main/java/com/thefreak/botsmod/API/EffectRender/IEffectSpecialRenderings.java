package com.thefreak.botsmod.API.EffectRender;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.util.BiConsumer;
import org.apache.logging.log4j.util.TriConsumer;

public interface IEffectSpecialRenderings {
    //Set to true if the potion effect has a custom background
    default boolean hasCustomBackground(EffectInstance effectInstance)
    {return false;}

    //Set the location of the custom background if true
    default ResourceLocation hasCustomBackgroundLocation(EffectInstance effectInstance)
    {return new ResourceLocation("textures/gui/container/inventory.png");}

    //Set the custom height of the background texture
    default int hasCustomBackgroundHeight(EffectInstance effectInstance)
    {return 32;}

    //Set the value added to the top value of the background effect
    default int customBackgroundHeightAdded(EffectInstance effectInstance)
    {return 0;}

    //Toggle !Show Effect Label in inventory
    default boolean showEffectLabelName(EffectInstance effectInstance) {return true;}

    //Toggle !Show Effect Time in inventory
    default boolean showEffectLabelTime(EffectInstance effectInstance) {return true;}

    //Toggle !Show Effect Icon
    default boolean showEffectIcon(EffectInstance effectInstance) {return true;}

    default TriConsumer<MatrixStack, EffectInstance, Minecraft> addAdditionalToRender(MatrixStack matrixStack, int i, int j, EffectInstance effectInstances, Effect focusedEffect) {
        return (matrixStack1,effectInstance, minecraft) ->{};
    }

    default boolean hasCustomIconBackground(EffectInstance effectInstance)
    {return false;}

    default ResourceLocation hasCustomIconBackgroundLocation(EffectInstance effectInstance)
    {return new ResourceLocation("textures/gui/container/inventory.png");}

    default int durationBring(EffectInstance effectInstance) {
        return 0;
    }

}
