package com.thefreak.botsmod.particles;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.particles.object.GoldenSpecterParticle;
import com.thefreak.botsmod.particles.object.SadFaceParticle;
import com.thefreak.botsmod.particles.object.SteamRingParticle;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterParticleTypeEvent {


    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticleType.GOLDEN_SPECTER_PARTICLE.get(), GoldenSpecterParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticleType.SAD_FACE_PARTICLE.get(), SadFaceParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticleType.STEAM_RING_PARTICLE.get(), SteamRingParticle.Provider::new);
    }
}
