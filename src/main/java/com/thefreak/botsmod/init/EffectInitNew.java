package com.thefreak.botsmod.init;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.objects.blocks.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectInitNew {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BotsMod.MOD_ID);

    public static final RegistryObject<MobEffect> EXPLOSIVE_EFFECT = EFFECTS.register("explosive_effect", () -> new ExplosiveEffect(MobEffectCategory.NEUTRAL, 0x0256));

    public static  final RegistryObject<MobEffect> MOGROVE_TOXINS = EFFECTS.register("mogrove_toxins", () -> new MogroveToxinsEffect(MobEffectCategory.HARMFUL, 0x0127));

    public static  final RegistryObject<MobEffect> POSSESION = EFFECTS.register("possesion_effect", () -> new PossesionEffect(MobEffectCategory.HARMFUL, 0xc2b2b0));

    public static  final RegistryObject<MobEffect> DECAYING = EFFECTS.register("decaying_effect", () -> new DecayingEffect(MobEffectCategory.HARMFUL, 0x9a655a));

    public static  final RegistryObject<MobEffect> WATCHED_UPON = EFFECTS.register("watched_upon_effect", () -> new WatchedUpon(MobEffectCategory.NEUTRAL, 0x44ca54));

}
