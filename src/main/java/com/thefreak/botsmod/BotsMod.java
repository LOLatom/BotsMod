package com.thefreak.botsmod;

import com.thefreak.botsmod.client.entity.render.PuffWormRender;
import com.thefreak.botsmod.entities.*;
import com.thefreak.botsmod.init.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.DistExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
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
    	modEventBus.addListener(this::setup);
    	modEventBus.addListener(this::doClientStuff);
        EffectInitNew.EFFECTS.register(modEventBus);
        ItemInitNew.ITEMS.register(modEventBus);
    	BlockInitNew.BLOCKS.register(modEventBus);
    	ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
    	ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
    	ModEntityTypes.ENTITY.register(modEventBus);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerModel);
        });
    	


        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            GlobalEntityTypeAttributes.put(ModEntityTypes.GIANT_TARDIGRADE.get(), GiantTardigradeEntity.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntityTypes.WANDERING_SPECTER.get(), WanderingSpecterEntity.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntityTypes.BANSHEE_SCREAM.get(), BansheeScreamEntity.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntityTypes.LADYBUG.get(), LadybugEntity.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntityTypes.TIPPY_LIZARD.get(), TippyLizardEntity.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntityTypes.PUFF_WORM.get(), PuffWormEntity.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntityTypes.DRAINED.get(), DrainedEntity.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntityTypes.DRAINED_CHIEF.get(), DrainedChiefEntity.setCustomAttributes().build());


        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(BlockInitNew.MOGROVE_BUBBLE_MUSHROOM.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInitNew.SPORE_PAD.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInitNew.MOGROVE_BUSH.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInitNew.BRONZE_ALLOY_CRYSTAL.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInitNew.SPORIAN_SPIKY_LONGUS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInitNew.SPORIAN_SPIKY_LONGUS_PLANT.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInitNew.SPORIAN_MOSS_GRASS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInitNew.SPORIAN_MANGROVE_BULB.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInitNew.SPORIAN_MANGROVE_TREE_VINES_TOP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInitNew.SPORIAN_MANGROVE_TREE_VINES.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInitNew.MUD_REED.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInitNew.MUD_REED_LEAVES.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.MUD_REED_PLANKS.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.SPROUTED_MUD.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.LONG_SPROUTED_MUD.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.TUBER_BUSH.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.TUBER_CROP.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.MOLDENWOOD_DOOR.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.AUTUMN_TREE_LEAVES.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.PEPON_FRUIT.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.STRECHED_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.CARVED_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.CARVED_STRECHED_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.GLOWING_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.GLOWING_STRECHED_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.TINY_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.TINY_PEPON_FRUIT_GROWTH.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.TINY_PEPON_FRUIT_HALF_GROWN.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.TINY_PEPON_FRUIT_SPROUT.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.CARVED_TINY_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.GLOWING_TINY_PEPON_FRUIT.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.BAUMEA_LEAVES.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.TAR_TORCH.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BlockInitNew.POST_MORTAL_ALTAR.get(), RenderType.cutoutMipped());

        ModelLoader.addSpecialModel(new ResourceLocation("botsmod:item/delta_crystal_shard_model"));
    }

    public void registerModel(ModelRegistryEvent event) {
        ModelLoader.addSpecialModel(new ResourceLocation(MOD_ID, "item/georite_crystal_model"));
        ModelLoader.addSpecialModel(new ResourceLocation(MOD_ID, "item/georite_crystal_model_glow"));
        ModelLoader.addSpecialModel(new ResourceLocation(MOD_ID, "item/georite_crystal_model_part"));
    }

    


    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
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
    
    public static class BotsItemGroup extends ItemGroup
    {
    	public static final BotsItemGroup instance = new BotsItemGroup(ItemGroup.TABS.length, "botstab");
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

    public static class BotsfoodItemGroup extends ItemGroup
    {
        public static final BotsfoodItemGroup food = new BotsfoodItemGroup(ItemGroup.TABS.length, "botsfood");
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
