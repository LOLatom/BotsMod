package com.thefreak.botsmod.API.EffectRender;


import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.apache.logging.log4j.util.BiConsumer;

public interface IEffectSpecialRenderings {
    //Set to true if the potion effect has a custom background
    default boolean hasCustomBackground(MobEffectInstance MobEffectInstance)
    {return false;}

    //Set the location of the custom background if true
    default ResourceLocation hasCustomBackgroundLocation(MobEffectInstance MobEffectInstance)
    {return new ResourceLocation("textures/gui/container/inventory.png");}

    //Set the custom height of the background texture
    default int hasCustomBackgroundHeight(MobEffectInstance MobEffectInstance)
    {return 32;}

    //Set the value added to the top value of the background effect
    default int customBackgroundHeightAdded(MobEffectInstance MobEffectInstance)
    {return 0;}

    //Toggle !Show Effect Label in inventory
    default boolean showEffectLabelName(MobEffectInstance MobEffectInstance) {return true;}

    //Toggle !Show Effect Time in inventory
    default boolean showEffectLabelTime(MobEffectInstance MobEffectInstance) {return true;}

    //Toggle !Show Effect Icon
    default boolean showEffectIcon(MobEffectInstance MobEffectInstance) {return true;}

    default BiConsumer<PoseStack, MobEffectInstance> addAdditionalToRender(PoseStack matrixStack, int i, int j, MobEffectInstance MobEffectInstances, MobEffect focusedEffect) {
        return (matrixStack1,MobEffectInstance) ->{};
    }

    default boolean hasCustomIconBackground(MobEffectInstance MobEffectInstance)
    {return false;}

    default ResourceLocation hasCustomIconBackgroundLocation(MobEffectInstance MobEffectInstance)
    {return new ResourceLocation("textures/gui/container/inventory.png");}

    default int durationBring(MobEffectInstance MobEffectInstance) {
        return 0;
    }

}
