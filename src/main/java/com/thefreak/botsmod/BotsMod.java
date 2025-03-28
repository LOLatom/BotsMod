package com.thefreak.botsmod;

import com.deltateam.deltalib.API.rendering.shader.PostProcessingUtils;
import com.deltateam.deltalib.accessors.ShaderAccessor;
import com.mojang.blaze3d.platform.ScreenManager;
import com.thefreak.botsmod.client.Rendering.RenderTargets;
import com.thefreak.botsmod.entities.*;
import com.thefreak.botsmod.entities.demons.SadMan;
import com.thefreak.botsmod.fluids.BOTSFluids;
import com.thefreak.botsmod.fluids.FluidInit;
import com.thefreak.botsmod.init.*;
import com.thefreak.botsmod.init.blockinit.NoItemBlockInit;
import com.thefreak.botsmod.init.customregistries.ProstheticRegistry;
import com.thefreak.botsmod.init.iteminit.FoodItemInit;
import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import com.thefreak.botsmod.init.sounds.SoundRegistry;
import com.thefreak.botsmod.objects.containers.SpecialisedCraftingMenu;
import com.thefreak.botsmod.objects.containers.screens.SpecialisedCraftingScreen;
import com.thefreak.botsmod.objects.items.magic.SpellCardItem;
import com.thefreak.botsmod.particles.ModParticleType;
import com.thefreak.botsmod.recipes.BotsRecipeType;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.shaders.ShaderEffects;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.FogType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        modEventBus.register(new ModMenuScreens());
        BOTSFluids.BOTS_FLUIDS.register(modEventBus);
        FluidInit.FLUIDS.register(modEventBus);
        EffectInitNew.EFFECTS.register(modEventBus);
        ItemInitNew.ITEMS.register(modEventBus);
        FoodItemInit.ITEMS.register(modEventBus);
    	BlockInitNew.BLOCKS.register(modEventBus);
        NoItemBlockInit.BLOCKS.register(modEventBus);
    	ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
    	ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
        BotsRecipeType.RECIPES.register(modEventBus);
        ModParticleType.PARTICLE_TYPES.register(modEventBus);
        SoundRegistry.SOUND_EVENTS.register(modEventBus);
        BotsPacketHandler.init();
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
        event.put(ModEntityTypes.WANDERING_SPECTER.get(), WanderingSpecterEntity.setCustomAttributes().build());
        event.put(ModEntityTypes.LADYBUG.get(), LadybugEntity.setCustomAttributes().build());
        event.put(ModEntityTypes.TIPPY_LIZARD.get(), TippyLizardEntity.setCustomAttributes().build());
        event.put(ModEntityTypes.PUFF_WORM.get(), PuffWormEntity.setCustomAttributes().build());
        event.put(ModEntityTypes.DRAINED.get(), DrainedEntity.setCustomAttributes().build());
        event.put(ModEntityTypes.DRAINED_CHIEF.get(), DrainedChiefEntity.setCustomAttributes().build());
        event.put(ModEntityTypes.WICKED_ONE.get(), WickedOne.createAttributes().build());
        event.put(ModEntityTypes.GATE_KEEPER.get(), GateKeeper.createAttributes().build());
        event.put(ModEntityTypes.KRASIA.get(), KrasiaEntity.createAttributes().build());
        event.put(ModEntityTypes.CAVE_ANGELIA_GUPPIE.get(), CaveAngeliaGuppie.createAttributes().build());
        event.put(ModEntityTypes.SAD_MAN.get(), SadMan.createAttributes().build());
        event.put(ModEntityTypes.SAD_FACE.get(), SadMan.createAttributes().build());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.MOLDENWOOD_DOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.AUTUMN_TREE_LEAVES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.PEPON_FRUIT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.STRECHED_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.CARVED_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.CARVED_STRECHED_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.GLOWING_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.GLOWING_STRECHED_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.BAUMEA_LEAVES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.TAR_TORCH.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.POST_MORTAL_ALTAR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.HEAT_ACCUMULATOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.HEAT_CAPACITOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.MEDUSA_GROVE_FLOWER.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.MEDUSA_GROVE_LEAVES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.LOG_MUSHROOM.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.BROWN_INSOMNIAN_MUSHROOM.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.LUXIOUS_PINK_MUSHROOM.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.GLOWY_NUT_MUSHROOM.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.LIVER_LOG_BUSH.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.RED_BIRD_GROWTH.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.RED_BIRD_BRANCH.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.FLESH_COLUMN.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockInitNew.SPECIALISED_CRAFTING_TABLE.get(), RenderType.cutoutMipped());
        //ItemBlockRenderTypes.setRenderLayer(BlockInitNew.ARM_FACTORY_BLOCK.get(), RenderType.cutoutMipped());



        ItemBlockRenderTypes.setRenderLayer(BOTSFluids.CELL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BOTSFluids.FLOWING_CELL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(FluidInit.CELL_FLUID.get(), RenderType.translucent());

        //weird ahh custom registry thingy
        ProstheticRegistry.setupRegistries();

        ForgeModelBakery.addSpecialModel(new ResourceLocation("botsmod:item/delta_crystal_shard_model"));
    }


    public void registerModel(ModelRegistryEvent event) {

        ForgeModelBakery.addSpecialModel(new ResourceLocation(MOD_ID, "item/georite_crystal_model"));
        ForgeModelBakery.addSpecialModel(new ResourceLocation(MOD_ID, "item/banhir_head_model"));
        ForgeModelBakery.addSpecialModel(new ResourceLocation(MOD_ID, "item/banhir_head_item_model"));
        ForgeModelBakery.addSpecialModel(new ResourceLocation(MOD_ID, "item/banhir_head_model_glow"));
        ForgeModelBakery.addSpecialModel(new ResourceLocation(MOD_ID, "item/georite_crystal_model_glow"));
        ForgeModelBakery.addSpecialModel(new ResourceLocation(MOD_ID, "item/georite_crystal_model_part"));
    }


    public void tick(TickEvent.ClientTickEvent event) {
        //Creating the shaders there :
        ShaderEffects.shaderEffectCreate(event);
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
    		return new ItemStack(BlockInitNew.OXYNE_ROCK_BRICKS.get());
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
            return new ItemStack(FoodItemInit.PINK_PURIFIED_SALT.get());
        }
    }

    public static class BotsMagicItemGroup extends CreativeModeTab {

        public static final BotsMagicItemGroup magic = new BotsMagicItemGroup(CreativeModeTab.TABS.length,"botsmagic");

        public BotsMagicItemGroup(int pId, String pLangId) {
            super(pId, pLangId);
        }


        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemInitNew.SPELL_CARD.get());
        }
    }


    public static BotsMod getInstance() {
        return instance;
    }

}
