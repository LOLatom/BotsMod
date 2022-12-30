package com.thefreak.botsmod.objects.keys;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ClientRegistry;

@OnlyIn(Dist.CLIENT)
public final class KeyInitiation {
    private KeyInitiation() {

    }

    public static KeyMapping botskeyMapping;
    public static KeyMapping F1;
    public static KeyMapping F2;
    public static KeyMapping F3;
    public static KeyMapping F4;
    public static KeyMapping S1;
    public static KeyMapping FASTS;


    public static void init() {
        botskeyMapping = register("divine", KeyMapping.CATEGORY_MOVEMENT, InputConstants.KEY_J);
        F1 = register("first_finger_motion",KeyMapping.CATEGORY_MOVEMENT, InputConstants.KEY_1);
        F2 = register("second_finger_motion",KeyMapping.CATEGORY_MOVEMENT, InputConstants.KEY_2);
        F3 = register("third_finger_motion",KeyMapping.CATEGORY_MOVEMENT, InputConstants.KEY_3);
        F4 = register("fourth_finger_motion",KeyMapping.CATEGORY_MOVEMENT, InputConstants.KEY_4);
        S1 = register("special",KeyMapping.CATEGORY_MOVEMENT, InputConstants.KEY_B);
        FASTS = register("fast_spell",KeyMapping.CATEGORY_MOVEMENT, InputConstants.KEY_V);
    }

    private static KeyMapping register(String name,String category , int keycode) {
            final var key = new KeyMapping("key.botsmod" + "." + name, keycode, category);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }


}
