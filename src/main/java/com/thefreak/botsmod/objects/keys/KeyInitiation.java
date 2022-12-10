package com.thefreak.botsmod.objects.keys;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

public final class KeyInitiation {
    private KeyInitiation() {

    }

    public static KeyMapping botskeyMapping;

    public static void init() {
        botskeyMapping = register("divine", KeyMapping.CATEGORY_MOVEMENT, InputConstants.KEY_J);
    }

    private static KeyMapping register(String name,String category , int keycode) {
            final var key = new KeyMapping("key.botsmod" + "." + name, keycode, category);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }


}
