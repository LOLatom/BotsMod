package com.thefreak.botsmod.client.entity;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.client.Rendering.models.*;
import com.thefreak.botsmod.client.entity.model.LightSwordAttackModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BotsModLayerDef {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BotsModLayers.LIGHT_SWORD_ATTACK, LightSwordAttackModel::createBodyLayer);
        event.registerLayerDefinition(BotsModLayers.MECHANICAL_ARM, MechanicalArmProstheticModel::createBodyLayer);
        event.registerLayerDefinition(BotsModLayers.REGULAR_PISTON, JointPistonComponentModel::createBodyLayer);
        event.registerLayerDefinition(BotsModLayers.UPPER_OUTER_STEEL_PLATE, UpperOuterSteelPlateModel::createBodyLayer);
        event.registerLayerDefinition(BotsModLayers.LOWER_OUTER_STEEL_PLATE, LowerOuterSteelPlateModel::createBodyLayer);
        event.registerLayerDefinition(BotsModLayers.MODULE_ATTACHMENT_COMPONENT, ModuleAttachmentComponentModel::createBodyLayer);
        event.registerLayerDefinition(BotsModLayers.LONG_BLADE_MODULE, LongBladeModuleModel::createBodyLayer);



    }


}
