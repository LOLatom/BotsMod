package com.thefreak.botsmod.init;

import com.thefreak.botsmod.BotsMod;

import com.thefreak.botsmod.entities.*;
import com.thefreak.botsmod.entities.Projectile.SaltedArrow;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
	
	public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, BotsMod.MOD_ID);

	public static final RegistryObject<EntityType<GiantTardigradeEntity>> GIANT_TARDIGRADE = ENTITY.register("giant_tardigrade", () ->
			EntityType.Builder.<GiantTardigradeEntity>of(GiantTardigradeEntity::new, EntityClassification.WATER_AMBIENT).sized(4.5F,4F).build(new ResourceLocation(BotsMod.MOD_ID,"giant_tardigrade").toString()));

	public static final RegistryObject<EntityType<WanderingSpecterEntity>> WANDERING_SPECTER = ENTITY.register("wandering_specter", () ->
			EntityType.Builder.<WanderingSpecterEntity>of(WanderingSpecterEntity::new, EntityClassification.MONSTER).sized(0.8F,2.1F).build(new ResourceLocation(BotsMod.MOD_ID,"wandering_specter").toString()));

	public static final RegistryObject<EntityType<LadybugEntity>> LADYBUG = ENTITY.register("ladybug", () ->
			EntityType.Builder.<LadybugEntity>of(LadybugEntity::new, EntityClassification.AMBIENT).sized(0.4F,0.4F).build(new ResourceLocation(BotsMod.MOD_ID,"ladybug").toString()));

	public static final RegistryObject<EntityType<TippyLizardEntity>> TIPPY_LIZARD = ENTITY.register("tippy_lizard", () ->
			EntityType.Builder.<TippyLizardEntity>of(TippyLizardEntity::new, EntityClassification.AMBIENT).sized(1F,0.45F).build(new ResourceLocation(BotsMod.MOD_ID,"tippy_lizard").toString()));

	public static final RegistryObject<EntityType<PuffWormEntity>> PUFF_WORM = ENTITY.register("puff_worm", () ->
			EntityType.Builder.<PuffWormEntity>of(PuffWormEntity::new, EntityClassification.AMBIENT).sized(1F,1F).build(new ResourceLocation(BotsMod.MOD_ID,"puff_worm").toString()));

	public static final RegistryObject<EntityType<DrainedEntity>> DRAINED = ENTITY.register("drained", () ->
			EntityType.Builder.<DrainedEntity>of(DrainedEntity::new, EntityClassification.AMBIENT).sized(0.8F,2.2F).build(new ResourceLocation(BotsMod.MOD_ID,"drained").toString()));

	public static final RegistryObject<EntityType<DrainedChiefEntity>> DRAINED_CHIEF = ENTITY.register("drained_chief", () ->
			EntityType.Builder.<DrainedChiefEntity>of(DrainedChiefEntity::new, EntityClassification.AMBIENT).sized(2F,2.9F).build(new ResourceLocation(BotsMod.MOD_ID,"drained_chief").toString()));

	//MISC_ITEMS
	public static final RegistryObject<EntityType<PinkPurifiedSaltItemEntity>> PINK_PURIFIED_SALT_ITEM_ENTITY = ENTITY.register("pink_purified_salt_item_entity", () ->
			EntityType.Builder.<PinkPurifiedSaltItemEntity>of(PinkPurifiedSaltItemEntity::new, EntityClassification.MISC).sized(0.1F,0.1F).build(new ResourceLocation(BotsMod.MOD_ID,"pink_purified_salt_item_entity").toString()));

	//MISC_SPECIAL

	public static final RegistryObject<EntityType<BansheeScreamEntity>> BANSHEE_SCREAM = ENTITY.register("banshee_scream", () ->
			EntityType.Builder.<BansheeScreamEntity>of(BansheeScreamEntity::new, EntityClassification.MISC).sized(0.3F,0.3F).build(new ResourceLocation(BotsMod.MOD_ID,"banshee_scream").toString()));

	public static final RegistryObject<EntityType<SaltedArrow>> SALTED_ARROW = ENTITY.register("salted_arrow", () ->
			EntityType.Builder.<SaltedArrow>of(SaltedArrow::new, EntityClassification.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(BotsMod.MOD_ID,"salted_arrow").toString()));

}
