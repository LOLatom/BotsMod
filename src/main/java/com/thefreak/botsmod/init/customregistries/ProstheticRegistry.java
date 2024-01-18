package com.thefreak.botsmod.init.customregistries;

import com.thefreak.botsmod.objects.customobjects.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;


@OnlyIn(Dist.CLIENT)
public class ProstheticRegistry {
    public static final HashMap<String ,ProstheticRender> PROSTHETIC_RENDERERS = new HashMap<>();

    public static void setupRegistries() {
        register("mechanicalArm", new MechanicalArmRender());
        register("regularPiston", new RegularPistonComponentRender());
        register("upperOuterSteelPlate", new UpperOuterSteelPlateRender());
        register("lowerOuterSteelPlate", new LowerOuterSteelPlateRender());
        register("moduleAttachmentComponent", new ModuleAttachmentComponentRender());
        register("longBladeModule", new LongBladeModuleRender());



    }

    public static void register(String id, ProstheticRender render) {
        PROSTHETIC_RENDERERS.put(id,render);
    }
}
