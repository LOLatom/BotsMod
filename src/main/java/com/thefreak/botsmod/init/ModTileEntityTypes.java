package com.thefreak.botsmod.init;

import com.thefreak.botsmod.BotsMod;

import com.thefreak.botsmod.tileentity.CenoGoblinAnvilTileEntity;
import com.thefreak.botsmod.tileentity.PostMortalAltarTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {
	
	public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(
			ForgeRegistries.BLOCK_ENTITIES, BotsMod.MOD_ID);

	public static final RegistryObject<BlockEntityType<CenoGoblinAnvilTileEntity>> CENO_GOBLIN_ANVIL_TILE_ENTITY = TILE_ENTITY_TYPES.register("ceno_goblin_anvil_tile_entity", () -> BlockEntityType.Builder.of(CenoGoblinAnvilTileEntity::new, BlockInitNew.CENO_GOBLIN_ANVIL.get()).build(null));

	public static final RegistryObject<BlockEntityType<PostMortalAltarTileEntity>> POST_MORTAL_ALTAR_TILE_ENTITY = TILE_ENTITY_TYPES.register("post_mortal_altar_tile_entity", () -> BlockEntityType.Builder.of(PostMortalAltarTileEntity::new, BlockInitNew.POST_MORTAL_ALTAR.get()).build(null));

}

