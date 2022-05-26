package com.thefreak.botsmod.init;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.objects.effect.DecayingEffect;
import com.thefreak.botsmod.objects.effect.ExplosiveEffect;
import com.thefreak.botsmod.objects.effect.MogroveToxinsEffect;
import com.thefreak.botsmod.objects.effect.PossesionEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectInitNew {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, BotsMod.MOD_ID);

    public static final RegistryObject<Effect> EXPLOSIVE_EFFECT = EFFECTS.register("explosive_effect", () -> new ExplosiveEffect(EffectType.NEUTRAL, 0x0256));

    public static  final RegistryObject<Effect> MOGROVE_TOXINS = EFFECTS.register("mogrove_toxins", () -> new MogroveToxinsEffect(EffectType.HARMFUL, 0x0127));

    public static  final RegistryObject<Effect> POSSESION = EFFECTS.register("possesion_effect", () -> new PossesionEffect(EffectType.HARMFUL, 0xc2b2b0));

    public static  final RegistryObject<Effect> DECAYING = EFFECTS.register("decaying_effect", () -> new DecayingEffect(EffectType.HARMFUL, 0x9a655a));
}
