package com.thefreak.botsmod.init;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.objects.effect.DecayingEffect;
import com.thefreak.botsmod.objects.effect.ExplosiveEffect;
import com.thefreak.botsmod.objects.effect.MogroveToxinsEffect;
import com.thefreak.botsmod.objects.effect.PossesionEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.Potion;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectInitNew {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BotsMod.MOD_ID);

    public static final RegistryObject<MobEffect> EXPLOSIVE_EFFECT = EFFECTS.register("explosive_effect", () -> new ExplosiveEffect(MobEffectCategory.NEUTRAL, 0x0256));

    public static  final RegistryObject<MobEffect> MOGROVE_TOXINS = EFFECTS.register("mogrove_toxins", () -> new MogroveToxinsEffect(MobEffectCategory.HARMFUL, 0x0127));

    public static  final RegistryObject<MobEffect> POSSESION = EFFECTS.register("possesion_effect", () -> new PossesionEffect(MobEffectCategory.HARMFUL, 0xc2b2b0));

    public static  final RegistryObject<MobEffect> DECAYING = EFFECTS.register("decaying_effect", () -> new DecayingEffect(MobEffectCategory.HARMFUL, 0x9a655a));
}
