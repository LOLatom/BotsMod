package com.thefreak.botsmod;

import com.deltateam.deltalib.API.rendering.shader.PostProcessingUtils;
import com.deltateam.deltalib.accessors.ShaderAccessor;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.thefreak.botsmod.client.entity.render.PuffWormRender;
import com.thefreak.botsmod.entities.*;
import com.thefreak.botsmod.init.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.client.renderer.RenderType;

import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("botsmod")
@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Bus.MOD)
public class BotsMod
{
    //aa
    // Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "botsmod";
    public static BotsMod instance;

    public BotsMod() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	modEventBus.addListener(this::registerEntityAttributes);
    	modEventBus.addListener(this::doClientStuff);
        EffectInitNew.EFFECTS.register(modEventBus);
        ItemInitNew.ITEMS.register(modEventBus);
    	BlockInitNew.BLOCKS.register(modEventBus);
    	ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
    	ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
        if (!FMLEnvironment.production) MinecraftForge.EVENT_BUS.addListener(this::tick);
    	ModEntityTypes.ENTITY.register(modEventBus);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerModel);
        });
    	


        instance = this;
        MinecraftForge.EVENT_BUS.register(this);

    }
   // public static final RenderTarget glowTarget = new TextureTarget(1, 1, true, Minecraft.ON_OSX);
   // public static final RenderTarget glowTargetSwap = new TextureTarget(1, 1, true, Minecraft.ON_OSX);

    public static void preRenderLevel() {
/*
        if (
				glowTarget.width != Minecraft.getInstance().getWindow().getWidth() ||
						glowTarget.height != Minecraft.getInstance().getWindow().getHeight()
		) {
			// TODO: check that this is actually the FBO size
			glowTarget.resize(Minecraft.getInstance().getWindow().getWidth(), Minecraft.getInstance().getWindow().getHeight(), Minecraft.ON_OSX);
			glowTargetSwap.resize(Minecraft.getInstance().getWindow().getWidth(), Minecraft.getInstance().getWindow().getHeight(), Minecraft.ON_OSX);
		}
        glowTarget.setClearColor(0, 0, 0, 0);
		glowTarget.clear(Minecraft.ON_OSX);
		glowTargetSwap.setClearColor(0, 0, 0, 0);
		glowTargetSwap.clear(Minecraft.ON_OSX);*/
    }

    public void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.GIANT_TARDIGRADE.get(), GiantTardigradeEntity.setCustomAttributes().build());
        event.put(ModEntityTypes.WANDERING_SPECTER.get(), WanderingSpecterEntity.setCustomAttributes().build());
        event.put(ModEntityTypes.BANSHEE_SCREAM.get(), BansheeScreamEntity.setCustomAttributes().build());
        event.put(ModEntityTypes.LADYBUG.get(), LadybugEntity.setCustomAttributes().build());
        event.put(ModEntityTypes.TIPPY_LIZARD.get(), TippyLizardEntity.setCustomAttributes().build());
        event.put(ModEntityTypes.PUFF_WORM.get(), PuffWormEntity.setCustomAttributes().build());
        event.put(ModEntityTypes.DRAINED.get(), DrainedEntity.setCustomAttributes().build());
        event.put(ModEntityTypes.DRAINED_CHIEF.get(), DrainedChiefEntity.setCustomAttributes().build());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.MOGROVE_BUBBLE_MUSHROOM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.SPORE_PAD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.MOGROVE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.BRONZE_ALLOY_CRYSTAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.SPORIAN_SPIKY_LONGUS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.SPORIAN_SPIKY_LONGUS_PLANT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.SPORIAN_MOSS_GRASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.SPORIAN_MANGROVE_BULB.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.SPORIAN_MANGROVE_TREE_VINES_TOP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.SPORIAN_MANGROVE_TREE_VINES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.MUD_REED.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.MUD_REED_LEAVES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.MUD_REED_PLANKS.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.SPROUTED_MUD.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.LONG_SPROUTED_MUD.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.TUBER_BUSH.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.TUBER_CROP.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.MOLDENWOOD_DOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.AUTUMN_TREE_LEAVES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.PEPON_FRUIT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.STRECHED_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.CARVED_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.CARVED_STRECHED_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.GLOWING_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.GLOWING_STRECHED_PEPON_FRUIT.get(), RenderType.cutoutMipped());
     /*   ItemBlockRenderTypes.setRenderLayer(BlockInitNew.TINY_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.TINY_PEPON_FRUIT_GROWTH.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.TINY_PEPON_FRUIT_HALF_GROWN.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.TINY_PEPON_FRUIT_SPROUT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.CARVED_TINY_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.GLOWING_TINY_PEPON_FRUIT.get(), RenderType.cutoutMipped()); */
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.BAUMEA_LEAVES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.TAR_TORCH.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.POST_MORTAL_ALTAR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.HEAT_ACCUMULATOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.HEAT_CAPACITOR.get(), RenderType.cutoutMipped());

        ForgeModelBakery.addSpecialModel(new ResourceLocation("botsmod:item/delta_crystal_shard_model"));
    }

    public void registerModel(ModelRegistryEvent event) {

        ForgeModelBakery.addSpecialModel(new ResourceLocation(MOD_ID, "item/georite_crystal_model"));
        ForgeModelBakery.addSpecialModel(new ResourceLocation(MOD_ID, "item/georite_crystal_model_glow"));
        ForgeModelBakery.addSpecialModel(new ResourceLocation(MOD_ID, "item/georite_crystal_model_part"));
    }


    public void tick(TickEvent.ClientTickEvent event) {
	/*	if (event.phase == TickEvent.Phase.START) {
			if (!PostProcessingUtils.hasPass(new ResourceLocation("deltalib:merge"))) {
				PostPass shader;
				shader = PostProcessingUtils.addPass(new ResourceLocation("deltalib:glow"), new ResourceLocation("deltalib:blur"));
				((ShaderAccessor) shader).addDepthTarget("VanillaDepth", Minecraft.getInstance().getMainRenderTarget());
				((ShaderAccessor) shader).addDepthTarget("GlowDepth", glowTarget);
				((ShaderAccessor) shader).setSourceBuffer(glowTarget);
				((ShaderAccessor) shader).setTargetBuffer(glowTargetSwap);
				shader = PostProcessingUtils.addPass(new ResourceLocation("deltalib:merge"), new ResourceLocation("deltalib:draw"));
				((ShaderAccessor) shader).addAuxTarget("glow", glowTargetSwap);
				((ShaderAccessor) shader).addDepthTarget("GlowDepth", glowTarget);
				shader = PostProcessingUtils.addPass(new ResourceLocation("deltalib:blit"), new ResourceLocation("minecraft:blit"));
			}
		}*/
    }


    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        BlockInitNew.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().tab(BotsItemGroup.instance);
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });

        LOGGER.debug("Registered BlockItems!");
    }
    
    public static class BotsItemGroup extends CreativeModeTab
    {
    	public static final BotsItemGroup instance = new BotsItemGroup(CreativeModeTab.TABS.length, "botstab");
    	private BotsItemGroup(int index, String label)
    	{
    		super(index, label);
    	}
    
    	@Override
    	public ItemStack makeIcon()
    	{
    		return new ItemStack(BlockInitNew.BRONZE_ALLOY_BRICKS.get());
    	}
    }

    public static class BotsfoodItemGroup extends CreativeModeTab
    {
        public static final BotsfoodItemGroup food = new BotsfoodItemGroup(CreativeModeTab.TABS.length, "botsfood");
        private BotsfoodItemGroup(int index, String label)
        {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ItemInitNew.PINK_PURIFIED_SALT.get());
        }
    }

}
