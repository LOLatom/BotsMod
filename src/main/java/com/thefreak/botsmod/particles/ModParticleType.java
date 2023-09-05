package com.thefreak.botsmod.particles;

import com.thefreak.botsmod.BotsMod;
import net.minecraft.client.particle.RisingParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class ModParticleType {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BotsMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> GOLDEN_SPECTER_PARTICLE = PARTICLE_TYPES.register("golden_specter", () -> new SimpleParticleType(true));
}
