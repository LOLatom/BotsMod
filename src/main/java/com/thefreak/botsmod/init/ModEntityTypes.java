package com.thefreak.botsmod.init;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.entities.*;
import com.thefreak.botsmod.entities.misc.ShadowLightningBolt;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
	
	public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, BotsMod.MOD_ID);

	public static final RegistryObject<EntityType<WanderingSpecterEntity>> WANDERING_SPECTER = ENTITY.register("wandering_specter", () ->
			EntityType.Builder.<WanderingSpecterEntity>of(WanderingSpecterEntity::new, MobCategory.MONSTER).sized(0.8F,2.1F).build(new ResourceLocation(BotsMod.MOD_ID,"wandering_specter").toString()));

	public static final RegistryObject<EntityType<LadybugEntity>> LADYBUG = ENTITY.register("ladybug", () ->
			EntityType.Builder.<LadybugEntity>of(LadybugEntity::new, MobCategory.AMBIENT).sized(0.4F,0.4F).build(new ResourceLocation(BotsMod.MOD_ID,"ladybug").toString()));

	public static final RegistryObject<EntityType<TippyLizardEntity>> TIPPY_LIZARD = ENTITY.register("tippy_lizard", () ->
			EntityType.Builder.<TippyLizardEntity>of(TippyLizardEntity::new, MobCategory.AMBIENT).sized(1F,0.45F).build(new ResourceLocation(BotsMod.MOD_ID,"tippy_lizard").toString()));

	public static final RegistryObject<EntityType<PuffWormEntity>> PUFF_WORM = ENTITY.register("puff_worm", () ->
			EntityType.Builder.<PuffWormEntity>of(PuffWormEntity::new, MobCategory.AMBIENT).sized(1F,1F).build(new ResourceLocation(BotsMod.MOD_ID,"puff_worm").toString()));

	public static final RegistryObject<EntityType<DrainedEntity>> DRAINED = ENTITY.register("drained", () ->
			EntityType.Builder.<DrainedEntity>of(DrainedEntity::new, MobCategory.MONSTER).sized(0.8F,2.2F).build(new ResourceLocation(BotsMod.MOD_ID,"drained").toString()));

	public static final RegistryObject<EntityType<DrainedChiefEntity>> DRAINED_CHIEF = ENTITY.register("drained_chief", () ->
			EntityType.Builder.<DrainedChiefEntity>of(DrainedChiefEntity::new, MobCategory.MONSTER).sized(2F,2.9F).build(new ResourceLocation(BotsMod.MOD_ID,"drained_chief").toString()));

	public static final RegistryObject<EntityType<WickedOne>> WICKED_ONE = ENTITY.register("wicked_one", () ->
			EntityType.Builder.<WickedOne>of(WickedOne::new, MobCategory.MONSTER).sized(0.8F,3.9F).build(new ResourceLocation(BotsMod.MOD_ID,"wicked_one").toString()));

	public static final RegistryObject<EntityType<GateKeeper>> GATE_KEEPER = ENTITY.register("gate_keeper", () ->
			EntityType.Builder.<GateKeeper>of(GateKeeper::new, MobCategory.MONSTER).sized(1.2F,3.9F).build(new ResourceLocation(BotsMod.MOD_ID,"gate_keeper").toString()));


	public static final RegistryObject<EntityType<KrasiaEntity>> KRASIA = ENTITY.register("krasia", () ->
			EntityType.Builder.<KrasiaEntity>of(KrasiaEntity::new, MobCategory.MISC).sized(1.5F,3.9F).build(new ResourceLocation(BotsMod.MOD_ID,"krasia").toString()));

	public static final RegistryObject<EntityType<CaveAngeliaGuppie>> CAVE_ANGELIA_GUPPIE = ENTITY.register("cave_angelia_guppie", () ->
			EntityType.Builder.<CaveAngeliaGuppie>of(CaveAngeliaGuppie::new, MobCategory.WATER_AMBIENT).sized(1F,0.5F).build(new ResourceLocation(BotsMod.MOD_ID,"cave_angelia_guppie").toString()));

	public static final RegistryObject<EntityType<ShadowLightningBolt>> SHADOW_LIGHTNING_BOLT = ENTITY.register("shadow_lightning_bolt", () ->
			EntityType.Builder.<ShadowLightningBolt>of(ShadowLightningBolt::new, MobCategory.MISC).sized(0F,0F).build(new ResourceLocation(BotsMod.MOD_ID,"shadow_lightning_bolt").toString()));

}
