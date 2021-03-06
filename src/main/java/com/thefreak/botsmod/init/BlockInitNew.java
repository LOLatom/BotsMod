package com.thefreak.botsmod.init;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.objects.blockpackage.ConnectedTexturePillar;
import com.thefreak.botsmod.objects.blockpackage.FallingBushBlock;
import com.thefreak.botsmod.objects.blockpackage.SustainablePlantType;
import com.thefreak.botsmod.objects.blocks.*;

import com.thefreak.botsmod.util.ModdedMaterials;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.audio.Sound;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.rmi.registry.Registry;

public class BlockInitNew {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BotsMod.MOD_ID);

	public static final RegistryObject<Block> JADE_BLOCK = BLOCKS.register("jade_block", () -> new Block(Block.Properties.create(Material.IRON).sound(SoundType.STONE).hardnessAndResistance(3F, 6F)));

	public static final RegistryObject<Block> MOGROVE_ROCK = BLOCKS.register("mogrove_rock", () -> new SustainablePlantType(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> DRIPPING_MOGROVE_ROCK = BLOCKS.register("dripping_mogrove_rock", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> MOGROVE_ROCK_NEST = BLOCKS.register("mogrove_rock_nest", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> MOGROVE_ROCK_NEST_NECTAR_FULL = BLOCKS.register("mogrove_rock_nest_nectar_full", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.SLIME).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> MOGROVE_ROCK_NEST_BUILD = BLOCKS.register("mogrove_rock_nest_build", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> MOGROVE_DIRT = BLOCKS.register("mogrove_dirt", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.GROUND).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> MOGROVE_WOOD = BLOCKS.register("mogrove_wood", () -> new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> MOGROVE_STRIPPED_WOOD = BLOCKS.register("mogrove_stripped_wood", () -> new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> MOGROVE_GRASS_BLOCK = BLOCKS.register("mogrove_grass_block", () -> new SustainablePlantType(Block.Properties.create(Material.ROCK).sound(SoundType.SLIME).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> MOGROVE_GRASS_BLOCK_FULL = BLOCKS.register("mogrove_grass_block_full", () -> new SustainablePlantType(Block.Properties.create(Material.ORGANIC).sound(SoundType.SLIME).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> MOGROVE_WOOD_PLANKS = BLOCKS.register("mogrove_wood_planks", () -> new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> MOGROVE_MUSHROOM = BLOCKS.register("mogrove_mushroom", () -> new MogroveRoofMushrooms(Block.Properties.create(Material.ORGANIC).sound(SoundType.WET_GRASS).hardnessAndResistance(0.2F, 0.3F).tickRandomly()));

	public static final RegistryObject<Block> MOGORVE_NUT_MUSHROOM = BLOCKS.register("mogrove_nut_mushroom", () -> new MogroveNutMushroom(Block.Properties.create(Material.ORGANIC).sound(SoundType.WET_GRASS).hardnessAndResistance(0.2F, 0.2F).notSolid().tickRandomly()));

	public static final RegistryObject<Block> MOGROVE_BUBBLE_MUSHROOM = BLOCKS.register("mogrove_bubble_mushroom", () -> new MogroveBubbleMushroom(Block.Properties.create(Material.ORGANIC).sound(SoundType.BAMBOO).hardnessAndResistance(2F, 2F).notSolid().tickRandomly()));

	public static final RegistryObject<Block> SPORE_BULB = BLOCKS.register("spore_bulb", () -> new SporeBulb(Block.Properties.create(Material.ORGANIC).sound(SoundType.CORAL).hardnessAndResistance(2F, 0.5F).notSolid().tickRandomly()));

	public static final RegistryObject<Block> SPORE_PAD = BLOCKS.register("spore_pad", () -> new SporePad(Block.Properties.create(Material.ORGANIC).hardnessAndResistance(0.2F, 0.2F).notSolid()));

	public static final RegistryObject<Block> MOGROVE_BUSH = BLOCKS.register("mogrove_bush", () -> new MogroveBush(Block.Properties.create(Material.ORGANIC).sound(SoundType.PLANT).hardnessAndResistance(0.3F, 0.3F).notSolid().tickRandomly()));

	public static final RegistryObject<Block> RAW_ANCIENT_POTTERY_POT = BLOCKS.register("raw_ancient_pottery_pot", () -> new RawAncientPotteryPot(Block.Properties.create(Material.CLAY).sound(SoundType.STONE).hardnessAndResistance(1.5F, 2F).notSolid().tickRandomly()));

	public static final RegistryObject<Block> ANCIENT_POTTERY_POT = BLOCKS.register("ancient_pottery_pot", () -> new RawAncientPotteryPot(Block.Properties.create(Material.CLAY).sound(SoundType.GLASS).hardnessAndResistance(1.5F, 2F).notSolid()));

	public static final RegistryObject<Block> RAW_ANCIENT_POTTERY_URN = BLOCKS.register("raw_ancient_pottery_urn", () -> new AncientUrnRaw(Block.Properties.create(Material.CLAY).sound(SoundType.STONE).hardnessAndResistance(1.5F, 2F).notSolid().tickRandomly()));

	public static final RegistryObject<Block> ANCIENT_POTTERY_URN = BLOCKS.register("ancient_pottery_urn", () -> new AncientUrnCooked(Block.Properties.create(Material.CLAY).sound(SoundType.GLASS).hardnessAndResistance(1.5F, 2F).notSolid()));

	public static final RegistryObject<Block> ANCIENT_BRICKS = BLOCKS.register("ancient_bricks", () -> new Block(Block.Properties.create(Material.CLAY).sound(SoundType.STONE).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> ANCIENT_PILLAR = BLOCKS.register("ancient_pillar", () -> new RotatedPillarBlock(Block.Properties.create(Material.CLAY).sound(SoundType.STONE).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> CRACKED_ANCIENT_BRICKS = BLOCKS.register("cracked_ancient_bricks", () -> new Block(Block.Properties.create(Material.CLAY).sound(SoundType.STONE).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> MOSSY_ANCIENT_BRICKS = BLOCKS.register("mossy_ancient_bricks", () -> new Block(Block.Properties.create(Material.CLAY).sound(SoundType.STONE).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> TRAPPED_ANCIENT_BRICKS = BLOCKS.register("trapped_ancient_bricks", () -> new TrappedAncientBricks(Block.Properties.create(Material.CLAY).sound(SoundType.STONE).hardnessAndResistance(2F, 4F).tickRandomly()));

	public static final RegistryObject<Block> ANCIENT_SMOOTH_BRICKS = BLOCKS.register("ancient_smooth_bricks", () -> new Block(Block.Properties.create(Material.CLAY).sound(SoundType.STONE).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> ANCIENT_SMOOTH_PILLAR = BLOCKS.register("ancient_smooth_pillar", () -> new RotatedPillarBlock(Block.Properties.create(Material.CLAY).sound(SoundType.STONE).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> ANCIENT_CHISELED_SMOOTH_BRICKS = BLOCKS.register("ancient_chiseled_smooth_bricks", () -> new Block(Block.Properties.create(Material.CLAY).sound(SoundType.STONE).hardnessAndResistance(2F, 4F)));

	public static final RegistryObject<Block> BRONZE_ALLOY_CHUNK = BLOCKS.register("bronze_alloy_chunk", () -> new Block(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.5F, 5.5F)));

	public static final RegistryObject<Block> BRONZE_ALLOY_BRICKS = BLOCKS.register("bronze_alloy_bricks", () -> new Block(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(4.5F, 7.5F)));

	public static final RegistryObject<Block> BRONZE_ALLOY_CHISELED_BRICKS = BLOCKS.register("bronze_alloy_chiseled_bricks", () -> new Block(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(4.5F, 7.5F)));

	public static final RegistryObject<Block> BRONZE_ALLOY_BRICKS_HORIZONTAL = BLOCKS.register("bronze_alloy_bricks_horizontal", () -> new Block(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(4.5F, 7.5F)));

	public static final RegistryObject<Block> BRONZE_ALLOY_BLOCK = BLOCKS.register("bronze_alloy_block", () -> new Block(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(4.5F, 7.5F)));

	public static final RegistryObject<Block> BRONZE_ALLOY_CRYSTAL = BLOCKS.register("bronze_alloy_crystal", () -> new BronzeAlloyCrystal(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(4.5F, 7.5F).tickRandomly()));

	public static final RegistryObject<Block> BRONZE_ALLOY_CHUNK_CRACKED = BLOCKS.register("bronze_alloy_chunk_cracked", () -> new Block(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.5F, 5.5F).tickRandomly()));

	public static final RegistryObject<Block> LAVA_ROCK_ALLOY = BLOCKS.register("lava_rock_alloy", () -> new LavaRockAlloy(Block.Properties.create(Material.ROCK).sound(SoundType.BASALT).hardnessAndResistance(1.5F, 0.5F)));

	public static final RegistryObject<Block> LAVA_ROCK_ALLOY_BRICKS = BLOCKS.register("lava_rock_alloy_bricks", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.BASALT).hardnessAndResistance(2.5F, 2F)));

	public static final RegistryObject<Block> LAVA_ROCK_ALLOY_PILLAR = BLOCKS.register("lava_rock_alloy_pillar", () -> new RotatedPillarBlock(Block.Properties.create(Material.ROCK).sound(SoundType.BASALT).hardnessAndResistance(1.5F, 0.5F)));

	public static final RegistryObject<Block> COAL_LAVA_ROCK_ALLOY = BLOCKS.register("coal_lava_rock_alloy", () -> new CoalLavaRockAlloy(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.BASALT).hardnessAndResistance(1.7F, 0.6F).tickRandomly()));

	public static final RegistryObject<Block> POLISHED_LAVA_ROCK_ALLOY = BLOCKS.register("polished_lava_rock_alloy", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.BASALT).hardnessAndResistance(1.7F, 0.6F).tickRandomly()));

	public static final RegistryObject<Block> CHISELED_LAVA_ROCK_ALLOY = BLOCKS.register("chiseled_lava_rock_alloy", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.BASALT).hardnessAndResistance(1.7F, 0.6F).tickRandomly()));

	public static final RegistryObject<Block> CHISELED_LAVA_ROCK_ALLOY_BRICKS = BLOCKS.register("chiseled_lava_rock_alloy_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.BASALT).hardnessAndResistance(1.7F, 0.6F)));

	public static final RegistryObject<Block> CHISELED_POLISHED_LAVA_ROCK_ALLOY = BLOCKS.register("chiseled_polished_lava_rock_alloy", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.BASALT).hardnessAndResistance(1.7F, 0.6F)));

	public static final RegistryObject<Block> POLISHED_LAVA_ROCK_ALLOY_PILLAR = BLOCKS.register("polished_lava_rock_alloy_pillar", () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.BASALT).hardnessAndResistance(1.7F, 0.6F)));

	public static final RegistryObject<Block> LAVA_ROCK_ALLOY_DIRT = BLOCKS.register("lava_rock_alloy_dirt", () -> new LavaRockAlloyDirt(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.SAND).hardnessAndResistance(1F, 0.6F)));

	public static final RegistryObject<Block> COAL_LAVA_ROCK_ALLOY_DIRT = BLOCKS.register("coal_lava_rock_alloy_dirt", () -> new CoalLavaRockAlloyDirt(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.SAND).hardnessAndResistance(1F, 0.6F)));

	public static final RegistryObject<Block> COAL_LAVA_ROCK_ALLOY_PILLAR = BLOCKS.register("coal_lava_rock_alloy_pillar", () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.BASALT).hardnessAndResistance(1F, 0.6F)));

	public static final RegistryObject<Block> SPORIAN_MANGROVE_TREE_LOG = BLOCKS.register("sporian_mangrove_tree_log", () -> new SporianMangroveTreeLog(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)));

	public static final RegistryObject<Block> STRIPPED_SPORIAN_MANGROVE_TREE_LOG = BLOCKS.register("stripped_sporian_mangrove_tree_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)));

	public static final RegistryObject<Block> SPORIAN_MANGROVE_TREE_PLANKS = BLOCKS.register("sporian_mangrove_tree_planks", () -> new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F).tickRandomly()));

	public static final RegistryObject<Block> SPORIAN_SPIKY_LONGUS = BLOCKS.register("sporian_spiky_longus", () -> new SporianSpikyLongusTop(AbstractBlock.Properties.create(Material.ORGANIC).sound(SoundType.PLANT).doesNotBlockMovement().hardnessAndResistance(0F, 0F).tickRandomly()));

	public static final RegistryObject<Block> SPORIAN_SPIKY_LONGUS_PLANT = BLOCKS.register("sporian_spiky_longus_plant", () -> new SporianSpikyLongusPlant(AbstractBlock.Properties.create(Material.ORGANIC).sound(SoundType.PLANT).doesNotBlockMovement().hardnessAndResistance(0F, 0F).tickRandomly()));

	public static final RegistryObject<Block> SPORIAN_DANDELION = BLOCKS.register("sporian_dandelion", () -> new SporianDandelion(AbstractBlock.Properties.create(Material.ORGANIC).sound(SoundType.PLANT).doesNotBlockMovement().hardnessAndResistance(0F, 0F).tickRandomly()));

	public static final RegistryObject<Block> SPORIAN_MOSS_GRASS = BLOCKS.register("sporian_moss_grass", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.BASALT).hardnessAndResistance(1.7F, 0.6F).tickRandomly()));

	public static final RegistryObject<Block> SPORIAN_MANGROVE_BULB = BLOCKS.register("sporian_mangrove_bulb", () -> new SporianMangroveBulb(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.BASALT).hardnessAndResistance(1.7F, 0.6F).tickRandomly()));

	public static final RegistryObject<Block> SPORIAN_MANGROVE_TREE_ROOT = BLOCKS.register("sporian_mangrove_tree_root", () -> new SporianMangroveTreeRoot(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(1.7F, 0.6F).tickRandomly()));

	public static final RegistryObject<Block> SPORIAN_MANGROVE_TREE_ROOT_TOP = BLOCKS.register("sporian_mangrove_tree_root_top", () -> new SporianMangroveTreeRootTop(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(1.7F, 0.6F).tickRandomly()));

	public static final RegistryObject<Block> SPORIAN_MANGROVE_TREE_VINES_TOP = BLOCKS.register("sporian_mangrove_tree_vines_top", () -> new SporianMangroveTreeVinesTop(AbstractBlock.Properties.create(Material.ORGANIC).sound(SoundType.PLANT).doesNotBlockMovement().hardnessAndResistance(0F, 0F).tickRandomly()));

	public static final RegistryObject<Block> SPORIAN_MANGROVE_TREE_VINES = BLOCKS.register("sporian_mangrove_tree_vines", () -> new SporianMangroveTreeVines(AbstractBlock.Properties.create(Material.ORGANIC).sound(SoundType.PLANT).doesNotBlockMovement().hardnessAndResistance(0F, 0F).tickRandomly()));

	public static final RegistryObject<Block> SPORIAN_MANGROVE_TREE_LEAVES = BLOCKS.register("sporian_mangrove_tree_leaves", () -> new SporianMangroveTreeLeaves(Block.Properties.create(Material.LEAVES).sound(SoundType.PLANT).hardnessAndResistance(1.0F, 1.0F)));

	public static final RegistryObject<Block> WET_MUD_BLOCK1 = BLOCKS.register("wet_mud_block1", () -> new Block(Block.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(4f, 1.0f)));

	public static final RegistryObject<Block> WET_MUD_BLOCK2 = BLOCKS.register("wet_mud_block2", () -> new Block(Block.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(4f, 1.0f)));

			public static final RegistryObject<Block> WET_MUD_BLOCK2_GLIDED = BLOCKS.register("wet_mud_block2_glided", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f, 1.0f)));

			public static final RegistryObject<Block> WET_MUD_BLOCK2_BRICKS = BLOCKS.register("wet_mud_block2_bricks", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f, 1.0f)));

			public static final RegistryObject<Block> CHISELED_WET_MUD_BLOCK2 = BLOCKS.register("chiseled_wet_mud_block2", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f, 1.0f)));

			public static final RegistryObject<Block> POLISHED_WET_MUD_BLOCK2 = BLOCKS.register("polished_wet_mud_block2", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f, 1.0f)));

			public static final RegistryObject<Block> DOUBLED_WET_MUD_BLOCK2 = BLOCKS.register("doubled_wet_mud_block2", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f, 1.0f)));

			public static final RegistryObject<Block> WET_MUD_BLOCK2_PILLAR = BLOCKS.register("wet_mud_block2_pillar", () -> new ConnectedTexturePillar(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f, 1.0f)));

	public static final RegistryObject<Block> DRY_MUD_BLOCK1 = BLOCKS.register("dry_mud_block1", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f, 1.0f)));

			public static final RegistryObject<Block> DRY_MUD_BLOCK1_GLIDED = BLOCKS.register("dry_mud_block1_glided", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f, 1.0f)));

			public static final RegistryObject<Block> CHISELED_DRY_MUD_BLOCK1 = BLOCKS.register("chiseled_dry_mud_block1", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f, 1.0f)));

			public static final RegistryObject<Block> DRY_MUD_BLOCK1_BRICKS = BLOCKS.register("dry_mud_block1_bricks", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f, 1.0f)));

			public static final RegistryObject<Block> DRY_MUD_BLOCK1_TILE = BLOCKS.register("dry_mud_block1_tile", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f, 1.0f)));

			public static final RegistryObject<Block> FRAMED_DRY_MUD_BLOCK1_TILE = BLOCKS.register("framed_dry_mud_block1_tile", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f, 1.0f)));

			public static final RegistryObject<Block> POLISHED_DRY_MUD_BLOCK1_TILE = BLOCKS.register("polished_dry_mud_block1_tile", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f, 1.0f)));

			public static final RegistryObject<Block> CHISELED_DRY_MUD_BLOCK1_TILE = BLOCKS.register("chiseled_dry_mud_block1_tile", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f, 1.0f)));

			public static final RegistryObject<Block> DRY_MUD_BLOCK1_PILLAR = BLOCKS.register("dry_mud_block1_pillar", () -> new ConnectedTexturePillar(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f, 1.0f)));

	public static final RegistryObject<Block> DRY_MUD_BLOCK2 = BLOCKS.register("dry_mud_block2", () -> new Block(Block.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(4f, 1.0f)));

	public static final RegistryObject<Block> DRY_MUD_BLOCK3 = BLOCKS.register("dry_mud_block3", () -> new Block(Block.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(4f, 1.0f)));

	public static final RegistryObject<Block> DRY_MUD_BLOCK4 = BLOCKS.register("dry_mud_block4", () -> new Block(Block.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(4f, 1.0f)));

	public static final RegistryObject<Block> MUD_REED = BLOCKS.register("mud_reed", () -> new MudReeds(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.BAMBOO).hardnessAndResistance(2F, 1F).tickRandomly()));

    public static final RegistryObject<Block> MUD_REED_LEAVES = BLOCKS.register("mud_reed_leaves", () -> new Block(AbstractBlock.Properties.create(Material.ORGANIC).sound(SoundType.PLANT).hardnessAndResistance(0.5F, 0F).notSolid()));

    public static final RegistryObject<Block> MUD_REED_PLANKS = BLOCKS.register("mud_reed_planks", () -> new Block(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.5F, 1F).notSolid()));

	public static final RegistryObject<Block> MUD_REED_PLANKS_SLAB = BLOCKS.register("mud_reed_slab", () -> new SlabBlock(AbstractBlock.Properties.from(MUD_REED_PLANKS.get())));

	public static final RegistryObject<Block> MUD_REED_PLANKS_STAIRS = BLOCKS.register("mud_reed_stairs", () -> new StairsBlock(MUD_REED_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(MUD_REED_PLANKS.get())));

	public static final RegistryObject<Block> MUD_REED_DOOR = BLOCKS.register("mud_reed_door", () -> new DoorBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.5F, 1F).notSolid()));

	public static final RegistryObject<Block> MUD_REED_TRAPDOOR = BLOCKS.register("mud_reed_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.5F, 1F).notSolid()));

	public static final RegistryObject<Block> MUD_REED_EMPTY_BOOKSHELF = BLOCKS.register("mud_reed_empty_bookshelf", () -> new Block(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.5F, 1F).notSolid()));

	public static final RegistryObject<Block> MUD_REED_BOOKSHELF = BLOCKS.register("mud_reed_bookshelf", () -> new Block(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.5F, 1F).notSolid()));

	public static final RegistryObject<Block> CATTAIL = BLOCKS.register("cattail", () -> new Cattail(AbstractBlock.Properties.create(Material.TALL_PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0F, 0F).notSolid().doesNotBlockMovement()));

	public static final RegistryObject<Block> SPROUTED_MUD = BLOCKS.register("sprouted_mud", () -> new SproutedMud(AbstractBlock.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(2F, 0.5F).notSolid().tickRandomly()));

	public static final RegistryObject<Block> LONG_SPROUTED_MUD = BLOCKS.register("long_sprouted_mud", () -> new LongSproutedMud(AbstractBlock.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(2F, 0.5F).notSolid().tickRandomly()));

	public static final RegistryObject<Block> TRUFFLE_BLOCK = BLOCKS.register("truffle_block", () -> new Truffle(AbstractBlock.Properties.create(Material.TALL_PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0.25F, 0F).notSolid()));

	public static final RegistryObject<Block> TUBER_CROP = BLOCKS.register("tuber_crop", () -> new TuberCrop(AbstractBlock.Properties.create(Material.TALL_PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0.25F, 0F).notSolid().doesNotBlockMovement().tickRandomly()));

	public static final RegistryObject<Block> TUBER_BUSH = BLOCKS.register("tuber_bush", () -> new TuberBush(AbstractBlock.Properties.create(Material.TALL_PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0.25F, 0F).notSolid().tickRandomly()));


}