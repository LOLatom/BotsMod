package com.thefreak.botsmod.init;

import com.thefreak.botsmod.BotsMod;

import com.thefreak.botsmod.tileentity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModTileEntityTypes {
	
	public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(
			ForgeRegistries.BLOCK_ENTITIES, BotsMod.MOD_ID);

	public static final RegistryObject<BlockEntityType<CenoGoblinAnvilTileEntity>> CENO_GOBLIN_ANVIL_TILE_ENTITY = TILE_ENTITY_TYPES.register("ceno_goblin_anvil", () -> BlockEntityType.Builder.of(CenoGoblinAnvilTileEntity::new, BlockInitNew.CENO_GOBLIN_ANVIL.get()).build(null));

	public static final RegistryObject<BlockEntityType<PostMortalAltarTileEntity>> POST_MORTAL_ALTAR_TILE_ENTITY = TILE_ENTITY_TYPES
			.register("post_mortal_altar", () -> BlockEntityType.Builder
					.of(PostMortalAltarTileEntity::new, BlockInitNew.POST_MORTAL_ALTAR.get()).build(null));

	public static final RegistryObject<BlockEntityType<HeatAccumulatorTileEntity>> HEAT_ACCUMULATOR_TILE_ENTITY = TILE_ENTITY_TYPES
			.register("heat_accumulator", () -> BlockEntityType.Builder
					.of(HeatAccumulatorTileEntity::new, BlockInitNew.HEAT_ACCUMULATOR.get()).build(null));

	public static final RegistryObject<BlockEntityType<HeatCapacitorTileEntity>> HEAT_CAPACITOR_TILE_ENTITY = TILE_ENTITY_TYPES
			.register("heat_capacitor", () -> BlockEntityType.Builder
					.of(HeatCapacitorTileEntity::new, BlockInitNew.HEAT_CAPACITOR.get()).build(null));

	public static final RegistryObject<BlockEntityType<CookingPotTileEntity>> COOKING_POT_TILE_ENTITY = TILE_ENTITY_TYPES
			.register("cooking_pot", () -> BlockEntityType.Builder
					.of(CookingPotTileEntity::new, BlockInitNew.COOKING_POT.get()).build(null));
}

